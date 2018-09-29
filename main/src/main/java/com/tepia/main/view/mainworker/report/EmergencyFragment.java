package com.tepia.main.view.mainworker.report;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.mainworker.report.adapter.AdapterEmergency;
import com.tepia.main.view.mainworker.report.adapter.AdapterWaterLevelReservoirs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    : 2018-9-20
 * Version :1.0
 * 功能描述 :应急
 **/
public class EmergencyFragment extends MVPBaseFragment<ReportContract.View, ReportPresenter> implements View.OnClickListener,OnDateSetListener {

    private RecyclerView shuiweiRec;
    private AdapterEmergency adapterShuiweiReservoirs;
    private TextView mstarttimeTv;
    private TextView currentResvoirTv;

    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;
    private List<EmergenceListBean.DataBean.ListBean> dataList = new ArrayList<>();
    private String startData,endData;
    private String reservoirId;

    public EmergencyFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report_emergency;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        shuiweiRec = findView(R.id.yingjiRec);
        currentResvoirTv = findView(R.id.currentResvoirTv);

        initRec();

        TextView sureSearchTv = findView(R.id.sureSearchTv);
        TextView shangbaoTv = findView(R.id.shangbaoTv);
        mstarttimeTv = findView(R.id.mstarttimeTv);
        sureSearchTv.setOnClickListener(this);
        shangbaoTv.setOnClickListener(this);
        mstarttimeTv.setOnClickListener(this);


        initTime();

        sureSearchTv.setOnClickListener(this);
        shangbaoTv.setOnClickListener(this);

        mPresenter.attachView(new ReportContract.View<EmergenceListBean>() {
            @Override
            public void success(EmergenceListBean waterLevelResponse) {
                EmergenceListBean.DataBean dataBean = waterLevelResponse.getData();
                List<EmergenceListBean.DataBean.ListBean> data = dataBean.getList();
                int totalSize = dataBean.getTotal();
                if (first) {
                    //首次加载
                    if (data == null || data.size() == 0) {
//                        showEmptyView();
                        adapterShuiweiReservoirs.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                    } else {
                        dataList.clear();
                        //首次加载
                        dataList.addAll(data);
                        adapterShuiweiReservoirs.notifyDataSetChanged();
                    }
                    adapterShuiweiReservoirs.setEnableLoadMore(true);
                } else if (isloadmore) {
                    adapterShuiweiReservoirs.addData(data);
                    mCurrentCounter = adapterShuiweiReservoirs.getData().size();
                    if (mCurrentCounter >= totalSize) {
                        //数据全部加载完毕
                        adapterShuiweiReservoirs.loadMoreEnd();
                        return;
                    }
                    adapterShuiweiReservoirs.loadMoreComplete();
                }
            }

            @Override
            public void failure(String msg) {
                adapterShuiweiReservoirs.setEmptyView(EmptyLayoutUtil.show(msg));
            }

            @Override
            public Context getContext() {
                return null;
            }
        });

    }

    /**
     * 初始化recycleview
     */
    private void initRec(){
        shuiweiRec.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        adapterShuiweiReservoirs = new AdapterEmergency(getBaseActivity(),R.layout.fragment_shuiwei_yingji_head_layout,dataList);
        shuiweiRec.setAdapter(adapterShuiweiReservoirs);
        adapterShuiweiReservoirs.setOnItemClickListener((adapter, view, position) -> {


        });
        adapterShuiweiReservoirs.setOnLoadMoreListener(() -> {
            shuiweiRec.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                mPresenter.getProblemList(reservoirId, "","",startData, endData, String.valueOf(currentPage), String.valueOf(pageSize),false);
            }, 1000);
        }, shuiweiRec);
    }

    /**
     * 查询应急情况列表
     * @param isshowloadiing
     */
    public void search(boolean isshowloadiing) {
        getReservoirId();
        adapterShuiweiReservoirs.setEnableLoadMore(false);
        dataList.clear();
        adapterShuiweiReservoirs.notifyDataSetChanged();
        currentPage = 1;
        first = true;
        isloadmore = false;
        if (mPresenter != null) {
            mPresenter.getProblemList(reservoirId, "","",startData, endData, String.valueOf(currentPage), String.valueOf(pageSize),isshowloadiing);
        }
    }

    @Override
    protected void initRequestData() {

        search(true);
    }

    /**
     * 获取水库id
     */
    private void getReservoirId(){
        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        reservoirId =  reservoirBean.getReservoirId();
        LogUtil.e("当前默认水库id--------------"+reservoirId);
        currentResvoirTv.setText("当前水库："+reservoirBean.getReservoir());
    }

    private void initTime(){
        timePickerDialogUtil = new TimePickerDialogUtil(getContext(),sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);
        endData = TimeFormatUtils.getStringDate();
        startData = TimeFormatUtils.getNextDay(endData, "-1");
        mstarttimeTv.setText(endData);
    }



    // 起始时间选择器
    private boolean startflag = false;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private long last_millseconds_start = 0;

    private TimePickerDialogUtil timePickerDialogUtil;

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        if (startflag) {
            last_millseconds_start = millseconds;
            String text = timePickerDialogUtil.getDateToString(millseconds);
            endData = text;
            mstarttimeTv.setText(endData);
            startflag = false;
            //默认查询一天的水位量
            startData = TimeFormatUtils.getNextDay(endData, "-1");
            LogUtil.e("endData", endData);
            LogUtil.e("startData", startData);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shangbaoTv) {
           Intent intent = new Intent();
           intent.setClass(getBaseActivity(),EmergencyDetailActivity.class);
           startActivity(intent);
        } else if (v.getId() == R.id.sureSearchTv) {
            search(true);
        }else if(v.getId() == R.id.mstarttimeTv){
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            startflag = true;
            if (last_millseconds_start != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(last_millseconds_start);
            }
            timePickerDialogUtil.builder.setTitleStringId(getContext().getString(R.string.endtimeTitle));
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        }
    }

}
