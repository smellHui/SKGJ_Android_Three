package com.tepia.main.view.maincommon.setting.worknotification.addworknotification;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivityAddWorkNotificationBinding;
import com.tepia.main.databinding.ActivityContactsBinding;
import com.tepia.main.model.detai.ReservoirBean;

import com.tepia.main.view.maincommon.setting.adapter.FileRecycleViewAdapter;
import com.tepia.main.view.maincommon.setting.train.AddTrainActivity;
import com.tepia.main.view.maincommon.setting.train.FileBean;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :  新增通知
 **/
@Route(path = AppRoutePath.app_add_work_notification)
public class AddWorkNotificationActivity extends MVPBaseActivity<AddWorkNotificationContract.View, AddWorkNotificationPresenter> implements AddWorkNotificationContract.View {

    private ActivityAddWorkNotificationBinding mBinding;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<FileBean> selectedFiles = new ArrayList<>();
    ArrayList<String> selectFilePaths = new ArrayList<>();

    private PhotoRecycleViewAdapter photoAdapter;
    private FileRecycleViewAdapter fileAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_work_notification;
    }

    @Override
    public void initView() {
        setCenterTitle("新增通知");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
        initImagePick();
        if (selectedPhotos != null) {
            mBinding.photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
        } else {
            mBinding.photoTitleTv.setText(getString(R.string.picstr, 0));
        }
        initFilePick();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        mBinding.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reservoirIds = "";
                if (SelectReservorsActivity.selectReservors != null) {
                    for (ReservoirBean bean : SelectReservorsActivity.selectReservors) {
                        reservoirIds += bean.getReservoirId() + ",";
                    }
                }
                String noticeTitle = mBinding.etTitle.getText().toString();
                String noticeContent = mBinding.etContent.getText().toString();
                if (TextUtils.isEmpty(reservoirIds)) {
                    ToastUtils.shortToast("请选择通知水库");
                    return;
                }
                if (TextUtils.isEmpty(noticeTitle)) {
                    ToastUtils.shortToast("请输入通知标题");
                    return;
                }
                if (TextUtils.isEmpty(noticeTitle)) {
                    ToastUtils.shortToast("请输入通知内容");
                    return;
                }
                selectFilePaths.clear();
                if (selectedFiles != null && selectedFiles.size() > 0) {
                    for (int i = 0; i < selectedFiles.size(); i++) {
                        selectFilePaths.add(selectedFiles.get(i).filePath);
                    }
                }
                mPresenter.addWorkNotice(reservoirIds, noticeTitle, noticeContent, selectFilePaths, selectedPhotos);
            }
        });
        mBinding.loSelectReservoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(AppRoutePath.app_select_reservor).navigation();
            }
        });
    }


    private void initImagePick() {
        mBinding.rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (photoAdapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoRecycleViewAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(true)
                            .setSelected(selectedPhotos)
                            .start(AddWorkNotificationActivity.this);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(AddWorkNotificationActivity.this);
                }

            }
        }));
        photoAdapter = new PhotoRecycleViewAdapter(getContext(), selectedPhotos);
        mBinding.rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mBinding.rvImagePick.setAdapter(photoAdapter);
    }

    private void initFilePick() {
        fileAdapter = new FileRecycleViewAdapter(getApplicationContext(), selectedFiles);
        mBinding.rvFilePick.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvFilePick.setAdapter(fileAdapter);
        fileAdapter.setOnAddItemClickListener((view, position) -> {
            new LFilePicker()
                    .withActivity(AddWorkNotificationActivity.this)
                    .withRequestCode(100)
                    .withMutilyMode(true)
                    .withFileFilter(new String[]{".doc", ".ppt", ".xls", ".docx", ".pptx", ".xlsx", ".pdf"})
                    .start();
        });
        fileAdapter.setOnAddDeleteClickListener((view, position) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddWorkNotificationActivity.this);
            builder.setTitle("温馨提示");
            builder.setMessage("确定删除" + selectedFiles.get(position).fileName + "吗？");
            builder.setPositiveButton("确定", (dialog, which) -> {
                //模拟删除数据，刷新数据列表定位到之前删除的位置
//                adapterPlanList.notifyDataSetChanged();
//                ((LinearLayoutManager)rvPlanList.getLayoutManager()).scrollToPositionWithOffset(adapterPosition, Px2dpUtils.dip2px(getContext(),200));
                selectedFiles.remove(position);
                fileAdapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("取消", (dialog, which) -> {
            });
            builder.show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }

            if (photos != null) {
                selectedPhotos.clear();
                selectedPhotos.addAll(photos);
                photoAdapter.notifyDataSetChanged();
                mBinding.photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
            }
        } else if (resultCode == RESULT_OK && (requestCode == 100)) {
            if (data != null) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                FileBean fileBean;
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        String path = list.get(i);
                        String fileName = path.substring(path.lastIndexOf("/") + 1);
                        fileBean = new FileBean();
                        fileBean.fileName = fileName;
                        fileBean.filePath = path;
                        if (!isFilesContain(path)) {
                            selectedFiles.add(fileBean);
                        }
                    }
                    fileAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private boolean isFilesContain(String path) {
        boolean isContain = false;
        if (selectedFiles != null && selectedFiles.size() > 0) {
            for (int i = 0; i < selectedFiles.size(); i++) {
                FileBean fileBean = selectedFiles.get(i);
                if (path.equals(fileBean.filePath)) {
                    isContain = true;
                }
            }
        }
        return isContain;
    }

    @Override
    protected void initRequestData() {
        if (SelectReservorsActivity.selectReservors != null) {
            String temp = "";
            for (ReservoirBean bean : SelectReservorsActivity.selectReservors) {
                temp += bean.getReservoir() + " ";
            }
            if (!TextUtils.isEmpty(temp)) {
                mBinding.tvReservoirNames.setText(temp);
            }
        }
    }

    @Override
    public void addWorkNoticeSuccess() {
        SelectReservorsActivity.selectReservors = null;
        finish();
    }

    @Override
    protected void onDestroy() {
        SelectReservorsActivity.selectReservors = null;
        super.onDestroy();
    }
}
