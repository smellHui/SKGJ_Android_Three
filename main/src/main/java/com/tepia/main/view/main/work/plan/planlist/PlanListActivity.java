package com.tepia.main.view.main.work.plan.planlist;


import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.main.R;
import com.tepia.main.view.main.jihua.PlanItemsFragment;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 * @author Joeshould
 */
@Route(path = AppRoutePath.app_plan_list)
public class PlanListActivity extends MVPBaseActivity<PlanListContract.View, PlanListPresenter> implements PlanListContract.View {
    @Autowired(name = "TYPEKEY")
    String typekey;

    private FragmentTransaction transaction;
    private PlanItemsFragment planListFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_plan_list;
    }

    @Override
    public void initView() {
        setCenterTitle("计划列表");
        showBack();
        initFragment();
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

    private void initFragment() {
        typekey = getIntent().getStringExtra("TYPEKEY");
        transaction = getSupportFragmentManager().beginTransaction();
        planListFragment = PlanItemsFragment.newInstance(typekey);
        transaction.add(R.id.fl_container, planListFragment);
        transaction.commit();
    }
}
