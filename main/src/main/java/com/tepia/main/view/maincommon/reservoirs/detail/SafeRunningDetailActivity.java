package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityItemSafeJiandingBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.reserviros.BizkeyBean;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.model.reserviros.SafeRunningBean;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterSafeRunningDetailReservoirs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    : 安全运行管理情况下鉴定详情
  * Version :1.0
  * 功能描述 :
 **/
public class SafeRunningDetailActivity extends BaseActivity {

    private LinearLayout officeLy;
    private LinearLayout pdfLy;
    private AdapterSafeRunningDetailReservoirs adapterSafeRunningDetailReservoirs;
    private List<BizkeyBean.DataBean> myReservoirsItemBeanList = new ArrayList<>();
    private ActivityItemSafeJiandingBinding activityItemSafeJiandingBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_item_safe_jianding;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("安全运行详情");
        showBack();
        SafeRunningBean.DataBean dataBean = ( SafeRunningBean.DataBean ) getIntent().getSerializableExtra("saferunning");
        String id = dataBean.getId();
        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> map_type = dictMapEntity.getObject().getReservoir_type();
        if (reservoirBean != null) {
            activityItemSafeJiandingBinding.nameOfReservoirTv.setText(reservoirBean.getReservoir());
            activityItemSafeJiandingBinding.typeOfReservoirTv.setText(map_type.get(reservoirBean.getReservoirType()));
            activityItemSafeJiandingBinding.timeOfReservoirTv.setText(dataBean.getReportDate());
            activityItemSafeJiandingBinding.addTimeOfReservoirTv.setText(dataBean.getCreateTime());
            activityItemSafeJiandingBinding.addNameOfReservoirTv.setText(dataBean.getUserName());
        }


        activityItemSafeJiandingBinding.safeRunningDetailRec.setLayoutManager(new LinearLayoutManager(this));
        adapterSafeRunningDetailReservoirs = new AdapterSafeRunningDetailReservoirs(this,R.layout.common_office_layout,myReservoirsItemBeanList);
        activityItemSafeJiandingBinding.safeRunningDetailRec.setAdapter(adapterSafeRunningDetailReservoirs);

    }

    @Override
    public void initView() {

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
