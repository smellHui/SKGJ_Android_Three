package com.tepia.main.view.mainworker.report;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leon.lfilepickerlibrary.utils.Constant;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.feedback.FeedbackFormView;
import com.tepia.main.common.feedback.SpacesItemDecoration;
import com.tepia.main.common.feedback.WrapLayoutManager;
import com.tepia.main.databinding.ActivityTrainEmergenceBinding;
import com.tepia.main.model.jishu.feedback.FeedbackListResponse;
import com.tepia.main.model.jishu.yunwei.JiShuRePortDetailResponse;
import com.tepia.main.model.report.ShangbaoManager;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;
import com.tepia.main.view.mainworker.report.Wrap.FeedbackEvent;
import com.tepia.main.view.mainworker.report.adapter.FeedbackListAdapter;
import com.tepia.main.view.mainworker.report.adapter.ImageShowAdapter;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-9-30
 * Version :1.0
 * 功能描述 : 应急详情页面展示
 **/
public class EmergenceShowDetailActivity extends BaseActivity {
    private ArrayList<String> images = new ArrayList<>();
    private Bitmap addBitmap;

    ActivityTrainEmergenceBinding bindings;
    private ImageShowAdapter imageShowAdapter;
    private FeedbackListAdapter feedbackListAdapter;
    private String problemId;
    private String problemStatus;//  4是待反馈，5就是已完结

    @Override
    public int getLayoutId() {
        return R.layout.activity_train_emergence;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setCenterTitle("应急详情页面");
        showBack();
        addBitmap = ((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.vedio_detail)
        ).getBitmap();
        bindings = DataBindingUtil.bind(mRootView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            problemId = bundle.getString(ConfigConsts.emergence);
            problemStatus = bundle.getString("problemStatus");
        }

        feedbackListAdapter = new FeedbackListAdapter();
        addHeadView();
        if (CollectionsUtil.isNotEmpty(problemStatus) && problemStatus.equals("4")) {
            addFooterView();
        }
        bindings.rv.setLayoutManager(new WrapLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bindings.rv.addItemDecoration(new SpacesItemDecoration(2));
        bindings.rv.setAdapter(feedbackListAdapter);

        getDetailedProblemInfoByProblemId(problemId);
        getFeedbackList(problemId);

    }

    private RecyclerView peixunRec;
    private TextView titleTv;
    private TextView rerserviorNameTv;
    private TextView nameTv;
    private TextView timeTv;
    private TextView descriptionTv;
    private ImageView videoIcon;
    private LinearLayout vedioLy;

    private void addHeadView() {
        View headView = LayoutInflater.from(getBaseContext()).inflate(R.layout.view_feenback_head, null);
        titleTv = headView.findViewById(R.id.titleTv);
        rerserviorNameTv = headView.findViewById(R.id.rerserviorNameTv);
        nameTv = headView.findViewById(R.id.nameTv);
        timeTv = headView.findViewById(R.id.timeTv);
        descriptionTv = headView.findViewById(R.id.descriptionTv);
        videoIcon = headView.findViewById(R.id.videoIcon);
        vedioLy = headView.findViewById(R.id.vedioLy);
        peixunRec = headView.findViewById(R.id.peixunRec);
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置
        peixunRec.setLayoutManager(manager);
        imageShowAdapter = new ImageShowAdapter(this, R.layout.activity_pei_xun_item, images);
        peixunRec.setAdapter(imageShowAdapter);
        feedbackListAdapter.addHeaderView(headView);
    }

    private FeedbackFormView feedbackFormView;

    private void addFooterView() {
        View footer = LayoutInflater.from(getBaseContext()).inflate(R.layout.view_feed_back_footer, null);
        feedbackFormView = footer.findViewById(R.id.view_feed_back);
        feedbackFormView.setUploadFeedbackListener((feedbackType, msg, selectPhotos, filePaths) -> {
            SimpleLoadDialog simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), "正在提交...", true);
            simpleLoadDialog.show();
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            if (CollectionsUtil.isNotEmpty(selectPhotos)) {
                Tiny.getInstance().source(selectPhotos.toArray(new String[selectPhotos.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                    @Override
                    public void callback(boolean isSuccess, String[] outfile) {
                        if (isSuccess) {
                            ArrayList<String> tempslist = new ArrayList<>();
                            tempslist.addAll(selectPhotos);
                            uploadFeedBack(feedbackType, msg, tempslist, filePaths);
                        } else {
                            ToastUtils.shortToast("图片压缩失败");
                        }
                        simpleLoadDialog.dismiss();
                    }
                });
            } else {
                uploadFeedBack(feedbackType, msg, selectPhotos, filePaths);
            }
        });
        feedbackListAdapter.addFooterView(footer);
    }

