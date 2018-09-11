package com.tepia.main.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.tepia.base.utils.LogUtil;
import com.tepia.base.view.dialog.permissiondialog.PromptDialog;
import com.tepia.main.R;
import com.tepia.main.view.login.LoginActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.yanzhenjie.permission.SettingService;

import java.util.List;


/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                              *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.vm.main.activity             *
 * 创建时间:2017年11月23日11:43                               *                                                                 *                                                               *
 * 更新时间:2017年11月23日11:43                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class SplashActivity extends Activity {

    private boolean isFirst;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        isFirst = SPUtils.getInstance(UIUtils.getContext()).getBoolean("isFirst", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //获取权限
            AndPermission.with(this)
                    .requestCode(SPLASH_PERMISSION_CODE)
                    .permission(permassion)
                    .callback(listener)
                    .rationale(rationaleListener)
                    .start();


        }else {
            goNext();
        }
    }

    private void goNext(){
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(com.tepia.main.model.user.UserManager.getInstance().getToken())) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        }, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private String[] permassion = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.RECORD_AUDIO};

    private Rationale ration;

    private PromptDialog promptDialog;
    private final int SPLASH_SETTING_CODE = 100; // 去往Setting页面的返回码
    private final int SPLASH_PERMISSION_CODE = 200; // 请求权限码

    /**
     * 权限监听
     */
    public PermissionListener listener = new PermissionListener() {

        /**
         * 申请权限成功
         * @param requestCode
         * @param grantPermissions
         */
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            if (requestCode == SPLASH_PERMISSION_CODE) {
                if (AndPermission.hasPermission(SplashActivity.this,permassion)) {
                    LogUtil.e("onSucceed -->", "HavePermission");
                    goNext();


                } else {
                    LogUtil.e("onSucceed -->", "NotHavePermission");
                    // 第一种：用AndPermission默认的提示语。
                    showSettingDialog();

                }
            }

        }

        /**
         *  申请权限失败
         * @param requestCode
         * @param deniedPermissions
         */
        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            if (requestCode == SPLASH_PERMISSION_CODE) {

                if (AndPermission.hasPermission(SplashActivity.this, permassion)) {
                    // 执行操作。
                    LogUtil.e("onFailed -->", "HavePermission");
                    goNext();


                } else {
                    LogUtil.e("onFailed -->", "NotHavePermission");
                    // 是否有不再提示并拒绝的权限。
                    if (AndPermission.hasAlwaysDeniedPermission(SplashActivity.this, deniedPermissions)) {
                        LogUtil.e("onFailed -->", "allwaysNotHavePermission");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showSettingDialog();
                                    }
                                });

                            }
                        },800);

                    } else {

                        //获取权限
                        AndPermission.with(SplashActivity.this)
                                .requestCode(SPLASH_PERMISSION_CODE)
                                .permission(permassion)
                                .callback(listener)
                                .rationale(rationaleListener)
                                .start();
                    }

                }

            }
        }
    };

    /**
     * 若选择不再询问后的跳转设置页的Dialog
     */
    private void showSettingDialog() {
        final SettingService settingService = AndPermission.defineSettingDialog(SplashActivity.this, SPLASH_SETTING_CODE);
        String message = shouldHavePermission(false);
        promptDialog = new PromptDialog(SplashActivity.this)
                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                .setAnimationEnable(false)
                .setSingle(true)
                .setCanCancle(false)
                .setTitleText(getString(R.string.prompt_info))
                .setContentText(message)
                .setPositiveListener(getString(R.string.excusme_sure), getString(R.string.btn_refuse), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                        // 你的dialog点击了确定调用：
                        settingService.execute();
                    }

                    @Override
                    public void onCancleClick(PromptDialog dialog) {
                        dialog.dismiss();
                        // 你的dialog点击了取消调用：
                        settingService.cancel();
                    }
                });
        promptDialog.show();
    }

    /**
     * Rationale功能是在用户拒绝一次权限后，再次申请时检测到已经申请过一次该权限了，允许开发者弹窗说明申请权限的目的，
     * 获取用户的同意后再申请权限，避免用户勾选不再提示，导致不能再次申请权限。的监听
     */
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            ration = rationale;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showRational();
                        }
                    });

                }
            },800);
//            AndPermission.rationaleDialog(Splash.this, rationale).show();

            // 此对话框可以自定义，调用rationale.resume()就可以继续申请。
        }
    };

    /**
     * 再一次提示的Dialog
     */
    private void showRational(){
        String message = shouldHavePermission(true);
//        final Rationale rationale1 = rationale;
        promptDialog =   new PromptDialog(SplashActivity.this)
                .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                .setAnimationEnable(true)
                .setSingle(true)
                .setTitleText(getString(R.string.prompt_info))
                .setContentText(message)
                .setPositiveListener(getString(R.string.excusme_sure), getString(R.string.btn_refuse), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                        ration.resume();
                    }

                    @Override
                    public void onCancleClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                });

        promptDialog .show();
    }

    /**
     * 拼接权限判断提示结果
     *
     * @return
     */
    private String shouldHavePermission(boolean isSHowRationale) {
        StringBuilder meaasge = new StringBuilder();
//        boolean havePhone = AndPermission.hasPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE);
        boolean haveSD = AndPermission.hasPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO,Manifest.permission.ACCESS_FINE_LOCATION );
//        boolean haveLoc = AndPermission.hasPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (isSHowRationale) {
           /* if (!havePhone) {
                meaasge.append(getString(R.string.permission_phone_message) + "\n" + " \n");
            }*/
            if (!haveSD) {
                meaasge.append(getString(R.string.permission_save_message) + "\n" + " \n");
            }
           /* if (!haveLoc) {
                meaasge.append(getString(R.string.permission_location_message) + "\n");
            }*/

        } else {
           /* if (!havePhone) {
                meaasge.append(getString(R.string.permission_phone_no) + "\n" + " \n");
            }*/
            if (!haveSD) {
                meaasge.append(getString(R.string.permission_save_no) + "\n" + " \n");
            }
           /* if (!haveLoc) {
                meaasge.append(getString(R.string.permission_location_no) + "\n"  + " \n");
            }*/
            meaasge.append(getString(R.string.permission_path));
        }
        return meaasge.toString();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(promptDialog != null && promptDialog.isShowing()){
            promptDialog.dismiss();
        }
    }

    private android.os.Handler handler = new android.os.Handler();

}
