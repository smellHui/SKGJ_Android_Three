package com.tepia.main.view.maincommon.reservoirs.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
/**
  * Created by      Android studio
  *
  * @author         :       ly(from Center Of Wuhan)
  * Date            :       2018-9-18
  * Version         :       1.0
  * 功能描述         :       水库库容曲线页面
  *
 **/
public class CapacityActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_capacity;
    }

    @Override
    public void initView() {
        setCenterTitle("水库库容曲线");
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
