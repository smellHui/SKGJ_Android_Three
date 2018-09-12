package com.tepia.main.view;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.gaodelibrary.UtilsContextOfGaode;
import com.inpor.fastmeetingcloud.receiver.HstApplication;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.TabFragmentHost;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.broadcastreceiver.WakeLockScreenReceiverOfMain;
import com.tepia.main.view.main.MainContract;
import com.tepia.main.view.main.MainPresenter;
import com.tepia.main.view.main.TabMainFragmentFactory;
import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.maintechnology.threekeypoint.ThreePointJiShuFragment;
import com.tepia.main.view.maintechnology.yunwei.YunWeiJiShuFragment;
import com.tepia.main.view.mainworker.homepage.HomeXunChaFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.setting.SettingFragment;
import com.tepia.main.view.mainworker.shangbao.ShangbaoFragment;
import com.tepia.main.view.mainworker.xuncha.XunchaFragment;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;


/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.appMain)
public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @Autowired(name = "position")
    int position = 0;

    private HomeXunChaFragment homeXunChaFragment;
    private ThreePointJiShuFragment threePointJiShuFragment;
    private YunWeiJiShuFragment yunWeiJiShuFragment;
    private ReservoirsFragment reservoirsFragment;
    private SettingFragment settingFragment;
    private ShangbaoFragment shangbaoFragment;
    private XunchaFragment xunchaFragment;


    private TabFragmentHost mTabHost;
    private String titles[];
    private static final int zero = 0;
    private static final int one = 1;
    private static final int two = 2;
    private static final int third = 3;
    private static final int four = 4;
    private static final int five = 5;
    private static final int six = 6;
    private boolean hasMeasured;
    /**
     * 锁屏相关
     */
    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;
    private WakeLockScreenReceiverOfMain WakeLockScreenReceiver = null;
    private boolean isRegisterReceiver = false;

    private String valuestr;


    // 图片
    private int mImages[] = {
            R.drawable.selector_tabbar_bus,
            R.drawable.selector_tabbar_exchange,
            R.drawable.selector_tabbar_clloction,
            R.drawable.selector_tabbar_bus,
            R.drawable.selector_tabbar_exchange,
            R.drawable.selector_tabbar_clloction,
            R.drawable.selector_tabbar_bus
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //视讯客户端初始化
        HstApplication.init(Utils.getContext());
        initViewPager();
        registerPowerReceiver();
        setNewBottom();
    }


    private void setNewBottom() {
        valuestr = getIntent().getStringExtra("key");
        titles = new String[]{
                getString(R.string.main_home),
                getString(R.string.main_xuncha),
                getString(R.string.main_shangbao),
                getString(R.string.main_yunwei),
                getString(R.string.main_threepoint),
                getString(R.string.main_reservoirs),
                getString(R.string.main_setting)

        };

        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        if ("1".equals(valuestr)) {

            //巡检责任人

            mTabHost.addTab(mTabHost.newTabSpec(titles[zero]).setIndicator(createIndicator(zero)),
                    homeXunChaFragment.getClass(), null);

            mTabHost.addTab(mTabHost.newTabSpec(titles[one]).setIndicator(createIndicator(one)),
                    xunchaFragment.getClass(), null);

            mTabHost.addTab(mTabHost.newTabSpec(titles[two]).setIndicator(createIndicator(two)),
                    shangbaoFragment.getClass(), null);
            mTabHost.addTab(mTabHost.newTabSpec(titles[five]).setIndicator(createIndicator(five)),
                    reservoirsFragment.getClass(), null);
            mTabHost.addTab(mTabHost.newTabSpec(titles[six]).setIndicator(createIndicator(six)),
                    settingFragment.getClass(), null);
        } else if ("2".equals(valuestr)) {
            //技术责任人

            mTabHost.addTab(mTabHost.newTabSpec(titles[zero]).setIndicator(createIndicator(zero)),
                    homeXunChaFragment.getClass(), null);


            mTabHost.addTab(mTabHost.newTabSpec(titles[third]).setIndicator(createIndicator(third)),
                    yunWeiJiShuFragment.getClass(), null);

            mTabHost.addTab(mTabHost.newTabSpec(titles[four]).setIndicator(createIndicator(four)),
                    threePointJiShuFragment.getClass(), null);
            mTabHost.addTab(mTabHost.newTabSpec(titles[five]).setIndicator(createIndicator(five)),
                    reservoirsFragment.getClass(), null);
            mTabHost.addTab(mTabHost.newTabSpec(titles[six]).setIndicator(createIndicator(six)),
                    settingFragment.getClass(), null);
        } else {
            //行政责任人
            mTabHost.addTab(mTabHost.newTabSpec(titles[zero]).setIndicator(createIndicator(zero)),
                    homeXunChaFragment.getClass(), null);


            mTabHost.addTab(mTabHost.newTabSpec(titles[third]).setIndicator(createIndicator(third)),
                    yunWeiJiShuFragment.getClass(), null);

            mTabHost.addTab(mTabHost.newTabSpec(titles[four]).setIndicator(createIndicator(four)),
                    threePointJiShuFragment.getClass(), null);
            mTabHost.addTab(mTabHost.newTabSpec(titles[five]).setIndicator(createIndicator(five)),
                    reservoirsFragment.getClass(), null);
            mTabHost.addTab(mTabHost.newTabSpec(titles[six]).setIndicator(createIndicator(six)),
                    settingFragment.getClass(), null);
        }


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabChanged(String tabId) {
                if (titles[zero].equals(tabId)) {
                    position = zero;
                } else if (titles[one].equals(tabId)) {
                    position = one;
                } else if (titles[two].equals(tabId)) {
                    position = two;
                } else if (titles[third].equals(tabId)) {
                    position = third;
                } else if (titles[four].equals(tabId)) {
                    position = four;
                } else if (titles[five].equals(tabId)) {
                    position = five;
                } else if (titles[six].equals(tabId)) {
                    position = six;
                }
            }
        });


    }


    /**
     * 获取每个图标布局
     *
     * @param index
     * @return
     */
    private View createIndicator(int index) {
        View view = null;

        //手工加载一个布局
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.tab_layout, null);
        ImageView imageView = view.findViewById(R.id.tabImg);
        imageView.setImageResource(mImages[index]);

        TextView textView = view.findViewById(R.id.tabTv);
        textView.setText(titles[index]);

        return view;
    }

    private AppBean appBean;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 200;

    @Override
    public void initData() {
        PgyUpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {

            }

            @Override
            public void onUpdateAvailable(String result) {
                // 将新版本信息封装到AppBean中
                appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("更新")
                        .setMessage("" + appBean.getReleaseNote())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton(
                                "确定",
                                new DialogInterface.OnClickListener() {


                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //android 6.0动态申请权限
                                        if (ContextCompat.checkSelfPermission(Utils.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                            }
                                            ActivityCompat.requestPermissions(MainActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                        } else {
                                            startDownloadTask(MainActivity.this, appBean.getDownloadURL());

                                        }
                                    }
                                }).show();

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        DictMapManager.getInstance().getDictMapEntity();
        setStatusBarTextDark();
        mTabHost.setCurrentTab(position);
    }

    /**
     * 初始化各个页面
     */
    private void initViewPager() {
        homeXunChaFragment = (HomeXunChaFragment)ARouter.getInstance().build(AppRoutePath.app_main_fragment_home_xuncha).navigation();

        threePointJiShuFragment = TabMainFragmentFactory.getInstance().getThreePointJiShuFragment();
        yunWeiJiShuFragment = TabMainFragmentFactory.getInstance().getYunWeiJiShuFragment();
        reservoirsFragment = TabMainFragmentFactory.getInstance().getReservoirsFragment();
        settingFragment = TabMainFragmentFactory.getInstance().getSettingFragment();
        shangbaoFragment = TabMainFragmentFactory.getInstance().getShangbaoFragment();
        xunchaFragment = TabMainFragmentFactory.getInstance().getXunchaFragment();

    }


    //注册锁屏监听广播
    private void registerPowerReceiver() {
        isRegisterReceiver = true;
        powerManager = (PowerManager) UtilsContextOfGaode.getContext().getSystemService(Context.POWER_SERVICE);

        if (null == wakeLock) {
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "track upload");
        }
        if (null == WakeLockScreenReceiver) {
            WakeLockScreenReceiver = new WakeLockScreenReceiverOfMain(wakeLock);
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        UtilsContextOfGaode.getContext().registerReceiver(WakeLockScreenReceiver, filter);

    }

    //解除锁屏监听广播
    private void unregisterPowerReceiver() {

        if (!isRegisterReceiver) {
            return;
        }
        if (null != WakeLockScreenReceiver) {
            UtilsContextOfGaode.getContext().unregisterReceiver(WakeLockScreenReceiver);
        }
        isRegisterReceiver = false;
    }

    private long mExitTime;

    /**
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mTabHost.getCurrentTab() == 1) {
                //当前显示地图界面
                if (MapArcgisFragment.status == 1) {
                    EventBus.getDefault().post(1);
//                    if (MapArcgisFragment.getInstance() != null) {
//                        MapArcgisFragment.getInstance().backClickFun();
//                    }
                } else {
                    backFun();
                }
            } else {
                backFun();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backFun() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.shortToast("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
//                closeTrace();
            AppManager.getInstance().exitApp();
            this.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("MainActivity", "极光推送before onresum状态getConnectionState：" + JPushInterface.getConnectionState(Utils.getContext()));
        LogUtil.e("MainActivity", "极光推送before onresum状态isPushStopped：" + JPushInterface.isPushStopped(Utils.getContext()));

        if (JPushInterface.isPushStopped(Utils.getContext()) || !JPushInterface.getConnectionState(Utils.getContext())) {
            LogUtil.e("MainActivity", "MainActivity极光推送已停止，正在重新开启");
            //极光推送
            JPushInterface.setDebugMode(true);
            JPushInterface.init(this);
            JPushInterface.resumePush(Utils.getContext());
        }
        String registId = JPushInterface.getRegistrationID(Utils.getContext());
        LogUtil.e("LoginActivity", "获取极光推送注册：" + registId);
        LogUtil.e("MainActivity", "极光推送after onresum状态getConnectionState：" + JPushInterface.getConnectionState(Utils.getContext()));
        LogUtil.e("MainActivity", "极光推送after onresum状态isPushStopped：" + JPushInterface.isPushStopped(Utils.getContext()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterPowerReceiver();
        TabMainFragmentFactory.getInstance().clearFragment();
    }


}
