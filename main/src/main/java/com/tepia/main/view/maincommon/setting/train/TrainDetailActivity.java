package com.tepia.main.view.maincommon.setting.train;

import android.content.Intent;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.train.TrainListResponse;

import java.io.Serializable;

/**
 * Created by      Intellij IDEA
 *  培训详情
 * @author :       wwj
 * Date    :       2018-09-28
 * Time    :       14:49
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainDetailActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_train;
    }

    @Override
    public void initView() {
        setCenterTitle("培训详情");
        showBack();
        Intent intent = getIntent();
        TrainListResponse.DataBean.ListBean item = (TrainListResponse.DataBean.ListBean)intent.getSerializableExtra("item");

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
