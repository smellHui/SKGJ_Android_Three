package com.tepia.main.view.maincommon.setting.train;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.data.Type;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.model.train.TrainContract;
import com.tepia.main.model.train.TrainPresenter;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.jihua.AddPlanActivity;
import com.tepia.main.view.maincommon.setting.adapter.FileRecycleViewAdapter;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.tepia.main.view.maintechnology.yunwei.OperationListFragment.strToLong;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018-9-30
 * Version :1.0
 * 功能描述 : 新增培训
 **/
public class AddTrainActivity extends BaseActivity {

    private TrainPresenter mPresenter;
    private EditText etPlanName;
    private TextView tvDate;
    private EditText etPlanPosition;
    private EditText etOrganizecompany;
    private EditText etRemarks;
    private TextView photoTitleTv;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<FileBean> selectedFiles = new ArrayList<>();
    ArrayList<String> selectFilePaths = new ArrayList<>();
    private RecyclerView rvImagePick;
    private PhotoRecycleViewAdapter photoAdapter;
    private RecyclerView rvFilePick;
    private FileRecycleViewAdapter fileAdapter;
    private Button btAdd;
    private boolean isAddPlan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_train;
    }

    @Override
    public void initView() {
        setCenterTitle("新增培训");
        showBack();
        etPlanName = findViewById(R.id.et_plan_name);
        tvDate = findViewById(R.id.tv_date);
        etPlanPosition = findViewById(R.id.et_plan_position);
        etOrganizecompany = findViewById(R.id.et_organizeCompany);
        etRemarks = findViewById(R.id.et_remarks);
        photoTitleTv = findViewById(R.id.photoTitleTv);
        photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
        rvImagePick = findViewById(R.id.rv_image_pick);
        rvFilePick = findViewById(R.id.rv_file_pick);
        btAdd = findViewById(R.id.bt_add);
        initSelectDate();
        initImagePick();
        initFilePick();
        initClick();
    }

    private void initClick() {
        btAdd.setOnClickListener(v -> {
            String trainTitle = etPlanName.getText().toString().replaceAll(" ","");
            String trainDate = tvDate.getText().toString();
            String trainPosition = etPlanPosition.getText().toString().replaceAll(" ","");
            String organizeCompany = etOrganizecompany.getText().toString().replaceAll(" ","");
            String trainContent = etRemarks.getText().toString().replaceAll(" ","");
            if (trainTitle==null||trainTitle.length()==0){
                ToastUtils.shortToast("培训主题不能为空");
                return;
            }
            if (trainDate==null||trainDate.length()==0){
                ToastUtils.shortToast("培训时间不能为空");
                return;
            }
            if (trainPosition==null||trainPosition.length()==0){
                ToastUtils.shortToast("培训地点不能为空");
                return;
            }
            if (organizeCompany==null||organizeCompany.length()==0){
                ToastUtils.shortToast("培训组织不能为空");
                return;
            }
            if (trainContent==null||trainContent.length()==0){
                ToastUtils.shortToast("培训内容不能为空");
                return;
            }
            selectFilePaths.clear();
            if (selectedFiles!=null&&selectedFiles.size()>0){
                for (int i = 0; i < selectedFiles.size(); i++) {
                    selectFilePaths.add(selectedFiles.get(i).filePath);
                }
            }
            mPresenter.addTrain(trainTitle,trainPosition,trainContent,organizeCompany,trainDate,selectedPhotos,selectFilePaths);
        });
    }

    private void initImagePick() {
        rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (photoAdapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoRecycleViewAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(true)
                            .setSelected(selectedPhotos)
                            .start(AddTrainActivity.this);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(AddTrainActivity.this);
                }

            }
        }));
        photoAdapter = new PhotoRecycleViewAdapter(getApplicationContext(), selectedPhotos);
        rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        rvImagePick.setAdapter(photoAdapter);
    }

    private void initFilePick() {
        fileAdapter = new FileRecycleViewAdapter(getApplicationContext(), selectedFiles);
        rvFilePick.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvFilePick.setAdapter(fileAdapter);
        fileAdapter.setOnAddItemClickListener((view, position) -> {
            new LFilePicker()
                    .withActivity(AddTrainActivity.this)
                    .withRequestCode(100)
                    .withMutilyMode(true)
                    .withFileFilter(new String[]{".doc", ".ppt", ".xls", ".docx", ".pptx", ".xlsx",".pdf",".txt"})
                    .start();
        });
        fileAdapter.setOnAddDeleteClickListener((view, position) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddTrainActivity.this);
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

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private TimePickerDialogUtil timePickerDialogUtil;

    private void initSelectDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(getApplicationContext(), sf);
        tvDate.setOnClickListener(v -> {
            String current = tvDate.getText().toString();
            long currentLong = 0;
            if (current!=null&&current.length()!=0){
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvDate.setText(text);
            }, Type.ALL, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears, R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择时间");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");
        });
    }

    @Override
    public void initData() {
        mPresenter = new TrainPresenter();
        mPresenter.attachView(new TrainContract.View<BaseResponse>() {
            @Override
            public void success(BaseResponse data) {
                ToastUtils.longToast("添加成功");
                Intent intent = new Intent();
                isAddPlan = true;
                intent.putExtra("isAddPlan",isAddPlan);
                AddTrainActivity.this.setResult(1, intent);
                finish();
            }

            @Override
            public void failure(String msg) {
                isAddPlan = false;
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

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
                photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
            }
        } else if (resultCode == RESULT_OK && (requestCode == 100)) {
            if (data!=null){
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                FileBean fileBean;
                if (list != null&&list.size()>0) {
                    for (int i = 0; i < list.size(); i++) {
                        String path = list.get(i);
                        String fileName = path.substring(path.lastIndexOf("/")+1);
                        fileBean  = new FileBean();
                        fileBean.fileName = fileName;
                        fileBean.filePath = path;
                        if (!isFilesContain(path)){
                            selectedFiles.add(fileBean);
                        }
                    }
                    fileAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private boolean isFilesContain(String path){
        boolean isContain = false;
        if (selectedFiles!=null&&selectedFiles.size()>0){
            for (int i = 0; i < selectedFiles.size(); i++) {
                FileBean fileBean = selectedFiles.get(i);
                if (path.equals(fileBean.filePath)){
                    isContain = true;
                }
            }
        }
        return isContain;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("isAddPlan",isAddPlan);
            AddTrainActivity.this.setResult(1, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
