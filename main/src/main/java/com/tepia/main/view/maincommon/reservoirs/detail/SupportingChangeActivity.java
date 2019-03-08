package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.arialyy.frame.util.show.T;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.image.PhotoSelectAdapter;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivitySupportingChangeBinding;
import com.tepia.main.databinding.ActivitySupportingDetailBinding;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.question.problemlist.PhotoAdapter;
import com.tepia.main.view.main.work.task.taskdeal.taskitemdeal.TaskItemDealFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.setting.train.AddTrainActivity;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    : 2018-9-25
 * Version :1.0
 * 功能描述 : 配套设施详情
 **/
@Route(path = AppRoutePath.app_support_change)
public class SupportingChangeActivity extends BaseActivity implements OnDateSetListener {
    //    ActivitySupportingDa binding;
    ActivitySupportingChangeBinding binding;
    //    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<ImageInfoBean> imagListBefore = new ArrayList<>();

    private PhotoSelectAdapter photoRecycleViewAdapterAfter;

    private int flag = 0;
    private SupportingBean.DataBean dataBean;
    private List<DictMapEntity.ArrayBean.NameValueBean> nameValueBeanListOfDe_type;
    private List<DictMapEntity.ArrayBean.NameValueBean> listOfDe_statues;

    @Override
    public int getLayoutId() {
        return R.layout.activity_supporting_change;
    }

    @Override
    public void initView() {
//        setCenterTitle("配套设施");
//        showBack();
        binding = DataBindingUtil.bind(mRootView);

        nameValueBeanListOfDe_type = DictMapManager.getInstance().getmDictMap().getArray().getDe_type();
        listOfDe_statues = DictMapManager.getInstance().getmDictMap().getArray().getDe_status();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("support_change")) {
            setCenterTitle("配套设施编辑");

            flag = 1;
            dataBean = (SupportingBean.DataBean) bundle.getSerializable("support_change");
            binding.nameTv.setText(dataBean.getDeName());
            binding.functionTv.setText(dataBean.getDeFunction());
            DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
            Map<String, String> mapDetype = dictMapEntity.getObject().getDe_type();
            deType = dataBean.getDeType();
            binding.typeTv.setText(mapDetype.get(deType));

            Map<String, String> mapDeStatus = dictMapEntity.getObject().getDe_status();
            deStatus = dataBean.getDeStatus();
            binding.destatusTv.setText(mapDeStatus.get(dataBean.getDeStatus()));

            binding.positionTv.setText(dataBean.getPosition());

            beginUseDate = dataBean.getBeginUseDate();
//            binding.timeTv.setText(dataBean.getBeginUseDate());
            binding.totalTv.setText(dataBean.getDeTotals());

            imagListBefore = dataBean.getFileInfo();



        } else {
            setCenterTitle("添加配套设施");

        }
        showBack();

