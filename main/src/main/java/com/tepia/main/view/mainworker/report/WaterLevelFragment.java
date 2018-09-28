package com.tepia.main.view.mainworker.report;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.threepoint.RainConditionResponse;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;
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
 * 功能描述 :上报页面--水位fragment
 **/
public class WaterLevelFragment extends MVPBaseFragment<ReportContract.View, ReportPresenter> implements OnDateSetListener,View.OnClickListener,
        ReportContract.View<BaseResponse>{

    private RecyclerView shuiweiRec;
    private AdapterWaterLevelReservoirs adapterShuiweiReservoirs;
    private TextView mstarttimeTv;

    private YunWeiJiShuPresenter yunWeiJiShuPresenter;

    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;
    private List<WaterLevelResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private String startData,endData;
    private String reservoirId;

    public WaterLevelFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_waterlevel;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        shuiweiRec = findView(R.id.shuiweiRec);



        initRec();

        TextView sureSearchTv = findView(R.id.sureSearchTv);
        TextView shangbaoTv = findView(R.id.shangbaoTv);
        mstarttimeTv = findView(R.id.mstarttimeTv);
        sureSearchTv.setOnClickListener(this);
        shangbaoTv.setOnClickListener(this);
        mstarttimeTv.setOnClickListener(this);


        initDialog();
        initTime();
        yunWeiJiShuPresenter = new YunWeiJiShuPresenter();


        yunWeiJiShuPresenter.attachView(new YunWeiJiShuContract.View<WaterLevelResponse>() {
            @Override
            public void success(WaterLevelResponse waterLevelResponse) {
                WaterLevelResponse.DataBean dataBean = waterLevelResponse.getData();
                List<WaterLevelResponse.DataBean.ListBean> data = dataBean.getList();
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
        adapterShuiweiReservoirs = new AdapterWaterLevelReservoirs(getBaseActivity(),R.layout.fragment_shuiwei_head_layout,dataList);
        shuiweiRec.setAdapter(adapterShuiweiReservoirs);
        adapterShuiweiReservoirs.setOnItemClickListener((adapter, view, position) -> {


        });
        adapterShuiweiReservoirs.setOnLoadMoreListener(() -> {
            shuiweiRec.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                yunWeiJiShuPresenter.listStRsvrRRByReservoir(reservoirId, startData, endData, String.valueOf(currentPage), String.valueOf(pageSize),false);
            }, 1000);
        }, shuiweiRec);
    }

    /**
     * 查询水情列表（即水位）
     * @param isshowloadiing
     */
    private void search(boolean isshowloadiing) {
        adapterShuiweiReservoirs.setEnableLoadMore(false);
        dataList.clear();
        adapterShuiweiReservoirs.notifyDataSetChanged();
        currentPage = 1;
        first = true;
        isloadmore = false;
        if (yunWeiJiShuPresenter != null) {
            yunWeiJiShuPresenter.listStRsvrRRByReservoir(reservoirId, startData, endData, String.valueOf(currentPage), String.valueOf(pageSize),true);
        }
    }

    @Override
    protected void initRequestData() {
        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        reservoirId =  reservoirBean.getReservoirId();
        LogUtil.e("当前默认水库id--------------"+reservoirId);

        selectReserviorTv.setText(reservoirBean.getReservoir());
        search(false);
    }

    private void initTime(){
        timePickerDialogUtil = new TimePickerDialogUtil(getContext(),sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);
        endData = TimeFormatUtils.getStringDate();
        startData = TimeFormatUtils.getNextDay(endData, "-1");
        mstarttimeTv.setText(endData);
    }



    private Dialog dialog_show;
    private TextView selectReserviorTv;
    private TextView selectShuiweiEv;

    private void initDialog() {
        LayoutInflater inflater = (LayoutInflater) getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup nullparent = null;
        View layout = inflater.inflate(R.layout.fragment_shangbao_dailog, nullparent);
        selectReserviorTv = layout.findViewById(R.id.selectReserviorTv);
        selectShuiweiEv = layout.findViewById(R.id.selectShuiweiEv);
        Button cancelBtn = layout.findViewById(R.id.cancelBtn);
        Button suerBtn = layout.findViewById(R.id.suerBtn);

        dialog_show = new Dialog(getBaseActivity(), R.style.Transparent);
        dialog_show.setCanceledOnTouchOutside(true);
        dialog_show.setContentView(layout);
        WindowManager windowManager = getBaseActivity().getWindowManager();
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        int width = metric.widthPixels * 4 / 5;
//        int height = metric.heightPixels / 3;
        WindowManager.LayoutParams lp = dialog_show.getWindow().getAttributes();
        lp.width = width;
        dialog_show.getWindow().setAttributes(lp);

        suerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rz = selectShuiweiEv.getText().toString();
                if(TextUtils.isEmpty(rz)){
                    ToastUtils.shortToast("请填写水位值");
                    return;
                }
                mPresenter.uploadingStRsvr(reservoirId,rz);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_show.dismiss();
            }
        });

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
            LogUtil.e("startTime", text);
            //默认查询一天的水位量
            startData = TimeFormatUtils.getNextDay(endData, "-1");


        }


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shangbaoTv) {
            dialog_show.show();

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

    @Override
    public void success(BaseResponse data) {
        dialog_show.dismiss();

        ToastUtils.shortToast("上传成功");
    }

    @Override
    public void failure(String msg) {
        dialog_show.dismiss();
        ToastUtils.shortToast("上传失败");
    }
}
