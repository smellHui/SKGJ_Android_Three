package com.tepia.main.view.mainworker.report;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityTrainEmergenceBinding;
import com.tepia.main.model.jishu.yunwei.JiShuRePortDetailResponse;
import com.tepia.main.model.report.ShangbaoManager;
import com.tepia.main.view.main.jihua.AddPlanActivity;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;
import com.tepia.main.view.mainworker.report.adapter.ImageShowAdapter;
import com.tepia.photo_picker.PhotoPreview;

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
    private String problemId;
    @Override
    public int getLayoutId() {
        return R.layout.activity_train_emergence;
    }

    @Override
    public void initView() {
        setCenterTitle("应急详情页面");
        showBack();
        addBitmap = ((BitmapDrawable) ContextCompat.getDrawable(this,R.drawable.vedio_detail)
        ).getBitmap();
        bindings = DataBindingUtil.bind(mRootView);
        initRec();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(ConfigConsts.emergence)){
            problemId = bundle.getString(ConfigConsts.emergence);
            getDetailedProblemInfoByProblemId(problemId);
        }

        if(com.tepia.main.model.user.UserManager.getInstance().isTechnology()){
            if(bundle != null && bundle.containsKey("problemStatus")){
                String problemStatus = bundle.getString("problemStatus");
                if ("4".equals(problemStatus)){
                    bindings.feedbackLy.setVisibility(View.VISIBLE);
                    bindings.showfeedbackLy.setVisibility(View.GONE);

                }else {
                    bindings.feedbackLy.setVisibility(View.GONE);
                    bindings.showfeedbackLy.setVisibility(View.VISIBLE);

                }
            }
            /*bindings.feedbackLy.setVisibility(View.VISIBLE);
            bindings.showfeedbackLy.setVisibility(View.GONE);*/
            bindings.includeBtn.sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String excuteDes = bindings.feedbackEv.getText().toString();
                    if (TextUtils.isEmpty(excuteDes)) {
                        ToastUtils.shortToast("请输入反馈意见");
                        return;
                    }
                    ShangbaoManager.getInstance().feedback(problemId,excuteDes)
                            .subscribe(new LoadingSubject<BaseResponse>(true, Utils.getContext().getString(R.string.data_loading)) {
                                @Override
                                protected void _onNext(BaseResponse operationPlanBean) {
                                    if (operationPlanBean != null) {
                                        if (operationPlanBean.getCode() == 0) {
                                            ToastUtils.shortToast("提交成功");
                                            Intent intent = new Intent();
                                            intent.putExtra("isSubmit",true);
                                            EmergenceShowDetailActivity.this.setResult(1,intent);
                                            finish();

                                        }else{
                                            ToastUtils.shortToast(operationPlanBean.getMsg());

                                        }
                                    }
                                }

                                @Override
                                protected void _onError(String message) {
                                    ToastUtils.shortToast(message);


                                }
                            });
                }
            });
        }else{
            bindings.feedbackLy.setVisibility(View.GONE);
            bindings.showfeedbackLy.setVisibility(View.VISIBLE);

        }

    }

    private void initRec(){

        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置
        bindings.peixunRec.setLayoutManager(manager);
        imageShowAdapter = new ImageShowAdapter(this,R.layout.activity_pei_xun_item,images);
        bindings.peixunRec.setAdapter(imageShowAdapter);
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
     * 查询详情
     * @param problem
     */
    private void getDetailedProblemInfoByProblemId(String problem){
        YunWeiJiShuPresenter yunWeiJiShuPresenter = new YunWeiJiShuPresenter();
        yunWeiJiShuPresenter.getDetailedProblemInfoByProblemId(problem);
        yunWeiJiShuPresenter.attachView(new YunWeiJiShuContract.View<JiShuRePortDetailResponse>() {
            @Override
            public void success(JiShuRePortDetailResponse jiShuRePortDetailResponse) {
                JiShuRePortDetailResponse.DataBean data = jiShuRePortDetailResponse.getData();
                bindings.titleTv.setText(data.getProblemTitle());
                bindings.rerserviorNameTv.setText("水库名称："+ data.getReservoirName());
                bindings.nameTv.setText("上报人："+ data.getUserName());
                bindings.timeTv.setText("上报时间："+ data.getCreateDate());
                bindings.descriptionTv.setText(data.getProblemDescription());
                if (TextUtils.isEmpty(data.getRemarks())) {
                    bindings.feedbackTv.setText(getString(R.string.setting_t_null));
                }else{
                    bindings.feedbackTv.setText(data.getRemarks());

                }
                //图片
                List<JiShuRePortDetailResponse.DataBean.ISysFileUploadsBean> imageList = data.getImages();

                if(imageList != null && imageList.size() > 0){
                    images.clear();
                    int size = imageList.size();
                    for (int i = 0; i < size; i++) {
                        images.add(imageList.get(i).getFilePath());
                    }
                    imageShowAdapter.notifyDataSetChanged();
                    imageShowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            PhotoPreview.builder()
                                    .setPhotos(images)
                                    .setShowDeleteButton(false)
                                    .setCurrentItem(position)
                                    .start(EmergenceShowDetailActivity.this);
                        }
                    });
                }

                //视频
                List<JiShuRePortDetailResponse.DataBean.ISysFileUploadsBean> vedioList = data.getVideos();
                if(vedioList != null && vedioList.size() > 0){
                    String  videoPath = vedioList.get(0).getFilePath();
                    Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Video.Thumbnails.MICRO_KIND);

                    if(bitmap != null) {
                        Bitmap bmpVedio = CanvasnewBitmap.doodle(bitmap,
                                addBitmap);
                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                        bindings.videoIcon.setImageBitmap(bmpVedio);
                    }else{
                        bindings.videoIcon.setImageBitmap(addBitmap);
                    }

                    bindings.videoIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(EmergenceShowDetailActivity.this,PlayLocalVedioActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("urllocalvedio",videoPath);
                            bundle.putString("flag","1");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("isSubmit",false);
            EmergenceShowDetailActivity.this.setResult(1,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
