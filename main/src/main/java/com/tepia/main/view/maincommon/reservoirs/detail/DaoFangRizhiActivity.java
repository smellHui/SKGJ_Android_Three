package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.DaoFangContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.DaoFangPresenter;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :
  * Version :1.0
  * 功能描述 : 到访日志
 **/
public class DaoFangRizhiActivity extends MVPBaseActivity<DaoFangContract.View,DaoFangPresenter> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_dao_fang_rizhi;
    }

    @Override
    public void initView() {
        setCenterTitle("到访日志");
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
