package com.tepia.main.view.mainworker.homepage;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.just.library.AgentWeb;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentHomeXunjianBinding;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.weather.AqiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by      Intellij IDEA
 *
 * @author :       liying
 * Date    :       2018-09-05
 * Time    :       下午2:16
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 * <p>
 * 主页一 首页 巡查责任人
 **/

@Route(path = AppRoutePath.app_main_fragment_home_xuncha)
public class HomeXunChaFragment extends MVPBaseFragment<HomeXunChaContract.View, HomeXunChaPresenter> implements HomeXunChaContract.View {
    public com.github.mikephil.charting.charts.LineChart lcReservoirCapacity;
    FragmentHomeXunjianBinding mBinding;

    public HomeXunChaFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_xunjian;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        lcReservoirCapacity = (LineChart) view.findViewWithTag("lc_reservoir_capacity");
        mBinding = DataBindingUtil.bind(view);
        setCenterTitle(getString(R.string.main_home));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvWorker.setLayoutManager(layoutManager);
        AdapterWorker adapterWorker = new AdapterWorker(R.layout.lv_tab_main_worker_item, null);
        mBinding.rvWorker.setAdapter(adapterWorker);
        mBinding.rvWorker.setNestedScrollingEnabled(false);

        ArrayList<UserInfoBean> userInfoBeans = new ArrayList<>();
        userInfoBeans.add(new UserInfoBean());
        userInfoBeans.add(new UserInfoBean());
        userInfoBeans.add(new UserInfoBean());
        adapterWorker.setNewData(userInfoBeans);

        mBinding.rvFloodControlMaterialList.setNestedScrollingEnabled(false);
        mBinding.rvFloodControlMaterialList.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterFloodControlMaterialList adapterFloodControlMaterialList = new AdapterFloodControlMaterialList(R.layout.lv_flood_control_material_item, null);
        mBinding.rvFloodControlMaterialList.setAdapter(adapterFloodControlMaterialList);
        ArrayList<AqiBean> aqiBeans = new ArrayList<>();
        aqiBeans.add(new AqiBean());
        aqiBeans.add(new AqiBean());
        aqiBeans.add(new AqiBean());
        adapterFloodControlMaterialList.setNewData(aqiBeans);
        initLineChart();

        AgentWeb.with(this)
                .setAgentWebParent(mBinding.wvRealTimeWaterLevelStorageCapacity, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .setIndicatorColorWithHeight(-1, 2)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()
                .ready()
                .go("http://114.135.11.80:11008/XTZSK/HTML/3DInfo/reservoirTest.html");
    }

    private void initLineChart() {

        ChartUtils.initChart(lcReservoirCapacity);
        ChartUtils.notifyDataSetChanged(lcReservoirCapacity, getData(), ChartUtils.dayValue);
    }

    @Override
    protected void initRequestData() {

    }

    private List<Entry> getData() {
        List<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 200));
        values.add(new Entry(1, 400));
        values.add(new Entry(2, 600));
        values.add(new Entry(3, 800));
        values.add(new Entry(4, 1000));
        values.add(new Entry(5, 750));
        values.add(new Entry(6, 50));
        return values;
    }

}
