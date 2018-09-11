package com.tepia.main.view.mainworker.setting;

import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.MyEditUserInfoView;
import com.tepia.main.R;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.user.UserManager;

import java.util.ArrayList;

/**
 * 编辑个人信息
 * by ly on 2018-4-13
 */
public class EditUserInfoActivity extends BaseActivity {

    private MyEditUserInfoView headMv;
    private MyEditUserInfoView nameMv;
    private MyEditUserInfoView phoneMv;
    private MyEditUserInfoView emailMv;
    private MyEditUserInfoView addressMv;
    private TextView updateBtn;

    private String imagePath;
    private String nameStr;
    private String birthdayStr;
    private String phoneStr;
    private String emailStr;
    private String addrStr;
    private UserInfoBean dataBean;

    private boolean isEditing;
    private ArrayList<MyEditUserInfoView> myEditUserInfoViewList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_user_info;
    }


    @Override
    public void initData() {

        dataBean = com.tepia.main.model.user.UserManager.getInstance().getUserBean();
        if(dataBean != null) {
            addrStr = dataBean.getData().getAddress();
            emailStr = dataBean.getData().getEmail();
            nameStr = dataBean.getData().getUserName();
            phoneStr = dataBean.getData().getMobile();
        }


    }
    @Override
    public void initView() {
        setCenterTitle(getString(R.string.setting_v_ziliao));
        showBack();

        initPresent();
        getRithtTv().setText(R.string.setting_update_edit);
        getRithtTv().setTextSize(16f);
        getRithtTv().setVisibility(View.VISIBLE);
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEditing) {
                    isEditing = true;
                    setEditText(isEditing);
                }else{
                    isEditing = false;
                    setEditText(isEditing);


                }
            }
        });
        setEditText(isEditing);
        updateBtn.setVisibility(View.INVISIBLE);
        headMv.setLeftTitle(getString(R.string.setting_t_head));
        headMv.getRightImageV().setVisibility(View.VISIBLE);
        headMv.getRightHeadImageV().setVisibility(View.VISIBLE);
        headMv.getRightHeadImageV().setImageResource(R.drawable.s_head);
        nameMv.setLeftTitle(getString(R.string.setting_t_name));
        nameMv.getRightEditV().setVisibility(View.VISIBLE);
        nameMv.getRightEditV().setText(nameStr);
        phoneMv.setLeftTitle(getString(R.string.setting_t_phone));

        phoneMv.getRightEditV().setInputType(InputType.TYPE_CLASS_PHONE);
        phoneMv.getRightEditV().setVisibility(View.VISIBLE);
        phoneMv.getRightEditV().setText(phoneStr);
        emailMv.setLeftTitle(getString(R.string.setting_t_email));

        emailMv.getRightEditV().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailMv.getRightEditV().setVisibility(View.VISIBLE);
        emailMv.getRightEditV().setText(emailStr);
        emailMv.getShortLine().setVisibility(View.INVISIBLE);
        emailMv.getLongLine().setVisibility(View.VISIBLE);
        addressMv.setLeftTitle(getString(R.string.setting_t_address));
        addressMv.getRightEditV().setVisibility(View.VISIBLE);
        addressMv.getRightEditV().setText(addrStr);
    }

    private void initPresent(){
        headMv = findViewById(R.id.headMv);
        nameMv = findViewById(R.id.nameMv);
        phoneMv = findViewById(R.id.phoneMv);
        emailMv = findViewById(R.id.emailMv);
        addressMv = findViewById(R.id.addressMv);
        updateBtn = findViewById(R.id.tv_sure);
        myEditUserInfoViewList.add(nameMv);
        myEditUserInfoViewList.add(phoneMv);
        myEditUserInfoViewList.add(emailMv);
        myEditUserInfoViewList.add(addressMv);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!NetUtil.isNetworkConnected(Utils.getContext())){
                    ToastUtils.shortToast(R.string.no_network);
                    return;
                }
                onViewClicked();
            }
        });
    }
    /**
     * 设置hint
     */
    private void setTextHint(){

        if(TextUtils.isEmpty(nameStr)){
            nameMv.getRightEditV().setText("");
            nameMv.getRightEditV().setHint(R.string.setting_t_null);
        }else{
            nameMv.getRightEditV().setText(nameStr);
        }
        if(TextUtils.isEmpty(phoneStr)){
            phoneMv.getRightEditV().setText("");
            phoneMv.getRightEditV().setHint(R.string.setting_t_null);
        }else{
            phoneMv.getRightEditV().setText(phoneStr);
        }
        if(TextUtils.isEmpty(emailStr)){
            emailMv.getRightEditV().setText("");
            emailMv.getRightEditV().setHint(R.string.setting_t_null);
        }else{
            emailMv.getRightEditV().setText(emailStr);
        }
        if(TextUtils.isEmpty(addrStr)){
            addressMv.getRightEditV().setText("");
            addressMv.getRightEditV().setHint(R.string.setting_t_null);
        }else{
            addressMv.getRightEditV().setText(addrStr);
        }
    }

    /**
     * 设置编辑状态：
     */
    private void setEditText(boolean isEdit){
        if(isEdit){
            updateBtn.setVisibility(View.VISIBLE);
            getRithtTv().setText(R.string.setting_update_cancel);

            nameMv.getRightEditV().setHint(R.string.setting_t_name_e);
            for (MyEditUserInfoView myEditUserInfoView:myEditUserInfoViewList){
                setGravity(myEditUserInfoView.getRightEditV(),Gravity.LEFT|Gravity.CENTER);

            }

            if (TextUtils.isEmpty(nameStr)) {
                nameMv.getRightEditV().setHint(R.string.setting_t_name_e);
            }
            if (TextUtils.isEmpty(phoneStr)) {
                phoneMv.getRightEditV().setHint(R.string.setting_t_ph_e);
            }
            if (TextUtils.isEmpty(emailStr)) {
                emailMv.getRightEditV().setHint(R.string.setting_t_email_e);
            }
            if (TextUtils.isEmpty(addrStr)) {
                addressMv.getRightEditV().setHint(R.string.setting_t_address);
            }
        }else{
            updateBtn.setVisibility(View.GONE);
            getRithtTv().setText(R.string.setting_update_edit);
            for (MyEditUserInfoView myEditUserInfoView:myEditUserInfoViewList){
                setGravity(myEditUserInfoView.getRightEditV(),Gravity.RIGHT|Gravity.CENTER);

            }
            setTextHint();

        }

        for (MyEditUserInfoView myEditUserInfoView:myEditUserInfoViewList){
            myEditUserInfoView.getRightEditV().setFocusableInTouchMode(isEdit);
            myEditUserInfoView.getRightEditV().setFocusable(isEdit);
        }



    }

    private void setGravity(EditText editText,int gravity){
        editText.setGravity(gravity);
    }





    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    public void onViewClicked() {
        String rightName = nameMv.getRightEditV().getText().toString();
        String rightphone = phoneMv.getRightEditV().getText().toString();
        String email = emailMv.getRightEditV().getText().toString();
        String address = addressMv.getRightEditV().getText().toString();
        if(TextUtils.isEmpty(rightName)){
            ToastUtils.shortToast("用户名不能为空");
            return;
        }
        com.tepia.main.model.user.UserManager.getInstance_ADMIN().updateSysUser(rightName,email,rightphone,address)
                .subscribe(new LoadingSubject<BaseResponse>(true,"正在提交") {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        if(baseResponse != null){
                            if (baseResponse.getCode() == 0) {
                                LogUtil.e("updateSysUser","getLoginUser:成功变更用户信息------");
                                isEditing = false;
//                                setEditText(isEditing);
                                updateBtn.setVisibility(View.INVISIBLE);
                                ToastUtils.longToast(R.string.setting_update_success);
                                saveUserInfoBean();

                            }else{
                                ToastUtils.longToast(baseResponse.getMsg());

                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e("updateSysUser:更新用户信息失败-----");
                        ToastUtils.longToast(R.string.setting_update_failed);

                    }
                });



    }

    /**
     * 获取用户信息并保存
     */
    private void saveUserInfoBean(){
        UserManager.getInstance_ADMIN().getLoginUser().subscribe(new LoadingSubject<UserInfoBean>(false,"正在提交") {
            @Override
            protected void _onNext(UserInfoBean userInfoBean) {
                if(userInfoBean != null){
                    if (userInfoBean.getCode() == 0) {
                        LogUtil.e("getLoginUser","getLoginUser:成功获取用户信息------");
                        UserManager.getInstance().setUserBean(userInfoBean);
                        dataBean = com.tepia.main.model.user.UserManager.getInstance().getUserBean();
                        if(dataBean != null) {
                            addrStr = dataBean.getData().getAddress();
                            emailStr = dataBean.getData().getEmail();
                            nameStr = dataBean.getData().getUserName();
                            phoneStr = dataBean.getData().getMobile();
                            setEditText(false);
                        }
                    }else{
                        ToastUtils.longToast(userInfoBean.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                LogUtil.e("getLoginUser:获取用户信息失败-----");


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myEditUserInfoViewList != null) {
            myEditUserInfoViewList.clear();
        }
    }
}
