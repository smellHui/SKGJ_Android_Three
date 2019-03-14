package com.tepia.main.view.main.map;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.map.MapCommonResponse;
import com.tepia.main.model.map.PictureResponse;
import com.tepia.main.model.map.RainfallResponse;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.model.map.StRiverResponse;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.model.map.WaterLevelResponse;
import com.tepia.main.model.map.WaterQualityResponse;
import com.tepia.main.view.main.map.adapter.CommonModel;
import com.tepia.main.view.main.map.adapter.search.MapSearchAdapter;
import com.tepia.main.view.main.map.adapter.search.SearchModel;
import com.tepia.main.view.main.map.presenter.MainMapContract;
import com.tepia.main.view.main.map.presenter.MainMapPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
  * Created by      Android studio
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/11/21
  * Version :1.0
  * 功能描述 :  地图搜索
 **/
public class MapSearchFragment extends MVPBaseFragment<MainMapContract.View, MainMapPresenter> {
    private Spinner spinner;
    private ArrayAdapter adapter;
    private static final String[] name = {"水库", "流量站", "水质站", "雨量站", "水位站", "图像站", "视频站"};
    private int position = 0;
    private RecyclerView rvSearchView;
    private MapSearchAdapter mapSearchAdapter;
    private EditText et_search;
    private List<SearchModel> dataList;
    private ImageView ivSearchBack;
    private OnAddBackClickListener onAddBackClickListener;
    private OnSearchListClickListener onSearchListClickListener;


    public void setOnAddBackClickListener(OnAddBackClickListener onAddBackClickListener) {
        this.onAddBackClickListener = onAddBackClickListener;
    }

    public void setOnSearchListClickListener(OnSearchListClickListener onSearchListClickListener) {
        this.onSearchListClickListener = onSearchListClickListener;
    }

    public interface OnAddBackClickListener {
        void onCilck();
    }

    public interface OnSearchListClickListener {
        void onCilck(SearchModel searchModel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_map_classified_search;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner1);
        et_search = findView(R.id.et_search);
        ImageView ivSearchBack = findView(R.id.iv_search_back);
        ivSearchBack.setOnClickListener(v -> onAddBackClickListener.onCilck());
        initSpinner();
        initRecyclerView();
        initSearch();
    }

