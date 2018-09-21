package com.tepia.main.view.login;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.view.floatview.FloatUtil;
import com.tepia.main.R;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.MainActivity;

import cn.jpush.android.api.JPushInterface;

import static com.pgyersdk.update.UpdateManagerListener.startDownloadTask;


/**
 * 登录页
 * 邮箱 784787081@qq.com
 */
@Route(path = AppRoutePath.applogin)
public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View ,View.OnClickListener{
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 200;
    private AppBean appBean;

    private EditText usernameEt;
    private EditText psEt;
    private TextView bt_login;
    private TextView helpTv;
    private TextView foggetTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initLayout();
    }

    @Override
    public void initData() {
//        UserManager.getInstance().clearCacheAndStopPush();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SPUtils.getInstance().getBoolean("ISSHOWFLOATVIEW", false)) {
            FloatUtil.getInstance().hideFloatView(this);
        }
    }

    @Override
    protected void onDestroy() {
        PgyUpdateManager.unregister();
        super.onDestroy();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void loginSuccess() {

        ARouter.getInstance().build(AppRoutePath.appMain).navigation();
        finish();
    }

    private void initLayout(){
        usernameEt = findViewById(R.id.usernameEt);
        psEt = findViewById(R.id.psEt);
        bt_login = findViewById(R.id.bt_login);
        foggetTv = findViewById(R.id.foggetTv);
        helpTv = findViewById(R.id.helpTv);
        foggetTv.setOnClickListener(this);
        helpTv.setOnClickListener(this);

        bt_login.setOnClickListener(view -> {
            if (DoubleClickUtil.isFastDoubleClick()) {
                return ;
            }

            if (TextUtils.isEmpty(usernameEt.getText())) {
                ToastUtils.shortToast(R.string.hintstr);
                return;
            }

            if(TextUtils.isEmpty(psEt.getText())){
                ToastUtils.shortToast(R.string.hintps);
                return ;
            }

            String registId = JPushInterface.getRegistrationID(Utils.getContext());
            LogUtil.e("LoginActivity","获取极光推送注册："+registId);
            mPresenter.login(usernameEt.getText().toString(),psEt.getText().toString(),registId);
//            Intent intent = new Intent();
//            intent.setClass(this, MainActivity.class);
//            intent.putExtra("key",usernameEt.getText().toString());
//            startActivity(intent);
//            finish();

        });
//        Intent intent = new Intent();
//        intent.setClass(this, MainActivity.class);
//        intent.putExtra("key",usernameEt.getText().toString());
//        startActivity(intent);
//        finish();
    }


    /**
     * 动态权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length >= 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownloadTask(LoginActivity.this, appBean.getDownloadURL());
                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setMessage("读写内存权限被拒绝,将导致定位功能无法正常使用，需要到设置页面手动授权")
                            .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //引导用户至设置页手动授权
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", Utils.getContext().getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //引导用户手动授权，权限请求失败
                                    ToastUtils.longToast("拒绝了读写内存权限，将不能使用自动升级！！！");

                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //引导用户手动授权，权限请求失败
                            ToastUtils.longToast("拒绝了读写内存权限，将不能使用自动升级！！！");

                        }
                    }).show();
                    // Permission Denied
                }
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.helpTv) {
           ToastUtils.shortToast("在线帮助");
        }else if(v.getId() == R.id.foggetTv){
            ToastUtils.shortToast("忘记密码");

        }
    }
}
