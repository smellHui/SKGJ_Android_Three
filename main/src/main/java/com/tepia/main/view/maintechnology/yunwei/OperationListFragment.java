package com.tepia.main.view.maintechnology.yunwei;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.view.maintechnology.yunwei.adapter.MyOperationListAdapter;

import java.util.ArrayList;

/**
 * Created by      Intellij IDEA
 *  运维列表
 * @author :       wwj
 * Date    :       2018-09-18
 * Time    :       10:31
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class OperationListFragment extends BaseCommonFragment {
    private ArrayAdapter spinnerAdapter;
    private TextView tvOperationTask;
    private Spinner spinner;
    private String[] reservoirs = {"全部","绿竹坝水库", "沙坝水库", "葡萄水库", "烂碑堰水库", "青年塘水库", "龙岩水库", "水洞水库", "岩门水库", "朱村水库", "清水河水库", "浒洋水库", "茅坪水库", "红星水库", "石关水库", "八幅堰水库", "东风水库", "胜利堰水库", "木斯千水库", "苦寨田水库"};
    private int spinnerPosition;
    private RecyclerView rv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operation_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        tvOperationTask = findView(R.id.tv_operation_task);
        spinner = findView(R.id.operation_spinner);
        rv = findView(R.id.rv_operation_list);
        String str="巡检任务:<font color='#e3654d'>20次</font>";
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(str);
        }
        tvOperationTask.setText(result);
        initSpinner();
        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i+"");
        }
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyOperationListAdapter rvAdapter = new MyOperationListAdapter(R.layout.operation_list_item, strings);
        rv.setAdapter(rvAdapter);
    }

    private void initSpinner() {
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

    @Override
    protected void initRequestData() {

    }
}
