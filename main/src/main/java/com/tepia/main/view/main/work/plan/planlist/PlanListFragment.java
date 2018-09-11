package com.tepia.main.view.main.work.plan.planlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentPlanListBinding;
import com.tepia.main.model.plan.PlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joeshould
 */
public class PlanListFragment  extends MVPBaseFragment<PlanListContract.View, PlanListPresenter> implements PlanListContract.View {
    private String typeStr;

    private FragmentPlanListBinding mBinding;
    private List<PlanBean> listdata = new ArrayList<>();
    private AdapterPlanList adapterPlanList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan_list;
    }

    @Override
    protected void initData() {
        listdata.add(new PlanBean());
        listdata.add(new PlanBean());
        listdata.add(new PlanBean());
    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        typeStr = getArguments().getString("TYPEKEY");
        initListView();
    }

    private void initListView() {
        mBinding.rvPlanList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPlanList = new AdapterPlanList(R.layout.item_plan_list_view,listdata);
        mBinding.rvPlanList.setAdapter(adapterPlanList);
        adapterPlanList.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.layout_add_plan_header_view,null));
    }

    @Override
    protected void initRequestData() {

    }

    public static PlanListFragment newInstance() {
        PlanListFragment f = new PlanListFragment();
        Bundle bundle = new Bundle();
        f.setArguments(bundle);
        return f;
    }

    public static PlanListFragment newInstance(String typekey){
        PlanListFragment f = new PlanListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPEKEY", typekey);
        f.setArguments(bundle);
        return f;
    }

}
