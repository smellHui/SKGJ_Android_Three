package com.tepia.main.view.main.work.task.taskdeal.taskitemdeal;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.common.image.PhotoSelectAdapter;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.databinding.FragmentTaskItemDealBinding;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.task.bean.ShowTypeBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdeal.TaskDealActivity;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

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

    private PhotoSelectAdapter photoRecycleViewAdapterBefore;
    private ArrayList<String> selectPhotosBefore = new ArrayList<>();
    private boolean isBefore = true;
    private PhotoSelectAdapter photoRecycleViewAdapterAfter;
    private ArrayList<String> selectPhotosAfter = new ArrayList<>();
    private List<ImageInfoBean> imagListBefore = new ArrayList<>();
    private List<ImageInfoBean> imagListAfter = new ArrayList<>();
    private TaskBean taskBean;
    private ArrayList<ShowTypeBean> showTypeBeanList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.workOrderId = getArguments().getString("workOrderId");
        this.itemId = getArguments().getString("itemId");
        this.executeStatus = getArguments().getString("executeStatus");
        this.taskItemBean = (TaskItemBean) getArguments().getSerializable("TaskItemBean");
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
                break;
            case "2":
                mBinding.tvBeforeHint.setText("维修养护前");
                mBinding.tvAfterHint.setText("维修养护后");
                mBinding.loAfter.setVisibility(View.VISIBLE);
                break;
            case "3":
                mBinding.tvBeforeHint.setText("保洁前");
                mBinding.tvAfterHint.setText("保洁后");
                mBinding.loAfter.setVisibility(View.VISIBLE);
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
                if (getBaseActivity() instanceof TaskDealActivity) {
                    ((TaskDealActivity) getBaseActivity()).onFocusChange(view, b);
                }
            }
        });

    }

    @Override
    protected void initRequestData() {
        if (taskItemBean == null || TextUtils.isEmpty(taskItemBean.getResultType())) {
            mPresenter.getTaskItemDetail(itemId, true, getString(R.string.data_loading));
        } else {
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
        } else {
            photoRecycleViewAdapterBefore.setShowType(true);
            photoRecycleViewAdapterAfter.setShowType(true);
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
    }

    private void initRadioView() {

        if (!TextUtils.isEmpty(executeResultType)) {
            switch (executeResultType) {
                case "1":
                    mBinding.rb1.setChecked(false);
                    mBinding.rb2.setChecked(true);
                    break;
                case "0":
                    mBinding.rb1.setChecked(true);
                    mBinding.rb2.setChecked(false);
                    break;
                default:
                    mBinding.rb1.setChecked(false);
                    mBinding.rb2.setChecked(true);
                    executeResultType = "1";
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
                    executeResultType = showTypeBeanList.get(1).getValue();
                    initRadioView();
                }

            } else {
                mBinding.rb1.setChecked(false);
                mBinding.rb2.setChecked(true);
                executeResultType = "1";
                initRadioView();
            }
        }
    }

    /**
     * 初始化 图片选择
     */
    private void initPhotoListView() {
        {
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
                                    mPresenter.deleteImage(imageInfoBean, true, true, "图片删除中...");
                                }
                            }).show();

                }

                @Override
                public void ondelete(int position) {
                    selectPhotosBefore.remove(position);
                    photoRecycleViewAdapterBefore.setLocalData(selectPhotosBefore);
                    mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
                }
            });
            photoRecycleViewAdapterBefore.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    isBefore = true;
                    if (photoRecycleViewAdapterBefore.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                        if (imagListBefore == null) {
                            imagListBefore = new ArrayList<>();
                        }
                        PhotoPicker.builder()
                                .setPhotoCount(5 - imagListBefore.size())
                                .setShowCamera(true)
                                .setPreviewEnabled(true)
                                .setSelected(selectPhotosBefore)
                                .start(getBaseActivity(), TaskItemDealFragment.this);
                    } else {
                        PhotoPreview.builder()
                                .setPhotos(photoRecycleViewAdapterBefore.getPhotoPaths())
                                .setCurrentItem(position)
                                .setShowDeleteButton(false)
                                .start(getBaseActivity(), TaskItemDealFragment.this);
                    }
                }
            });

        }
        {
            photoRecycleViewAdapterAfter = new PhotoSelectAdapter(getContext());
            mBinding.rvAddPhotoAfter.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
            mBinding.rvAddPhotoAfter.setAdapter(photoRecycleViewAdapterAfter);
            photoRecycleViewAdapterAfter.setLocalData(selectPhotosAfter);
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
                                    mPresenter.deleteImage(imageInfoBean, false, true, "图片删除中...");
                                }
                            }).show();

                }

                @Override
                public void ondelete(int position) {
                    selectPhotosAfter.remove(position);
                    photoRecycleViewAdapterAfter.setLocalData(selectPhotosAfter);
                    mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
                }
            });
            photoRecycleViewAdapterAfter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    isBefore = false;
                    if (photoRecycleViewAdapterAfter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                        if (imagListAfter == null) {
                            imagListAfter = new ArrayList<>();
                        }
                        PhotoPicker.builder()
                                .setPhotoCount(5 - imagListAfter.size())
                                .setShowCamera(true)
                                .setPreviewEnabled(true)
                                .setSelected(selectPhotosAfter)
                                .start(getBaseActivity(), TaskItemDealFragment.this);
                    } else {
                        PhotoPreview.builder()
                                .setPhotos(photoRecycleViewAdapterAfter.getPhotoPaths())
                                .setCurrentItem(position)
                                .start(getBaseActivity(), TaskItemDealFragment.this);
                    }

                }
            });

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            if (isBefore) {
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
                mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
            } else {
                selectPhotosAfter.clear();
                if (photos == null) {
                    return;
                }
                for (String temp : photos) {
                    if (temp != null) {
                        if (!temp.contains("http:")) {
                            selectPhotosAfter.add(temp);
                        }
                    }
                }
                photoRecycleViewAdapterAfter.setLocalData(selectPhotosAfter);
                mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
            }
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
        refreshView(data);
        imagListBefore = data.getStartImages();
        imagListAfter = data.getEndImages();
        if (imagListBefore.size() > 0) {
            photoRecycleViewAdapterBefore.setNetData(data.getStartImages());
            mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
        }
        if (imagListAfter.size() > 0) {
            photoRecycleViewAdapterAfter.setNetData(data.getEndImages());
            mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
        }
    }

    @Override
    public void delFileSucess(ImageInfoBean imageInfoBean, boolean isbefore) {
        if (isbefore) {
            imagListBefore.remove(imageInfoBean);
            photoRecycleViewAdapterBefore.setNetData(imagListBefore);
            mBinding.tvPhotoNumBefore.setText(photoRecycleViewAdapterBefore.getPhotoPaths().size() + "/5");
        } else {
            imagListAfter.remove(imageInfoBean);
            photoRecycleViewAdapterAfter.setNetData(imagListAfter);
            mBinding.tvPhotoAfterNum.setText(photoRecycleViewAdapterAfter.getPhotoPaths().size() + "/5");
        }
    }

    public ReturnData getDealContent() {
        ReturnData returnData = new ReturnData();
        returnData.setFinish(false);
        if (TextUtils.isEmpty(etDescStr)) {
            ToastUtils.shortToast("请输入描述");
            return returnData;
        }
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
        returnData.setFiles(selectPhotosBefore);
        returnData.setEndfiles(selectPhotosAfter);
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

    public class ReturnData {
        private boolean isFinish;
        private String workOrderId;
        private String itemId;
        private String exResult;
        private String exDesc;
        private String lgtd;
        private String lttd;
        private List<String> files;
        private List<String> endfiles;

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
