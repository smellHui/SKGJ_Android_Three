package com.tepia.base.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.tepia.base.AppRoutePath;
import com.tepia.base.R;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.FloatUtil;


/**
 * @author by Joeshould on 2018/5/22.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar loToolbarCommon;
    private TextView tvCenterText;
    private TextView tvLeftText;
    private TextView tvRightText;
    private long lastClick;
    public ImmersionBar mImmersionBar;
    public View mRootView;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = getLayoutInflater().inflate(this.getLayoutId(), null, false);
        super.setContentView(mRootView);


        AppManager.getInstance().addActivity(this);
        initToolBar();
        initData();
        initView();
        initListener();
        //初始化沉浸式
        initImmersionBar();
        ARouter.getInstance().inject(this);
        setStatusBarTextDark();
        if (SPUtils.getInstance().getBoolean("ISSHOWFLOATVIEW", false)) {
            FloatUtil.getInstance().addActivity(this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (SPUtils.getInstance().getBoolean("ISSHOWFLOATVIEW", false)) {
            FloatUtil.getInstance().stopHandler(this);
        }
    }

    /**
     * 将状态栏 文字 设为 暗色
     */
    public void setStatusBarTextDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        if (loToolbarCommon != null) {
            mImmersionBar.titleBar(loToolbarCommon).init();
        }
    }


    /**
     * 初始化导航栏
     */
    protected void initToolBar() {
        loToolbarCommon = findViewById(R.id.lo_toolbar_common);
        tvCenterText = findViewById(R.id.tv_center_text);
        tvLeftText = findViewById(R.id.tv_left_text);
        tvRightText = findViewById(R.id.tv_right_text);
        if (loToolbarCommon != null) {
            //将Toolbar显示到界面
            setSupportActionBar(loToolbarCommon);
        }
        if (tvCenterText != null) {
            //getTitle()的值是activity的android:lable属性值
            tvCenterText.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public TextView getRithtTv() {
        return tvRightText;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRequestData();
        if (SPUtils.getInstance().getBoolean("ISSHOWFLOATVIEW", false)) {
            FloatUtil.getInstance().showFloatView(this);
            FloatUtil.getInstance().setOnCLicekListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (FloatUtil.getInstance().isDraging()) {
                    } else {
                        if (DoubleClickUtil.isFastDoubleClick()) {
                            return;
                        }
                        ARouter.getInstance().build(AppRoutePath.app_speak)
                                .withBoolean("isAudio", true)
                                .navigation();
                    }
                }
            });
        } else {
            FloatUtil.getInstance().hideFloatView(this);
            FloatUtil.getInstance().stopHandler(this);
        }

    }

    public Toolbar getLoToolbarCommon() {
        return loToolbarCommon;
    }

    public void setLoToolbarCommon(Toolbar loToolbarCommon) {
        this.loToolbarCommon = loToolbarCommon;
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    protected abstract void initListener();

    /**
     * 联网请求数据(由子类实现)
     */
    protected abstract void initRequestData();

    public void showBack() {
        if (loToolbarCommon == null) {
            return;
        }
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        loToolbarCommon.setNavigationIcon(null);
        tvLeftText.setVisibility(View.VISIBLE);
        tvLeftText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFastClick()) {
                    finish();
                }
            }
        });

    }


    /**
     * 设置头部标题居中
     *
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
            setSupportActionBar(loToolbarCommon);
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
        } else {
            loToolbarCommon.setTitle(title);
            setSupportActionBar(loToolbarCommon);
        }
    }


    public boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }

    /**
     * 设置app不跟随系统变化
     * @return
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration newConfig = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (newConfig.fontScale != 1) {
            newConfig.fontScale = 1;
            if (Build.VERSION.SDK_INT >= 17) {
                Context configurationContext = createConfigurationContext(newConfig);
                resources = configurationContext.getResources();
                displayMetrics.scaledDensity = displayMetrics.density * newConfig.fontScale;
            } else {
                resources.updateConfiguration(newConfig, displayMetrics);
            }
        }
        return resources;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                if (v.getWindowToken() != null) {
                    hideKeyboard(v.getWindowToken());
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v     视图
     * @param event 视图事件
     * @return 返回
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        try {
            //通知父控件勿拦截本控件touch事件
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if (v instanceof EditText) {
                int[] l = {0, 0};
                v.getLocationInWindow(l);
                int left = l[0],
                        top = l[1],
                        bottom = top + v.getHeight(),
                        right = left + v.getWidth();
                // 点击EditText的事件，忽略它。
                return event.getX() <= left || event.getX() >= right
                        || event.getY() <= top || event.getY() >= bottom;
            }
        } catch (Exception e) {
            return false;
        }

        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
