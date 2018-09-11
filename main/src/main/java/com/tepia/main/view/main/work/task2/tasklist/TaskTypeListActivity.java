package com.tepia.main.view.main.work.task2.tasklist;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;

/**
 * @author Joeshould
 */
@Route(path = AppRoutePath.app_task_list_2)
public class TaskTypeListActivity extends BaseActivity {
    @Autowired(name = "TYPEKEY")
    String typekey;
    @Autowired(name = "SearchKey")
    String SearchKey;
    private FragmentTransaction transaction;
    private TaskTypeFragment taskTypeFragment;
    public static int pagerCount = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zonglan;
    }

    @Override
    public void initView() {


    }


    @Override
    public void initData() {
//        initFragment();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        initFragment();
    }


    private void initFragment() {

        typekey = getIntent().getStringExtra("TYPEKEY");
        transaction = getSupportFragmentManager().beginTransaction();
        taskTypeFragment = TaskTypeFragment.newInstance(typekey, pagerCount);
        transaction.replace(R.id.fl_container, taskTypeFragment);
        transaction.show(taskTypeFragment);
        transaction.commitAllowingStateLoss();
        if ("全部".equals(typekey) && !TextUtils.isEmpty(SearchKey)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String temp = SearchKey.replace("搜索", "");
                    temp = temp.replace("工单", "");
                    temp = temp.replace("任务", "");
                    temp = temp.replace("。", "");
                    if (!TextUtils.isEmpty(temp)) {
                        taskTypeFragment.taskSearch(temp, true);
                    }

                }
            }, 500);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pagerCount = 0;
        finish();
    }
}
