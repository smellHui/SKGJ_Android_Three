package com.tepia.main.view.main.jihua;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.main.R;
import com.tepia.main.model.question.ReservoirBean;
import com.tepia.main.model.question.ReservoirDateBean;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.jihua.presenter.InspectionPlanContract;
import com.tepia.main.view.main.jihua.presenter.InspectionPlanPresenter;
import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.main.question.QuestionContract;
import com.tepia.main.view.main.question.QuestionPresenter;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 添加计划
 * @author 44822
 */
public class AddPlanActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar loToolbarCommon;
    private TextView tvCenterText;
    private TextView tvLeftText;
    private TextView tvRightText;
    private long lastClick;

    private Spinner spinner;
    private Spinner spinner2;
    private ArrayAdapter adapter;
    private static final String[] name = {"日常巡检", "定期巡检", "特别巡检"};
//    运维类别1：巡检；2：维修养护；3：保洁
    private String[] operations = {"巡检","维修养护","保洁"};
    private String[] reservoirs = {"绿竹坝水库", "沙坝水库", "葡萄水库", "烂碑堰水库", "青年塘水库", "龙岩水库", "水洞水库", "岩门水库", "朱村水库", "清水河水库", "浒洋水库", "茅坪水库", "红星水库", "石关水库", "八幅堰水库", "东风水库", "胜利堰水库", "木斯千水库", "苦寨田水库"};
    private int position = 0;
    private int operationPosition = 0;
    private AutoCompleteTextView acTextView;
    private MyAdapter myAdapter;
    private EditText etPlanName;
    private EditText etRemarks;

    private boolean isAddPlan;
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_add_plan;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_add_plan);
        initToolBar();
        initView();
    }

    /**
     * 初始化导航栏
     */
    protected void initToolBar() {
        loToolbarCommon = findViewById(com.tepia.base.R.id.lo_toolbar_common);
        tvCenterText = findViewById(com.tepia.base.R.id.tv_center_text);
        tvLeftText = findViewById(com.tepia.base.R.id.tv_left_text);
        tvRightText = findViewById(com.tepia.base.R.id.tv_right_text);
        if (loToolbarCommon != null) {
            //将Toolbar显示到界面
            setSupportActionBar(loToolbarCommon);
        }
        if (tvCenterText != null) {
            //getTitle()的值是activity的android:lable属性值
            tvCenterText.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
    /**
     * 设置头部标题居中
     *
     * @param title
     */
    public void setCenterTitle(CharSequence title) {
        if (tvCenterText != null) {
            loToolbarCommon.setVisibility(View.VISIBLE);
            tvCenterText.setVisibility(View.VISIBLE);
            tvLeftText.setVisibility(View.INVISIBLE);
            tvCenterText.setText(title);
            //设置无图标
            tvCenterText.setCompoundDrawables(null, null, null, null);
        } else {
            loToolbarCommon.setTitle(title);
            setSupportActionBar(loToolbarCommon);
        }
    }

    public void showBack() {
        if (loToolbarCommon == null) {
            return;
        }
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        loToolbarCommon.setNavigationIcon(null);
        tvLeftText.setVisibility(View.VISIBLE);
        tvLeftText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFastClick()) {
                    Intent intent = new Intent();
                    intent.putExtra("isAddPlan",isAddPlan);
                    AddPlanActivity.this.setResult(1, intent);
                    finish();
                }
            }
        });
    }

    public boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }
    public void initView() {
        setCenterTitle("新增计划");
        showBack();
        initSpinner();
//        initReservoirs();
        acTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        etPlanName = findViewById(R.id.et_plan_name);
        etRemarks = findViewById(R.id.et_remarks);
        initAutoCompleteTextView();
        initAddDate();
        Button btAdd = findViewById(R.id.bt_add);
        btAdd.setOnClickListener(v -> {
            addPlan();
        });
    }

    private SimpleLoadDialog simpleLoadDialog;
    private void addPlan() {
        simpleLoadDialog = new SimpleLoadDialog(this,"正在添加中...",true);
        simpleLoadDialog.show();
        String planName = etPlanName.getText().toString();
        String planType = "";
        switch (position) {
            case 0:
                planType = "1";
                break;
            case 1:
                planType = "2";
                break;
            case 2:
                planType = "3";
                break;
            default:
                break;
        }
        String operationType = "";
        switch (position) {
            case 0:
                operationType = "1";
                break;
            case 1:
                operationType = "2";
                break;
            case 2:
                operationType = "3";
                break;
            default:
                break;
        }
        String reservoirName = acTextView.getText().toString();
        String reservoirId = "";
        if (reservoirDatas!=null&&reservoirDatas.size()>0){
            for (int i = 0; i < reservoirDatas.size(); i++) {
                if (reservoirName.equals(reservoirDatas.get(i).getReservoir())){
                    reservoirId = reservoirDatas.get(i).getReservoirId();
                }
            }
        }
        String planTimes = "";
        ArrayList<String> planTimeList = new ArrayList<>();
        if (addDateNameView.getChildCount() > 0) {
            for (int i = 0; i < addDateNameView.getChildCount(); i++) {
                final View childAt = addDateNameView.getChildAt(i);
                TextView tv_start_date = childAt.findViewById(R.id.tv_start_date);
                TextView tvEndDate = childAt.findViewById(R.id.tv_end_date);
                    planTimeList.add(tv_start_date.getText() + "—" + tvEndDate.getText());
            }
        }
        if (planTimeList.size() > 0) {
            for (int i = 0; i < planTimeList.size(); i++) {
                if (i == planTimeList.size() - 1) {
                    planTimes += planTimeList.get(i);
                } else {
                    planTimes += planTimeList.get(i) + ",";
                }
            }
        }
//        LogUtil.i("planTimes:"+planTimes);
        String remarks = etRemarks.getText().toString();
        InspectionPlanPresenter mPresenter = new InspectionPlanPresenter();
        mPresenter.attachView(new InspectionPlanContract.View<BaseResponse>() {
            @Override
            public void success(BaseResponse data) {
//                LogUtil.i("code:" + data.getCode());
                simpleLoadDialog.dismiss();
                ToastUtils.longToast("添加成功");
                isAddPlan = true;
                Intent intent = new Intent();
                intent.putExtra("isAddPlan",isAddPlan);
                AddPlanActivity.this.setResult(1, intent);
                finish();
            }

            @Override
            public void failure(String msg) {
//                LogUtil.i("msg" + msg);
                simpleLoadDialog.dismiss();
                isAddPlan = false;
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
//        planType      计划类型:(1：日常；2：定期；3：特别)
//        operationType     运维类别1：巡检；2：维修养护；3：保洁
        if ("".equals(reservoirId)){
            simpleLoadDialog.dismiss();
            ToastUtils.shortToast("水库名称不正确");
            return;
        }
        if (TextUtils.isEmpty(planName)){
            simpleLoadDialog.dismiss();
            ToastUtils.shortToast("计划名称不能为空");
            return;
        }
        mPresenter.saveBizPlanInfo(planType, operationType, reservoirId, planName, planTimes, remarks, "");


    }

    private boolean isLoadReservoirs = false;
    private List<ReservoirDateBean> reservoirDatas;
    private void initReservoirs() {
        //获取新增计划水库数组
        QuestionPresenter questionPresenter = new QuestionPresenter();
        questionPresenter.listReservoirInCenter("");
        questionPresenter.attachView(new QuestionContract.View<ReservoirBean>() {

            @Override
            public Context getContext() {
                return null;
            }

            @Override
            public void success(ReservoirBean reservoirBean) {
//                LogUtil.i("水库:" + reservoirBean.getData().size());
                reservoirDatas = reservoirBean.getData();
                if (reservoirDatas != null && reservoirDatas.size() > 0) {
                    reservoirList.clear();
                    for (int i = 0; i < reservoirDatas.size(); i++) {
                        reservoirList.add(reservoirDatas.get(i).getReservoir());
                    }
                    myAdapter.notifyDataSetChanged();
                }
                isLoadReservoirs = true;
            }

            @Override
            public void failure(String msg) {
                if (!isLoadReservoirs) {
                    boolean networkConnected = NetUtil.isNetworkConnected(getContext());
                    if (networkConnected) {
                        initReservoirs();
                    } else {
                        initReservoirs();
                        ToastUtils.shortToast("当前没网络");
                    }
                }
            }
        });
    }

//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    protected void initListener() {
//
//    }
//
//    @Override
//    protected void initRequestData() {
//
//    }

    //装在所有动态添加的Item的LinearLayout容器
    private LinearLayout addDateNameView;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private void initAddDate() {
        timePickerDialogUtil = new TimePickerDialogUtil(this, sf);
        addDateNameView = findViewById(R.id.ll_addView);
        //默认添加一个Item
        addViewItem(null);
    }


    //添加ViewItem
    private void addViewItem(View view) {
        if (addDateNameView.getChildCount() == 0) {//如果一个都没有，就添加一个
            View hotelEvaluateView = View.inflate(this, R.layout.add_plan_item_add_date, null);
            Button btn_add = (Button) hotelEvaluateView.findViewById(R.id.btn_addDate);
            btn_add.setText("+新增");
            btn_add.setTag("add");
            btn_add.setOnClickListener(this);
            LinearLayout llStartDate = hotelEvaluateView.findViewById(R.id.ll_start_date);
            TextView tv_start_date = hotelEvaluateView.findViewById(R.id.tv_start_date);
            tv_start_date.setText(TimeFormatUtils.dateToStrLong(TimeFormatUtils.getNow()));
            LinearLayout llEndDate = hotelEvaluateView.findViewById(R.id.ll_end_date);
            TextView tv_end_date = hotelEvaluateView.findViewById(R.id.tv_end_date);
            tv_end_date.setText(TimeFormatUtils.dateToStrLong(TimeFormatUtils.getNow()));
            llStartDate.setOnClickListener(v -> addDate(v, 0, true));
            llEndDate.setOnClickListener(v -> addDate(v, 0, false));
            addDateNameView.addView(hotelEvaluateView);
            //sortHotelViewItem();
        } else if (((String) view.getTag()).equals("add")) {//如果有一个以上的Item,点击为添加的Item则添加
            View hotelEvaluateView = View.inflate(this, R.layout.add_plan_item_add_date, null);
            TextView tv_start_date = hotelEvaluateView.findViewById(R.id.tv_start_date);
            tv_start_date.setText(TimeFormatUtils.dateToStrLong(TimeFormatUtils.getNow()));
            TextView tv_end_date = hotelEvaluateView.findViewById(R.id.tv_end_date);
            tv_end_date.setText(TimeFormatUtils.dateToStrLong(TimeFormatUtils.getNow()));
            addDateNameView.addView(hotelEvaluateView);
            sortDateViewItem();
        }
        //else {
        //  sortHotelViewItem();
        //}
    }

    /**
     * 添加日期
     *
     * @param v
     * @param i       日期列表的position
     * @param isStart
     */
    private void addDate(View v, int i, boolean isStart) {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;

        if (isStart) {
            //开始时间
            long currentLong = 0;
//            LogUtil.i("ll_start_date");
            View childAt = addDateNameView.getChildAt(i);
            TextView tvStartDate = v.findViewById(R.id.tv_start_date);
            //找到对应的结束日期控件
            TextView tv_end_date = childAt.findViewById(R.id.tv_end_date);
            String current = (String) tvStartDate.getText();
            currentLong = strToLong(current);
//            LogUtil.i("currentLong:" + currentLong);
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                String endString = tv_end_date.getText().toString();
                long endMillsecond = TimeFormatUtils.strToLong(endString);
//                LogUtil.i("结束时间:"+endMillsecond);
//                LogUtil.i("开始时间"+millseconds);
                tvStartDate.setText(text);
              //设置结束日期时间为对应开始日期的之后3分钟
                if (millseconds>endMillsecond){
                    String endText = timePickerDialogUtil.getDateToString(millseconds+3000 * 60);
                    tv_end_date.setText(endText);
                }
//                LogUtil.e("startTime", text);
            }, Type.ALL, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId(getApplicationContext().getString(R.string.starttimeTitle));
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");
        } else {
            //结束时间
//            LogUtil.i("ll_end_date");
            //拿到对应的开始时间
            View childAt = addDateNameView.getChildAt(i);
            TextView tv_start_date = childAt.findViewById(R.id.tv_start_date);
            String startDate = (String) tv_start_date.getText();
//            LogUtil.i("startDate:" + startDate);
            long startLong = strToLong(startDate);
//            LogUtil.i("startLong:" + startLong);
            long currentLong = 0;
            TextView tvEndDate = v.findViewById(R.id.tv_end_date);
            String current = (String) tvEndDate.getText();
            currentLong = strToLong(current);
//            LogUtil.i("currentLong:" + currentLong);
            //设置结束日期开始时间为对应开始日期的之后3分钟
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvEndDate.setText(text);
//                LogUtil.e("endTime", text);
            }, Type.ALL, startLong + 3000 * 60, startLong + fiveYears);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId(getApplicationContext().getString(R.string.endtimeTitle));
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");

        }
    }

    private ArrayList<String> reservoirList = new ArrayList<>();

    private void initAutoCompleteTextView() {
        for (int i = 0; i < reservoirs.length; i++) {
            reservoirList.add(reservoirs[i]);
        }
        //设置弹出列表的高度
        acTextView.setDropDownHeight(Px2dpUtils.dip2px(this, 200));
        //设置弹出列表距离输入框的高度
        acTextView.setDropDownVerticalOffset(Px2dpUtils.dip2px(this, 2));
        myAdapter = new MyAdapter();
        acTextView.setAdapter(myAdapter);
        acTextView.setOnFocusChangeListener((v, hasFocus) -> {
            AutoCompleteTextView view = (AutoCompleteTextView) v;
            if (hasFocus) {
                view.showDropDown();
            }
        });
        initReservoirs();
    }

    private void initSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner1);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, name);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(R.layout.dropdown_stytle);
        //将adapter2 添加到spinner中
        spinner.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                position = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        //将可选内容与ArrayAdapter连接起来
      ArrayAdapter  adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, operations);
        //设置下拉列表的风格
        adapter2.setDropDownViewResource(R.layout.dropdown_stytle);
        //将adapter2 添加到spinner中
        spinner2.setAdapter(adapter2);
        //添加事件Spinner事件监听
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                operationPosition = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    private TimePickerDialogUtil timePickerDialogUtil;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_addDate) {
            addViewItem(v);
        }
    }

    public static long strToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        //继续转换得到秒数的long型
        long lTime = strtodate.getTime();
        return lTime;
    }

    /**
     * Item排序
     */
    private void sortDateViewItem() {
        //获取LinearLayout里面所有的view
        for (int i = 0; i < addDateNameView.getChildCount(); i++) {
            final View childAt = addDateNameView.getChildAt(i);
            final Button btn_remove = (Button) childAt.findViewById(R.id.btn_addDate);
            btn_remove.setText("-删除");
            btn_remove.setTag("remove");//设置删除标记
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //从LinearLayout容器中删除当前点击到的ViewItem
                    addDateNameView.removeView(childAt);
                }
            });
            //如果是最后一个ViewItem，就设置为添加
            if (i == (addDateNameView.getChildCount() - 1)) {
                Button btn_add = (Button) childAt.findViewById(R.id.btn_addDate);
                btn_add.setText("+新增");
                btn_add.setTag("add");
                btn_add.setOnClickListener(this);
            }
            LinearLayout llStartDate = childAt.findViewById(R.id.ll_start_date);
            LinearLayout llEndDate = childAt.findViewById(R.id.ll_end_date);
            int position = i;
            llStartDate.setOnClickListener(v -> addDate(v, position, true));
            llEndDate.setOnClickListener(v -> addDate(v, position, false));
        }
    }

    class MyAdapter extends BaseAdapter implements Filterable {

        @Override
        public int getCount() {
            return reservoirList.size();
        }

        @Override
        public Object getItem(int position) {
            return reservoirList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = (TextView) getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            } else {
                textView = (TextView) convertView;
            }
            textView.setText(reservoirList.get(position));
            return textView;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    //在这个方法中我们需要实现过滤数据的操作,即：在补全里显示什么数据是在这个方法里决定的
                    //constraint即为用户的输入
                    FilterResults results = new FilterResults();
                    ArrayList<String> newData = new ArrayList<>();
                    if (constraint == null || "".equals(constraint.toString().replace(" ", ""))) {
                        for (int i = 0; i < reservoirs.length; i++) {
                            newData.add(reservoirs[i]);
                        }
                    } else {
                        String s = constraint.toString().replace(" ", "");
                        for (int i = 0; i < reservoirs.length; i++) {
                            if (reservoirs[i].contains(s)) {
                                newData.add(reservoirs[i]);
                            }
                        }
                    }
                    results.values = newData;
                    results.count = newData.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    reservoirList = (ArrayList<String>) results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("isAddPlan",isAddPlan);
            AddPlanActivity.this.setResult(1, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
