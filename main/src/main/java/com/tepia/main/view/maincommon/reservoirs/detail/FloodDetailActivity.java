package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivityFloodDetailBinding;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.model.reserviros.FloodBeanDetailBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.view.main.question.problemlist.PhotoAdapter;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-25
  * Version :1.0
  * 功能描述 :防汛物资详情
 **/
@Route(path = AppRoutePath.app_flood_detail)
public class FloodDetailActivity extends BaseActivity {
    ActivityFloodDetailBinding activityFloodDetailBinding;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_flood_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("防汛物资详情");
        showBack();
        activityFloodDetailBinding = DataBindingUtil.bind(mRootView);
        Bundle bundle = getIntent().getExtras();
        String tempp =   getIntent().getStringExtra("floodid");
        String materialId = "";
        if(!TextUtils.isEmpty(tempp)) {
            FloodBean.DataBean dataBean = new Gson().fromJson(tempp,FloodBean.DataBean.class);
            materialId = dataBean.getId();
        }
        initRec();

        ReservirosManager.getInstance().getMaterialByMaterialId(materialId)
                .subscribe(new LoadingSubject<FloodBeanDetailBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(FloodBeanDetailBean floodBean) {
                        if (floodBean != null) {
                            if (floodBean.getCode() == 0) {
                                activityFloodDetailBinding.contentLy.setVisibility(View.VISIBLE);
                                activityFloodDetailBinding.includeEmpty.setVisibility(View.GONE);
                                FloodBeanDetailBean.DataBean dataBean = floodBean.getData();
                                if (!TextUtils.isEmpty(dataBean.getMeName())) {
                                    activityFloodDetailBinding.meNameTv.setText( dataBean.getMeName());
                                }
                                if (!TextUtils.isEmpty(dataBean.getMeTotals())) {
                                    activityFloodDetailBinding.meTotalsTv.setText(dataBean.getMeTotals());

                                }
                                if (!TextUtils.isEmpty(dataBean.getPosition())) {
                                    activityFloodDetailBinding.positionTv.setText(dataBean.getPosition());

                                }
                                if (!TextUtils.isEmpty(dataBean.getManageName())) {
                                    activityFloodDetailBinding.manageNameTv.setText(dataBean.getManageName() + "");
                                }

                                if (!TextUtils.isEmpty(dataBean.getPhoneNum())) {
                                    activityFloodDetailBinding.phoneNumTv.setText(dataBean.getPhoneNum() + "");
                                }
                                if(dataBean.getFileInfo() != null) {
                                    for (FloodBeanDetailBean.DataBean.FileInfoBean fileInfoBean : dataBean.getFileInfo()) {
                                        if (fileInfoBean != null) {
                                            selectedPhotos.add(fileInfoBean.getFilePath());
                                        }
                                    }
                                }
                            }else{
                                activityFloodDetailBinding.includeEmpty.setVisibility(View.VISIBLE);
                                activityFloodDetailBinding.contentLy.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        activityFloodDetailBinding.includeEmpty.setVisibility(View.VISIBLE);
                        activityFloodDetailBinding.contentLy.setVisibility(View.GONE);

                    }
                });
    }

    /**
     * 初始化recycleview
     */
    private void initRec(){
        photoAdapter = new PhotoAdapter(this, R.layout.activity_problem_photo_layout,selectedPhotos);
        activityFloodDetailBinding.bizRy.setLayoutManager(new GridLayoutManager(this,4));
        activityFloodDetailBinding.bizRy.setAdapter(photoAdapter);
        activityFloodDetailBinding.bizRy.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .setShowDeleteButton(false)
                    .start(FloodDetailActivity.this);

        }));

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
}
