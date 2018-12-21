package com.tepia.main.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.AppManager;
import com.tepia.main.R;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NullFragment extends BaseCommonFragment {


    public NullFragment() {
        // Required empty public constructor
    }




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_null;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView(View view) {
        setCenterTitle("空页面");
        TabMainFragmentFactory.getInstance().clearFragment();
        Button nullshowBtn = findView(R.id.nullshowBtn);
        nullshowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.getInstance().clearCacheAndStopPush();
                AppManager.getInstance().finishAll();
                TabMainFragmentFactory.getInstance().clearFragment();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    protected void initRequestData() {

    }

}
