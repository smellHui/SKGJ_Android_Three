package com.tepia.main.view.mainworker.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.APPCostant;
import com.tepia.main.R;
import com.tepia.main.model.user.UserManager;

/**
 * 密码修改
 * @author ly on 2018/7/29
 */
@Route(path = AppRoutePath.app_password)
public class ChangeKeyWordActivity extends BaseActivity {


    private LinearLayout loTitlebar;

    private ImageView ivTitlebarBack;

    private TextView tvTitlebarTitle;

    private EditText etOldPassword;

    private EditText etNewPassword;

    private EditText etNewPasswordAgain;

    private TextView tvSure;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_key_word;
    }

    @Override
    public void initView() {
        setCenterTitle("修改密码");
        showBack();
        etOldPassword = findViewById(R.id.et_old_password);
        etNewPassword = findViewById(R.id.et_new_password);
        etNewPasswordAgain = findViewById(R.id.et_new_password_again);
        tvSure = findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = etNewPassword.getText().toString();
                String newPasswordAgain = etNewPasswordAgain.getText().toString();
                if(!NetUtil.isNetworkConnected(Utils.getContext())){
                    ToastUtils.shortToast(R.string.no_network);
                    return;
                }
                if (TextUtils.isEmpty(newPassword)) {
                    ToastUtils.shortToast("请输入新密码");
                    return;
                }
                if (TextUtils.isEmpty(newPasswordAgain)) {
                    ToastUtils.shortToast("请再次输入原新密码");
                    return;
                }
                if (newPassword != null && !newPassword.equals(newPasswordAgain)) {
                    ToastUtils.shortToast("两次输入不一样");
                    return;
                }
                updateps(newPassword,newPasswordAgain);
            }
        });


    }

    @Override
    public void initData() {


    }

    /**
     * 修改密码接口
     * @param newPassword
     * @param newPasswordAgain
     */
    private void updateps(String newPassword,String newPasswordAgain){
        UserManager.getInstance_ADMIN().updatePwd(newPassword,newPasswordAgain).subscribe(new LoadingSubject<BaseResponse>(true,"正在提交") {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                if(baseResponse != null){
                    if (baseResponse.getCode() == 0) {
                        ToastUtils.longToast("密码修改成功");
                        finish();
                    }else{
                        ToastUtils.longToast(baseResponse.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.longToast("密码修改失败");

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