        initRec();
        initTimePicker();
        TextView sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    if (selectedPhotos.size() > 0) {
                        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                        Tiny.getInstance().source(selectedPhotos.toArray(new String[selectedPhotos.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                            @Override
                            public void callback(boolean isSuccess, String[] outfile) {
                                if (isSuccess) {
                                    ArrayList<String> tempslist = new ArrayList<>();
                                    Collections.addAll(tempslist, outfile);
                                    insertReservoirDevice(tempslist);

                                }
                            }
                        });
                    } else {
                        insertReservoirDevice(selectedPhotos);
                    }
                } else {
                    if (selectedPhotos.size() > 0) {
                        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                        Tiny.getInstance().source(selectedPhotos.toArray(new String[selectedPhotos.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                            @Override
                            public void callback(boolean isSuccess, String[] outfile) {
                                if (isSuccess) {
                                    ArrayList<String> tempslist = new ArrayList<>();
                                    Collections.addAll(tempslist, outfile);
                                    updateReservoirDevice(tempslist);

                                }
                            }
                        });
                    } else {
                        updateReservoirDevice(selectedPhotos);
                    }
                }
            }
        });
        binding.typeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectTypeDialog();
            }
        });

        binding.destatusTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectStatusDialog(listOfDe_statues);
            }
        });
        binding.timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePickerDialogUtil.startDialog != null) {
                    timePickerDialogUtil.startDialog = null;
                }

                timePickerDialogUtil.builder.setTitleStringId("请选择时间");
                timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
                timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");
            }
        });

    }

    private void showSelectTypeDialog() {
        if (nameValueBeanListOfDe_type == null && nameValueBeanListOfDe_type.size() == 0) {
            return;
        }
        if (nameValueBeanListOfDe_type != null) {
            String[] stringItems = new String[nameValueBeanListOfDe_type.size()];
            for (int i = 0; i < nameValueBeanListOfDe_type.size(); i++) {
                stringItems[i] = nameValueBeanListOfDe_type.get(i).getName();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
            dialog.title("请选择设施类型")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    binding.typeTv.setText(nameValueBeanListOfDe_type.get(position).getName());
                    deType = nameValueBeanListOfDe_type.get(position).getValue();
                    dialog.dismiss();
                }
            });
        }
    }

    private void showSelectStatusDialog(List<DictMapEntity.ArrayBean.NameValueBean> listOfStatus) {
        if (listOfStatus == null && listOfStatus.size() == 0) {
            return;
        }
        if (listOfStatus != null) {
            String[] stringItems = new String[listOfStatus.size()];
            for (int i = 0; i < listOfStatus.size(); i++) {
                stringItems[i] = listOfStatus.get(i).getName();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
            dialog.title("请选择设施状态")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    binding.destatusTv.setText(listOfStatus.get(position).getName());
                    deStatus = listOfStatus.get(position).getValue();
                    dialog.dismiss();
                }
            });
        }
    }

    private String deName, deType, deTotals, deFunction, beginUseDate, position, deStatus = "1", remark = "";

    /**
     * 修改
     */
    private void updateReservoirDevice(ArrayList<String> selectedPhotos) {
        if (dataBean != null) {
            deName = binding.nameTv.getText().toString();
            if (TextUtils.isEmpty(deName)) {
                ToastUtils.shortToast("请输入设备名称");
                return;
            }
            deFunction = binding.functionTv.getText().toString();
            if (TextUtils.isEmpty(deFunction)) {
                ToastUtils.shortToast("请输入设备功能");
                return;
            }

            position = binding.positionTv.getText().toString();
            if (TextUtils.isEmpty(position)) {
                ToastUtils.shortToast("请输入位置");
                return;
            }


            if (TextUtils.isEmpty(deType)) {
                ToastUtils.shortToast("请输入设备类型");
                return;
            }
//        deStatus = binding.destatusTv.getText().toString();
            if (TextUtils.isEmpty(deStatus)) {
                ToastUtils.shortToast("请输入设备状态");
                return;
            }

            deTotals = binding.totalTv.getText().toString();
            if (TextUtils.isEmpty(deTotals)) {
                ToastUtils.shortToast("请输入数量");
                return;
            }


            beginUseDate = binding.timeTv.getText().toString();
            if (TextUtils.isEmpty(beginUseDate)) {
                ToastUtils.shortToast("请输入投入使用时间");
                return;
            }

            dataBean.setDeName(deName);
            dataBean.setDeType(deType);
            dataBean.setDeTotals(deTotals);
            dataBean.setDeFunction(deFunction);
            dataBean.setBeginUseDate(beginUseDate);
            dataBean.setPosition(position);
            dataBean.setDeStatus(deStatus);
            if (selectedPhotos.size() > 0) {
                for(String path :selectedPhotos){
                    ImageInfoBean imageInfoBean = new ImageInfoBean();
                    imageInfoBean.setFilePath(path);
                    imagListBefore.add(imageInfoBean);
                }

            }
            dataBean.setFileInfo(imagListBefore);
            ReservirosManager.getInstance().updateReservoirDevice(dataBean.getId(), dataBean.getReservoirId(), deName, deType, deTotals, deFunction, beginUseDate, position, deStatus, remark, selectedPhotos)
                    .subscribe(new LoadingSubject<BaseResponse>(true, Utils.getContext().getString(R.string.data_loading)) {
                        @Override
                        protected void _onNext(BaseResponse baseResponse) {
                            if (baseResponse != null) {
                                if (baseResponse.getCode() == 0) {
                                    ToastUtils.shortToast("修改成功");
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("dataBean", dataBean);
                                    intent.putExtras(bundle);
                                    SupportingChangeActivity.this.setResult(1, intent);
                                    finish();

                                } else {
                                }
                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            ToastUtils.shortToast(message);

                        }
                    });


        }

    }

    /*private void check(){
        deName = binding.nameTv.getText().toString();
        if (TextUtils.isEmpty(deName)) {
            ToastUtils.shortToast("请输入设备名称");
            return;
        }

        deType = binding.typeTv.getText().toString();
        if (TextUtils.isEmpty(deType)) {
            ToastUtils.shortToast("请输入设备类型");
            return;
        }
        deTotals = binding.totalTv.getText().toString();
        if (TextUtils.isEmpty(deTotals)) {
            ToastUtils.shortToast("请输入数量");
            return;
        }

        deFunction = binding.functionTv.getText().toString();
        if (TextUtils.isEmpty(deFunction)) {
            ToastUtils.shortToast("请输入设备功能");
            return;
        }
        beginUseDate = binding.timeTv.getText().toString();
        if (TextUtils.isEmpty(beginUseDate)) {
            ToastUtils.shortToast("请输入投入使用时间");
            return;
        }
        position = binding.positionTv.getText().toString();
        if (TextUtils.isEmpty(position)) {
            ToastUtils.shortToast("请输入位置");
            return;
        }
        deStatus = binding.destatusTv.getText().toString();
        if (TextUtils.isEmpty(deStatus)) {
            ToastUtils.shortToast("请输入设备状态");
            return;
        }
    }*/

    /**
     * 新增
     */
    private void insertReservoirDevice(ArrayList<String> selectedPhotos) {

        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);

        deName = binding.nameTv.getText().toString();
        if (TextUtils.isEmpty(deName)) {
            ToastUtils.shortToast("请输入设备名称");
            return;
        }
        deFunction = binding.functionTv.getText().toString();
        if (TextUtils.isEmpty(deFunction)) {
            ToastUtils.shortToast("请输入设备功能");
            return;
        }

        position = binding.positionTv.getText().toString();
        if (TextUtils.isEmpty(position)) {
            ToastUtils.shortToast("请输入位置");
            return;
        }


        if (TextUtils.isEmpty(deType)) {
            ToastUtils.shortToast("请输入设备类型");
            return;
        }
//        deStatus = binding.destatusTv.getText().toString();
        if (TextUtils.isEmpty(deStatus)) {
            ToastUtils.shortToast("请输入设备状态");
            return;
        }

        deTotals = binding.totalTv.getText().toString();
        if (TextUtils.isEmpty(deTotals)) {
            ToastUtils.shortToast("请输入数量");
            return;
        }


        beginUseDate = binding.timeTv.getText().toString();
        if (TextUtils.isEmpty(beginUseDate)) {
            ToastUtils.shortToast("请输入投入使用时间");
            return;
        }
        ReservirosManager.getInstance().insertReservoirDevice(reservoirId, deName, deType, deTotals, deFunction, beginUseDate, position, deStatus, remark, selectedPhotos)
                .subscribe(new LoadingSubject<BaseResponse>(true, Utils.getContext().getString(R.string.data_loading)) {

                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 0) {
                                ToastUtils.shortToast("新增成功");
                                finish();
                            } else {
                                ToastUtils.shortToast("失败");

                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);

                    }
                });

    }


    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    /**
     * 初始化recycleview
     */
    private void initRec() {

        photoRecycleViewAdapterAfter = new PhotoSelectAdapter(this);
        binding.bizRy.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        binding.bizRy.setAdapter(photoRecycleViewAdapterAfter);
        photoRecycleViewAdapterAfter.setNetData(imagListBefore);
        photoRecycleViewAdapterAfter.setLocalData(selectedPhotos);
        photoRecycleViewAdapterAfter.setDeleteListener(new PhotoSelectAdapter.DeleteListener() {
            @Override
            public void ondelete(ImageInfoBean imageInfoBean) {
                new AlertDialog.Builder(SupportingChangeActivity.this)
                        .setMessage("是否确定删除照片?")
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
                                if (imageInfoBean != null) {
                                    deleteImage(imageInfoBean, true, "正在删除图片...");

                                }
                            }
                        }).show();

            }

            @Override
            public void ondelete(int position) {
                if (selectedPhotos.size() > 0) {
                    selectedPhotos.remove(position);
                }
                photoRecycleViewAdapterAfter.setLocalData(selectedPhotos);
            }
        });
        photoRecycleViewAdapterAfter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (photoRecycleViewAdapterAfter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                    if (imagListBefore == null) {
                        imagListBefore = new ArrayList<>();
                    }
                    PhotoPicker.builder()
                            .setPhotoCount(5 - imagListBefore.size())
                            .setShowCamera(true)
                            .setPreviewEnabled(true)
                            .setSelected(selectedPhotos)
                            .start(SupportingChangeActivity.this);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(photoRecycleViewAdapterAfter.getPhotoPaths())
                            .setCurrentItem(position)
                            .start(SupportingChangeActivity.this);
                }

            }
        });

    }

    public void deleteImage(ImageInfoBean imageInfoBean, boolean isShow, String msg) {
        TaskManager.getInstance().delFile(imageInfoBean.getFileId()).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (response.getCode() == 0) {
                    imagListBefore.remove(imageInfoBean);
                    photoRecycleViewAdapterAfter.setNetData(imagListBefore);
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });

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
            if (photos != null) {
                selectedPhotos.clear();
                if (photos == null) {
                    return;
                }
                for (String temp : photos) {
                    if (temp != null) {
                        if (!temp.contains("http:")) {
                            selectedPhotos.add(temp);
                        }
                    }
                }
                photoRecycleViewAdapterAfter.setLocalData(selectedPhotos);

            }
        }
    }

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private TimePickerDialogUtil timePickerDialogUtil;

    /**
     * 初始化时间选择器
     */
    private void initTimePicker() {
        timePickerDialogUtil = new TimePickerDialogUtil(this, sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);
        binding.timeTv.setText(beginUseDate);

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {


        String text = timePickerDialogUtil.getDateToString(millseconds);
        beginUseDate = text;
        binding.timeTv.setText(beginUseDate);
        LogUtil.e("startTime", text);


    }

}
