package com.tepia.main.view.main.detail.reservior;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.detai.ImageBean;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.detai.ReservoirDetailBean;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.AdapterStationDetailList;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.RainFullFragment;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.StationDetailResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 水库详情页面
 * @author liying
 * @date 2018/7/25
 */
public class ReserviorFragment extends BaseCommonFragment {

    private static String ReserviorFragment_ = "ReserviorFragment_";
    private static String ReserviorFragment_STCD = "ReserviorFragment_STCD";

    private RecyclerView baseinfoRecV;
    private AdapterStationDetailList adapterStationDetailList;
    private List<StationDetailResponse> baseinfo_list = new ArrayList<>();
    private ReservoirBean reservoirbean;
    private String stcd;
    public ReserviorFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param stcd  测站id
     * @return
     */
    public static ReserviorFragment newInstance(String stcd){

        ReserviorFragment f = new ReserviorFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ReserviorFragment_STCD, stcd);
        f.setArguments(bundle);
        return f;
    }

    /**
     *
     * @param reservoirResponse  水库实体
     * @return
     */
    public static ReserviorFragment newInstance(ReservoirResponse.DataBean reservoirResponse){

        ReserviorFragment f = new ReserviorFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ReserviorFragment_, reservoirResponse);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_reservior;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        if(getArguments() != null && getArguments().containsKey(ReserviorFragment_STCD)){
            stcd = getArguments().getString(ReserviorFragment_STCD);
        }

        baseinfoRecV = findView(R.id.baseinfoRecV);
        baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));
        baseinfoRecV.setHasFixedSize(true);
        baseinfoRecV.setNestedScrollingEnabled(false);
        adapterStationDetailList = new AdapterStationDetailList(getContext(),R.layout.fragment_mine_item_monitordetail,baseinfo_list);
        baseinfoRecV.setAdapter(adapterStationDetailList);
        getDetail(stcd);


    }

    private void getDetail(String stcd){
        DetailManager.getInstance().findAppReservoirById(stcd)
                .subscribe(new LoadingSubject<ReservoirDetailBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(ReservoirDetailBean reservoirResponse) {
                        if (reservoirResponse != null) {
                            if (reservoirResponse.getCode() == 0) {
                                 reservoirbean = reservoirResponse.getData();
                                if(reservoirbean != null) {
                                    getStaionDetai();
                                    adapterStationDetailList.notifyDataSetChanged();
                                }

                            }else{
                                adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(message));
                    }
                });
    }

    /**
     * 基础信息
     */
    private void getStaionDetai() {
        baseinfo_list.clear();
        list_add_baseinfo(getString(R.string.stationName), reservoirbean.getReservoir(),"");
        String statusstr = getString(R.string.setting_t_null);
        if("0".equals(reservoirbean.getStatus())){
            statusstr = "开启";
        }else if("1".equals(reservoirbean.getStatus())){
            statusstr = "禁用";

        }else if("2".equals(reservoirbean.getStatus())){
            statusstr = "未接入";

        }else if("3".equals(reservoirbean.getStatus())){
            statusstr = "接入中";

        }else if("4".equals(reservoirbean.getStatus())){
            statusstr = "已接入";

        }else if("5".equals(reservoirbean.getStatus())){
            statusstr = "到期";

        }

        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> map_Damtype = dictMapEntity.getObject().getDam_type();

        list_add_baseinfo(getString(R.string.status), statusstr,"");
        list_add_baseinfo(getString(R.string.manageUnit), reservoirbean.getManageDepId(),"");
        list_add_baseinfo(getString(R.string.belongtown), reservoirbean.getReservoirAddress(),"");
        list_add_baseinfo(getString(R.string.longtude), String.valueOf(reservoirbean.getReservoirLongitude()),"");
        list_add_baseinfo(getString(R.string.latitude), String.valueOf(reservoirbean.getReservoirLatitude()),"");
        list_add_baseinfo(getString(R.string.staionType), reservoirbean.getBuildStartDate(),"");
        list_add_baseinfo(getString(R.string.belongRiver), getString(R.string.setting_t_null),"");
        list_add_baseinfo(getString(R.string.whenbuild), reservoirbean.getBuildStartDate(),"");
        list_add_baseinfo(getString(R.string.whenfinish), reservoirbean.getBuildEndDate(),"");
        list_add_baseinfo(getString(R.string.baxing), map_Damtype.get(reservoirbean.getDamType()),"");
        list_add_baseinfo(getString(R.string.bagao), reservoirbean.getDamHeight(),"(m)");
        list_add_baseinfo(getString(R.string.bakuan), reservoirbean.getDamWidth(),"(m)");
        list_add_baseinfo(getString(R.string.bachang), reservoirbean.getDamLength(),"(m)");
        list_add_baseinfo(getString(R.string.badinggaocheng), reservoirbean.getDamCrestElevation(),"(m)");
        list_add_baseinfo(getString(R.string.badikuan), reservoirbean.getDamBotmMaxWidth(),"(m)");
        list_add_baseinfo(getString(R.string.sishuiwei), reservoirbean.getDeadWaterLevel(),"(m)");
        list_add_baseinfo(getString(R.string.sikurong), reservoirbean.getDeathLevelVolume(),"(万m³)");
        list_add_baseinfo(getString(R.string.xushuiwei), reservoirbean.getNormalImpoundedLevel(),"m");
        list_add_baseinfo(getString(R.string.xushuikurong), getString(R.string.setting_t_null),"(万m³)");
        list_add_baseinfo(getString(R.string.xinglikurong), reservoirbean.getEffectiveVolume(),"(万m³)");
        list_add_baseinfo(getString(R.string.hongshuiwei), reservoirbean.getDesignFloodWaterLevel(),"(m)");
        list_add_baseinfo(getString(R.string.jiaohehongshuiwei), reservoirbean.getCheckFloodWaterLevel(),"(m)");
        list_add_baseinfo(getString(R.string.jiyumianji), reservoirbean.getRainwaterCollectingArea(),"(m²)");
        list_add_baseinfo(getString(R.string.kurongxishu), reservoirbean.getCapacityCoefficient(),"");
        list_add_baseinfo(getString(R.string.guangainongtian), reservoirbean.getLrrigatedFarmlandArea(),"亩");
        list_add_baseinfo(getString(R.string.nongtiangongshuiliang), reservoirbean.getAvgIrrigationWaterSupply(),"(万m³/a)");
        list_add_baseinfo(getString(R.string.nongcunrengongshuiliang), reservoirbean.getDrinkingWaterSupply(),"(万m³/a)");
        list_add_baseinfo(getString(R.string.zongshuiliang), reservoirbean.getAvgTotalWaterSupply(),"(万m³/a)");
        list_add_baseinfo(getString(R.string.kaifaliyong), reservoirbean.getUtilizationRateOfDevelopment(),"(万m³/a)");

        list_add_baseinfo(getString(R.string.yiliudao), reservoirbean.getSpillwayType(),"");
        list_add_baseinfo(getString(R.string.yiliudaozongchang), reservoirbean.getSpillwayLength(),"(m)");
        list_add_baseinfo(getString(R.string.yinshuiliudaochang), reservoirbean.getDiversionCanalLength(),"(m)");
        list_add_baseinfo(getString(R.string.qudigaocheng), reservoirbean.getCanalBottomElevation(),"");
        list_add_baseinfo(getString(R.string.yiliufangshi), reservoirbean.getOverflowMode(),"");
        list_add_baseinfo(getString(R.string.yandinggaocheng), reservoirbean.getWeirCrestElevation(),"");

    }

    private void list_add_baseinfo(String letfStr, String rightStr,String unitStr) {
        StationDetailResponse stationDetailResponse = new StationDetailResponse();
        stationDetailResponse.setTitleLeft(letfStr);
        setRightText(stationDetailResponse, rightStr,unitStr);
        baseinfo_list.add(stationDetailResponse);
    }

    private void setRightText(StationDetailResponse stationDetailResponse, String str,String unitStr) {
        if (TextUtils.isEmpty(str)) {
            stationDetailResponse.setTitleRight(getString(R.string.setting_t_null) + unitStr);
        } else {
            stationDetailResponse.setTitleRight(str + unitStr);
        }

    }

    @Override
    protected void initRequestData() {

    }

}
