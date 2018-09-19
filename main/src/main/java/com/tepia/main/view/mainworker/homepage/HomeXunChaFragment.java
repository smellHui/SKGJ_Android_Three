package com.tepia.main.view.mainworker.homepage;


import android.databinding.DataBindingUtil;
import android.graphics.Color;
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
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentHomeXunjianBinding;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.weather.AqiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        主页一 首页 巡查责任人
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
        getRightTianqi().setVisibility(View.VISIBLE);
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

        initXunCha();

        AgentWeb.with(this)
                .setAgentWebParent(mBinding.wvRealTimeWaterLevelStorageCapacity, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .setIndicatorColorWithHeight(-1, 2)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()
                .ready()
                .go("http://114.135.11.80:11008/XTZSK/HTML/3DInfo/reservoirTest.html");
    }

    /**
     * 初始化 巡查频率 巡查统计
     */
    private void initXunCha() {

        mBinding.rtcpXunchaCount.setTextColor(Color.parseColor("#596470"));
        mBinding.rtcpXunchaCount.setTextSize(18);
        mBinding.rtcpXunchaCount.setMax(100);
        mBinding.rtcpXunchaCount.setProgress(100);
        mBinding.rtcpXunchaCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#21a1ff"));
        mBinding.rtcpXunchaCount.setText("25");

        mBinding.rtcpQuestionCount.setTextColor(Color.parseColor("#809dd2"));
        mBinding.rtcpQuestionCount.setTextSize(14);
        mBinding.rtcpQuestionCount.setMax(100);
        mBinding.rtcpQuestionCount.setProgress(75);
        mBinding.rtcpQuestionCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#809dd2"));
        mBinding.rtcpQuestionCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#e9f1fc"));
        mBinding.rtcpQuestionCount.setText("12");

        mBinding.rtcpDealedCount.setTextColor(Color.parseColor("#4acaa0"));
        mBinding.rtcpDealedCount.setTextSize(14);
        mBinding.rtcpDealedCount.setMax(100);
        mBinding.rtcpDealedCount.setProgress(75);
        mBinding.rtcpDealedCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#4acaa0"));
        mBinding.rtcpDealedCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#ddf9f0"));
        mBinding.rtcpDealedCount.setText("9");

        mBinding.rtcpNotDealCount.setTextColor(Color.parseColor("#ff8773"));
        mBinding.rtcpNotDealCount.setTextSize(14);
        mBinding.rtcpNotDealCount.setMax(100);
        mBinding.rtcpNotDealCount.setProgress(75);
        mBinding.rtcpNotDealCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#ff8773"));
        mBinding.rtcpNotDealCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#fae8e5"));
        mBinding.rtcpNotDealCount.setText("3");
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
