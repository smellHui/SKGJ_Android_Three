package com.tepia.main.view.main.question;

import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.SPUtils;
import com.tepia.main.R;

/**
 * 事件上报页面
 * @author  by BLiYing on 2018/7/10.
 */
@Route(path = AppRoutePath.app_question)
public class QuestionActivity extends BaseActivity {

    protected static String key_Title = "key_Title";
    protected static String key_Content = "key_Content";
    private static String key_questionType = "key_questionType";
    private static String key_reservoirid = "key_reservoirid";
    private static String key_reservoirname = "key_reservoirname";
    private static String key_selectedPhotos = "key_selectedPhotos";

    @Override
    public int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    public void initView() {
        initQuestionFragment();
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

    private FragmentTransaction transaction;
    private QuestionNewFragment questionFragment;

    /**
     * 事件上报
     */
    private void initQuestionFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        questionFragment = new QuestionNewFragment();
        transaction.replace(R.id.fl_container, questionFragment);
        transaction.show(questionFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.getInstance().remove(key_Title);
        SPUtils.getInstance().remove(key_Content);
    }
}
