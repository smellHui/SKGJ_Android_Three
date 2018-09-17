/*
 * The MIT License (MIT)
 *
 * Copyright 2016 by OCN.YAN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND TORT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tepia.base.mvp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.tepia.base.AppRoutePath;
import com.tepia.base.R;
import com.tepia.base.utils.ToastUtils;

import java.util.List;




/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:Fragment基类                                      *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.vm.base                        *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/

public abstract class BaseCommonFragment extends Fragment {

    Toolbar loToolbarCommon;

    TextView tvCenterText;

    TextView tvLeftText;

    TextView tvRightText;
    TextView tv_right_tianqi;

    private BaseActivity mActivity;
    /**
     *  标识fragment视图已经初始化完毕
     */
    private boolean isViewPrepared;
    /**
     * 标识已经触发过懒加载数据
     */
    private boolean hasFetchData;
    protected View mRootView;
    private long lastClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            initData();
            initToolBar(mRootView);
            initView(mRootView);

        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    private void initToolBar(View view) {
        loToolbarCommon = view.findViewById(R.id.lo_toolbar_common);
        tvCenterText = view.findViewById(R.id.tv_center_text);

        tvLeftText = view.findViewById(R.id.tv_left_text);
        tvRightText = view.findViewById(R.id.tv_right_text);
        tv_right_tianqi = view.findViewById(R.id.tv_right_tianqi);
        tv_right_tianqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(AppRoutePath.app_weather_forecast).navigation();
            }
        });
       /* if (loToolbarCommon != null) {
            ImmersionBar.setTitleBar(getBaseActivity(), loToolbarCommon);
        }*/
    }

    public boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }

    public void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        loToolbarCommon.setNavigationIcon(null);
        tvLeftText.setVisibility(View.VISIBLE);
        tvLeftText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFastClick()) {
                    mActivity.onBackPressed();
                }
            }
        });

    }




    /***
     * 设置头部标题居中
     * by ly
     * @param title
     */
    public void setCenterTitle(CharSequence title) {
        if (tvCenterText != null) {
            loToolbarCommon.setVisibility(View.VISIBLE);
            tvCenterText.setVisibility(View.VISIBLE);
            tvLeftText.setVisibility(View.INVISIBLE);
            tvCenterText.setText(title);
            //设置无图标
            tvCenterText.setCompoundDrawables(null, null, null, null);
        } else {
            loToolbarCommon.setTitle(title);
            mActivity.setSupportActionBar(loToolbarCommon);
        }
    }

    /**
     * 标题在左
     *
     * @param title
     */
    public void setLeftTitle(CharSequence title) {
        if (tvLeftText != null) {
            loToolbarCommon.setVisibility(View.VISIBLE);
            tvLeftText.setVisibility(View.VISIBLE);
            tvCenterText.setVisibility(View.INVISIBLE);
            tvLeftText.setText(title);
            //leftTitleTv.setCompoundDrawables(null, null, null, null); //设置无图标
        } else {
            loToolbarCommon.setTitle(title);
            mActivity.setSupportActionBar(loToolbarCommon);
        }
    }

    /**
     * 天气按钮
     * @return
     */
    public TextView getRightTianqi(){
        return tv_right_tianqi;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            FetchDataPrepared();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewPrepared = true;
        FetchDataPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasFetchData = false;
        isViewPrepared = false;
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }


    /**
     * 懒加载数据
     */
    private void FetchDataPrepared() {
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            initRequestData();
        }
    }

    /**
     * 布局相关的初始化(由子类实现)
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件和数据(由子类实现)
     */
    protected abstract void initData();

    /**
     * 初始化控件和数据(由子类实现)
     */
    protected abstract void initView(View view);



    /**
     * 联网请求数据(由子类实现)
     */
    protected abstract void initRequestData();

    protected <T extends View> T findView(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }

    /**
     * fragment中动态权限获取回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
                }
            }
        }
    }


}
