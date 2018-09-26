package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivityFloodDetailBinding;
import com.tepia.main.model.reserviros.FloodBean;
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
        if(bundle != null && bundle.containsKey("floodid")) {
            FloodBean.DataBean dataBean = (FloodBean.DataBean) bundle.getSerializable("floodid");
            activityFloodDetailBinding.meNameTv.setText("名称：" + dataBean.getMeName());
            activityFloodDetailBinding.meTotalsTv.setText("数量：" + dataBean.getMeTotals());
            activityFloodDetailBinding.positionTv.setText("位置：" + dataBean.getPosition());
            activityFloodDetailBinding.manageNameTv.setText("管理人员：" + dataBean.getManageName()+"");
            activityFloodDetailBinding.phoneNumTv.setText("联系方式：" + dataBean.getPhoneNum()+"");
            if(dataBean.getFileInfo() != null) {
                for (FloodBean.DataBean.FileInfoBean fileInfoBean : dataBean.getFileInfo()) {
                    if (fileInfoBean != null) {
                        selectedPhotos.add(fileInfoBean.getFilePath());
                    }
                }
            }
        }
        initRec();
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
