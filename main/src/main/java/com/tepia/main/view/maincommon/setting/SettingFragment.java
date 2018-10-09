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
import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.BadgeView;
import com.tepia.main.R;
import com.tepia.main.common.MySettingView;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.model.worknotification.NotFeedBackCountResponse;
import com.tepia.main.model.worknotification.WorkNotificationManager;
import com.tepia.main.view.MenuItemBean;
import com.tepia.main.view.TabMainFragmentFactory;
import com.tepia.main.view.login.LoginActivity;
import com.tepia.main.view.maincommon.setting.train.TrainActivity;
import com.tepia.main.view.maincommon.setting.voiceassistant.VoiceAssistantSettingActivity;


/**
 * 设置页面
 * by ly on 2018/6/4
 */
@Route(path = AppRoutePath.app_main_fragment_mine)
public class SettingFragment extends BaseCommonFragment implements View.OnClickListener {

    private ImageView headIv;
    private TextView userTv;
    private TextView zhizeTv;
    private MySettingView personinfoMv;
    private MySettingView msgMv;
    private MySettingView setMv;
    private MySettingView mvVoiceAssistant;
    private MySettingView peixunMv;
    private MySettingView zhizeMvMv;
    private MySettingView loginOutMv;
    private EditText changeipEv;

    private Context mContext;
    private MySettingView worknotificationMv;
    private BadgeView badgeView;


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
        setCenterTitle(getString(R.string.setting_title));
        getRightTianqi().setVisibility(View.VISIBLE);
        initView();
    }

    @Override
    protected void initRequestData() {



    }



    private void updateData() {
        WorkNotificationManager.getInstance().findNotFeedBackCount().safeSubscribe(new LoadingSubject<NotFeedBackCountResponse>() {
            @Override
            protected void _onNext(NotFeedBackCountResponse notFeedBackCountResponse) {
                if (notFeedBackCountResponse != null && notFeedBackCountResponse.getData() != 0) {
                    worknotificationMv.getSecondtitleTv().setVisibility(View.VISIBLE);
                    worknotificationMv.getSecondtitleTv().setText("          ");
                    if (badgeView == null) {
                        badgeView = new BadgeView(getContext(), worknotificationMv.getSecondtitleTv());
                        badgeView.setText("" + notFeedBackCountResponse.getData());
                        badgeView.setBadgePosition(BadgeView.POSITION_CENTER);
                        badgeView.setBadgeBackgroundColor(0xFFff0015);
                        badgeView.show();
                    }else {
                        badgeView.setText("" + notFeedBackCountResponse.getData());
                    }
                }else if (badgeView != null){
                    badgeView.setVisibility(View.GONE);
                }
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == R.id.headIv) {

            intent = new Intent(getBaseActivity(), EditUserInfoActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.userTv) {
            intent = new Intent(getBaseActivity(), EditUserInfoActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.worknotificationMv) {
            if (UserManager.getInstance().getMenuList().size() == 5) {
                if (UserManager.getInstance().getMenuList().get(4).getChildren() != null && UserManager.getInstance().getMenuList().get(4).getChildren().size() != 0) {
                    MenuItemBean bean = UserManager.getInstance().getMenuList().get(4).getChildren().get(0);
                    ARouter.getInstance().build(bean.getMenuHref()).navigation();
                }
            }

        } else if (view.getId() == R.id.personinfoMv) {
            intent = new Intent(getBaseActivity(), EditUserInfoActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.zhizeMvMv) {
            intent = new Intent(getBaseActivity(), DutyActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.peixunMv) {
            intent = new Intent(getBaseActivity(), TrainActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.msgMv) {
//            ToastUtils.shortToast("待开发功能");
            ARouter.getInstance().build(AppRoutePath.app_contacts).navigation();
        } else if (view.getId() == R.id.setMv) {
            intent = new Intent(getBaseActivity(), VersionActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.mv_voice_assistant) {
            intent = new Intent(getBaseActivity(), VoiceAssistantSettingActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.loginOutMv) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity());
            builder.setMessage(R.string.exit_message);
            builder.setCancelable(true);
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


    private void initView() {

        headIv = findView(R.id.headIv);
        userTv = findView(R.id.userTv);
        zhizeTv = findView(R.id.zhizeTv);

        personinfoMv = findView(R.id.personinfoMv);

        msgMv = findView(R.id.msgMv);
        setMv = findView(R.id.setMv);
        worknotificationMv = findView(R.id.worknotificationMv);
        mvVoiceAssistant = findView(R.id.mv_voice_assistant);
        peixunMv = findView(R.id.peixunMv);
        zhizeMvMv = findView(R.id.zhizeMvMv);
        loginOutMv = findView(R.id.loginOutMv);


        headIv.setOnClickListener(SettingFragment.this);
        userTv.setOnClickListener(SettingFragment.this);
        personinfoMv.setOnClickListener(SettingFragment.this);
        msgMv.setOnClickListener(SettingFragment.this);
        setMv.setOnClickListener(SettingFragment.this);
        mvVoiceAssistant.setOnClickListener(SettingFragment.this);
        peixunMv.setOnClickListener(SettingFragment.this);
        zhizeMvMv.setOnClickListener(SettingFragment.this);
        loginOutMv.setOnClickListener(SettingFragment.this);
        worknotificationMv.setOnClickListener(SettingFragment.this);
        setItem();


    }

    private void setItem() {
        personinfoMv.setTitle(getString(R.string.personinfostr));
        personinfoMv.setIvLeft(R.drawable.s_personinfo);
        personinfoMv.setVisibility(View.GONE);
        worknotificationMv.setTitle("工作通知");
        worknotificationMv.setIvLeft(R.drawable.s_tongzhi);
        peixunMv.setTitle(getString(R.string.peixunstr));
        peixunMv.setIvLeft(R.drawable.s_peixun);
        zhizeMvMv.setTitle(getString(R.string.zhizestr));
        zhizeMvMv.setIvLeft(R.drawable.s_zhize);


        msgMv.setTitle(getString(R.string.phonestr));
        msgMv.setIvLeft(R.drawable.s_msg);
        setMv.setTitle(getString(R.string.setstr));
        setMv.setIvLeft(R.drawable.s_set);
        mvVoiceAssistant.setTitle(getString(R.string.text_voice_assistant));
        mvVoiceAssistant.setIvLeft(R.drawable.s_yuyin);
        mvVoiceAssistant.setVisibility(View.GONE);

        loginOutMv.setTitle(getString(R.string.loginout));
        loginOutMv.setIvLeft(R.drawable.s_loginout);


    }

    /**
     * 获取用户信息并保存
     */
    private void saveUserInfoBean() {
        UserManager.getInstance_ADMIN().getLoginUser().subscribe(new LoadingSubject<UserInfoBean>(false, "正在提交") {
            @Override
            protected void _onNext(UserInfoBean userInfoBean) {
                if (userInfoBean != null) {
                    if (userInfoBean.getCode() == 0) {
                        LogUtil.e("getLoginUser", "getLoginUser:成功获取用户信息------");
                        UserManager.getInstance().setUserBean(userInfoBean);
                        userTv.setText(userInfoBean.getData().getUserName());
                        zhizeTv.setText(userInfoBean.getData().getOfficeName());
                    } else {
                        ToastUtils.longToast(userInfoBean.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                UserInfoBean userInfoBean = UserManager.getInstance().getUserBean();
                if (userInfoBean != null) {
                    userTv.setText(userInfoBean.getData().getUserName());
                }
                LogUtil.e("getLoginUser:获取用户信息失败-----");


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetUtil.isNetworkConnected(getBaseActivity())) {
            saveUserInfoBean();
        }
        updateData();
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
