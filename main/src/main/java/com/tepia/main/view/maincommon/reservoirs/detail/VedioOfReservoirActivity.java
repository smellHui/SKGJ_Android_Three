package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.view.main.detail.vedio.VedioFragment;
/**
  * Created by      Android studio
  *
  * @author         :       ly(from Center Of Wuhan)
  * Date            :       2018-9-18
  * Version         :       1.0
  * 功能描述         :       水库视频
 **/
public class VedioOfReservoirActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_vedio_of_reservoirs;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("视频列表");
        showBack();
        VideoResponse.DataBean dataBean = new VideoResponse.DataBean();
        dataBean.setAccessType("1");
        initVedioDetailFragment(dataBean);
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

    private void initVedioDetailFragment(VideoResponse.DataBean dataBean) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        VedioFragment detailFragment = VedioFragment.newInstance(dataBean);
        transaction.replace(R.id.fl_container, detailFragment);
        transaction.show(detailFragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}
