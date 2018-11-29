package com.tepia.main.view.main.map;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.map.adapter.near.MyNearReservoirAdapter;
import com.tepia.main.view.main.map.adapter.near.NearReservoirResponse;
import com.tepia.main.view.main.map.adapter.search.SearchModel;
import com.tepia.main.view.main.map.presenter.MainMapContract;
import com.tepia.main.view.main.map.presenter.MainMapPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018/11/22
 * Version :1.0
 * 功能描述 :  附近水库
 **/
public class NearReservoirFragment extends BaseCommonFragment {
    private static final String[] name = {"水库", "流量站", "水质站", "雨量站", "水位站", "图像站", "视频站"};
    private EditText etSearch;
    private MainMapPresenter mPresenter;
    private MapSearchFragment.OnAddBackClickListener onAddBackClickListener;
    private List<NearReservoirResponse.DataBean> dataList;
    private RecyclerView rv;
    private MyNearReservoirAdapter mAdapter;
    private List<NearReservoirResponse.DataBean> nearReservoirs;
    private double lgtd;
    private double lttd;

    public void setOnAddBackClickListener(MapSearchFragment.OnAddBackClickListener onAddBackClickListener) {
        this.onAddBackClickListener = onAddBackClickListener;
    }

    private MapSearchFragment.OnSearchListClickListener onSearchListClickListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_near_reservoir;
    }

    public void setOnSearchListClickListener(MapSearchFragment.OnSearchListClickListener onSearchListClickListener) {
        this.onSearchListClickListener = onSearchListClickListener;
    }

    public static NearReservoirFragment newInstance(double lgtd, double lttd) {

        NearReservoirFragment f = new NearReservoirFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("lgtd", lgtd);
        bundle.putDouble("lttd", lttd);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void initData() {
        if (getArguments() != null && getArguments().containsKey("lgtd") && getArguments().containsKey("lttd")) {
            lgtd = getArguments().getDouble("lgtd", 0);
            lttd = getArguments().getDouble("lttd", 0);
//            ToastUtils.longToast(lgtd+"--"+lttd);
        }
        mPresenter = new MainMapPresenter();
        mPresenter.attachView(new MainMapContract.View<NearReservoirResponse>() {
            @Override
            public void success(NearReservoirResponse nearReservoirResponse) {
                if (null != nearReservoirResponse.getData() && nearReservoirResponse.getData().size() > 0) {
                    nearReservoirs = nearReservoirResponse.getData();
                    dataList.clear();
                    dataList.addAll(nearReservoirs);
                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter.setEmptyView(EmptyLayoutUtil.show("附近无水库"));
                }
//                ToastUtils.longToast("size:"+nearReservoirResponse.getData().size());
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
                mAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initView(View view) {
//        setCenterTitle("附近水库");
        etSearch = findView(R.id.et_search);
        ImageView ivSearchBack = findView(R.id.iv_search_back);
        ivSearchBack.setOnClickListener(v -> onAddBackClickListener.onCilck());
//        TextView leftBack = findView(R.id.tv_left_text);
//        leftBack.setOnClickListener(v -> onAddBackClickListener.onCilck());
        rv = findView(R.id.rv_near_reservoir);
        initRecycleView();
        initSearch();
        initRequestResponse();
    }

    private void initRequestResponse() {
        //"107.033107","27.470493"
        mPresenter.getNearbyReservoir(String.valueOf(lgtd), String.valueOf(lttd));
    }

    private void initSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchStr = s.toString().replaceAll(" ", "");
                if (null != nearReservoirs && nearReservoirs.size() > 0) {
                    dataList.clear();
                    for (int i = 0; i < nearReservoirs.size(); i++) {
                        String reservoir = nearReservoirs.get(i).getReservoir();
                        if (reservoir.contains(searchStr)) {
                            dataList.add(nearReservoirs.get(i));
                        }
                    }
                    if (null != dataList && dataList.size() > 0) {
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    mAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initRecycleView() {
        dataList = new ArrayList<>();
        nearReservoirs = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MyNearReservoirAdapter(R.layout.search_item_rv, dataList);
        //添加Android自带的分割线
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (null != dataList && dataList.size() > 0) {
                NearReservoirResponse.DataBean dataBean = dataList.get(position);
                SearchModel searchModel = new SearchModel();
                searchModel.setName(dataBean.getReservoir());
                String lgtd = dataBean.getReservoirLongitude();
                String lttd = dataBean.getReservoirLatitude();
                searchModel.setTypeId(0);
                searchModel.setType(name[0]);
                searchModel.setStcd(dataBean.getReservoirId());
                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                    searchModel.setLgtd(Double.parseDouble(lgtd));
                    searchModel.setLttd(Double.parseDouble(lttd));
                }
                onSearchListClickListener.onCilck(searchModel);
            }
        });
    }

    @Override
    protected void initRequestData() {

    }
}
