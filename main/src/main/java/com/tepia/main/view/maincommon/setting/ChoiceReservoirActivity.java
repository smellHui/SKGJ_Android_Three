package com.tepia.main.view.maincommon.setting;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.setting.adapter.MySelectReservoirAdapter;

import java.util.ArrayList;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/16
  * Version :1.0
  * 功能描述 :搜索水库
 **/

public class ChoiceReservoirActivity extends BaseActivity {
    public static final int resultCode = 1001;
    public static String isAllReservoir = "isAllReservoir";

    private EditText etSearch;
    private RecyclerView rvSelectReservoir;
    private ArrayList<ReservoirBean> localReservoirList;
    private ArrayList<ReservoirBean> reservoirList = new ArrayList<>();
    private ArrayList<ReservoirBean> searchReservoirList = new ArrayList<>();
    private MySelectReservoirAdapter mySelectReservoirAdapter;
    private boolean isSelectAll;

    public static Intent setIntent(Intent intent, boolean isSelectAll) {
        intent.putExtra("isSelectAll", isSelectAll);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_reservoir;
    }

    @Override
    public void initView() {
        setCenterTitle("选择水库");
        showBack();
        isSelectAll = getIntent().getBooleanExtra("isSelectAll", false);
        etSearch = findViewById(R.id.et_search);
        rvSelectReservoir = findViewById(R.id.rv_select_reservoir);
        initRecyclerView();
        initSearch();
    }

    private void initSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchStr = s.toString().replaceAll(" ", "");
                if (null!=reservoirList&&reservoirList.size()>0){
                    searchReservoirList.clear();
                    for (int i = 0; i < reservoirList.size(); i++) {
                        String reservoir =  reservoirList.get(i).getReservoir();
                        if (reservoir.contains(searchStr)){
                            searchReservoirList.add(reservoirList.get(i));
                        }
                    }
                    if (null!=searchReservoirList&&searchReservoirList.size()>0){
                        mySelectReservoirAdapter.notifyDataSetChanged();
                    }else {
                        mySelectReservoirAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                        mySelectReservoirAdapter.notifyDataSetChanged();
                    }
                }else {
                    mySelectReservoirAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                    mySelectReservoirAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initRecyclerView() {
        rvSelectReservoir.setLayoutManager(new LinearLayoutManager(this));
        mySelectReservoirAdapter = new MySelectReservoirAdapter(R.layout.lv_select_reservoir_item, searchReservoirList);
        rvSelectReservoir.setAdapter(mySelectReservoirAdapter);
        if (null!=localReservoirList){
            if (isSelectAll){
                ReservoirBean reservoirBean = new ReservoirBean();
                reservoirBean.setReservoir("全部");
                searchReservoirList.add(reservoirBean);
            }
            searchReservoirList.addAll(localReservoirList);
            reservoirList.addAll(searchReservoirList);
            mySelectReservoirAdapter.notifyDataSetChanged();
        }
        mySelectReservoirAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (null!=searchReservoirList&&searchReservoirList.size()>=(position)){
                ReservoirBean reservoirBean = searchReservoirList.get(position);
                if ("全部".equals(reservoirBean.getReservoir())){
                    Intent intent = new Intent();
                    intent.putExtra(isAllReservoir,true);
                    ChoiceReservoirActivity.this.setResult(resultCode,intent);
                    finish();
                    return;
                }
                UserManager.getInstance().saveDefaultReservoir(reservoirBean);
                ChoiceReservoirActivity.this.setResult(resultCode);
                finish();
            }

        });
    }

    @Override
    public void initData() {
        localReservoirList = UserManager.getInstance().getLocalReservoirList();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
