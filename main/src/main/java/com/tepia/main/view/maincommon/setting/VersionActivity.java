package com.tepia.main.view.maincommon.setting;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;

import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.MyEditUserInfoView;
import com.tepia.main.R;

import static com.tepia.main.view.MainActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;

/**
 * 点击设置后显示页面
 * @author ly on 2018/7/17
 */
public class VersionActivity extends BaseActivity implements View.OnClickListener{

    private MyEditUserInfoView editChangeKeyword;

    private MyEditUserInfoView suggestionMv;

    private MyEditUserInfoView curversionMv;

    private MyEditUserInfoView aboutusMv;

    private MyEditUserInfoView editziliaoMv;

    private MyEditUserInfoView remindMv;

    private CheckBox rightCheckB;
    private MyEditUserInfoView mvVersionUpdate;
    private AppBean appBean;

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
                    checkVersionUppdate();
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
    private void checkVersionUppdate() {
        PgyUpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {
                ToastUtils.shortToast("已是最新版本");
            }

            @Override
            public void onUpdateAvailable(String result) {
                // 将新版本信息封装到AppBean中
                appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(VersionActivity.this)
                        .setTitle("更新")
                        .setMessage(""+appBean.getReleaseNote())
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
                                            ActivityCompat.requestPermissions(VersionActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                        } else {
                                            startDownloadTask(VersionActivity.this, appBean.getDownloadURL());

                                        }
                                    }
                                }).show();

            }
        });
    }

    private void initallView() {
        editChangeKeyword.setLeftTitle("修改密码");
        editChangeKeyword.getRightImageV().setVisibility(View.VISIBLE);
        editziliaoMv.setLeftTitle(getString(R.string.setting_v_ziliao));
        editziliaoMv.getRightImageV().setVisibility(View.VISIBLE);
        remindMv.setLeftTitle(getString(R.string.setting_v_remind));
        remindMv.getCheckBox().setVisibility(View.VISIBLE);
        remindMv.getShortLine().setVisibility(View.INVISIBLE);
        remindMv.getLongLine().setVisibility(View.VISIBLE);
        suggestionMv.setLeftTitle(getString(R.string.setting_v_suggestion));
        suggestionMv.getRightImageV().setVisibility(View.VISIBLE);
        curversionMv.setLeftTitle(getString(R.string.setting_v_curversion));
        mvVersionUpdate.setLeftTitle("版本更新");

        aboutusMv.setLeftTitle(getString(R.string.setting_v_about));
        aboutusMv.getRightImageV().setVisibility(View.VISIBLE);
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
}