    private void uploadFeedBack(String feedbackType, String msg, List<String> imgPaths, List<String> filePaths) {
        ShangbaoManager.getInstance().uploadFeedback(problemId, feedbackType, msg, imgPaths, filePaths)
                .subscribe(new LoadingSubject<BaseResponse>(true, Utils.getContext().getString(R.string.data_loading)) {

                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        EventBus.getDefault().post(new FeedbackEvent());
                        finish();
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    /**
     * 查询详情
     *
     * @param problem
     */
    private void getDetailedProblemInfoByProblemId(String problem) {
        YunWeiJiShuPresenter yunWeiJiShuPresenter = new YunWeiJiShuPresenter();
        yunWeiJiShuPresenter.getDetailedProblemInfoByProblemId(problem);
        yunWeiJiShuPresenter.attachView(new YunWeiJiShuContract.View<JiShuRePortDetailResponse>() {
            @Override
            public void success(JiShuRePortDetailResponse jiShuRePortDetailResponse) {
                JiShuRePortDetailResponse.DataBean data = jiShuRePortDetailResponse.getData();
                titleTv.setText(data.getProblemTitle());
                rerserviorNameTv.setText("水库名称：" + data.getReservoirName());
                String username = data.getUserName();
                if (TextUtils.isEmpty(username)) {
                    username = getString(R.string.setting_t_null);
                }
                nameTv.setText("上报人：" + username);
                timeTv.setText("上报时间：" + data.getCreateDate());
                descriptionTv.setText(data.getProblemDescription());

                //图片
                List<JiShuRePortDetailResponse.DataBean.ISysFileUploadsBean> imageList = data.getImages();

                if (imageList != null && imageList.size() > 0) {
                    images.clear();
                    int size = imageList.size();
                    for (int i = 0; i < size; i++) {
                        images.add(imageList.get(i).getFilePath());
                    }
                    imageShowAdapter.notifyDataSetChanged();
                    imageShowAdapter.setOnItemClickListener((adapter, view, position) -> PhotoPreview.builder()
                            .setPhotos(images)
                            .setShowDeleteButton(false)
                            .setCurrentItem(position)
                            .start(EmergenceShowDetailActivity.this));
                }

                //视频
                List<JiShuRePortDetailResponse.DataBean.ISysFileUploadsBean> vedioList = data.getVideos();
                if (vedioList != null && vedioList.size() > 0) {
                    String videoPath = vedioList.get(0).getFilePath();
                    Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Video.Thumbnails.MICRO_KIND);

                    if (bitmap != null) {
                        Bitmap bmpVedio = CanvasnewBitmap.doodle(bitmap,
                                addBitmap);
                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                        videoIcon.setImageBitmap(bmpVedio);
                    } else {
                        videoIcon.setImageBitmap(addBitmap);

                    }

                    videoIcon.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        intent.setClass(EmergenceShowDetailActivity.this, PlayLocalVedioActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("urllocalvedio", videoPath);
                        bundle.putString("flag", "1");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    });
                } else {
//                    bindings.videoIcon.
                    vedioLy.setVisibility(View.GONE);
                }


            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    private void getFeedbackList(String problem) {
        YunWeiJiShuPresenter yunWeiJiShuPresenter = new YunWeiJiShuPresenter();
        yunWeiJiShuPresenter.getDetailedProblemFeedbackByProblemId(problem);
        yunWeiJiShuPresenter.attachView(new YunWeiJiShuContract.View<FeedbackListResponse>() {
            @Override
            public Context getContext() {
                return null;
            }

            @Override
            public void success(FeedbackListResponse feedbackListResponse) {
                feedbackListAdapter.setNewData(feedbackListResponse.getData());
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }
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
                feedbackFormView.setPhotoImgs(photos);
            }
        } else if (resultCode == RESULT_OK && (requestCode == 100)) {
            if (data != null) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                feedbackFormView.setFilePaths(list);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("isSubmit", false);
            EmergenceShowDetailActivity.this.setResult(1, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
