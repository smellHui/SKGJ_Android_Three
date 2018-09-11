package com.tepia.main.view.mainworker.setting.voiceassistant;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.SPUtils;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityVoiceAssistantSettingBinding;

/**
 * 语音助手设置页面
 * Created by Joeshould on 2018/7/26.
 */

public class VoiceAssistantSettingActivity extends BaseActivity {
    ActivityVoiceAssistantSettingBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_voice_assistant_setting;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        setCenterTitle(ResUtils.getString(R.string.text_voice_assistant));
        showBack();
        if (SPUtils.getInstance().getBoolean("ISSHOWFLOATVIEW", false)){
            mBinding.cbFloatingWindow.setChecked(true);
        }else {
            mBinding.cbFloatingWindow.setChecked(false);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        mBinding.cbFloatingWindow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SPUtils.getInstance().putBoolean("ISSHOWFLOATVIEW", b);
                onResume();
            }
        });
        mBinding.loOpenVoiceAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_speak)
                        .navigation();
            }
        });
        mBinding.loVoiceAssistantReadme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_voiceassistant_readme)
                        .navigation();
            }
        });
    }

    @Override
    protected void initRequestData() {

    }
}
