package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.view.main.detail.vedio.VedioFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :
  * Version :1.0
  * 功能描述 : 组中视频
 **/
public class VedioListActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_vedio_list;
    }

    @Override
    public void initView() {
        TextView nameTv = findViewById(R.id.nameTv);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VideoResponse.DataBean dataBean = (VideoResponse.DataBean) bundle.getSerializable("vedio");
            String vsnm = bundle.getString("vsnm");
            setCenterTitle(vsnm);
            showBack();
            String reservoirName = bundle.getString("name");
            nameTv.setText(reservoirName);
            initVedioDetailFragment(dataBean);

        }
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
        transaction.commit();
    }
}
