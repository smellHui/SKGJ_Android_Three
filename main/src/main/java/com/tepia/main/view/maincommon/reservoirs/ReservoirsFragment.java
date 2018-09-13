package com.tepia.main.view.maincommon.reservoirs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

/**
 * @author         :
 * Version         :       1.0
 * 功能描述        :  主页四 —— 水库页
 **/
@Route(path = AppRoutePath.app_main_fragment_reservoirs)
public class ReservoirsFragment extends BaseCommonFragment {


    public ReservoirsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reservoirs;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
     setCenterTitle(getString(R.string.main_reservoirs));
    }

    @Override
    protected void initRequestData() {

    }
}
