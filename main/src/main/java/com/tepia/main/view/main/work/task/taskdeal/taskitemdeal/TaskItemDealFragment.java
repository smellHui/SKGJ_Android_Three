package com.tepia.main.view.main.work.task.taskdeal.taskitemdeal;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.common.image.PhotoSelectAdapter;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.databinding.FragmentTaskItemDealBinding;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.task.LatLngAndAddressBean;
import com.tepia.main.model.task.bean.ShowTypeBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdeal.NewTaskDealActivity;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;
import com.tepia.photo_picker.entity.WaterBean;
import com.tepia.photo_picker.utils.BitmapUtils;
import com.tepia.photo_picker.utils.ImageCaptureManager;
import com.tepia.photo_picker.widget.WaterView;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskItemDealFragment extends MVPBaseFragment<TaskItemDealContract.View, TaskItemDealPresenter> implements TaskItemDealContract.View {

    private String workOrderId;
    private String itemId;
    private TaskItemBean taskItemBean;
    private String executeStatus;
    private FragmentTaskItemDealBinding mBinding;
    private String executeResultType;
    private String etDescStr;
    /**
     * 判断是哪个部分的照片返回
     */
    private String isBefore = "";
    /**
     * 维护、保洁前
     */
    private PhotoSelectAdapter photoRecycleViewAdapterBefore;
//    public ArrayList<String> selectPhotosBefore = new ArrayList<>();
    /**
     * 维护、保洁后
     */
    private PhotoSelectAdapter photoRecycleViewAdapterAfter;
//    public ArrayList<String> selectPhotosAfter = new ArrayList<>();
    /**
     * 维护、保洁中
     */
    private PhotoSelectAdapter photoRecycleViewAdapterDuring;
//    public ArrayList<String> selectPhotosDuring = new ArrayList<>();

//    private List<ImageInfoBean> imagListBefore = new ArrayList<>();
//    private List<ImageInfoBean> imagListAfter = new ArrayList<>();
//    private List<ImageInfoBean> imagListDuring = new ArrayList<>();


    private TaskBean taskBean;
    private ArrayList<ShowTypeBean> showTypeBeanList;

    /**
     * 拍照工具类
     */
    private ImageCaptureManager captureManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.workOrderId = getArguments().getString("workOrderId");
        this.itemId = getArguments().getString("itemId");
        this.executeStatus = getArguments().getString("executeStatus");

//        this.taskItemBean = (TaskItemBean) getArguments().getSerializable("TaskItemBean");
        taskItemBean = DataSupport.where("itemId=?", itemId).findFirst(TaskItemBean.class);
        this.taskBean = (TaskBean) getArguments().getSerializable("taskBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task_item_deal;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        captureManager = new ImageCaptureManager(getActivity());
        initListener();
        initPhotoListView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.etDesc.clearFocus();
            }
        });
        switch (taskBean.getBizPlanInfo().getOperationType()) {
            case "1":
                mBinding.tvBeforeHint.setText("巡检照片");
                mBinding.loAfter.setVisibility(View.GONE);
                mBinding.loDuring.setVisibility(View.GONE);
                break;
            case "2":
                mBinding.tvBeforeHint.setText("维修养护前");
                mBinding.tvAfterHint.setText("维修养护后");
                mBinding.tvDuringHint.setText("维修养护中");
                mBinding.loAfter.setVisibility(View.VISIBLE);
                mBinding.loDuring.setVisibility(View.VISIBLE);
                break;
            case "3":
                mBinding.tvBeforeHint.setText("保洁前");
                mBinding.tvAfterHint.setText("保洁后");
                mBinding.tvDuringHint.setText("保洁中");
                mBinding.loAfter.setVisibility(View.VISIBLE);
                mBinding.loDuring.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

    }

    private void initListener() {

        mBinding.rgList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_1) {
                    executeResultType = "0";
                    mBinding.rb1.setChecked(true);
                    mBinding.rb2.setChecked(false);
                } else {
                    executeResultType = "1";
                    mBinding.rb1.setChecked(false);
                    mBinding.rb2.setChecked(true);
                }
            }
        });
        mBinding.etDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                etDescStr = mBinding.etDesc.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mBinding.etDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (getBaseActivity() instanceof NewTaskDealActivity) {
                    ((NewTaskDealActivity) getBaseActivity()).onFocusChange(view, b);
                }
            }
        });

    }

    @Override
    protected void initRequestData() {
        if (taskItemBean == null || TextUtils.isEmpty(taskItemBean.getResultType())) {
            Log.i(TaskItemDealFragment.this.getClass().getName(), "taskItemBeanIsNull");
            mPresenter.getTaskItemDetail(itemId, true, getString(R.string.data_loading));
        } else {
            Log.i(TaskItemDealFragment.this.getClass().getName(), "initRequestData");
            refreshView(taskItemBean);
            mPresenter.getTaskItemDetail(itemId, false, "");
        }
    }

    private void refreshView(TaskItemBean taskItemBean) {
        mBinding.tvItemname.setText(taskItemBean.getContent());
        initRadioView();
        if (TextUtils.isEmpty(etDescStr)) {
            mBinding.etDesc.setText(taskItemBean.getExecuteResultDescription());
        } else {
            mBinding.etDesc.setText(etDescStr);
        }

        if ("2".equals(executeStatus) && UserManager.getInstance().getUserBean().getData().getUserCode().equals(taskBean.getExecuteId())) {

            mBinding.rgList.setClickable(true);
            mBinding.rb1.setClickable(true);
            mBinding.rb2.setClickable(true);
            mBinding.etDesc.setEnabled(true);
        } else {
            mBinding.rgList.setClickable(false);
            mBinding.rb1.setClickable(false);
            mBinding.rb2.setClickable(false);
            mBinding.etDesc.setEnabled(false);
        }
        if ("2".equals(executeStatus) && UserManager.getInstance().getUserBean().getData().getUserCode().equals(taskBean.getExecuteId())) {

            photoRecycleViewAdapterBefore.setShowType(false);
            photoRecycleViewAdapterAfter.setShowType(false);
            photoRecycleViewAdapterDuring.setShowType(false);
        } else {
            photoRecycleViewAdapterBefore.setShowType(true);
            photoRecycleViewAdapterAfter.setShowType(true);
            photoRecycleViewAdapterDuring.setShowType(true);
        }

        mBinding.tvTaskItemName.setText(taskItemBean.getSuperviseItemName());
        mBinding.tvTaskItemExeTime.setText("执行时间：" + taskItemBean.getExcuteDate());
        if ("4".equals(taskBean.getExecuteStatus())) {
            mBinding.loExamine.setVisibility(View.VISIBLE);
            mBinding.tvTaskItemExamineStatus.setText("审核情况：" + ("0".equals(taskItemBean.getExaminedResultType()) ? "正常" : "不正常"));
            mBinding.tvTaskItemExamineDes.setText("审核描述：" + taskItemBean.getExaminedResultDescription());
        } else {
            mBinding.loExamine.setVisibility(View.GONE);
        }
//        imagListBefore = taskItemBean.getStartImages();
//        imagListAfter = taskItemBean.getEndImages();
//        imagListDuring = taskItemBean.getIngImages();

        if (taskItemBean.getStartImages() != null && taskItemBean.getStartImages().size() > 0) {
            photoRecycleViewAdapterBefore.setNetData(taskItemBean.getStartImages());
            mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
        }
        if (taskItemBean.getEndImages() != null && taskItemBean.getEndImages().size() > 0) {
            photoRecycleViewAdapterAfter.setNetData(taskItemBean.getEndImages());
            mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
        }
        if (taskItemBean.getIngImages() != null && taskItemBean.getIngImages().size() > 0) {
            photoRecycleViewAdapterDuring.setNetData(taskItemBean.getIngImages());
            mBinding.tvPhotoNumDuring.setText(photoRecycleViewAdapterDuring.getPhotoPaths().size() + "/5");
        }

        // 如果存在本地未上传图片，则放入相应适配器中


        if (taskItemBean.isCommitLocal()) {
            refreshViewWithLocalData();
        }
    }

    private void refreshViewWithLocalData() {
        if (TextUtils.isEmpty(taskItemBean.getExecuteDate()) && TextUtils.isEmpty(taskItemBean.getExcuteDate()) && taskItemBean.getExecuteDate().compareTo(taskItemBean.getExcuteDate()) <= 0) {
            return;
        }
        if (TextUtils.isEmpty(etDescStr)) {
            mBinding.etDesc.setText(taskItemBean.getExDesc());
        } else {
            mBinding.etDesc.setText(etDescStr);
        }

        switch (taskItemBean.getExResult()) {
            case "0":
                mBinding.rb1.setChecked(true);
                mBinding.rb2.setChecked(false);
                break;
            case "1":
                mBinding.rb1.setChecked(false);
                mBinding.rb2.setChecked(true);
                break;
            default:
                mBinding.rb1.setChecked(true);
                mBinding.rb2.setChecked(false);
                executeResultType = "0";
                break;
        }

        mBinding.tvTaskItemExeTime.setText("执行时间：" + taskItemBean.getExecuteDate());
        if (taskItemBean.getBeforelist() != null) {
            ArrayList<String> list = new Gson().fromJson(taskItemBean.getBeforelist(), new TypeToken<ArrayList<String>>() {
            }.getType());
            if (list != null && list.size() > 0) {
                List<String> listtemp = photoRecycleViewAdapterBefore.getPhotoPaths();
                ArrayList<String> before = new ArrayList<>();
                for (String temp : listtemp) {
                    if (!temp.contains("http")) {
                        before.add(temp);
                    }
                }
                if (listtemp.size() == 0) {
                    for (String temp : list) {
                        if (!before.contains(temp)) {
                            before.add(temp);
                        }
                    }
                }


//                if (selectPhotosBefore == null) {
//                    selectPhotosBefore = new ArrayList<>();
//                }

                photoRecycleViewAdapterBefore.setLocalData(before);
                mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
            }
        }
        if (taskItemBean.getDuringlist() != null) {
            ArrayList<String> list = new Gson().fromJson(taskItemBean.getDuringlist(), new TypeToken<ArrayList<String>>() {
            }.getType());
            if (list != null && list.size() > 0) {
                List<String> listtemp = photoRecycleViewAdapterDuring.getPhotoPaths();
                ArrayList<String> during = new ArrayList<>();
                for (String temp : listtemp) {
                    if (!temp.contains("http")) {
                        during.add(temp);
                    }
                }
                if (listtemp.size() == 0) {
                    for (String temp : list) {
                        if (!during.contains(temp)) {
                            during.add(temp);
                        }
                    }
                }
//                if (selectPhotosDuring == null) {
//                    selectPhotosDuring = new ArrayList<>();
//                }

                photoRecycleViewAdapterDuring.setLocalData(during);
                mBinding.tvPhotoNumDuring.setText(photoRecycleViewAdapterDuring.getPhotoPaths().size() + "/5");
            }
        }
        if (taskItemBean.getAfterlist() != null) {
            ArrayList<String> list = new Gson().fromJson(taskItemBean.getAfterlist(), new TypeToken<ArrayList<String>>() {
            }.getType());
            if (list != null && list.size() > 0) {
                List<String> listtemp = photoRecycleViewAdapterAfter.getPhotoPaths();
                ArrayList<String> after = new ArrayList<>();
                for (String temp : listtemp) {
                    if (!temp.contains("http")) {
                        after.add(temp);
                    }
                }
                if (listtemp.size() == 0) {
                    for (String temp : list) {
                        if (!after.contains(temp)) {
                            after.add(temp);
                        }
                    }
                }
//                if (selectPhotosAfter == null) {
//                    selectPhotosAfter = new ArrayList<>();
//                }

                photoRecycleViewAdapterAfter.setLocalData(after);
                mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
            }
        }
    }

    private void initRadioView() {

        if (!TextUtils.isEmpty(executeResultType)) {
            switch (executeResultType) {
                case "0":
                    mBinding.rb1.setChecked(true);
                    mBinding.rb2.setChecked(false);
                    break;
                case "1":
                    mBinding.rb1.setChecked(false);
                    mBinding.rb2.setChecked(true);
                    break;
                default:
                    mBinding.rb1.setChecked(true);
                    mBinding.rb2.setChecked(false);
                    executeResultType = "0";
                    break;
            }
        } else {
            taskItemBean.getResultType();
            String temp = taskItemBean.getDicName();
            if (!TextUtils.isEmpty(taskItemBean.getResultType())) {
                try {
                    temp = DictMapManager.getInstance().getmDictMap().getObject().getSuperviseShowType().get(taskItemBean.getResultType());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (TextUtils.isEmpty(temp)) {
                return;
            }
            showTypeBeanList = new ArrayList<>();
            String[] items = TextUtils.split(temp, ",");
            for (String item : items) {
                ShowTypeBean bean = new ShowTypeBean();
                String[] values = TextUtils.split(item, ":");
                if (values.length == 2) {
                    bean.setName(values[1]);
                    bean.setValue(values[0]);
                    if (values[0].equals(taskItemBean.getExecuteResultType())) {
                        bean.setChecked(true);
                    } else {
                        bean.setChecked(false);
                    }
                }
                showTypeBeanList.add(bean);
            }
            if (showTypeBeanList.size() == 2) {
                mBinding.rb1.setText(showTypeBeanList.get(0).getName());
                mBinding.rb1.setChecked(showTypeBeanList.get(0).isChecked());
                mBinding.rb2.setText(showTypeBeanList.get(1).getName());
                mBinding.rb2.setChecked(showTypeBeanList.get(1).isChecked());
                if (showTypeBeanList.get(0).isChecked()) {
                    executeResultType = showTypeBeanList.get(0).getValue();
                } else if (showTypeBeanList.get(1).isChecked()) {
                    executeResultType = showTypeBeanList.get(1).getValue();
                } else {
                    executeResultType = showTypeBeanList.get(0).getValue();
                    initRadioView();
                }

            } else {
                mBinding.rb2.setChecked(false);
                mBinding.rb1.setChecked(true);
                executeResultType = "0";
                initRadioView();
            }
        }
    }

    /**
     * 初始化 图片选择
     */
    private void initPhotoListView() {
        // 维护、保洁前
        {
            photoRecycleViewAdapterBefore = new PhotoSelectAdapter(getContext());
            mBinding.rvAddPhotoBefore.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
            mBinding.rvAddPhotoBefore.setAdapter(photoRecycleViewAdapterBefore);
            if (!TextUtils.isEmpty(taskItemBean.getBeforelist())) {
                ArrayList<String> beforelist = new Gson().fromJson(taskItemBean.getBeforelist(), new TypeToken<ArrayList<String>>() {
                }.getType());
                photoRecycleViewAdapterBefore.setLocalData(beforelist);
            }
//            photoRecycleViewAdapterBefore.setLocalData(selectPhotosBefore);
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
                                    mPresenter.deleteImage(imageInfoBean, "before", true, "图片删除中...");
                                }
                            }).show();

                }

                @Override
                public void ondelete(int num) {
//                    if (selectPhotosBefore.size() > 0 && selectPhotosBefore.size() > position) {
//                        selectPhotosBefore.remove(position);
//                    }
//                    photoRecycleViewAdapterBefore.setLocalData(selectPhotosBefore);
//                    mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
                    List<String> listtemp = photoRecycleViewAdapterBefore.getPhotoPaths();
                    ArrayList<String> before = new ArrayList<>();
                    for (String temp : listtemp) {
                        if (!temp.contains("http")) {
                            before.add(temp);
                        }
                    }
//                    taskItemBean.setBeforelist(new Gson().toJson(before));
//                    taskItemBean.save();
                    mBinding.tvPhotoNumBefore.setText(num + "/5");
                }
            });
            photoRecycleViewAdapterBefore.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    isBefore = "before";
                    if (photoRecycleViewAdapterBefore.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {

                        if (photoRecycleViewAdapterBefore.getPhotoPaths().size() >= 5) {
                            ToastUtils.shortToast("最多选择5张图片");
                        } else {
                            showPicDialog();
                        }
                    } else {
                        PhotoPreview.builder()
                                .setPhotos(photoRecycleViewAdapterBefore.getPhotoPaths())
                                .setCurrentItem(position)
                                .setShowDeleteButton(false)
                                .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                    }
                }
            });

        }
        // 维护、保洁中
        {
            photoRecycleViewAdapterDuring = new PhotoSelectAdapter(getContext());
            mBinding.rvAddPhotoDuring.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
            mBinding.rvAddPhotoDuring.setAdapter(photoRecycleViewAdapterDuring);
            if (!TextUtils.isEmpty(taskItemBean.getDuringlist())) {
                ArrayList<String> duringlist = new Gson().fromJson(taskItemBean.getDuringlist(), new TypeToken<ArrayList<String>>() {
                }.getType());
                photoRecycleViewAdapterDuring.setLocalData(duringlist);
            }
//            photoRecycleViewAdapterDuring.setLocalData(selectPhotosDuring);
            photoRecycleViewAdapterDuring.setDeleteListener(new PhotoSelectAdapter.DeleteListener() {
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
                                    mPresenter.deleteImage(imageInfoBean, "during", true, "图片删除中...");
                                }
                            }).show();

                }

                @Override
                public void ondelete(int num) {
//                    if (selectPhotosDuring.size() > 0) {
//                        selectPhotosDuring.remove(position);
//                    }
//                    photoRecycleViewAdapterDuring.setLocalData(selectPhotosDuring);
//                    mBinding.tvPhotoNumDuring.setText(photoRecycleViewAdapterDuring.getPhotoPaths().size() + "/5");
                    List<String> listtemp = photoRecycleViewAdapterDuring.getPhotoPaths();
                    ArrayList<String> during = new ArrayList<>();
                    for (String temp : listtemp) {
                        if (!temp.contains("http")) {
                            during.add(temp);
                        }
                    }
//                    taskItemBean.setDuringlist(new Gson().toJson(during));
//                    taskItemBean.save();
                    mBinding.tvPhotoNumDuring.setText(num + "/5");
                }
            });
            photoRecycleViewAdapterDuring.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    isBefore = "during";
                    if (photoRecycleViewAdapterDuring.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
//                        if (imagListDuring == null) {
//                            imagListDuring = new ArrayList<>();
//                        }
                        if (photoRecycleViewAdapterDuring.getPhotoPaths().size() >= 5) {
                            ToastUtils.shortToast("最多选择5张图片");

                        } else {
                            showPicDialog();
                        }
//                        PhotoPicker.builder()
//                                .setPhotoCount(5 - imagListDuring.size())
//                                .setShowCamera(true)
//                                .setPreviewEnabled(true)
//                                .setSelected(selectPhotosDuring)
//                                .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                    } else {
                        PhotoPreview.builder()
                                .setPhotos(photoRecycleViewAdapterDuring.getPhotoPaths())
                                .setCurrentItem(position)
                                .setShowDeleteButton(false)
                                .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                    }

                }
            });
        }
        // 维护、保洁后
        {
            photoRecycleViewAdapterAfter = new PhotoSelectAdapter(getContext());
            mBinding.rvAddPhotoAfter.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
            mBinding.rvAddPhotoAfter.setAdapter(photoRecycleViewAdapterAfter);
            if (!TextUtils.isEmpty(taskItemBean.getAfterlist())) {
                ArrayList<String> afterlist = new Gson().fromJson(taskItemBean.getAfterlist(), new TypeToken<ArrayList<String>>() {
                }.getType());
                photoRecycleViewAdapterAfter.setLocalData(afterlist);
            }
//            photoRecycleViewAdapterAfter.setLocalData(selectPhotosAfter);
            photoRecycleViewAdapterAfter.setDeleteListener(new PhotoSelectAdapter.DeleteListener() {
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
                                    mPresenter.deleteImage(imageInfoBean, "after", true, "图片删除中...");
                                }
                            }).show();

                }

                @Override
                public void ondelete(int num) {
//                    if (selectPhotosAfter.size() > 0) {
//                        selectPhotosAfter.remove(position);
//                    }
//                    photoRecycleViewAdapterAfter.setLocalData(selectPhotosAfter);
//                    mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
                    List<String> listtemp = photoRecycleViewAdapterAfter.getPhotoPaths();
                    ArrayList<String> after = new ArrayList<>();
                    for (String temp : listtemp) {
                        if (!temp.contains("http")) {
                            after.add(temp);
                        }
                    }
//                    taskItemBean.setAfterlist(new Gson().toJson(after));
//                    taskItemBean.save();
                    mBinding.tvPhotoAfterNum.setText(num + "/5");
                }
            });
            photoRecycleViewAdapterAfter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    isBefore = "after";
                    if (photoRecycleViewAdapterAfter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
//                        if (imagListAfter == null) {
//                            imagListAfter = new ArrayList<>();
//                        }
                        if (photoRecycleViewAdapterAfter.getPhotoPaths().size() >= 5) {
                            ToastUtils.shortToast("最多选择5张图片");

                        } else {
                            showPicDialog();
                        }

//                        PhotoPicker.builder()
//                                .setPhotoCount(5 - imagListAfter.size())
//                                .setShowCamera(false)
//                                .setPreviewEnabled(true)
//                                .setSelected(selectPhotosAfter)
//                                .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                    } else {
                        PhotoPreview.builder()
                                .setPhotos(photoRecycleViewAdapterAfter.getPhotoPaths())
                                .setCurrentItem(position)
                                .setShowDeleteButton(false)
                                .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                    }

                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        if (resultCode == RESULT_OK && (requestCode == taskItemBean.getReservoirSuperviseSequence() || requestCode == taskItemBean.getReservoirSuperviseSequence())) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            if ("before".equals(isBefore)) {
                ArrayList<String> before = new ArrayList<>();
//                selectPhotosBefore.clear();
                if (photos == null) {
                    return;
                }
                for (String temp : photos) {
                    if (temp != null) {
                        if (!temp.contains("http:")) {
                            before.add(temp);
                        }
                    }
                }
                taskItemBean.setBeforelist(new Gson().toJson(before));
//                taskItemBean.save();
                photoRecycleViewAdapterBefore.setLocalData(before);
                mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
////                List<String> tempPaths = photoRecycleViewAdapterBefore.getPhotoPaths();
////                List<String> temp = new ArrayList<>();
////                for (String tempstr:tempPaths){
////                    if (!tempstr.contains("http")){
////                        temp.add(tempstr);
////                    }
////                }
////                taskItemBean.setBeforelist(new Gson().toJson(temp));
//                taskItemBean.setExecuteDate(TimeFormatUtils.getStringDate());
//                taskItemBean.setCommitLocal(true);
//                taskItemBean.save();
            } else if ("during".equals(isBefore)) {
                ArrayList<String> during = new ArrayList<>();
//                selectPhotosDuring.clear();
                if (photos == null) {
                    return;
                }
                for (String temp : photos) {
                    if (temp != null) {
                        if (!temp.contains("http:")) {
                            during.add(temp);
                        }
                    }
                }
                taskItemBean.setDuringlist(new Gson().toJson(during));
//                taskItemBean.save();
                photoRecycleViewAdapterDuring.setLocalData(during);
                mBinding.tvPhotoNumDuring.setText(photoRecycleViewAdapterDuring.getPhotoPaths().size() + "/5");
////                List<String> tempPaths = photoRecycleViewAdapterDuring.getPhotoPaths();
////                List<String> temp = new ArrayList<>();
////                for (String tempstr:tempPaths){
////                    if (!tempstr.contains("http")){
////                        temp.add(tempstr);
////                    }
////                }
////                taskItemBean.setDuringlist(new Gson().toJson(temp));
//                taskItemBean.setExecuteDate(TimeFormatUtils.getStringDate());
//                taskItemBean.setCommitLocal(true);
//                taskItemBean.save();
            } else {
                ArrayList<String> after = new ArrayList<>();
//                selectPhotosAfter.clear();
                if (photos == null) {
                    return;
                }
                for (String temp : photos) {
                    if (temp != null) {
                        if (!temp.contains("http:")) {
                            after.add(temp);
                        }
                    }
                }
                taskItemBean.setAfterlist(new Gson().toJson(after));
//                taskItemBean.save();
                photoRecycleViewAdapterAfter.setLocalData(after);
                mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
////                List<String> tempPaths = photoRecycleViewAdapterAfter.getPhotoPaths();
////                List<String> temp = new ArrayList<>();
////                for (String tempstr:tempPaths){
////                    if (!tempstr.contains("http")){
////                        temp.add(tempstr);
////                    }
////                }
////                taskItemBean.setAfterlist(new Gson().toJson(temp));
//                taskItemBean.setCommitLocal(true);
//                taskItemBean.setExecuteDate(TimeFormatUtils.getStringDate());
//                taskItemBean.save();
            }
        }
        // 拍照限请求返回
        if (requestCode == ImageCaptureManager.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            if (captureManager == null) {
                FragmentActivity activity = getActivity();
                captureManager = new ImageCaptureManager(activity);
            }
            String path = captureManager.getCurrentPhotoPath();
            WaterView waterView = mBinding.waterview;
            LatLngAndAddressBean latLngAndAddressBean = ((NewTaskDealActivity) getActivity()).getAddress();
            String x = "";
            String y = "";
            if (latLngAndAddressBean.getPoint() != null) {
                try {
                    BigDecimal bgx = new BigDecimal(latLngAndAddressBean.getPoint().getX()).setScale(6, RoundingMode.HALF_UP);
                    x = bgx.toString();
                    BigDecimal bgy = new BigDecimal(latLngAndAddressBean.getPoint().getY()).setScale(6, RoundingMode.HALF_UP);
                    y = bgy.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            WaterBean bean = new WaterBean(latLngAndAddressBean.getReservoirName(), latLngAndAddressBean.getCity(), x, y);
            waterView.setData(bean);
            BitmapUtils.addWaterOnPhoto(getActivity(), path, mBinding.waterview, new BitmapUtils.SaveBitmapCallBack() {
                @Override
                public void onSuccess(File file) {
                    captureManager.galleryAddPic();
                    String filepath = captureManager.getCurrentPhotoPath();
                    if ("before".equals(isBefore)) {
                        List<String> listtemp = photoRecycleViewAdapterBefore.getPhotoPaths();
                        ArrayList<String> before = new ArrayList<>();
                        for (String temp : listtemp) {
                            if (!temp.contains("http")) {
                                before.add(temp);
                            }
                        }
                        before.add(filepath);
//                        selectPhotosBefore.clear();
//                        selectPhotosBefore.add(filepath);
                        taskItemBean.setBeforelist(new Gson().toJson(before));
//                        taskItemBean.save();
                        photoRecycleViewAdapterBefore.setLocalData(before);
                        mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");

////                        List<String> tempPaths = photoRecycleViewAdapterBefore.getPhotoPaths();
////                        List<String> temp = new ArrayList<>();
////                        for (String tempstr:tempPaths){
////                            if (!tempstr.contains("http")){
////                                temp.add(tempstr);
////                            }
////                        }
////                        taskItemBean.setBeforelist(new Gson().toJson(temp));
//                        taskItemBean.setCommitLocal(true);
//                        taskItemBean.setExecuteDate(TimeFormatUtils.getStringDate());
//                        taskItemBean.save();
                    } else if ("during".equals(isBefore)) {
                        List<String> listtemp = photoRecycleViewAdapterDuring.getPhotoPaths();
                        ArrayList<String> during = new ArrayList<>();
                        for (String temp : listtemp) {
                            if (!temp.contains("http")) {
                                during.add(temp);
                            }
                        }
                        during.add(filepath);
//                        selectPhotosDuring.clear();
//                        selectPhotosDuring.add(filepath);
                        taskItemBean.setDuringlist(new Gson().toJson(during));
//                        taskItemBean.save();
                        photoRecycleViewAdapterDuring.setLocalData(during);
                        mBinding.tvPhotoNumDuring.setText(photoRecycleViewAdapterDuring.getPhotoPaths().size() + "/5");

//                        List<String> tempPaths = photoRecycleViewAdapterDuring.getPhotoPaths();
////                        List<String> temp = new ArrayList<>();
////                        for (String tempstr:tempPaths){
////                            if (!tempstr.contains("http")){
////                                temp.add(tempstr);
////                            }
////                        }
////                        taskItemBean.setDuringlist(new Gson().toJson(temp));
//                        taskItemBean.setCommitLocal(true);
//                        taskItemBean.setExecuteDate(TimeFormatUtils.getStringDate());
//                        taskItemBean.save();
                    } else {
                        List<String> listtemp = photoRecycleViewAdapterAfter.getPhotoPaths();
                        ArrayList<String> after = new ArrayList<>();
                        for (String temp : listtemp) {
                            if (!temp.contains("http")) {
                                after.add(temp);
                            }
                        }
                        after.add(filepath);
//                        selectPhotosAfter.clear();
//                        selectPhotosAfter.add(filepath);
                        taskItemBean.setAfterlist(new Gson().toJson(after));
//                        taskItemBean.save();
                        photoRecycleViewAdapterAfter.setLocalData(after);
                        mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");

////                        List<String> tempPaths = photoRecycleViewAdapterAfter.getPhotoPaths();
////                        List<String> temp = new ArrayList<>();
////                        for (String tempstr:tempPaths){
////                            if (!tempstr.contains("http")){
////                                temp.add(tempstr);
////                            }
////                        }
////                        taskItemBean.setAfterlist(new Gson().toJson(temp));
//                        taskItemBean.setCommitLocal(true);
//                        taskItemBean.setExecuteDate(TimeFormatUtils.getStringDate());
//                        taskItemBean.save();
                    }
                }

                @Override
                public void onIOFailed(IOException exception) {

                }

                @Override
                public void onCreateDirFailed() {

                }
            });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        taskItemBean.setCommitLocal(false);
//        taskItemBean.setExecuteDate(null);
//        taskItemBean.save();
    }

    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case REQUEST_TAKE_PHOTO_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    //有权限，直接拍照
                    takePicture();
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getActivity(), "获取权限失败，请到系统设置中开启相机读写权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    public final static TaskItemDealFragment newInstance(TaskBean taskBean, String workOrderId, String itemId, TaskItemBean bean, String executeStatus) {
        TaskItemDealFragment f = new TaskItemDealFragment();
        Bundle bundle = new Bundle();
        bundle.putString("workOrderId", workOrderId);
        bundle.putString("itemId", itemId);
        bundle.putSerializable("taskBean", taskBean);
        bundle.putSerializable("TaskItemBean", bean);
        bundle.putString("executeStatus", executeStatus);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void getTaskItemDetailSucess(TaskItemBean data) {
        taskItemBean = data;
        Log.i(TaskItemDealFragment.this.getClass().getName(), "getTaskItemDetailSucess");
        refreshView(taskItemBean);


    }

    @Override
    public void delFileSucess(ImageInfoBean imageInfoBean, String isbefore) {
        if ("before".equals(isbefore)) {
            taskItemBean.getStartImages().remove(imageInfoBean);
            photoRecycleViewAdapterBefore.setNetData(taskItemBean.getStartImages());
            mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
        } else if ("during".equals(isbefore)) {
            taskItemBean.getIngImages().remove(imageInfoBean);
            photoRecycleViewAdapterDuring.setNetData(taskItemBean.getIngImages());
            mBinding.tvPhotoNumDuring.setText(photoRecycleViewAdapterDuring.getPhotoPaths().size() + "/5");
        } else {
            taskItemBean.getEndImages().remove(imageInfoBean);
            photoRecycleViewAdapterAfter.setNetData(taskItemBean.getEndImages());
            mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
        }
    }

    public ReturnData getDealContent() {
        ReturnData returnData = new ReturnData();
        returnData.setFinish(false);
//        if (TextUtils.isEmpty(etDescStr)) {
//            ToastUtils.shortToast("请输入描述");
//            return returnData;
//        }
        if (TextUtils.isEmpty(executeResultType)) {
            ToastUtils.shortToast("请输入描述");
            return returnData;
        }
//        if (selectPhotosBefore == null || selectPhotosBefore.size() == 0) {
//            ToastUtils.shortToast("请选择图片");
//            return returnData;
//        }
//        if (selectPhotosAfter == null || selectPhotosAfter.size() == 0) {
//            ToastUtils.shortToast("请选择图片");
//            return returnData;
//        }
        returnData.setFinish(true);
        returnData.setWorkOrderId(workOrderId);
        returnData.setItemId(itemId);
        returnData.setExResult(executeResultType);
        returnData.setExDesc(etDescStr);
        returnData.setLgtd("12");
        returnData.setLttd("12");
        ArrayList<String> selectPhotosBefore1 = new ArrayList<>();
        ArrayList<String> selectPhotosAfter1 = new ArrayList<>();
        ArrayList<String> selectPhotosDuring1 = new ArrayList<>();
//        selectPhotosAfter1.addAll(selectPhotosAfter);
//        selectPhotosBefore1.addAll(selectPhotosBefore);
//        selectPhotosDuring1.addAll(selectPhotosDuring);

        ArrayList<String> listbefore = photoRecycleViewAdapterBefore.getPhotoPaths();
        for (String temp : listbefore) {
            if (!temp.contains("http")) {
                selectPhotosBefore1.add(temp);
            }
        }

        ArrayList<String> listduring = photoRecycleViewAdapterDuring.getPhotoPaths();
        for (String temp : listduring) {
            if (!temp.contains("http")) {
                selectPhotosDuring1.add(temp);
            }
        }

        ArrayList<String> listafter = photoRecycleViewAdapterAfter.getPhotoPaths();
        for (String temp : listafter) {
            if (!temp.contains("http")) {
                selectPhotosAfter1.add(temp);
            }
        }
        returnData.setFiles(selectPhotosBefore1);
        returnData.setEndfiles(selectPhotosAfter1);
        returnData.setDuringfiles(selectPhotosDuring1);
        return returnData;
    }

    public boolean getExeStutas() {
        if (taskItemBean == null) {
            return false;
        }
        return "0".equals(taskItemBean.getCompleteStatus());
    }

    public void clearFocus() {
        if (mBinding != null) {
            mBinding.etDesc.clearFocus();
            InputMethodManager imm = (InputMethodManager) getBaseActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            // 强制隐藏软键盘
            imm.hideSoftInputFromWindow(mBinding.etDesc.getWindowToken(), 0);
        }
    }

    private Dialog dialog;
    private final static int REQUEST_TAKE_PHOTO_PERMISSION = 20;

    private void showPicDialog() {
        View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
        dialog = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        window.findViewById(R.id.bt_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAdded()){
                    return;
                }
                String state = Environment.getExternalStorageState(); //拿到sdcard是否可用的状态码Manifest.permission.CAMERA
                if (state.equals(Environment.MEDIA_MOUNTED)) {   //如果可用
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO_PERMISSION);
                        } else {
                            //有权限，直接拍照
                            takePicture();
                        }
                    } else {
                        takePicture();
                    }
                } else {
                    Toast.makeText(getActivity(), "sdcard不可用", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GalleryActivity.openActivity(PublishNoteActivity.this, false, num, REQUEST_CODE_TAG);
                if ("before".equals(isBefore)) {
                    List<String> listtemp = photoRecycleViewAdapterBefore.getPhotoPaths();
                    ArrayList<String> before = new ArrayList<>();
                    ArrayList<String> net_before = new ArrayList<>();
                    for (String temp : listtemp) {
                        if (!temp.contains("http")) {
                            before.add(temp);
                        } else {
                            net_before.add(temp);
                        }
                    }
                    PhotoPicker.builder()
                            .setPhotoCount(before.size() + (5 - listtemp.size()))
                            .setShowCamera(false)
                            .setPreviewEnabled(true)
                            .setSelected(before)
                            .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                } else if ("during".equals(isBefore)) {
                    List<String> listtemp = photoRecycleViewAdapterDuring.getPhotoPaths();
                    ArrayList<String> during = new ArrayList<>();
                    for (String temp : listtemp) {
                        if (!temp.contains("http")) {
                            during.add(temp);
                        }
                    }
                    PhotoPicker.builder()
                            .setPhotoCount(during.size() + 5 - listtemp.size())
                            .setShowCamera(false)
                            .setPreviewEnabled(true)
                            .setSelected(during)
                            .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                } else {
                    List<String> listtemp = photoRecycleViewAdapterAfter.getPhotoPaths();
                    ArrayList<String> after = new ArrayList<>();
                    for (String temp : listtemp) {
                        if (!temp.contains("http")) {
                            after.add(temp);
                        }
                    }
                    PhotoPicker.builder()
                            .setPhotoCount(after.size() + 5 - listtemp.size())
                            .setShowCamera(false)
                            .setPreviewEnabled(true)
                            .setSelected(after)
                            .start(getBaseActivity(), TaskItemDealFragment.this, taskItemBean.getReservoirSuperviseSequence());
                }

                dialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
    }


    private void takePicture() {
        try {
            Intent intent = captureManager.dispatchTakePictureIntent();
            startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    public class ReturnData {
        private boolean isFinish;
        private String workOrderId;
        private String itemId;
        private String exResult;
        private String exDesc;
        private String lgtd;
        private String lttd;
        private List<String> files;
        private List<String> duringfiles;
        private List<String> endfiles;

        public List<String> getDuringfiles() {
            return duringfiles;
        }

        public void setDuringfiles(List<String> duringfiles) {
            this.duringfiles = duringfiles;
        }

        public boolean isFinish() {
            return isFinish;
        }

        public void setFinish(boolean finish) {
            isFinish = finish;
        }

        public String getWorkOrderId() {
            return workOrderId;
        }

        public void setWorkOrderId(String workOrderId) {
            this.workOrderId = workOrderId;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getExResult() {
            return exResult;
        }

        public void setExResult(String exResult) {
            this.exResult = exResult;
        }

        public String getExDesc() {
            return exDesc;
        }

        public void setExDesc(String exDesc) {
            this.exDesc = exDesc;
        }

        public String getLgtd() {
            return lgtd;
        }

        public void setLgtd(String lgtd) {
            this.lgtd = lgtd;
        }

        public String getLttd() {
            return lttd;
        }

        public void setLttd(String lttd) {
            this.lttd = lttd;
        }

        public List<String> getFiles() {
            return files;
        }

        public void setFiles(List<String> files) {
            this.files = files;
        }

        public List<String> getEndfiles() {
            return endfiles;
        }

        public void setEndfiles(List<String> endfiles) {
            this.endfiles = endfiles;
        }
    }
}
