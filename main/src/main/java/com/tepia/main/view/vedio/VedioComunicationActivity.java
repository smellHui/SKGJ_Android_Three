package com.tepia.main.view.vedio;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.inpor.fastmeetingcloud.manager.LoginManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.permissiondialog.PromptDialog;
import com.tepia.main.R;
import com.tepia.main.model.VedioBean;
import com.tepia.main.utils.NotificationUtil;
import com.tepia.main.utils.PermissionUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.yanzhenjie.permission.SettingService;

import java.io.IOException;
import java.util.List;

/**
 * 视频自定义消息下发成功时弹出界面
 * @author ly
 * @date 2018/8/15
 */
public class VedioComunicationActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = VedioComunicationActivity.class.getName();
    public static final String media_open = "media_open";
    private TextView refusebtn;
    private TextView agreebtn;
    private TextView whoaskTv;
    private String userName,password,roomId,port,serverAddress;
    private MediaPlayer mMediaPlayer;
    private VedioBean vedioBean;
    private String[] permassion = {Manifest.permission.RECORD_AUDIO,Manifest.permission.CAMERA};
    /**
     * // 去往Setting页面的返回码
     */
    private final int SPLASH_SETTING_CODE = 100;
    private final int SPLASH_PERMISSION_CODE = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_comunication);

        refusebtn = findViewById(R.id.refusebtn);
        agreebtn = findViewById(R.id.agreebtn);
        whoaskTv = findViewById(R.id.whoaskTv);
        refusebtn.setOnClickListener(this);
        agreebtn.setOnClickListener(this);
        whoaskTv.setText("xxxx");
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            vedioBean = (VedioBean) bundle.getSerializable("vedioBeanSerial");
            if(vedioBean != null) {
                whoaskTv.setText(vedioBean.getUserName());
            }
        }
        startMedia();
        IntentFilter filter = new IntentFilter();
        filter.addAction(media_open);
        registerReceiver(broadcastReceiver,filter);
        countDownTimer.start();
    }

    /**
     * CountDownTimer 实现倒计时
     */
    private CountDownTimer countDownTimer = new CountDownTimer(30*1000, 1*1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            LogUtil.e(millisUntilFinished + "");

        }

        @Override
        public void onFinish() {
            ToastUtils.shortToast("由于长时间未接听，自动拒接");
            finish();
        }
    };



    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(media_open)){
                LogUtil.e(media_open,"收到关闭视频声音的通知");
                if(mMediaPlayer != null){
                    mMediaPlayer.stop();
                }
            }
        }
    };

    private void startMedia() {
        mMediaPlayer = MediaPlayer.create(this, getSystemDefultRingtoneUri());
        mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }

    /**
     * 获取系统默认铃声的Uri
     */
    private Uri getSystemDefultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(this,
                RingtoneManager.TYPE_RINGTONE);
    }

    @Override
    public void onClick(View view) {
        countDownTimer.cancel();
        if(view.getId() == R.id.agreebtn){
            if(vedioBean != null) {
                userName = vedioBean.getLoginCode();
                password = vedioBean.getUserCode();
                roomId = vedioBean.getRoomId();
                port = vedioBean.getPort();
                serverAddress = vedioBean.getIp();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //获取权限
                    AndPermission.with(this)
                            .requestCode(SPLASH_PERMISSION_CODE)
                            .permission(permassion)
                            .callback(listener)
                            .rationale(rationaleListener)
                            .start();


                }else {
                    openLogin(userName, password, roomId, port, serverAddress);
                }
            }

        }else if(view.getId() == R.id.refusebtn){
            finish();
        }

    }

    private void openLogin(String userName,String password,String roomId,String port,String serverAddress){
        if(!NetUtil.isNetworkConnected(this)){
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        /*LoginManager.getInstance().enterRoom(this,serverAddress,port,userName,password,roomId);
        Intent intent = new Intent(VedioComunicationActivity.this, StartTheMiddleTierActivity.class);
        intent.setAction(Constant.INTENT_APP_ACTION_ACCOUNT_PASSWORD_ROOMID);
        Bundle bundle = new Bundle();
        bundle.putString(ThirdLoginConstant.BUNDLE_USERNAME,userName);
        bundle.putString(ThirdLoginConstant.BUNDLE_PASSWORD,password);
        bundle.putString(ThirdLoginConstant.BUNDLE_SERVER_ROOMID,roomId);
        bundle.putString(ThirdLoginConstant.BUNDLE_SERVER_PORT,port);
        bundle.putString(ThirdLoginConstant.BUNDLE_SERVER_ADDRESS,serverAddress);
        intent.putExtras(bundle);
        startActivity(intent);*/
        finish();
    }

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
                if (AndPermission.hasPermission(VedioComunicationActivity.this,permassion)) {
                    LogUtil.e("onSucceed -->", "HavePermission");
                    openLogin(userName, password, roomId, port, serverAddress);

                } else {
                    LogUtil.e("onSucceed -->", "NotHavePermission");
                    // 第一种：用AndPermission默认的提示语。
                    ToastUtils.longToast("请允许相关权限");
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

                if (AndPermission.hasPermission(VedioComunicationActivity.this, permassion)) {
                    // 执行操作。
                    LogUtil.e("onFailed -->", "HavePermission");
                    openLogin(userName, password, roomId, port, serverAddress);



                } else {
                    LogUtil.e("onFailed -->", "NotHavePermission");
                    // 是否有不再提示并拒绝的权限。
                    if (AndPermission.hasAlwaysDeniedPermission(VedioComunicationActivity.this, deniedPermissions)) {
                        LogUtil.e("onFailed -->", "allwaysNotHavePermission");
                        ToastUtils.longToast("请允许相关权限");
                        showSettingDialog();

                    } else {

                        //获取权限
                        AndPermission.with(VedioComunicationActivity.this)
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
     * Rationale功能是在用户拒绝一次权限后，再次申请时检测到已经申请过一次该权限了，允许开发者弹窗说明申请权限的目的，
     * 获取用户的同意后再申请权限，避免用户勾选不再提示，导致不能再次申请权限。的监听
     */
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            ToastUtils.longToast("请允许相关权限");
            showSettingDialog();
        }
    };

    /**
     * 若选择不再询问后的跳转设置页的Dialog
     */
    private PromptDialog promptDialog;
    private void showSettingDialog() {
        final SettingService settingService = AndPermission.defineSettingDialog(VedioComunicationActivity.this, SPLASH_SETTING_CODE);
        String message = PermissionUtil.shouldHavePermission(false,permassion);
        promptDialog = new PromptDialog(VedioComunicationActivity.this)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
        }
        countDownTimer.cancel();
        unregisterReceiver(broadcastReceiver);
        NotificationUtil.clearNotification();
    }
}
