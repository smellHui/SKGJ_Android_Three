package com.tepia.main.view.maincommon.reservoirs.detail.addoreditflood;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.common.image.PhotoSelectAdapter;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.databinding.ActivityAddOrEditFloodBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.reserviros.FloodBeanDetailBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdeal.taskitemdeal.TaskItemDealFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;


/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/3/6 18:14
 * @修改人 ：
 * @修改时间 :       2019/3/6 18:14
 * @功能描述 :       添加 或者 修改 防汛物资
 **/
@Route(path = AppRoutePath.app_flood_add_or_edit)
public class AddOREditFloodActivity extends MVPBaseActivity<AddOREditFloodContract.View, AddOREditFloodPresenter> implements AddOREditFloodContract.View {

    private String type;
    private ActivityAddOrEditFloodBinding mBinding;
    private String reservoirId;
    private List<DictMapEntity.ArrayBean.NameValueBean> meTypeList;
    private String meType;

    private PhotoSelectAdapter photoRecycleViewAdapterBefore;
    public ArrayList<String> selectPhotosBefore = new ArrayList<>();
    private List<ImageInfoBean> imagListBefore = new ArrayList<>();
    private FloodBeanDetailBean.DataBean floodBean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_or_edit_flood;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        if (type.equals("add")) {
            setCenterTitle("添加防汛物资");
            initAddView();
            mBinding.btSure.setText("添加");
            mBinding.btSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DoubleClickUtil.isFastDoubleClick()) {
                        return;
                    }
                    String meName = mBinding.nameMv.getRightEditV().getText().toString();
                    String meTotals = mBinding.tvNum.getRightEditV().getText().toString();
                    String position = mBinding.tvPosition.getRightEditV().getText().toString();
                    String manageName = mBinding.tvManagerName.getRightEditV().getText().toString();
                    String phoneNum = mBinding.tvManagerPhoneNum.getRightEditV().getText().toString();
                    String remark = "";
                    mPresenter.addReservoirMaterial(reservoirId, meName, meType, meTotals, position, manageName, phoneNum, remark, selectPhotosBefore);
                }
            });
        } else {
            setCenterTitle("编辑防汛物资");
            initAddView();
            initEditView();
            mBinding.btSure.setText("确定");
            mBinding.btSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DoubleClickUtil.isFastDoubleClick()) {
                        return;
                    }
                    String meName = mBinding.nameMv.getRightEditV().getText().toString();
                    String meTotals = mBinding.tvNum.getRightEditV().getText().toString();
                    String position = mBinding.tvPosition.getRightEditV().getText().toString();
                    String manageName = mBinding.tvManagerName.getRightEditV().getText().toString();
                    String phoneNum = mBinding.tvManagerPhoneNum.getRightEditV().getText().toString();
                    String remark = "";
                    mPresenter.updateReservoirMaterial(floodBean.getId(), reservoirId, meName, meType, meTotals, position, manageName, phoneNum, remark, selectPhotosBefore);
                }
            });
        }
        showBack();


    }

    private void initEditView() {
        if (floodBean == null) {
            return;
        }
        if (!TextUtils.isEmpty(floodBean.getMeName())) {
            mBinding.nameMv.getRightEditV().setText(floodBean.getMeName());
        }
        if (!TextUtils.isEmpty(floodBean.getMeTypeName())) {
            mBinding.tvType.getRightTextV().setText(floodBean.getMeTypeName());
            meType = floodBean.getMeType();

        }
        if (!TextUtils.isEmpty(floodBean.getMeTotals())) {
            mBinding.tvNum.getRightEditV().setText(floodBean.getMeTotals());

        }
        if (!TextUtils.isEmpty(floodBean.getPosition())) {
            mBinding.tvPosition.getRightEditV().setText(floodBean.getPosition());

        }
        if (!TextUtils.isEmpty(floodBean.getManageName())) {
            mBinding.tvManagerName.getRightEditV().setText(floodBean.getManageName() + "");
        }

        if (!TextUtils.isEmpty(floodBean.getPhoneNum())) {
            mBinding.tvManagerPhoneNum.getRightEditV().setText(floodBean.getPhoneNum() + "");
        }
        if (floodBean.getFileInfo() != null) {
            imagListBefore = floodBean.getFileInfo();
            photoRecycleViewAdapterBefore.setNetData(floodBean.getFileInfo());
        }

    }

    private void initAddView() {
        mBinding.nameMv.setLeftTitle("物资名称：");
        mBinding.nameMv.getRightEditV().setVisibility(View.VISIBLE);
        mBinding.nameMv.getRightEditV().setHint("请输入物资名称");
        mBinding.nameMv.getRightEditV().setText("");

        mBinding.tvType.setLeftTitle("物资类型：");
        mBinding.tvType.getRightTextV().setVisibility(View.VISIBLE);
        mBinding.tvType.getRightTextV().setHint("请选择物资数量");
        mBinding.tvType.getRightTextV().setText("");
        if (meTypeList != null && meTypeList.size() > 0) {
            mBinding.tvType.getRightTextV().setText("" + meTypeList.get(0).getName());
            meType = meTypeList.get(0).getValue();
        }

        mBinding.tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showSelectTypeDialog();
            }
        });

        mBinding.tvNum.setLeftTitle("物资数量：");
        mBinding.tvNum.getRightEditV().setVisibility(View.VISIBLE);
        mBinding.tvNum.getRightEditV().setHint("请输入物资数量");
        mBinding.tvNum.getRightEditV().setText("");

        mBinding.tvPosition.setLeftTitle("存放位置：");
        mBinding.tvPosition.getRightEditV().setVisibility(View.VISIBLE);
        mBinding.tvPosition.getRightEditV().setHint("请输入存放位置");
        mBinding.tvPosition.getRightEditV().setText("");

        mBinding.tvManagerName.setLeftTitle("管理人员：");
        mBinding.tvManagerName.getRightEditV().setVisibility(View.VISIBLE);
        mBinding.tvManagerName.getRightEditV().setHint("请输入管理人员名字");
        mBinding.tvManagerName.getRightEditV().setText("");

        mBinding.tvManagerPhoneNum.setLeftTitle("联系方式：");
        mBinding.tvManagerPhoneNum.getRightEditV().setVisibility(View.VISIBLE);
        mBinding.tvManagerPhoneNum.getRightEditV().setHint("请输入联系方式");
        mBinding.tvManagerPhoneNum.getRightEditV().setText("");

        initPhotoListView();
    }

    private void initPhotoListView() {
        photoRecycleViewAdapterBefore = new PhotoSelectAdapter(getContext());
        mBinding.rvAddPhotoBefore.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mBinding.rvAddPhotoBefore.setAdapter(photoRecycleViewAdapterBefore);
        photoRecycleViewAdapterBefore.setLocalData(selectPhotosBefore);
        photoRecycleViewAdapterBefore.setDeleteListener(new PhotoSelectAdapter.DeleteListener() {
            @Override
            public void ondelete(ImageInfoBean imageInfoBean) {
                new AlertDialog.Builder(getContext())
                        .setMessage("是否确定删除照片")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                mPresenter.deleteImage(imageInfoBean, true, "图片删除中...");
                            }
                        }).show();

            }

            @Override
            public void ondelete(int position) {
                if (selectPhotosBefore.size() > 0) {
                    selectPhotosBefore.remove(position);
                }
                photoRecycleViewAdapterBefore.setLocalData(selectPhotosBefore);
//                mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
            }
        });
        photoRecycleViewAdapterBefore.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (photoRecycleViewAdapterBefore.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                    if (imagListBefore == null) {
                        imagListBefore = new ArrayList<>();
                    }
                    PhotoPicker.builder()
                            .setPhotoCount(5 - imagListBefore.size())
                            .setShowCamera(true)
                            .setPreviewEnabled(true)
                            .setSelected(selectPhotosBefore)
                            .start(AddOREditFloodActivity.this, 100);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(photoRecycleViewAdapterBefore.getPhotoPaths())
                            .setCurrentItem(position)
                            .setShowDeleteButton(false)
                            .start(AddOREditFloodActivity.this, 100);
                }
            }
        });
    }

    private void showSelectTypeDialog() {
        if (meTypeList == null && meTypeList.size() == 0) {
            return;
        }
        if (meTypeList != null) {
            String[] stringItems = new String[meTypeList.size()];
            for (int i = 0; i < meTypeList.size(); i++) {
                stringItems[i] = meTypeList.get(i).getName();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getContext(), stringItems, null);
            dialog.title("请选择物资类型")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mBinding.tvType.getRightTextV().setText(meTypeList.get(position).getName());
                    meType = meTypeList.get(position).getValue();
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void initData() {
        meTypeList = DictMapManager.getInstance().getmDictMap().getArray().getMeType();
        type = getIntent().getStringExtra("TYPE");
        reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        if (TextUtils.isEmpty(type)) {
            type = "add";
        }

        if (type.equals("edit")) {
            String temp = getIntent().getStringExtra("FloodBean");
            floodBean = new Gson().fromJson(temp, FloodBeanDetailBean.DataBean.class);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void addReservoirMaterialSuccess() {
        finish();
    }

    @Override
    public void delFileSucess(ImageInfoBean imageInfoBean) {
        imagListBefore.remove(imageInfoBean);
        photoRecycleViewAdapterBefore.setNetData(imagListBefore);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        if (resultCode == RESULT_OK && (requestCode == 100 || requestCode == 100)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }

            selectPhotosBefore.clear();
            if (photos == null) {
                return;
            }
            for (String temp : photos) {
                if (temp != null) {
                    if (!temp.contains("http:")) {
                        selectPhotosBefore.add(temp);
                    }
                }
            }
            photoRecycleViewAdapterBefore.setLocalData(selectPhotosBefore);
//                mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");

        }
    }
}
