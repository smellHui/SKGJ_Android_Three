package com.tepia.main.view.mainworker.xuncha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class XunchaFragment extends BaseCommonFragment {


    public XunchaFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xuncha;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_xuncha));
    }

    @Override
    protected void initRequestData() {

    }

}
