package com.tepia.main.view;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.example.gaodelibrary.UtilsContextOfGaode;
import com.google.gson.Gson;
//import com.pgyersdk.javabean.AppBean;
//import com.pgyersdk.update.PgyUpdateManager;
//import com.pgyersdk.update.UpdateManagerListener;
import com.taobao.sophix.SophixManager;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.BadgeView;
import com.tepia.main.APPCostant;
import com.tepia.main.CacheConsts;
import com.tepia.main.R;
import com.tepia.main.TabFragmentHost;
import com.tepia.main.broadcastreceiver.WakeLockScreenReceiverOfMain;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.model.worknotification.NotFeedBackCountResponse;
import com.tepia.main.model.worknotification.WorkNotificationManager;
import com.tepia.main.view.main.MainContract;
import com.tepia.main.view.main.MainPresenter;
import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.update.VersionInfoResponse;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        主页
 **/
@Route(path = AppRoutePath.appMain)
public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    private TabFragmentHost mTabHost;
//    private FragmentTabHost mTabHost;

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
        //热阿里更新，请求补丁
        SophixManager.getInstance().queryAndLoadNewPatch();

        // TODO: 2018/10/22 视讯客户端初始化
        //视讯客户端初始化
//        HstApplication.init(Utils.getContext());
        registerPowerReceiver();
        setNewBottom();
        resumePush();

    }


    private void setNewBottom() {

        mTabHost = findViewById(R.id.maintabhost);
        //给mtabHos设置父容器。设置标签页对应的内容
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        if (UserManager.getInstance().getMenuList() != null) {
            TabMainFragmentFactory.getInstance().setMenuData(UserManager.getInstance().getMenuList());
        } else {
            TabMainFragmentFactory.getInstance().setMenuData1("1");
        }

        ArrayList<String> titles = TabMainFragmentFactory.getInstance().getTitles();
        ArrayList<Integer> imageIds = TabMainFragmentFactory.getInstance().getImageIds();
        ArrayList<? extends BaseCommonFragment> fragments = TabMainFragmentFactory.getInstance().getMainFragments();
        for (int i = 0; i < fragments.size(); i++) {
            mTabHost.addTab(mTabHost.newTabSpec(titles.get(i)).setIndicator(createIndicator(imageIds.get(i), titles.get(i))),
                    fragments.get(i).getClass(), null);
        }

        if (fragments.size() == 0) {
            NullFragment nullFragment = new NullFragment();
            mTabHost.addTab(mTabHost.newTabSpec("设置").setIndicator(createIndicator(R.drawable.selector_tabbar_mine, "设置")),
                    nullFragment.getClass(), null);
        }

        /*mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabChanged(String tabId) {
                LogUtil.e("-------------切换fragment--------");
            }
        });*/
        updateData();

        mTabHost.setOnTabChangedListener(tabId -> {
            resumePush();
            EventBus.getDefault().post(100);
        });

        /*UserManager.getInstance().clearCacheAndStopPush();
        AppManager.getInstance().finishAll();
        TabMainFragmentFactory.getInstance().clearFragment();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();*/
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

//    private AppBean appBean;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 200;

    @Override
    public void initData() {
        valuestr = getIntent().getStringExtra("key");
        initTabMenu(valuestr);
        updateApp();
//        PgyUpdateManager.register(this, new UpdateManagerListener() {
//            @Override
//            public void onNoUpdateAvailable() {
//                PgyUpdateManager.unregister();
//            }
//
//            @Override
//            public void onUpdateAvailable(String result) {
//                // 将新版本信息封装到AppBean中
//                PgyUpdateManager.unregister();
//                appBean = getAppBeanFromString(result);
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("更新")
//                        .setMessage("" + appBean.getReleaseNote())
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        })
//                        .setPositiveButton(
//                                "确定",
//                                new DialogInterface.OnClickListener() {
//
//
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        //android 6.0动态申请权限
//                                        if (ContextCompat.checkSelfPermission(Utils.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                                                != PackageManager.PERMISSION_GRANTED) {
//                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//                                            }
//                                            ActivityCompat.requestPermissions(MainActivity.this,
//                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//                                        } else {
//                                            startDownloadTask(MainActivity.this, appBean.getDownloadURL());
//
//                                        }
//                                    }
//                                }).show();
//
//            }
//        });
//        mPresenter.getVersionInfo("9ED843B12FCF47F6BC2ECD1DD216A0E2");
    }

    private void initTabMenu(String valuestr) {
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {


    }


    /**
     * 更新水库信息
     */
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
                LogUtil.e("水库信息更新失败");

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
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "track:upload");
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
            boolean isShowMap = SPUtils.getInstance().getBoolean(CacheConsts.haslook, false);
            if (mTabHost.getCurrentTab() == 2 && isShowMap == true) {
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
        //        updateData();
        resumePush();

    }

    /**
     * 重连极光推送
     */
    protected void resumePush() {
        // TODO: 2018/10/22 和视频会议连接在一起的极光推送。不要删除
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
        if (mTabHost != null) {
            mTabHost.removeAllViews();
        }
//        PgyUpdateManager.unregister();
        //合适的地方关闭
        AllenVersionChecker.getInstance().cancelAllMission();
    }

    private VersionInfoResponse mBean;
    private void updateApp() {
        DownloadBuilder builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl(APPCostant.API_APP_UPDATE + "app/bizAppInfo/checkNewVersionInfo?appId=6565488BBFC346B68DCB18A80A47BE9E")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(DownloadBuilder downloadBuilder, String result) {
                        VersionInfoResponse versionInfoResponse = new Gson().fromJson(result, VersionInfoResponse.class);
                        mBean = versionInfoResponse;
                        int versionCode = versionInfoResponse.getData().getAppVersionIndex();
                        PackageManager pm = getPackageManager();
                        try {
                            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
                            int localVersionCode = packageInfo.versionCode;
                            // 线上版本大于本地版本则更新
                            if (versionCode > localVersionCode) {
                                UIData uiData = UIData.create();
                                uiData.setTitle("版本更新");
                                uiData.setDownloadUrl(versionInfoResponse.getData().getAppDownLoadUrl());
                                uiData.setContent(versionInfoResponse.getData().getLevelDescpt());
                                return uiData;
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                            return null;
                        }

                        //获取包信息
//                            V2.1.1可以根据服务器返回的结果，动态在此设置是否强制更新等
//                            downloadBuilder.setForceUpdateListener(() -> {
//                                forceUpdate();
//                            });
                        return null;
//                            Toast.makeText(V2Activity.this, "request successful", Toast.LENGTH_SHORT).show();
//                            return crateUIData();
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {

                    }
                }).setApkDownloadListener(new APKDownloadListener() {
                    @Override
                    public void onDownloading(int progress) {

                    }

                    @Override
                    public void onDownloadSuccess(File file) {

                    }

                    @Override
                    public void onDownloadFail() {
                        ToastUtils.shortToast("下载失败！");
                    }

                    @Override
                    public void installApk() {
                        if (mBean != null){
                            mPresenter.uploadDownloadActionCount("6565488BBFC346B68DCB18A80A47BE9E", mBean.getData().getAppVersionIndex());
                        }
                    }
                });

//            builder.setSilentDownload(true);
        // 强制重新下载
            builder.setForceRedownload(true);
//            builder.setShowDownloadingDialog(false);
//            builder.setShowNotification(false);
//            builder.setNotificationBuilder(createCustomNotification());
//            builder.setShowDownloadFailDialog(false);
//            builder.setDirectDownload(true);
//            builder.setShowNotification(false);
//            builder.setShowDownloadingDialog(false);
//            builder.setShowDownloadFailDialog(false);

        builder.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "已取消更新", Toast.LENGTH_SHORT).show();
            }
        });
        builder.executeMission(this);
    }

}
