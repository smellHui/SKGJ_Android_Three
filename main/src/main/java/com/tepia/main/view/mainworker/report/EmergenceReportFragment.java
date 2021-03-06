package com.tepia.main.view.mainworker.report;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.arialyy.frame.util.show.T;
import com.example.gaodelibrary.GaodeEntity;
import com.example.gaodelibrary.OnGaodeLibraryListen;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.model.report.ShangbaoManager;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.FileUtil;
import com.tepia.main.view.MainActivity;
import com.tepia.main.view.main.question.TypeResponse;
import com.tepia.main.view.mainworker.report.adapter.AdapterEmergenceReport;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-9-29
 * Version :1.0
 * 功能描述 :应急上报fragment
 **/
public class EmergenceReportFragment extends MVPBaseFragment<ReportContract.View, ReportPresenter> implements View.OnClickListener, View.OnTouchListener {


    private EditText titleEv;
    private EditText contentEv;
    private RecyclerView rvImagePick;
    private TextView sendBtn;
    private TextView typeTv;
    private TextView resviorTv;
    private TextView photoTitleTv;
    private String questionType;
    private String questionTitle;
    private String questionContent;
    private String reservoirId;
    private PhotoRecycleViewAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<TypeResponse> data = new ArrayList<>();
    private List<com.tepia.main.model.detai.ReservoirBean> dateBeanList;
    private String userCode = "";
    private RecyclerView recycleChangyong;
    private AdapterEmergenceReport adapterQuestion;
    //标签个数
    private static int labelcount = 8;
    private static int REQUEST_VIDEO_CODE = 100;
    private static int REQUEST_DELETE_CODE = 200;
    private ImageView videoIcon;
    // 视频路径
    public static String videoPath = "";
    private Bitmap addBitmap;

    private GaodeEntity gaodeEntity;
    private double latitude = 0;
    private double longitude = 0;

    protected static final String key_Title = "key_Title";
    protected static final String key_Content = "key_Content";
    protected static final String longitudeStr = "longitudeStr";
    protected static final String latitudeStr = "latitudeStr";
    protected static final String photoListStr = "photoListStr";
    protected static final String videoPathStr = "videoPathStr";

    @Override
    protected int getLayoutId() {
        return R.layout.emergence_layout_new;
    }

    @Override
    protected void initData() {
    }


