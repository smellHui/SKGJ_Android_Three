package com.tepia.main.view.maincommon.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.common.MySettingView;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.login.LoginActivity;
import com.tepia.main.view.main.TabMainFragmentFactory;
import com.tepia.main.view.maincommon.setting.voiceassistant.VoiceAssistantSettingActivity;


/**
 * 设置页面
 * by ly on 2018/6/4
 */
@Route(path = AppRoutePath.app_main_fragment_mine)
public class SettingFragment extends BaseCommonFragment implements View.OnClickListener{

    private ImageView headIv;
    private TextView userTv;
    private MySettingView daibanMv;
    private MySettingView msgMv;
    private MySettingView setMv;
    private MySettingView mvVoiceAssistant;
    private TextView loginoutBtn;
    private EditText changeipEv;

    private Context mContext;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {
        mContext = getActivity();
    }

    @Override
    protected void initView(View view) {
//        setCenterTitle(getString(R.string.setting_title));
        initView();
    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId() == R.id.headIv){

            intent = new Intent(getBaseActivity(), EditUserInfoActivity.class);
            startActivity(intent);

        }else if(view.getId() == R.id.userTv){
            ToastUtils.shortToast("待开发功能");

        }else if(view.getId() == R.id.daibanMv){
            ToastUtils.shortToast("待开发功能");

        }else if(view.getId() == R.id.msgMv){
            ToastUtils.shortToast("待开发功能");

        }else if(view.getId() == R.id.setMv){
            intent = new Intent(getBaseActivity(),VersionActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.mv_voice_assistant){
            intent = new Intent(getBaseActivity(),VoiceAssistantSettingActivity.class);
            startActivity(intent);

        }else if(view.getId() == R.id.loginoutBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.exit_message);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserManager.getInstance().clearCacheAndStopPush();
                    AppManager.getInstance().finishAll();
                    TabMainFragmentFactory.getInstance().clearFragment();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getBaseActivity().finish();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();



        }
    }



    private void initView(){

        headIv = findView(R.id.headIv);
        userTv = findView(R.id.userTv);

        daibanMv = findView(R.id.daibanMv);

        msgMv = findView(R.id.msgMv);
        setMv = findView(R.id.setMv);
        mvVoiceAssistant =findView(R.id.mv_voice_assistant);
        loginoutBtn = findView(R.id.loginoutBtn);


        headIv.setOnClickListener(SettingFragment.this);
        userTv.setOnClickListener(SettingFragment.this);
        daibanMv.setOnClickListener(SettingFragment.this);
        msgMv.setOnClickListener(SettingFragment.this);
        setMv.setOnClickListener(SettingFragment.this);
        mvVoiceAssistant.setOnClickListener(SettingFragment.this);
        loginoutBtn.setOnClickListener(SettingFragment.this);
        setItem();

        // TODO: 2018/8/20 打包发布时记得屏蔽
        changeipEv = findView(R.id.changeipEv);
        /*changeipEv.setVisibility(View.VISIBLE);
        changeipEv.setText(APPCostant.API_SERVER_URL);
        changeipEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                APPCostant.API_SERVER_URL = charSequence.toString();
                LogUtil.e("ip地址："+APPCostant.API_SERVER_URL);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

    }

    private void setItem(){
        daibanMv.setTitle(getString(R.string.daibanstr));
        daibanMv.setIvLeft(R.drawable.s_daiban);
        msgMv.setTitle(getString(R.string.msgstr));
        msgMv.setIvLeft(R.drawable.s_msg);
        setMv.setTitle(getString(R.string.setstr));
        setMv.setIvLeft(R.drawable.s_set);
        mvVoiceAssistant.setTitle(getString(R.string.text_voice_assistant));
        mvVoiceAssistant.setIvLeft(R.drawable.s_yuyin);

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
                        userTv.setText(userInfoBean.getData().getUserName());
                    }else{
                        ToastUtils.longToast(userInfoBean.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                UserInfoBean userInfoBean = UserManager.getInstance().getUserBean();
                if(userInfoBean != null){
                    userTv.setText(userInfoBean.getData().getUserName());
                }
                LogUtil.e("getLoginUser:获取用户信息失败-----");


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(NetUtil.isNetworkConnected(getBaseActivity())) {
//            saveUserInfoBean();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
