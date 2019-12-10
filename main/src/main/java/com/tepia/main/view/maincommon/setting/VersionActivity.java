package com.tepia.main.view.maincommon.setting;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

//import com.pgyersdk.javabean.AppBean;
//import com.pgyersdk.update.PgyUpdateManager;
//import com.pgyersdk.update.UpdateManagerListener;
import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.google.gson.Gson;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.MyEditUserInfoView;
import com.tepia.main.APPCostant;
import com.tepia.main.R;
import com.tepia.main.view.main.MainContract;
import com.tepia.main.view.main.MainPresenter;
import com.tepia.main.view.maincommon.setting.voiceassistant.SpeakActivity;
import com.tepia.main.view.update.VersionInfoResponse;

import java.io.File;

import static com.tepia.main.view.MainActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;

/**
 * 点击设置后显示页面
 * @author ly on 2018/7/17
 */
public class VersionActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements View.OnClickListener,MainContract.View{

    private MyEditUserInfoView editChangeKeyword;

    private MyEditUserInfoView suggestionMv;

    private MyEditUserInfoView curversionMv;

    private MyEditUserInfoView aboutusMv;

    private MyEditUserInfoView editziliaoMv;

    private MyEditUserInfoView remindMv;

    private CheckBox rightCheckB;
    private MyEditUserInfoView mvVersionUpdate;
//    private AppBean appBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_version;
    }

    @Override
    public void initView() {
        setCenterTitle(getString(R.string.setting_setting));
        showBack();

        editChangeKeyword = findViewById(R.id.edit_change_keyword);
        editziliaoMv = findViewById(R.id.editziliaoMv);
        remindMv = findViewById(R.id.remindMv);
        suggestionMv = findViewById(R.id.suggestionMv);
        curversionMv = findViewById(R.id.curversionMv);
        aboutusMv = findViewById(R.id.aboutusMv);
        mvVersionUpdate = findViewById(R.id.mv_version_update);
        rightCheckB = findViewById(R.id.rightCheckB);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent;
                int i = view.getId();
                if (i == R.id.editziliaoMv) {
                    intent = new Intent(VersionActivity.this, EditUserInfoActivity.class);
                    startActivity(intent);

                } else if (i == R.id.suggestionMv) {
                    intent = new Intent(VersionActivity.this, SuggestionActivity.class);
                    startActivity(intent);

                } else if (i == R.id.curversionMv) {
                } else if (i == R.id.aboutusMv) {
                    intent = new Intent(VersionActivity.this, AboutUsActivity.class);
                    startActivity(intent);
                } else if (i == R.id.edit_change_keyword) {
                    intent = new Intent(VersionActivity.this, ChangeKeyWordActivity.class);
                    startActivity(intent);
                }else if (i == R.id.mv_version_update) {
//                    checkVersionUppdate();
                    updateApp();
                }
            }
        };
        editChangeKeyword.setOnClickListener(onClickListener);
        suggestionMv.setOnClickListener(onClickListener);
        editziliaoMv.setOnClickListener(onClickListener);
        curversionMv.setOnClickListener(onClickListener);
        mvVersionUpdate.setOnClickListener(onClickListener);
        aboutusMv.setOnClickListener(onClickListener);

        initallView();
    }

    /**
     * 检查版本更新
     */
//    private void checkVersionUppdate() {
//        PgyUpdateManager.register(this, new UpdateManagerListener() {
//            @Override
//            public void onNoUpdateAvailable() {
//                PgyUpdateManager.unregister();
//                ToastUtils.shortToast("已是最新版本");
//            }
//
//            @Override
//            public void onUpdateAvailable(String result) {
//                PgyUpdateManager.unregister();
//                // 将新版本信息封装到AppBean中
//                appBean = getAppBeanFromString(result);
//                new AlertDialog.Builder(VersionActivity.this)
//                        .setTitle("更新")
//                        .setMessage(""+appBean.getReleaseNote())
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
//                                            ActivityCompat.requestPermissions(VersionActivity.this,
//                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//                                        } else {
//                                            startDownloadTask(VersionActivity.this, appBean.getDownloadURL());
//
//                                        }
//                                    }
//                                }).show();
//
//            }
//        });
//    }

    private void initallView() {
        editChangeKeyword.setLeftTitle("修改密码");
        editChangeKeyword.getRightImageV().setVisibility(View.VISIBLE);
        editChangeKeyword.getRightImageV().setImageResource(R.drawable.s_right);
        editziliaoMv.setLeftTitle(getString(R.string.setting_v_ziliao));
        editziliaoMv.getRightImageV().setVisibility(View.VISIBLE);
        editziliaoMv.getRightImageV().setImageResource(R.drawable.s_right);

        remindMv.setLeftTitle(getString(R.string.setting_v_remind));
        remindMv.getCheckBox().setVisibility(View.VISIBLE);
        remindMv.getShortLine().setVisibility(View.INVISIBLE);
        remindMv.getLongLine().setVisibility(View.VISIBLE);
        suggestionMv.setLeftTitle(getString(R.string.setting_v_suggestion));
        suggestionMv.getRightImageV().setVisibility(View.VISIBLE);
        suggestionMv.getRightImageV().setImageResource(R.drawable.s_right);

        curversionMv.setLeftTitle(getString(R.string.setting_v_curversion));
        mvVersionUpdate.setLeftTitle("版本更新");

        aboutusMv.setLeftTitle(getString(R.string.setting_v_about));
        aboutusMv.getRightImageV().setVisibility(View.VISIBLE);
        aboutusMv.getRightImageV().setImageResource(R.drawable.s_right);

        aboutusMv.getShortLine().setVisibility(View.INVISIBLE);
        aboutusMv.getLongLine().setVisibility(View.VISIBLE);
        curversionMv.getRightTextV().setText(getVersion());
        curversionMv.getRightTextV().setVisibility(View.VISIBLE);

    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Intent intent;
        if (R.id.curversionMv == viewId) {

        }else if(R.id.aboutusMv == viewId){
            intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
        }


    }

    private VersionInfoResponse mBean;
    private void updateApp() {
        DownloadBuilder builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl(APPCostant.API_APP_UPDATE + "app/bizAppInfo/checkNewVersionInfo?appId=6565488BBFC346B68DCB18A80A47BE9E")
                .request(new RequestVersionListener() {
                    @androidx.annotation.Nullable
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
                            }else {
                                ToastUtils.shortToast("当前已是最新版本！");
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
                        ToastUtils.shortToast("未检测到新版本！");
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
                Toast.makeText(VersionActivity.this, "已取消更新", Toast.LENGTH_SHORT).show();
            }
        });
        builder.executeMission(this);
    }
}
