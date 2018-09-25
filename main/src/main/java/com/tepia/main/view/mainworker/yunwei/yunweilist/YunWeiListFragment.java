package com.tepia.main.view.mainworker.yunwei.yunweilist;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.main.R;
import com.tepia.main.databinding.FragemntStartYunweiBinding;
import com.tepia.main.databinding.FragemntYunweiListBinding;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        运维记录
 **/

public class YunWeiListFragment extends MVPBaseFragment<YunWeiListContract.View, YunWeiListPresenter> implements YunWeiListContract.View {
    private FragemntYunweiListBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_yunwei_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
       mBinding =  DataBindingUtil.bind(view);
    }

    @Override
    protected void initRequestData() {

    }
}