    private void initSpinner() {
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, name);
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
    }

    private void initRecyclerView() {
        //模拟的数据（实际开发中一般是从网络获取的）
        dataList = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            datas.add("第"+i+"条数据");
//        }
        rvSearchView = findView(R.id.rv_search_list);
        rvSearchView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mapSearchAdapter = new MapSearchAdapter(R.layout.search_item_rv, dataList);
        View emptyView = getLayoutInflater().inflate(R.layout.view_empty_list_view, null);
        mapSearchAdapter.setEmptyView(emptyView);
        //添加Android自带的分割线
        rvSearchView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvSearchView.setAdapter(mapSearchAdapter);

        mapSearchAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (null != dataList && dataList.size() > 0) {
                onSearchListClickListener.onCilck(dataList.get(position));
            }
        });
    }

    private void initSearch() {
        Button btSearch = findView(R.id.bt_search);
        btSearch.setOnClickListener(v -> {
            String s = et_search.getText().toString().replace(" ", "");
            dataList.clear();
            mapSearchAdapter.notifyDataSetChanged();
            if (position == 0) {
                //水库
                requestReservoir(s);
            } else if (position == 1) {
                //流量站
                requestStRiver(s);
            } else if (position == 2) {
                //水质站
                requsetStWqB(s);
            }else if (position==3){
                //雨量站
                requestRainfall(s);
            }else if(position == 4){
                //水位监测点
                requestAllWaterLevel(s);
            }else if (position == 5){
                //图像站
                requestAllPicture(s);
            }else if (position == 6){
                //视频站
                requestVideo(s);
            }
        });

    }

    private List<VideoResponse.DataBean> videoDatas;
    /**
     * 根据名称搜索视频站
     * @param s
     */
    private void requestVideo(String s) {
        mPresenter.attachView(new MainMapContract.View<VideoResponse>() {
            @Override
            public void success(VideoResponse videoResponse) {
                int code = videoResponse.getCode();
                if (code == 0) {
                    videoDatas = videoResponse.getData();
                    if (videoDatas != null && videoDatas.size() > 0) {
                        Gson gson = new Gson();
                        for (int i = 0; i < videoDatas.size(); i++) {
                            SearchModel searchModel = new SearchModel();
                            searchModel.setName(videoDatas.get(i).getVsnm());
                            String lgtd = videoDatas.get(i).getLgtd();
                            String lttd = videoDatas.get(i).getLttd();
                            searchModel.setTypeId(6);
                            searchModel.setType(name[6]);
                            String searchString = gson.toJson(videoDatas.get(i));
                            searchModel.setSearchString(searchString);
                            if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                searchModel.setLgtd(Double.parseDouble(lgtd));
                                searchModel.setLttd(Double.parseDouble(lttd));
                            }
                            dataList.add(searchModel);
                        }
                        mapSearchAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.findAllVsVideo(s);
    }

    private List<MapCommonResponse.DataBean> pictureDatas;
    /**
     * 根据名称搜索图像站
     * @param s
     */
    private void requestAllPicture(String s) {
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse pictureResponse) {
                int code = pictureResponse.getCode();
                if (code == 0) {
                    pictureDatas = pictureResponse.getData();
                    if (pictureDatas != null && pictureDatas.size() > 0) {
                        Gson gson = new Gson();
                        for (int i = 0; i < pictureDatas.size(); i++) {
                            SearchModel searchModel = new SearchModel();
                            searchModel.setName(pictureDatas.get(i).getStnm());
                            String lgtd = pictureDatas.get(i).getLgtd();
                            String lttd = pictureDatas.get(i).getLttd();
                            searchModel.setTypeId(5);
                            searchModel.setType(name[5]);
                            searchModel.setStcd(pictureDatas.get(i).getStcd());
                            if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                searchModel.setLgtd(Double.parseDouble(lgtd));
                                searchModel.setLttd(Double.parseDouble(lttd));
                            }
                            dataList.add(searchModel);
                        }
                        mapSearchAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType(s, "ii", "");
    }

    private List<MapCommonResponse.DataBean> waterLevelDatas;

    /**
     * 根据名称搜索水位
     */
    private void requestAllWaterLevel(String s) {
        Date date = new Date();
        String formatPattern = "yyyy-MM-dd";
        String baseDate = new SimpleDateFormat(formatPattern).format(date);
        String startDate = baseDate + " 00:00:00";
        String endDate = baseDate + " 23:59:59";
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse waterLevelResponse) {
                int code = waterLevelResponse.getCode();
                if (code == 0) {
                    waterLevelDatas = waterLevelResponse.getData();
                    if (waterLevelDatas != null && waterLevelDatas.size() > 0) {
                        Gson gson = new Gson();
                        for (int i = 0; i < waterLevelDatas.size(); i++) {
                            SearchModel searchModel = new SearchModel();
                            searchModel.setName(waterLevelDatas.get(i).getStnm());
                            String lgtd = waterLevelDatas.get(i).getLgtd();
                            String lttd = waterLevelDatas.get(i).getLttd();
                            searchModel.setTypeId(4);
                            searchModel.setType(name[4]);
                            searchModel.setStcd(waterLevelDatas.get(i).getStcd());
                            if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                searchModel.setLgtd(Double.parseDouble(lgtd));
                                searchModel.setLttd(Double.parseDouble(lttd));
                            }
                            dataList.add(searchModel);
                        }
                        mapSearchAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType(s, "rr", "");
    }

    private List<MapCommonResponse.DataBean> rainfallDatas;

    /**
     * 根据名称搜索雨量站
     * @param s
     */
    private void requestRainfall(String s) {
        Date date = new Date();
        String formatPattern = "yyyy-MM-dd";
        String baseDate = new SimpleDateFormat(formatPattern).format(date);
        String startDate = baseDate + " 00:00:00";
        String endDate = baseDate + " 23:59:59";
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse rainfallResponse) {
                int code = rainfallResponse.getCode();
                if (code == 0) {
                    rainfallDatas = rainfallResponse.getData();
                    if (rainfallDatas != null && rainfallDatas.size() > 0) {
                        Gson gson = new Gson();
                        for (int i = 0; i < rainfallDatas.size(); i++) {
                            SearchModel searchModel = new SearchModel();
                            searchModel.setName(rainfallDatas.get(i).getStnm());
                            String lgtd = rainfallDatas.get(i).getLgtd();
                            String lttd = rainfallDatas.get(i).getLttd();
                            searchModel.setTypeId(3);
                            searchModel.setType(name[3]);
                            searchModel.setStcd(rainfallDatas.get(i).getStcd());
                            if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                searchModel.setLgtd(Double.parseDouble(lgtd));
                                searchModel.setLttd(Double.parseDouble(lttd));
                            }
                            dataList.add(searchModel);
                        }
                        mapSearchAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType(s, "pp", "");
    }

    private List<MapCommonResponse.DataBean> waterQualityDatas;

    /**
     * 根据名称搜索水质站
     * @param s
     */
    private void requsetStWqB(String s) {
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse waterQualityResponse) {
                int code = waterQualityResponse.getCode();
                if (code == 0) {
                    waterQualityDatas = waterQualityResponse.getData();
                    if (waterQualityDatas != null && waterQualityDatas.size() > 0) {
                        Gson gson = new Gson();
                        for (int i = 0; i < waterQualityDatas.size(); i++) {
                            SearchModel searchModel = new SearchModel();
                            searchModel.setName(waterQualityDatas.get(i).getStnm());
                            String lgtd = waterQualityDatas.get(i).getLgtd();
                            String lttd = waterQualityDatas.get(i).getLttd();
                            searchModel.setTypeId(2);
                            searchModel.setType(name[2]);
//                            String searchString = gson.toJson(waterQualityDatas.get(i));
//                            searchModel.setSearchString(searchString);
                            if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                searchModel.setLgtd(Double.parseDouble(lgtd));
                                searchModel.setLttd(Double.parseDouble(lttd));
                            }
                            dataList.add(searchModel);
                        }
                        mapSearchAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType(s, "wq", "");
    }

    private List<ReservoirBean> reservoirDatas;

    /**
     * 根据名称搜索水库
     *
     * @param s
     */
    private void requestReservoir(String s) {
        mPresenter.attachView(new MainMapContract.View<ReservoirListResponse>() {

            @Override
            public Context getContext() {
                return null;
            }

            @Override
            public void success(ReservoirListResponse reservoirResponse) {
                int code = reservoirResponse.getCode();
                if (code == 0) {
                    reservoirDatas = reservoirResponse.getData();
                    if (reservoirDatas != null && reservoirDatas.size() > 0) {
                        Gson gson = new Gson();
                        for (int i = 0; i < reservoirDatas.size(); i++) {
                            SearchModel searchModel = new SearchModel();
                            searchModel.setName(reservoirDatas.get(i).getReservoir());
                            String lgtd = reservoirDatas.get(i).getReservoirLongitude();
                            String lttd = reservoirDatas.get(i).getReservoirLatitude();
                            searchModel.setTypeId(0);
                            searchModel.setType(name[0]);
                            searchModel.setStcd(reservoirDatas.get(i).getReservoirId());
//                            String searchString = gson.toJson(reservoirDatas.get(i));
//                            searchModel.setSearchString(searchString);
                            if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                searchModel.setLgtd(Double.parseDouble(lgtd));
                                searchModel.setLttd(Double.parseDouble(lttd));
                            }
                            dataList.add(searchModel);
                        }
                        mapSearchAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }
        });
        mPresenter.findAppAllReservoir(s,"",true);
    }

    private List<MapCommonResponse.DataBean> stRiverDatas;

    /**
     * 根据名称搜索流量
     * @param s
     */
    private void requestStRiver(String s) {
        Date date = new Date();
        String formatPattern = "yyyy-MM-dd";
        String baseDate = new SimpleDateFormat(formatPattern).format(date);
        String startDate = baseDate + " 00:00:00";
        String endDate = baseDate + " 23:59:59";
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse stRiverResponse) {
                int code = stRiverResponse.getCode();
                if (code == 0) {
                    stRiverDatas = stRiverResponse.getData();
                    if (stRiverDatas != null && stRiverDatas.size() > 0) {
                        for (int i = 0; i < stRiverDatas.size(); i++) {
                            SearchModel searchModel = new SearchModel();
                            searchModel.setName(stRiverDatas.get(i).getStnm());
                            String lgtd = stRiverDatas.get(i).getLgtd();
                            String lttd = stRiverDatas.get(i).getLttd();
                            searchModel.setTypeId(1);
                            searchModel.setType(name[1]);
                            searchModel.setStcd(stRiverDatas.get(i).getStcd());
                            if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                searchModel.setLgtd(Double.parseDouble(lgtd));
                                searchModel.setLttd(Double.parseDouble(lttd));
                            }
                            dataList.add(searchModel);
                        }
                        mapSearchAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType(s, "zq", "");
    }

    @Override
    protected void initRequestData() {

    }


}
