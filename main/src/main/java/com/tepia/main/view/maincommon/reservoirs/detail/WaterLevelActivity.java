package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arialyy.frame.util.show.T;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.common.DecimalInputTextWatcher;
import com.tepia.main.databinding.ActivityWaterLevelBinding;
import com.tepia.main.model.reserviros.CurrentFloodSeasonBean;
import com.tepia.main.model.reserviros.FloodSeasonBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.VisitLogBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterVisitLog;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterWaterlevelReservoirs;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * 创建时间 :2018-12-26
 * 更新时间 :2019-1-3
 * Version :1.0
 * 功能描述 :汛限水位页面
 **/
public class WaterLevelActivity extends MVPBaseActivity<ReserviorContract.View, ReserviorPresent> implements ReserviorContract.View<FloodSeasonBean>, OnDateSetListener {

    private AdapterWaterlevelReservoirs adapterWaterlevelReservoirs;
    private ActivityWaterLevelBinding binding;

    private List<FloodSeasonBean.DataBean.ListBean> dataList = new ArrayList<>();
    private String reservoirId;

    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;

    private WaterLevelActivity waterLevelActivity;

    @Override
    public int getLayoutId() {
        return R.layout.activity_water_level;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.bind(mRootView);
        setCenterTitle(getString(R.string.waterlevel_name));
        showBack();
        waterLevelActivity = this;
        reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        binding.rerserviorNameTag.nameTv.setText(reservoirName);
        binding.addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime();
                initDialog(true, null);

            }
        });
        SwipeRefreshLayout srflContainer = findViewById(R.id.srfl_container);
        srflContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(false);
                srflContainer.setRefreshing(false);
            }
        });
        initRec();
        search(true);


    }

    @Override
    public void initView() {

    }

    /**
     * 初始化recycleview
     */
    private void initRec() {
        binding.waterleverRec.setLayoutManager(new LinearLayoutManager(this));
        adapterWaterlevelReservoirs = new AdapterWaterlevelReservoirs(this, waterLevelActivity, R.layout.activity_waterlevel_item, dataList);
        binding.waterleverRec.setAdapter(adapterWaterlevelReservoirs);
        adapterWaterlevelReservoirs.setOnLoadMoreListener(() -> {
            binding.waterleverRec.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                mPresenter.getReservoirFloodSeason(reservoirId, String.valueOf(currentPage), String.valueOf(pageSize), false);


            }, 1000);
        }, binding.waterleverRec);
    }

    private void search(boolean isshowloadiing) {
        adapterWaterlevelReservoirs.setEnableLoadMore(false);
        dataList.clear();
        adapterWaterlevelReservoirs.notifyDataSetChanged();
        currentPage = 1;
        first = true;
        isloadmore = false;
        if (mPresenter != null) {
            mPresenter.getReservoirFloodSeason(reservoirId, String.valueOf(currentPage), String.valueOf(pageSize), isshowloadiing);
            findReservoirCurrentFloodSeason();
        }
    }

    /**
     * 刷新
     */
    public void refresh(boolean isshowloading) {
        closeDialog();
        first = true;
        search(isshowloading);
    }

    public void closeDialog(){
        if(dialog_show != null && dialog_show.isShowing()){
            dialog_show.dismiss();
        }
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

    private Dialog dialog_show;
    private TextView selectTimeTv;
    private EditText selectShuiweiEv;

    public void initDialog(boolean isEditTime, FloodSeasonBean.DataBean.ListBean floodseasonbean) {
        LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
        final ViewGroup nullparent = null;
        View layout = inflater.inflate(R.layout.fragment_waterlevel_dailog, nullparent);
        selectTimeTv = layout.findViewById(R.id.selectTimeTv);
        selectShuiweiEv = layout.findViewById(R.id.selectShuiweiEv);
        Button cancelBtn = layout.findViewById(R.id.cancelBtn);
        Button suerBtn = layout.findViewById(R.id.suerBtn);
        TextView title_waterlevelTv = layout.findViewById(R.id.title_waterlevelTv);


        dialog_show = new Dialog(this, R.style.Transparent);
        dialog_show.setCanceledOnTouchOutside(true);
        dialog_show.setContentView(layout);
        WindowManager windowManager = getWindowManager();
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        int width = metric.widthPixels * 4 / 5;
//        int height = metric.heightPixels / 3;
        WindowManager.LayoutParams lp = dialog_show.getWindow().getAttributes();
        lp.width = width;
        dialog_show.getWindow().setAttributes(lp);
        //限制输入位数：整数x位，小数点后x位
        selectShuiweiEv.addTextChangedListener(new DecimalInputTextWatcher(selectShuiweiEv, 4, 3));
        suerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rz = selectShuiweiEv.getText().toString();
               /* DecimalFormat df = new DecimalFormat("#.00");
                String rz = df.format(Double.parseDouble(waterleverValue));*/
                LogUtil.e("水位值:" + rz);
                if (TextUtils.isEmpty(rz)) {
                    ToastUtils.shortToast("请填写水位值");
                    return;
                }

                if (isEditTime) {
                    String selectTime = selectTimeTv.getText().toString();
                    if (TextUtils.isEmpty(selectTime)) {
                        ToastUtils.shortToast("请选择时间");
                        return;
                    }
                    //新增水位，则时间可选
                    mPresenter.addReservoirFloodSeason(reservoirId, selectTime, rz,waterLevelActivity);

                } else {
                    //更新水位，时间则不可编辑
                    mPresenter.updateFloodSeason(floodseasonbean.getId(), rz,waterLevelActivity);
                }

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_show.dismiss();
            }
        });

        if (isEditTime) {
            title_waterlevelTv.setText("新  增  汛  限  水  位");
            selectTimeTv.setClickable(true);
            selectTimeTv.setEnabled(true);
            selectTimeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timePickerDialogUtil.builder.setTitleStringId("请选择月份");
                    timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
                    timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");

                }
            });
        } else {
            title_waterlevelTv.setText("修  改  汛  限  水  位");
            selectTimeTv.setClickable(false);
            selectTimeTv.setEnabled(false);
            selectTimeTv.setText(floodseasonbean.getFloodYearMonth() + " (不可编辑) ");
            String floodlevel = floodseasonbean.getFloodLevel();
            selectShuiweiEv.setText(floodlevel);
            //将光标移至文字末尾
            selectShuiweiEv.setSelection(floodlevel.length());
        }

        if (!isFinishing()) {
            dialog_show.show();
        }
    }

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
    private TimePickerDialogUtil timePickerDialogUtil;

    private void initTime() {
        if (timePickerDialogUtil == null) {
            timePickerDialogUtil = new TimePickerDialogUtil(getContext(), sf);
            timePickerDialogUtil.initTimePickerNextYear(this, Type.YEAR_MONTH);
        }

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
            String date = timePickerDialogUtil.getDateToString(millseconds);
            selectTimeTv.setText(date);

    }

    @Override
    public void success(FloodSeasonBean floodSeasonBean) {
        FloodSeasonBean.DataBean dataBean = floodSeasonBean.getData();

        List<FloodSeasonBean.DataBean.ListBean> data = dataBean.getList();
        int totalSize = dataBean.getTotal();
        if (first) {
            //首次加载
            if (data == null || data.size() == 0) {
//                        showEmptyView();
                adapterWaterlevelReservoirs.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
//                binding.waterleverTv.setText(getString(R.string.setting_t_null));
            } else {
                dataList.clear();
                //首次加载
                dataList.addAll(data);
                adapterWaterlevelReservoirs.notifyDataSetChanged();
              /*  if (dataList.size() > 0) {
                    binding.waterleverTv.setText(dataList.get(0).getFloodSeasonWaterLevel());
                }*/
//                findReservoirCurrentFloodSeason();
            }
            adapterWaterlevelReservoirs.setEnableLoadMore(true);
        } else if (isloadmore) {

            adapterWaterlevelReservoirs.addData(data);
            mCurrentCounter = adapterWaterlevelReservoirs.getData().size();
            if (mCurrentCounter >= totalSize) {
                //数据全部加载完毕
                adapterWaterlevelReservoirs.loadMoreEnd();
                return;
            }
            adapterWaterlevelReservoirs.loadMoreComplete();

        }
    }

    /**
     * 获取汛限水位
     */
    private void findReservoirCurrentFloodSeason(){
        ReservirosManager.getInstance().findReservoirCurrentFloodSeason(reservoirId)
                .subscribe(new LoadingSubject<CurrentFloodSeasonBean>(false, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(CurrentFloodSeasonBean currentFloodSeasonBean) {
                        if (currentFloodSeasonBean != null) {
                            if (currentFloodSeasonBean.getCode() == 0) {
                                binding.waterleverTv.setText(currentFloodSeasonBean.getData() + "");
                            }else{
                                LogUtil.e("findReservoirCurrentFloodSeason",currentFloodSeasonBean.getMsg() + " ");
                                binding.waterleverTv.setText(getString(R.string.setting_t_null));

                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e("findReservoirCurrentFloodSeason",message + " ");
                        binding.waterleverTv.setText(getString(R.string.setting_t_null));



                    }
                });
    }

    @Override
    public void failure(String msg) {
        adapterWaterlevelReservoirs.setEmptyView(EmptyLayoutUtil.show(msg));
//        ToastUtils.shortToast(msg);



    }
}