    @Override
    protected void initView(View view) {
        setCenterTitle("应急上报");
        showBack();
        addBitmap = ((BitmapDrawable) ContextCompat.getDrawable(getContext(), R.drawable.newplay)
        ).getBitmap();

        reservoirId = getArguments().getString("reservoirId");
        String reservoir = getArguments().getString("reservoir");
        typeTv = findView(R.id.typeTv);
        resviorTv = findView(R.id.resviorTv);
        resviorTv.setText(reservoir);
        photoTitleTv = findView(R.id.photoTitleTv);
        videoIcon = findView(R.id.videoIcon);
        photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
//        resviorTv.setOnClickListener(this);
        typeTv.setOnClickListener(this);
        videoIcon.setOnClickListener(this);

        dateBeanList = UserManager.getInstance().getLocalReservoirList();

        titleEv = findView(R.id.titleEv);
        titleEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                questionTitle = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        contentEv = findView(R.id.contentEv);
        //设置EditText的显示方式为多行文本输入
        contentEv.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //文本显示的位置在EditText的最上方
        contentEv.setGravity(Gravity.TOP);
        //改变默认的单行模式
        contentEv.setSingleLine(false);
        //水平滚动设置为False
        contentEv.setHorizontallyScrolling(false);


        contentEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                questionContent = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        contentEv.setOnTouchListener(this);
        rvImagePick = findView(R.id.rv_image_pick);
        sendBtn = findView(R.id.sendBtn);
        sendBtn.setOnClickListener(this);
        rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (photoAdapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoRecycleViewAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(true)
                            .setSelected(selectedPhotos)
                            .start(getBaseActivity(), EmergenceReportFragment.this);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(getBaseActivity(), EmergenceReportFragment.this);
                }

            }
        }));

        getSP();

        photoAdapter = new PhotoRecycleViewAdapter(getBaseActivity(), selectedPhotos);
        rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        rvImagePick.setAdapter(photoAdapter);

        gaodeEntity = new GaodeEntity(getContext());
        gaodeEntity.initLocation();
        gaodeEntity.startLocation();
        gaodeEntity.setLocationListen(new OnGaodeLibraryListen.LocationListen() {
            @Override
            public void getCurrentGaodeLocation(AMapLocation aMapLocation) {
                if (TextUtils.isEmpty(SPUtils.getInstance().getString(longitudeStr, ""))) {
                    latitude = aMapLocation.getLatitude();
                    longitude = aMapLocation.getLongitude();
                }

            }
        });
    }


    /**
     * 获取数据
     */
    private void getSP() {
        questionTitle = SPUtils.getInstance().getString(key_Title, "");
        LogUtil.e("questionTitle", questionTitle + "--");

        questionContent = SPUtils.getInstance().getString(key_Content, "");
        LogUtil.e("questionContent", questionContent);

        String photoListStrFromSP = SPUtils.getInstance().getString(photoListStr, "");
        if (!TextUtils.isEmpty(photoListStrFromSP)) {
            List<String> selectedPhotosList = Arrays.asList(photoListStrFromSP.split(","));
            for (String pathStr : selectedPhotosList) {
                if (!TextUtils.isEmpty(pathStr)) {
                    File file = new File(pathStr);
                    if (file != null && file.canRead() && file.length() > 0) {
                        selectedPhotos.add(pathStr);
                    }

                }
            }
        }


        String lngStr = SPUtils.getInstance().getString(longitudeStr, "");
        String latStr = SPUtils.getInstance().getString(latitudeStr, "");
        if (!TextUtils.isEmpty(lngStr)) {
            longitude = Double.valueOf(lngStr);

        }
        if (!TextUtils.isEmpty(latStr)) {
            latitude = Double.valueOf(latStr);
        }
        videoPath = SPUtils.getInstance().getString(videoPathStr, "");

        titleEv.setText(questionTitle);
        contentEv.setText(questionContent);
        if (!TextUtils.isEmpty(videoPath)) {
            showVedio();
        }


    }

    private void putSP() {
        SPUtils.getInstance().putString(key_Title, questionTitle);
        SPUtils.getInstance().putString(key_Content, questionContent);
        SPUtils.getInstance().putString(latitudeStr, String.valueOf(latitude));
        SPUtils.getInstance().putString(longitudeStr, String.valueOf(longitude));
        StringBuilder stringBuilder = new StringBuilder();
        int size = selectedPhotos.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(selectedPhotos.get(i));
            if (i < size - 1) {
                stringBuilder.append(",");
            }
        }
        SPUtils.getInstance().putString(photoListStr, stringBuilder.toString());
        SPUtils.getInstance().putString(videoPathStr, videoPath);
    }

    private void removeSP() {
        SPUtils.getInstance().remove(key_Title);
        SPUtils.getInstance().remove(key_Content);
        SPUtils.getInstance().remove(latitudeStr);
        SPUtils.getInstance().remove(longitudeStr);
        SPUtils.getInstance().remove(photoListStr);
        SPUtils.getInstance().remove(videoPathStr);
    }


    @Override
    protected void initRequestData() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            }
            photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
        } else if (requestCode == REQUEST_VIDEO_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                // 视频路径
                videoPath = FileUtil.getPath(getBaseActivity(), uri);
                showVedio();
            }
        } else if (requestCode == REQUEST_DELETE_CODE) {
            if (TextUtils.isEmpty(videoPath)) {
                videoIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.vedio));
            }
        }


    }

    private void showVedio() {
        // 视频大小
        long videoSize = 0;
        try {
            videoSize = FileUtil.getFileSize(new File(videoPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.e("videoPath== " + videoPath + " videoSize== " + videoSize);
        //转换文件大小类型
        if (videoSize > 20 * 1024 * 1024) {
            ToastUtils.shortToast("大小超出限制，最大20MB");
            videoPath = "";
            return;
        }else if(videoSize == 0){
            videoPath = "";
            SPUtils.getInstance().remove(videoPathStr);
            ToastUtils.shortToast("视频文件不存在，请在相册中查看");
            return;
        }
        // 通过视频路径获取bitmap
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Video.Thumbnails.MICRO_KIND);
        if (bitmap != null) {
            Bitmap bmpVedio = CanvasnewBitmap.doodle(bitmap,
                    addBitmap);
            if (bitmap != null) {
                bitmap.recycle();
            }
            videoIcon.setImageBitmap(bmpVedio);
        }

    }


    /**
     * 展示水库
     *
     * @param mDateBeanList
     */
    private void showRiverDialog(List<com.tepia.main.model.detai.ReservoirBean> mDateBeanList) {

        if (mDateBeanList != null && mDateBeanList.size() > 0) {
            String[] stringItems = new String[mDateBeanList.size()];
            for (int i = 0; i < mDateBeanList.size(); i++) {
                stringItems[i] = mDateBeanList.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    resviorTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                    resviorTv.setText(stringItems[position]);
                    reservoirId = mDateBeanList.get(position).getReservoirId();
                    dialog.dismiss();


                }
            });
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
        if ((view.getId() == R.id.contentEv && canVerticalScroll(contentEv))) {
            //告诉父view，我的事件自己处理
            view.getParent().requestDisallowInterceptTouchEvent(true);
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                //告诉父view，你可以处理了
                view.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }


    @Override
    public void onClick(View view) {

        int viewID = view.getId();
        if (viewID == R.id.sendBtn) {
            questionTitle = titleEv.getText().toString();
            questionContent = contentEv.getText().toString();
            if (TextUtils.isEmpty(questionTitle)) {
                ToastUtils.shortToast("请输入" + getString(R.string.titlestr));
                return;
            }

            if (TextUtils.isEmpty(reservoirId)) {
                ToastUtils.shortToast("请选择" + getString(R.string.resviorstr));
                return;
            }
            if (TextUtils.isEmpty(questionContent)) {
                ToastUtils.shortToast("请输入事件内容");
                return;
            }
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
//                ToastUtils.shortToast("网络质量不好，以离线存储相关数据，请在有网时再上传");
                showDialog("没有连接网络，是否保存本次数据，以便有网时上传?");
                return;
            }


            mPresenter.attachView(new ReportContract.View<BaseResponse>() {

                @Override
                public Context getContext() {
                    return null;
                }

                @Override
                public void success(BaseResponse data) {
                    videoPath = "";
                    ToastUtils.shortToast("提交成功");
                    removeSP();
                    Intent intent = new Intent();
                    getBaseActivity().setResult(200, intent);
                    getBaseActivity().finish();
                }

                @Override
                public void failure(String msg) {
                    showDialog("网络质量不好，上传失败。是否保存本次数据，以便网络状况更佳时上传?");
                    LogUtil.e("EmergenceReportFragment", msg + "---");

                }
            });

            mPresenter.reportProblem(reservoirId, questionTitle, questionContent, String.valueOf(longitude),
                    String.valueOf(latitude), selectedPhotos, videoPath);


        } else if (viewID == R.id.resviorTv) {
            if (dateBeanList != null && dateBeanList.size() > 0) {
//                showRiverDialog(dateBeanList);
            }
        } else if (viewID == R.id.videoIcon) {
            if (TextUtils.isEmpty(videoPath)) {
                getVedio();
            } else {
                Intent intent = new Intent();
                intent.setClass(getBaseActivity(), PlayLocalVedioActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("urllocalvedio", videoPath);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_DELETE_CODE);

            }
        }
    }


    /**
     * 提示是否保存数据
     * @param msg
     */
    private void showDialog(String msg) {
        new AlertDialog.Builder(getContext())
                .setTitle("温馨提示")
                .setMessage(msg)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeSP();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        putSP();
                    }
                }).show();
    }


    /**
     * 还原页面
     */
    private void clear() {
        for (int i = 0; i < data.size(); i++) {

            TypeResponse typeResponse = data.get(i);
            typeResponse.setShowIcon(false);

        }

        //图片选择器还原
        if (selectedPhotos.size() > 0) {
            selectedPhotos.clear();
            photoAdapter.notifyDataSetChanged();
        }
        questionType = "";
        questionTitle = "";
        questionContent = "";
        reservoirId = "";
        titleEv.setText("");
        contentEv.setText("");

        typeTv.setText(getString(R.string.selecttype));
        typeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        resviorTv.setText(getString(R.string.selectresviorstr));
        resviorTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));

    }

    private void getVedio() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_VIDEO_CODE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        clear();

        if (dateBeanList != null) {
            dateBeanList.clear();
        }
        if (selectedPhotos != null) {
            selectedPhotos.clear();
        }
        if (data != null) {
            data.clear();
        }
        if (gaodeEntity != null) {
            gaodeEntity.closeLocation();
        }
    }

}
