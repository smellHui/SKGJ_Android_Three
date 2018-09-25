package com.tepia.main.view.maincommon.setting;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9
  * Version :1.0
  * 功能描述 :工作职责
 **/
public class DutyActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_duty;
    }

    @Override
    public void initView() {
       setCenterTitle("工作职责");
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
