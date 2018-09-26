package com.tepia.main.view.maincommon.reservoirs.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivityVisitLogBinding;
import com.tepia.main.databinding.ActivityVisitLogDetailBinding;
import com.tepia.main.model.reserviros.VisitLogBean;
import com.tepia.main.model.reserviros.VisitLogDetailBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.question.problemlist.AdapterBizProblem;
import com.tepia.main.view.main.question.problemlist.PhotoAdapter;
import com.tepia.main.view.main.question.problemlist.ProblemDetailActivity;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterVisitLog;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.VisitLogContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.VisitLogPresenter;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :
  * Version :1.0
  * 功能描述 : 到访日志
 **/
public class VisitLogDetailActivity extends MVPBaseActivity<VisitLogContract.View,VisitLogPresenter> implements VisitLogContract.View<VisitLogDetailBean>{

    ActivityVisitLogDetailBinding activityVisitLogDetailBinding;
    private AdapterVisitLog adapterVisitLog;
    private String id;

    private TextView tv_reservoir_name;
    private TextView switchTv;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();




    @Override
    public int getLayoutId() {
        return R.layout.activity_visit_log_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVisitLogDetailBinding = DataBindingUtil.bind(mRootView);

        setCenterTitle("到访日志详情");
        showBack();
        tv_reservoir_name = findViewById(R.id.tv_reservoir_name);
        switchTv = findViewById(R.id.switchTv);
        switchTv.setVisibility(View.INVISIBLE);
        initRec();
        Bundle bundle = new Bundle();
        if (bundle != null) {
            id = bundle.getString(VisitLogActivity.key_visit_log);
        }
        //id = "ae4b532e8ab946a19e7436c5ea7c447b";
        mPresenter.detail(id);
    }

    @Override
    public void initView() {


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
        photoAdapter = new PhotoAdapter(getContext(), R.layout.activity_problem_photo_layout,selectedPhotos);
        activityVisitLogDetailBinding.bizRy.setLayoutManager(new GridLayoutManager(getContext(),4));
        activityVisitLogDetailBinding.bizRy.setAdapter(photoAdapter);
        activityVisitLogDetailBinding.bizRy.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .setShowDeleteButton(false)
                    .start(VisitLogDetailActivity.this);

        }));

    }




    @Override
    public void success(VisitLogDetailBean visitLogBean) {
        VisitLogDetailBean.DataBean dataBean = visitLogBean.getData();
        tv_reservoir_name.setText(dataBean.getReservoir());
        activityVisitLogDetailBinding.causeTv.setText(dataBean.getVisitCause());
        activityVisitLogDetailBinding.contentTv.setText(dataBean.getWorkContent());
        activityVisitLogDetailBinding.usernameTv.setText(dataBean.getUserName());
        selectedPhotos.clear();
        for (VisitLogDetailBean.DataBean.FileInfosBean fileInfosBean: dataBean.getFileInfos()){
            selectedPhotos.add(fileInfosBean.getFilePath());
        }
        if(selectedPhotos.size() > 0) {
            photoAdapter.notifyDataSetChanged();
        }
        PhotoPicker.builder()
                .setPhotoCount(selectedPhotos.size());
    }

    @Override
    public void failure(String msg) {
//        adapterVisitLog.setEmptyView(EmptyLayoutUtil.show(msg));

    }
}
