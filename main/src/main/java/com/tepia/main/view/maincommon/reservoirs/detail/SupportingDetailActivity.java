package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivitySupportingDetailBinding;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.view.main.question.problemlist.PhotoAdapter;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    : 2018-9-25
  * Version :1.0
  * 功能描述 : 配套设施详情
 **/
public class SupportingDetailActivity extends BaseActivity {
    ActivitySupportingDetailBinding activitySupportingDetailBinding;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_supporting_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("配套设施详情");
        showBack();
        activitySupportingDetailBinding = DataBindingUtil.bind(mRootView);
        Bundle bundle = getIntent().getExtras();
        SupportingBean.DataBean dataBean = (SupportingBean.DataBean) bundle.getSerializable("supportingid");
        activitySupportingDetailBinding.nameTv.setText("名称："+dataBean.getDeName());
        activitySupportingDetailBinding.functionTv.setText("用途："+dataBean.getDeFunction());
        if (dataBean.getFileInfo() != null) {
            for (SupportingBean.DataBean.FileInfoBean fileInfoBean: dataBean.getFileInfo()){
                if(fileInfoBean != null) {
                    selectedPhotos.add(fileInfoBean.getFilePath());
                }
            }
        }

        initRec();
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
    private void initRec(){
        photoAdapter = new PhotoAdapter(this, R.layout.activity_problem_photo_layout,selectedPhotos);
        activitySupportingDetailBinding.bizRy.setLayoutManager(new GridLayoutManager(this,4));
        activitySupportingDetailBinding.bizRy.setAdapter(photoAdapter);
        activitySupportingDetailBinding.bizRy.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .setShowDeleteButton(false)
                    .start(SupportingDetailActivity.this);

        }));

    }
}
