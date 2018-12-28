package com.tepia.main.view.mainadminister.yunwei;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.jzxiang.pickerview.data.Type;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.mainadminister.yunwei.adapter.MyAdminOperationListAdapter;
import com.tepia.main.view.maincommon.setting.ChoiceReservoirActivity;
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
 * 功能描述 :行政运维
 **/

public class AdminOperationListFragment extends MVPBaseFragment<YunWeiJiShuContract.View, YunWeiJiShuPresenter> {
    private String[] tabNames = {"巡检", "维修养护", "保洁", "上报"};
    private ArrayAdapter spinnerAdapter;
    private TextView tvOperationTask;
    private Spinner spinner;
    private String[] reservoirs;
    private int spinnerPosition;
    private RecyclerView rv;
    private String typeStr;
    private String operationType;
    private TextView tvStartDate;
    private List<AdminWorkOrderResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private MyAdminOperationListAdapter rvAdapter;
    private int pageSize = 10;
    private int currentPage = 1;
    private boolean isloadmore;
    private boolean first;
    private int mCurrentCounter = 0;
    private String reservoirId;
    private String startDate;
    private String typeName;
    private TextView tvComplete;
    private TextView tvPercent;
    private ArrayList<ReservoirBean> localReservoirList;
    private ReservoirBean selectedResrvoir;
    private TextView tvReservoir;
    private int currentSearchType = 0;//0是水库，1是乡镇
    private String currentDate;
    private String areaCode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_admin_operation_list;
    }

    @Override
    protected void initData() {
        localReservoirList = UserManager.getInstance().getLocalReservoirList();
        mPresenter.attachView(new YunWeiJiShuContract.View<AdminWorkOrderResponse>() {
            @Override
            public void success(AdminWorkOrderResponse adminWorkOrderResponse) {
//                LogUtil.i("个数" + workOrderListResponse.getCode());
                AdminWorkOrderResponse.DataBean dataBean = adminWorkOrderResponse.getData();
                if (null != dataBean) {
                    List<AdminWorkOrderResponse.DataBean.ListBean> data = dataBean.getList();
                    int totalSize = dataBean.getTotal();
                    int pages = dataBean.getPages();
                    if (first) {
                        if (data == null || data.size() == 0) {
                            dataList.clear();
                            rvAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                            rvAdapter.notifyDataSetChanged();
                        } else {
                            dataList.clear();
                            dataList.addAll(data);
                            rvAdapter.notifyDataSetChanged();
                            first = false;
                        }
                        rvAdapter.setEnableLoadMore(true);
                        if (pages == 1) {
                            //只有一页
                            rvAdapter.loadMoreEnd();
                            return;
                        }
                    } else if (isloadmore) {
                        dataList.addAll(data);
                        rvAdapter.notifyDataSetChanged();
                        mCurrentCounter = rvAdapter.getData().size();
                        if (mCurrentCounter >= totalSize) {
                            //数据全部加载完毕
                            rvAdapter.loadMoreEnd();
                            return;
                        }
                        rvAdapter.loadMoreComplete();
                    }
                    isFirstLoad = true;
                }else {
                    dataList.clear();
                    rvAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                    rvAdapter.notifyDataSetChanged();
                    isFirstLoad = true;
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
                if (isloadmore) {
                    if (currentPage > 1) {
                        currentPage--;
                        rvAdapter.loadMoreFail();
                    }
                } else {
                    dataList.clear();
                    rvAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                    rvAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initView(View view) {
        typeStr = getArguments().getString("type");
//        bundle.putString("countSymbol",countSymbol);
//        bundle.putString("areaCode",areaCode);
        String countSymbol = getArguments().getString("countSymbol");
        areaCode = getArguments().getString("areaCode");
        LinearLayout llSearchType = findView(R.id.ll_search_type);
        if (!"1".equals(countSymbol)){
            //显示乡镇
            llSearchType.setVisibility(View.GONE);
        }
//        LogUtil.i("typeStr:" + typeStr);
        if (tabNames[3].equals(typeStr)) {
            operationType = "";
        } else if (tabNames[0].equals(typeStr)) {
            operationType = "1";
            typeName = "巡检";
        } else if (tabNames[1].equals(typeStr)) {
            operationType = "2";
            typeName = "维护";
        } else if (tabNames[2].equals(typeStr)) {
            operationType = "3";
            typeName = "保洁";
        }
        TextView tvSelectMonth = findView(R.id.tv_select_month);
        tvSelectMonth.setText("选择月份:");
        tvOperationTask = findView(R.id.tv_operation_task);
        tvComplete = findView(R.id.tv_complete);
        tvPercent = findView(R.id.tv_percent);
//        spinner = findView(R.id.operation_spinner);
        rv = findView(R.id.rv_operation_list);
        tvStartDate = findView(R.id.tv_start_date);
        tvReservoir = findView(R.id.tv_reservoir);
        TextView tvCleanMonth = findView(R.id.tv_clean_month);
        tvCleanMonth.setOnClickListener(v -> {
            tvStartDate.setText("");
        });
        tvReservoir.setOnClickListener(v -> {
            if (currentSearchType == 0) {
                Intent intent = new Intent(getActivity(), ChoiceReservoirActivity.class);
                ChoiceReservoirActivity.setIntent(intent, true);
                intent.putExtra(ChoiceReservoirActivity.isFromYunWei,true);
                startActivityForResult(intent, ChoiceReservoirActivity.resultCode);
            }
//            startActivityForResult(new Intent(getActivity(), ChoiceReservoirActivity.class), ChoiceReservoirActivity.resultCode)
        });
        initRadioButton();
        initStartDate();
//        initSpinner();
        initRecyclerView();
        initSearch();
        initRequestResponse();
    }

    private void initRadioButton() {
        RadioGroup radioGroup = findView(R.id.rg);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int i = group.getCheckedRadioButtonId();
            if (i == R.id.radio_reservoir) {
                currentSearchType = 0;
            } else if (i == R.id.radio_town) {
                currentSearchType = 1;
                tvReservoir.setText("全部");
                reservoirId = "";
            }
        });
    }

    private void initRequestResponse() {
        if (!isFirstLoad) {
            reservoirId = "";
            tvReservoir.setText("全部");
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if (mPresenter != null) {
                boolean isShow = false;
                if ("1".equals(operationType)){
                    isShow =true;
                }
                mPresenter.getAdminWorkOrderList(reservoirId, operationType, currentDate, String.valueOf(currentPage), String.valueOf(pageSize), isShow);
            }
        }
    }

    private void initSearch() {
        Button btConfirm = findView(R.id.bt_confirm);
        TextView tvTitleTownReservoir = findView(R.id.tv_title_town_reservoir);
        currentDate = TimeFormatUtils.getStringDateShort2();
        tvStartDate.setText(currentDate);
        startDate = currentDate;
        btConfirm.setOnClickListener(v -> {
            dataList.clear();
            startDate = tvStartDate.getText().toString();
//            LogUtil.i("查询时间:"+startDate);
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
            } else {
                if (currentSearchType == 0) {
                    tvTitleTownReservoir.setText("水库");
                    if (mPresenter != null) {
                        if ((null == reservoirId || "".equals(reservoirId))&&(startDate==null||"".equals(startDate))) {
                            tvStartDate.setText(currentDate);
                            startDate = currentDate;
                        }
                        rvAdapter.setCurrentSearchType(0);
                        mPresenter.getAdminWorkOrderList(reservoirId, operationType, startDate, String.valueOf(currentPage), String.valueOf(pageSize), true);
                    }
                } else if (currentSearchType == 1) {
                    tvTitleTownReservoir.setText("乡镇(水库数)");
                    if (null == startDate || "".equals(startDate)) {
                        tvStartDate.setText(currentDate);
                        startDate = currentDate;
                    }
                    rvAdapter.setCurrentSearchType(1);
                    mPresenter.getTownWorkOrderList(operationType, areaCode, startDate, String.valueOf(currentPage), String.valueOf(pageSize), true);
                }
            }
        });
    }

    private TimePickerDialogUtil timePickerDialogUtil;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM", Locale.getDefault());

    private void initStartDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(getActivity(), sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        //昨天减1
        ca.add(Calendar.DATE, -1);
        Date lastDate = ca.getTime();
//        tvStartDate.setText(sf.format(lastDate));
        tvStartDate.setOnClickListener(v -> {
            String current = (String) tvStartDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvStartDate.setText(text);
            }, Type.YEAR_MONTH, System.currentTimeMillis() - fiveYears, System.currentTimeMillis(), R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择查询月份");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        });
    }

    public static long strToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        //继续转换得到秒数的long型
        long lTime = strtodate.getTime();
        return lTime;
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAdapter = new MyAdminOperationListAdapter(R.layout.admin_operation_list_item, dataList);
        rvAdapter.openLoadAnimation();
        rv.setAdapter(rvAdapter);
        rvAdapter.setOnLoadMoreListener(() -> {
            rv.postDelayed(() -> {
                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast(R.string.no_network);
                }else {
                    currentPage++;
                    first = false;
                    isloadmore = true;
                    //加载更多数据
                    loadDataOrMore(false);
                }
            }, 500);
        }, rv);
        rvAdapter.setOnItemClickListener((adapter, view, position) -> {
//            LogUtil.i("position:"+position);
//            ARouter.getInstance().build(AppRoutePath.app_task_detail)
//                    .withString("workOrderId", dataList.get(position).getWorkOrderId())
//                    .navigation();
            int rvCurrentSearchType = rvAdapter.getCurrentSearchType();
            if (0 == rvCurrentSearchType) {
                Intent bundle = new Intent(getActivity(), AdminOperationActivity.class);
                bundle.putExtra("item", dataList.get(position));
                bundle.putExtra("operationType", operationType);
                startActivity(bundle);
            } else if (rvCurrentSearchType == 1) {
                Intent intent = new Intent(getActivity(), TownOperationActivity.class);
                AdminWorkOrderResponse.DataBean.ListBean listBean = dataList.get(position);
                String areaName = listBean.getAreaName();
                String date = listBean.getDate();
                String areaCode = listBean.getAreaCode();
                TownOperationActivity.setIntent(intent,operationType,areaCode,date,areaName);
                startActivity(intent);
            }
        });
    }

    private void loadDataOrMore(boolean isShowLoading) {
        if (currentSearchType == 0) {
            if (mPresenter != null) {
                mPresenter.getAdminWorkOrderList(reservoirId, operationType, startDate, String.valueOf(currentPage), String.valueOf(pageSize), false);
            }
        } else if (currentSearchType == 1) {
            mPresenter.getTownWorkOrderList(operationType, areaCode, startDate, String.valueOf(currentPage), String.valueOf(pageSize), false);
        }
    }

    //    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initSpinner() {
        if (localReservoirList != null && localReservoirList.size() > 0) {
            reservoirs = new String[localReservoirList.size() + 1];
            for (int i = 0; i < localReservoirList.size(); i++) {
                reservoirs[i + 1] = localReservoirList.get(i).getReservoir();
            }
            reservoirs[0] = "全部";
        }
//        localReservoirList.forEach(name -> LogUtil.i(name.getReservoir()));
//        LogUtil.i(Arrays.toString(reservoirs));
        //将可选内容与ArrayAdapter连接起来
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_operation_reservoir, reservoirs);
        //设置下拉列表的风格
        spinnerAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        //将adapter2 添加到spinner中
        spinner.setAdapter(spinnerAdapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                spinnerPosition = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    boolean isFirstLoad;

    @Override
    protected void initRequestData() {

    }

    private void setSpanned(int doneNum, int totals) {
        int percent = 0;
        if (doneNum != 0 && totals != 0) {
            percent = doneNum * 100 / totals;
        }
        String str = typeName + "任务:<font color='#e3654d'>" + totals + "次</font>";
        String totalStr = "完成" + typeName + ":<font color='#e3654d'>" + doneNum + "次</font>";
        String percentStr = "完成率:<font color='#e3654d'>" + percent + "%</font>";
        Spanned result;
        Spanned complete;
        Spanned percentSd;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY);
            complete = Html.fromHtml(totalStr, Html.FROM_HTML_MODE_LEGACY);
            percentSd = Html.fromHtml(percentStr, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(str);
            complete = Html.fromHtml(totalStr);
            percentSd = Html.fromHtml(percentStr);
        }
        tvOperationTask.setText(result);
        tvComplete.setText(complete);
        tvPercent.setText(percentSd);
    }

    private void showSelectReservoir() {
        if (localReservoirList != null) {
            String[] stringItems = new String[localReservoirList.size() + 1];
            for (int i = 0; i < localReservoirList.size(); i++) {
                stringItems[i + 1] = localReservoirList.get(i).getReservoir();
            }
            stringItems[0] = "全部";
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    spinnerPosition = position;
//                    mBinding.tvReservoir.setText(selectedResrvoir.getReservoir());
//                    selectFinish(selectedYunWeiType, selectedResrvoir);
//                    mBinding.loHeader.tvReservoirName.setText(selectedResrvoir.getReservoir());
                    tvReservoir.setText(stringItems[position]);
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ChoiceReservoirActivity.resultCode:
                if (null != data) {
                    boolean isSelectAll = data.getBooleanExtra(ChoiceReservoirActivity.isAllReservoir, false);
                    boolean isKeyBack = data.getBooleanExtra(ChoiceReservoirActivity.isKeyBack,false);
//                    intent.putExtra("reservoirId",reservoirId);
//                    intent.putExtra("reservoir",reservoir);
                    String backReservoirId = data.getStringExtra("reservoirId");
                    String reservoir = data.getStringExtra("reservoir");
                    if (isKeyBack){
                        return;
                    }
                    if (isSelectAll) {
                        tvReservoir.setText("全部");
                        this.reservoirId = "";
                        return;
                    }
                    if (!reservoirId.equals(backReservoirId)) {
                        tvReservoir.setText(reservoir);
                        reservoirId = backReservoirId;
                    }
                }
//                ReservoirBean defaultReservoir = UserManager.getInstance().getDefaultReservoir();
//                if (!reservoirId.equals(defaultReservoir.getReservoirId())) {
//                    tvReservoir.setText(defaultReservoir.getReservoir());
//                    reservoirId = defaultReservoir.getReservoirId();
//                }
                break;
            default:
                break;
        }
    }
}
