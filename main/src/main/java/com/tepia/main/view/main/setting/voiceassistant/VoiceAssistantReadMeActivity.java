package com.tepia.main.view.main.setting.voiceassistant;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityVoiceAssistantReadmeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 语音助手 使用说明 页面
 * @author Joeshould Created by Joeshould on 2018/8/6.
 */
@Route(path = AppRoutePath.app_voiceassistant_readme)
public class VoiceAssistantReadMeActivity extends BaseActivity {
    private ActivityVoiceAssistantReadmeBinding mBinding;
    private List<ReadMeBean> listData = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_voice_assistant_readme;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        setCenterTitle(ResUtils.getString(R.string.text_voice_assistant_readme));
        showBack();
        initListView();
    }

    private void initListView() {
        listData = new ArrayList<>();
        listData.add(new ReadMeBean("功能页面跳转","通过语音控制界面跳转","例如：\n1、打开我的待办事项\n2、我要上报等等"));
        listData.add(new ReadMeBean("闲聊","通过语音进行语音对话","例如：\n1、问：现有多少水库 答：现有123个水库\n2、问：你是谁？ 答：我是最帅的语义助手"));
        mBinding.rvReadmeList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        AdapterReadmeList adapterReadmeList = new AdapterReadmeList(R.layout.lv_item_readme_list,listData);
        mBinding.rvReadmeList.setAdapter(adapterReadmeList);
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
}
