package com.tepia.main.view.main;


import android.Manifest;
import android.animation.Animator;
import android.annotation.TargetApi;
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
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.gaodelibrary.UtilsContextOfGaode;
import com.example.gaodelibrary.WakeLockScreenReceiver;
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
import com.tepia.main.view.WakeLockScreenReceiverOfMain;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.LiuliangFragment;
import com.tepia.main.view.main.detail.vedio.VedioFragment;
import com.tepia.main.view.main.map.MapArcgis2Fragment;
import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.main.setting.SettingFragment;
import com.tepia.main.view.main.work.WorkFragment;
import com.tepia.main.view.main.work.WorkFragmentNew;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = AppRoutePath.appMain)
public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @Autowired(name = "position")
    int position = 0;

    public WorkFragment mTabMainTaskFragment;
//    public WorkFragmentNew mTabMainTaskFragment;
    public MapArcgisFragment mapArcgisFragment;
    public SettingFragment settingFragment;

    private TabFragmentHost mTabHost;
    private String titles[];
    private static final int zero = 0;
    private static final int one = 1;
    private static final int two = 2;
    private static final int third = 3;
    private boolean hasMeasured;
    /**
     * 锁屏相关
     */
    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;
    private WakeLockScreenReceiverOfMain WakeLockScreenReceiver = null;
    private boolean isRegisterReceiver = false;


    // 图片
    private int mImages[] = {
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
//        HstApplication.initHstApplication(Utils.getContext());
        HstApplication.init(Utils.getContext());
        initViewPager();
        registerPowerReceiver();
        setNewBottom();
    }


    private void setNewBottom() {

        titles = new String[]{
                getString(R.string.work),
                getString(R.string.main_map),
                getString(R.string.setting_tepia),
                getString(R.string.work)
        };
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec(titles[zero]).setIndicator(createIndicator(zero)),
                mTabMainTaskFragment.getClass(), null);

       /* mTabHost.addTab(mTabHost.newTabSpec(titles[one]).setIndicator(createIndicator(one)),
                questionFragment.getClass(), null);*/
        mapArcgisFragment = TabMainFragmentFactory.getInstance().getMapArcgisFragment();
        mTabHost.addTab(mTabHost.newTabSpec(titles[one]).setIndicator(createIndicator(one)),
                mapArcgisFragment.getClass(), null);

        mTabHost.addTab(mTabHost.newTabSpec(titles[two]).setIndicator(createIndicator(two)),
                settingFragment.getClass(), null);
        //  2018/7/29 需要删除  //WaterLevelFragment
       /* mTabHost.addTab(mTabHost.newTabSpec(titles[3]).setIndicator(createIndicator(3)),
                LiuliangFragment.class, null);*/

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabChanged(String tabId) {
                if (titles[zero].equals(tabId)) {
                    ImageView imageView = mTabHost.getCurrentTabView().findViewById(R.id.tabImg);
                    animation(imageView);
                    position = zero;
//                    mTabHost.setCurrentTab(zero);
                } else if (titles[one].equals(tabId)) {
                    ImageView imageView = mTabHost.getCurrentTabView().findViewById(R.id.tabImg);
                    animation(imageView);
                    position = one;
//                    mTabHost.setCurrentTab(one);
                } else if (titles[two].equals(tabId)) {
                    ImageView imageView = mTabHost.getCurrentTabView().findViewById(R.id.tabImg);
                    animation(imageView);
                    position = two;
//                    mTabHost.setCurrentTab(two);
                }
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animation(ImageView imageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);
                    //进行自己的动画操作
                    final Animator animator = ViewAnimationUtils.createCircularReveal(imageView,
                            imageView.getWidth() / 2,
                            imageView.getHeight() / 2,
                            0,
                            (float) Math.hypot(imageView.getWidth(), imageView.getHeight()));
                    // Math.hypot确定圆的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            imageView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animator.setDuration(500);
                    animator.start();
                }
            });
        }

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
        mTabMainTaskFragment = TabMainFragmentFactory.getInstance().getTabMainTaskFragment();
//        mTabMainTaskFragment = TabMainFragmentFactory.getInstance().getWorkFragmentNew();
//        questionFragment = TabMainFragmentFactory.getInstance().getQuestionFragment();
        mapArcgisFragment = TabMainFragmentFactory.getInstance().getMapArcgisFragment();
        settingFragment = TabMainFragmentFactory.getInstance().getSettingFragment();

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
            if (mTabHost.getCurrentTab()==1){
                //当前显示地图界面
                if (MapArcgisFragment.status == 1) {
                    EventBus.getDefault().post(1);
//                    if (MapArcgisFragment.getInstance() != null) {
//                        MapArcgisFragment.getInstance().backClickFun();
//                    }
                } else {
                    backFun();
                }
            }else {
                backFun();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backFun(){
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
