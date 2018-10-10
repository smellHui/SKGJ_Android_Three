package com.tepia.main.view.maintechnology.threekeypoint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzxiang.pickerview.data.Type;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.jishu.threepoint.RainConditionResponse;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.MyRainListAdapter;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.MyWaterListAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018/10/9
 * Version :1.0
 * 功能描述 : 三个重点历史数据
 **/

public class HistoryDataActivity extends BaseActivity {
    private YunWeiJiShuPresenter mPresenter;
    private YunWeiJiShuPresenter waterPresenter;
    private RecyclerView rvWater;
    private RecyclerView rvRain;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private String startDate;
    private String endDate;
    private String reservoirId;
    private Bundle extras;
    private TextView tvTitle;
    private LinearLayout llWater;
    private LinearLayout llRain;
    private Intent intent;
    private String searchType;
    private int pageSize = 10;
    private int currentPage = 1;
    private List<WaterLevelResponse.DataBean.ListBean> waterDataList = new ArrayList<>();
    private List<RainConditionResponse.DataBean.ListBean> rainDataList = new ArrayList<>();
    private MyWaterListAdapter waterListAdapter;
    private MyRainListAdapter rainListAdapter;
    private String reservoirName;
    private boolean isloadmore;
    private boolean first;
    private int mCurrentCounter = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_data;
    }

    @Override
    public void initView() {
        setCenterTitle("历史数据");
        showBack();
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvTitle = findViewById(R.id.tv_title);
        llWater = findViewById(R.id.ll_water);
        llRain = findViewById(R.id.ll_rain);
        rvWater = findViewById(R.id.rv_water);
        rvRain = findViewById(R.id.rv_rain);
        initStartDate();
        initEndDate();
        if (extras != null && extras.containsKey("searchType") && extras.containsKey("reservoirName") && extras.containsKey("reservoirId")) {
            //加载网络,刷新数据
            searchType = intent.getStringExtra("searchType");
            reservoirName = intent.getStringExtra("reservoirName");
            reservoirId = intent.getStringExtra("reservoirId");
            waterListAdapter = new MyWaterListAdapter(R.layout.threepoint_jishu_item_water, waterDataList);
            rainListAdapter = new MyRainListAdapter(R.layout.threepoint_jishu_item_rain, rainDataList);
//            reservoirId = "6942292cad144bds97fa6c31f96ee687";
            if (searchType != null && reservoirName != null && reservoirId != null) {
                if ("water".equals(searchType)) {
                    //显示水情
                    llWater.setVisibility(View.VISIBLE);
                    llRain.setVisibility(View.GONE);
                    tvTitle.setText(reservoirName + "水情数据");
                    //设置recycle
                    rvWater.setLayoutManager(new LinearLayoutManager(this));
                    rvWater.setAdapter(waterListAdapter);
                    waterListAdapter.openLoadAnimation();
                    waterListAdapter.setOnLoadMoreListener(() -> {
                        rvWater.postDelayed(() -> {
                            currentPage++;
                            first = false;
                            isloadmore = true;
                            //加载更多数据
                            if (waterPresenter != null) {
                                waterPresenter.listStRsvrRRByReservoir(reservoirId, startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), false);
                            }
                        }, 1000);
                    }, rvWater);
                } else if ("rain".equals(searchType)) {
                    //显示雨情
                    llWater.setVisibility(View.GONE);
                    llRain.setVisibility(View.VISIBLE);
                    tvTitle.setText(reservoirName + "雨情数据");
                    //设置recycle
                    rvRain.setLayoutManager(new LinearLayoutManager(this));
                    rvRain.setAdapter(rainListAdapter);
                    rainListAdapter.openLoadAnimation();
                    rainListAdapter.setOnLoadMoreListener(() -> {
                        rvRain.postDelayed(() -> {
                            currentPage++;
                            first = false;
                            isloadmore = true;
                            //加载更多数据
                            if (mPresenter != null) {
                                mPresenter.listStPpthRByReservoir(reservoirId, startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), false);
                            }
                        }, 1000);
                    }, rvRain);
                }
                initRequestResponse();
            }
        }
        initSearch();
    }

    private void initRequestResponse() {
        currentPage = 1;
        isloadmore = false;
        first = true;
        currentPage = 1;
        isloadmore = false;
        first = true;
        if ("water".equals(searchType)) {
            waterDataList.clear();
//            LogUtil.i("查询时间:"+startDate);
            waterListAdapter.setEnableLoadMore(false);
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
            } else {
                if (waterPresenter != null) {
                    waterPresenter.listStRsvrRRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize), true);
                }
            }
        } else if ("rain".equals(searchType)) {
            rainDataList.clear();
//            LogUtil.i("查询时间:"+startDate);
            rainListAdapter.setEnableLoadMore(false);
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
            } else {
                if (mPresenter != null) {
                    mPresenter.listStPpthRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize), true);
                }
            }
        }
    }

    private void initSearch() {
        Button btConfirm = findViewById(R.id.bt_confirm);
        btConfirm.setOnClickListener(v -> {
            startDate = tvStartDate.getText().toString();
            if (startDate != null && startDate.length() > 0) {
                startDate = startDate + " 00:00:00";
            }
            endDate = tvEndDate.getText().toString();
            if (endDate != null && endDate.length() > 0) {
                endDate = endDate + " 23:59:59";
            }
            currentPage = 1;
            isloadmore = false;
            first = true;
            if ("water".equals(searchType)) {
                waterDataList.clear();
//            LogUtil.i("查询时间:"+startDate);
                waterListAdapter.setEnableLoadMore(false);
                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast(R.string.no_network);
                } else {
                    if (waterPresenter != null) {
                        waterPresenter.listStRsvrRRByReservoir(reservoirId, startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), true);
                    }
                }
            } else if ("rain".equals(searchType)) {
                rainDataList.clear();
//            LogUtil.i("查询时间:"+startDate);
                rainListAdapter.setEnableLoadMore(false);
                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast(R.string.no_network);
                } else {
                    if (mPresenter != null) {
                        mPresenter.listStPpthRByReservoir(reservoirId, startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), true);
                    }
                }
            }
        });
    }

    @Override
    public void initData() {
        intent = getIntent();
//        Intent intent = new Intent(getActivity(), HistoryDataActivity.class);
//        intent.putExtra("searchType","rain");
//        intent.putExtra("reservoirName",reservoirName);
//        intent.putExtra("reservoirId",reservoirId);
//        startActivity(intent);
//          mPresenter.listStPpthRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize));
//                waterPresenter.listStRsvrRRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize),false);
        extras = intent.getExtras();
        mPresenter = new YunWeiJiShuPresenter();
        waterPresenter = new YunWeiJiShuPresenter();
        mPresenter.attachView(new YunWeiJiShuContract.View<RainConditionResponse>() {
            @Override
            public void success(RainConditionResponse rainConditionResponse) {
                RainConditionResponse.DataBean dataBean = rainConditionResponse.getData();
                List<RainConditionResponse.DataBean.ListBean> data = dataBean.getList();
//                if (data == null || data.size() == 0) {
////                    LogUtil.i("搜索为空");
//                    rainDataList.clear();
//                    rainListAdapter.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
//                    rainListAdapter.notifyDataSetChanged();
//                } else {
//                    rainDataList.clear();
//                    rainDataList.addAll(data);
//                    rainListAdapter.notifyDataSetChanged();
//                }
                int totalSize = dataBean.getTotal();
                int pages = dataBean.getPages();
                if (first) {
                    if (data == null || data.size() == 0) {
                        rainDataList.clear();
                        rainListAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                        rainListAdapter.notifyDataSetChanged();
                    } else {
                        rainDataList.clear();
                        rainDataList.addAll(data);
                        rainListAdapter.notifyDataSetChanged();
                        first = false;
                    }
                    rainListAdapter.setEnableLoadMore(true);
                    if (pages == 1) {
                        //只有一页
                        rainListAdapter.loadMoreEnd();
                        return;
                    }
                } else if (isloadmore) {
                    rainDataList.addAll(data);
                    rainListAdapter.notifyDataSetChanged();
                    mCurrentCounter = rainListAdapter.getData().size();
                    if (mCurrentCounter >= totalSize) {
                        //数据全部加载完毕
                        rainListAdapter.loadMoreEnd();
                        return;
                    }
                    rainListAdapter.loadMoreComplete();
                }
            }

            @Override
            public void failure(String msg) {
                if (isloadmore) {
                    if (currentPage > 1) {
                        currentPage--;
                        rainListAdapter.loadMoreFail();
                    }
                } else {
                    rainListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                }
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        waterPresenter.attachView(new YunWeiJiShuContract.View<WaterLevelResponse>() {
            @Override
            public void success(WaterLevelResponse waterLevelResponse) {
                WaterLevelResponse.DataBean dataBean = waterLevelResponse.getData();
                List<WaterLevelResponse.DataBean.ListBean> data = dataBean.getList();
                int totalSize = dataBean.getTotal();
                int pages = dataBean.getPages();
                if (first) {
                    if (data == null || data.size() == 0) {
                        waterDataList.clear();
                        waterListAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                        waterListAdapter.notifyDataSetChanged();
                    } else {
                        waterDataList.clear();
                        waterDataList.addAll(data);
                        waterListAdapter.notifyDataSetChanged();
                        first = false;
                    }
                    waterListAdapter.setEnableLoadMore(true);
                    if (pages == 1) {
                        //只有一页
                        waterListAdapter.loadMoreEnd();
                        return;
                    }
                } else if (isloadmore) {
                    waterDataList.addAll(data);
                    waterListAdapter.notifyDataSetChanged();
                    mCurrentCounter = waterListAdapter.getData().size();
                    if (mCurrentCounter >= totalSize) {
                        //数据全部加载完毕
                        waterListAdapter.loadMoreEnd();
                        return;
                    }
                    waterListAdapter.loadMoreComplete();
                }
            }

            @Override
            public void failure(String msg) {
                if (isloadmore) {
                    if (currentPage > 1) {
                        currentPage--;
                        waterListAdapter.loadMoreFail();
                    }
                } else {
                    waterListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                }
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    private TimePickerDialogUtil timePickerDialogUtil;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private void initStartDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(this, sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        //昨天减1
        ca.add(Calendar.DATE, -1);
        Date lastDate = ca.getTime();
//        tvStartDate.setText(TimeFormatUtils.dateToStrLong(lastDate));
        tvStartDate.setOnClickListener(v -> {
            String current = (String) tvStartDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvStartDate.setText(text);
            }, Type.YEAR_MONTH_DAY, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears, R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择开始日期");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");
        });
    }

    private void initEndDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(this, sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        //昨天减1
        ca.add(Calendar.DATE, -1);
        Date lastDate = ca.getTime();
//        tvEndDate.setText(TimeFormatUtils.dateToStrLong(lastDate));
        tvEndDate.setOnClickListener(v -> {
            String current = (String) tvEndDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvEndDate.setText(text);
            }, Type.YEAR_MONTH_DAY, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears, R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择结束日期");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");
        });
    }

    public static long strToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        //继续转换得到秒数的long型
        long lTime = strtodate.getTime();
        return lTime;
    }

}
