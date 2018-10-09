package com.tepia.main.view.maintechnology.threekeypoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/9
  * Version :1.0
  * 功能描述 : 三个重点历史数据
 **/

public class HistoryDataActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_data;
    }

    @Override
    public void initView() {
        setCenterTitle("历史数据");
        showBack();
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
