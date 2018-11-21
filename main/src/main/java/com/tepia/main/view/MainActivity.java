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
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.gaodelibrary.UtilsContextOfGaode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inpor.fastmeetingcloud.base.BaseFragment;
import com.inpor.fastmeetingcloud.receiver.HstApplication;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.BadgeView;
import com.tepia.main.R;
import com.tepia.main.broadcastreceiver.WakeLockScreenReceiverOfMain;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.model.worknotification.NotFeedBackCountResponse;
import com.tepia.main.model.worknotification.WorkNotificationManager;
import com.tepia.main.view.main.MainContract;
import com.tepia.main.view.main.MainPresenter;

import com.tepia.main.view.main.map.MapArcgisFragment;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        主页
 **/
@Route(path = AppRoutePath.appMain)
public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    //    private TabFragmentHost mTabHost;
    private FragmentTabHost mTabHost;

    /**
     * 锁屏相关
     */
    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;
    private WakeLockScreenReceiverOfMain WakeLockScreenReceiver = null;
    private boolean isRegisterReceiver = false;

    /**
     * 暂时暂时身份标识 等接口 好之后 再改为动态菜单
     */
    private String valuestr;
    private BadgeView badgeView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        // TODO: 2018/10/22 视讯客户端初始化
        //视讯客户端初始化
        //HstApplication.init(Utils.getContext());
        registerPowerReceiver();
        setNewBottom();
    }


    private void setNewBottom() {

        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        if (UserManager.getInstance().getMenuList() != null) {
            TabMainFragmentFactory.getInstance().setMenuData(UserManager.getInstance().getMenuList());
        } else {
            TabMainFragmentFactory.getInstance().setMenuData("1");
        }

        ArrayList<String> titles = TabMainFragmentFactory.getInstance().getTitles();
        ArrayList<Integer> imageIds = TabMainFragmentFactory.getInstance().getImageIds();
        ArrayList<? extends BaseCommonFragment> fragments = TabMainFragmentFactory.getInstance().getMainFragments();
        for (int i = 0; i < fragments.size(); i++) {
            mTabHost.addTab(mTabHost.newTabSpec(titles.get(i)).setIndicator(createIndicator(imageIds.get(i), titles.get(i))),
                    fragments.get(i).getClass(), null);
        }


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabChanged(String tabId) {
                LogUtil.e("-------------切换fragment--------");
                updateData();
            }
        });


    }


    /**
     * 获取每个图标布局
     *
     * @param imageId
     * @param title
     * @return
     */
    private View createIndicator(Integer imageId, String title) {
        View view = null;

        //手工加载一个布局
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.tab_layout, null);
        ImageView imageView = view.findViewById(R.id.tabImg);
        imageView.setImageResource(imageId);
        TextView textView = view.findViewById(R.id.tabTv);
        textView.setText(title);
        return view;
    }

    private AppBean appBean;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 200;

    @Override
    public void initData() {
        valuestr = getIntent().getStringExtra("key");
        initTabMenu(valuestr);

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

    private void initTabMenu(String valuestr) {
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {


    }


    private void updateData() {
        UserManager.getInstance_ADMIN().getReservoirList().safeSubscribe(new LoadingSubject<ReservoirListResponse>() {
            @Override
            protected void _onNext(ReservoirListResponse response) {
                if (response.getCode() == 0) {
                    UserManager.getInstance().saveReservoirList(response.getData());

                } else {

                }
            }

            @Override
            protected void _onError(String message) {
//                ToastUtils.shortToast(message);

            }
        });
        WorkNotificationManager.getInstance().findNotFeedBackCount().safeSubscribe(new LoadingSubject<NotFeedBackCountResponse>() {
            @Override
            protected void _onNext(NotFeedBackCountResponse notFeedBackCountResponse) {

                if (notFeedBackCountResponse != null && notFeedBackCountResponse.getData() != 0) {
                    if (badgeView == null) {
                        badgeView = new BadgeView(getContext(), (View) findViewById(R.id.badgeView));
                        badgeView.setText(notFeedBackCountResponse.getData() + "");
                        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                        badgeView.setBadgeBackgroundColor(0xFFff0015);
                        badgeView.show();
                    } else {

                        badgeView.setText(notFeedBackCountResponse.getData() + "");

                    }
                } else if (badgeView != null) {
                    badgeView.setVisibility(View.GONE);
                }

            }

            @Override
            protected void _onError(String message) {

            }
        });
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
        // TODO: 2018/10/22 和视频会议连接在一起的极光推送。不要删除
        LogUtil.e("MainActivity", "极光推送before onresum状态getConnectionState：" + JPushInterface.getConnectionState(Utils.getContext()));
        LogUtil.e("MainActivity", "极光推送before onresum状态isPushStopped：" + JPushInterface.isPushStopped(Utils.getContext()));
        updateData();
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
