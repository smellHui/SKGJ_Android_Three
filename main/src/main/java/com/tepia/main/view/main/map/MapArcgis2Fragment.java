package com.tepia.main.view.main.map;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.layers.OpenStreetMapLayer;
import com.esri.arcgisruntime.location.LocationDataSource;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.google.gson.Gson;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.ScrollLayout.ContentRecyclerView;
import com.tepia.base.view.ScrollLayout.ContentScrollView;
import com.tepia.base.view.ScrollLayout.ScrollLayout;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.map.MapCommonResponse;
import com.tepia.main.model.map.RainfallResponse;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.model.map.WaterLevelResponse;
import com.tepia.main.model.map.WaterQualityResponse;
import com.tepia.main.view.main.detail.imageshow.ImageFragment;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.LiuliangFragment;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.RainFullFragment;
import com.tepia.main.view.main.detail.reservior.ReserviorFragment;
import com.tepia.main.view.main.detail.shuiweizhan.WaterLevelFragment;
import com.tepia.main.view.main.detail.shuizhizhan.WaterQualityFragment;
import com.tepia.main.view.main.detail.vedio.VedioFragment;
import com.tepia.main.view.main.map.adapter.CommonModel;
import com.tepia.main.view.main.map.adapter.DHotelEntityAdapter;
import com.tepia.main.view.main.map.adapter.LHotelEntityAdapter;
import com.tepia.main.view.main.map.adapter.LTntity;
import com.tepia.main.view.main.map.adapter.SectionData;
import com.tepia.main.view.main.map.adapter.search.SearchModel;
import com.tepia.main.view.main.map.presenter.MainMapContract;
import com.tepia.main.view.main.map.presenter.MainMapPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 地图界面
 *
 * @author 44822
 */
public class MapArcgis2Fragment extends MVPBaseFragment<MainMapContract.View, MainMapPresenter> {

    private Context mContext;
    private MapView mapView;
    private ArcGISTiledLayer layer;
    private ArcGISTiledLayer imgLayer;
    private ScrollLayout scroll_down_layout;
    private ScrollLayout scroll_item_layout;
    private DrawerLayout drawerLayout;
    private TextView text_foot;
    private ImageView mIvVector;
    private ImageView mIvRaster;
    private ImageView mImTvVector;
    private TextView mTvVector;
    private ImageView mImTvRaster;
    private TextView mTvRaster;
    private LocationDisplay mLocationDisplay;
    private ContentRecyclerView listRecylcler;
    private ImageView ivArrowBack;
    private ArrayList<LTntity> drawerLayoutDatas;
    private double itemScale = 72223.819286;
    private double groupScale = 1155581.108577;
    private TextView tvMapTitle;
    private Callout callout;
    private LinearLayout llZoom;
    private GraphicsOverlay stRiverOverLay;
    private FragmentTransaction transaction;
    private ImageView imgLayer1;
    private GraphicsOverlay stWQOverLay;
    private GraphicsOverlay rainfallOverLay;
    private GraphicsOverlay waterLevelOverlay;
    private GraphicsOverlay pictureOverlay;
    private TextView scrollItemTitle;
    //详情标题栏
    private LinearLayout detailTitleLl;
    private LinearLayout llTitleMap;
    private TextView tvLlMapTitle;
    private ImageView ivLlArrowBack;
    private DHotelEntityAdapter dHotelEntityAdapter;
    private ContentScrollView itemScrollView;
    private GraphicsOverlay videoOverlay;
    private TextView tvListTitle;
    private FrameLayout flSearchLayout;
    private GraphicsOverlay searchOverlay;
    public boolean isLoaded = false;
    private Point lastPoint;
    private double lastScale;
    private Map<Integer, SparseBooleanArray> layerMap;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    protected void initData() {
        mContext = getActivity();
    }

    /**
     * 图标
     */
    int[] pics = {R.drawable.m_reservior, R.drawable.m_waterflow, R.drawable.m_water_quality, R.drawable.m_rain, R.drawable.m_waterlevel, R.drawable.m_image, R.drawable.m_vedio};
    private HashMap<Integer, int[]> picMap;

//    {
//        picMap = new HashMap<>();
//        picMap.put(0, pics);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View view) {
        picMap = new HashMap<>();
        picMap.put(0, pics);
        mapView = findView(R.id.mapView);
        listRecylcler = findView(R.id.rv_detail_map_pl);
        tvMapTitle = findView(R.id.tv_map_title);
        llZoom = findView(R.id.ll_zoom);
        //默认选中矢量图
        mIvVector = findView(R.id.iv_vector);
        mIvRaster = findView(R.id.iv_raster);
        mImTvVector = findView(R.id.im_tv_vector);
        mTvVector = findView(R.id.tv_vector);
        mImTvRaster = findView(R.id.im_tv_raster);
        mTvRaster = findView(R.id.tv_raster);
        //详情标题栏
        detailTitleLl = findView(R.id.detail_title_ll);
        detailTitleLl.setPadding(0, ScreenUtil.getStatusBarHeight(), 0, 0);
        tvLlMapTitle = findView(R.id.tv_ll_map_title);
        ivLlArrowBack = findView(R.id.iv_ll_arrow_back);
        ivLlArrowBack.setOnClickListener(this::btnClick);
        //地图界面标题栏
        llTitleMap = findView(R.id.ll_title_map);
        setTransparency();
        initMap();
        text_foot = view.findViewById(R.id.text_foot);
        tvListTitle = findView(R.id.tv_list_title);
        ImageView imgLocation = view.findViewById(R.id.img_location);
        ViewTreeObserver viewTreeObserver = mapView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mapHeight = mapView.getHeight();
                Rect viewRect = new Rect();
                imgLocation.getGlobalVisibleRect(viewRect);
                int img_location_top = viewRect.top;
//                Log.i("距离顶部的高度:",viewRect.toString());
////                Log.i("屏幕高度:",""+ ScreenUtil.getScreenHeightPix(mContext));
                initScrollLayout(view, img_location_top);
            }
        });
        initDrawerLayout();
        initListRecycler();
        initOnClick(view);
        //默认加载水库
        initReservoirData();
        initSearchLayout();
    }

    private MapSearchFragment detailFragment = null;

    private void initSearchLayout() {
        ImageView imgSearch = findView(R.id.img_search);
        flSearchLayout = findView(R.id.fl_search_layout);
        flSearchLayout.setPadding(0, ScreenUtil.getStatusBarHeight(), 0, 0);
        imgSearch.setOnClickListener(v -> {
            if (null == detailFragment) {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                detailFragment = new MapSearchFragment();
                detailFragment.setOnAddBackClickListener(() -> {
                    Animator searchAnimation = createSearchAnimation(flSearchLayout, true);
                    searchAnimation.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            flSearchLayout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    searchAnimation.start();
                });
                detailFragment.setOnSearchListClickListener(searchModel -> {
                    setSearchListAdapterClick(searchModel);
                });
                transaction.replace(R.id.fl_search_layout, detailFragment);
                transaction.show(detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                flSearchLayout.setVisibility(View.VISIBLE);
            } else {

                Animator animator = createSearchAnimation(flSearchLayout, false);
                animator.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        flSearchLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();
            }

        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Animator createSearchAnimation(View view, boolean isOn) {
        Animator animator;
        double radio = Math.sqrt(Math.pow(view.getWidth(), 2) + Math.pow(view.getHeight(), 2));
        if (isOn) {
            animator = ViewAnimationUtils.createCircularReveal(
                    view,//操作的视图
                    0,//动画开始的中心点x
                    0,//动画开始的中心点y
                    (float) radio,//动画开始半径
                    0//动画结束的b半径
            );
        } else {
            animator = ViewAnimationUtils.createCircularReveal(
                    view,// 操作的视图
                    0,// 动画开始的中心点X
                    0,// 动画开始的中心点Y
                    0,// 动画开始半径
                    (float) radio);// 动画结束半径
        }
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(700);
        return animator;
    }

    /**
     * 搜索列表的点击事件
     *
     * @param searchModel
     */
    private void setSearchListAdapterClick(SearchModel searchModel) {
        Gson gson = new Gson();
        Map<Integer, SparseBooleanArray> map = dHotelEntityAdapter.getMap();
        SparseBooleanArray sparseBooleanArray = map.get(0);
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            boolean b = sparseBooleanArray.get(i);
//            LogUtil.i(i + "列表选中的状态:" + b);
        }
        //隐藏之前所有的图层
        for (int i = 0; i < overlayList.size(); i++) {
            overlayList.get(i).setVisible(false);
        }
        //情况搜索图层
        searchOverlay.getGraphics().clear();
        searchOverlay.setVisible(true);
        flSearchLayout.setVisibility(View.GONE);
//            LogUtil.i("搜索的类型:"+searchModel.getType());
        if (scroll_item_layout.getVisibility() != View.VISIBLE) {
            hideListShowDetail();
        }
        if (searchModel.getTypeId() == 0) {
            //点击的是水库
            String searchString = searchModel.getSearchString();
            if (null != searchString) {
                ReservoirResponse.DataBean dataBean = gson.fromJson(searchString, ReservoirResponse.DataBean.class);
                initReservoirDetailFragment(dataBean);
            }
        } else if (searchModel.getTypeId() == 1) {
            //点击的是流量站
            String stcd = searchModel.getStcd();
            initStRiverDetailFragment(stcd);
        } else if (searchModel.getTypeId() == 2) {
            //点击的是水质站
            String searchString = searchModel.getSearchString();
            if (null != searchString) {
                String stcd = searchModel.getStcd();
                WaterQualityResponse.DataBean bean = gson.fromJson(searchString, WaterQualityResponse.DataBean.class);
                initWaterQualityDetailFragment(stcd, bean);
            }
        } else if (searchModel.getTypeId() == 3) {
            //点击的是雨量站
            String searchString = searchModel.getSearchString();
            if (null != searchString) {
                String stcd = searchModel.getStcd();
                RainfallResponse.DataBean.StStbprpBBean dataBean = gson.fromJson(searchString, RainfallResponse.DataBean.class).getStStbprpB();
                initRainfallDetailFragment(stcd, dataBean);
            }
        } else if (searchModel.getTypeId() == 4) {
            //点击的是水位站
            String searchString = searchModel.getSearchString();
            if (null != searchString) {
                String stcd = searchModel.getStcd();
                WaterLevelResponse.DataBean.StStbprpBBean stStbprpB = gson.fromJson(searchString, WaterLevelResponse.DataBean.class).getStStbprpB();
                initWaterLevelDetailFragment(stcd, stStbprpB);
            }
        } else if (searchModel.getTypeId() == 5) {
            //点击的是图像站
            String stcd = searchModel.getStcd();
            initPicDetailFragment(stcd);
        } else if (searchModel.getTypeId() == 6) {
            //点击的是视频站
            String searchString = searchModel.getSearchString();
            if (null != searchString) {
                VideoResponse.DataBean dataBean = gson.fromJson(searchString, VideoResponse.DataBean.class);
                initVedioDetailFragment(dataBean);
            }
        }
        double lgtd = searchModel.getLgtd();
        double lttd = searchModel.getLttd();
        Point point = transformationPoint(lgtd, lttd);
        Map<String, Object> attrs = new HashMap<>(1);
        addPic(searchOverlay, picMap.get(0)[searchModel.getTypeId()], point,attrs);
        String name = searchModel.getName();
        if (73.66 < lgtd && lgtd < 135.05 && lttd > 3.86 && lttd < 53.55) {
            mapView.setViewpointCenterAsync(transformationPoint(lgtd, lttd), itemScale).addDoneListener(() -> {
                if (scroll_item_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
                    //上移地图
                    moveMap(true);
                }
            });
        }
        ivArrowBack.setVisibility(View.VISIBLE);
        tvMapTitle.setText(name);
        tvLlMapTitle.setText(name);
        scrollItemTitle.setText(name + "详情");
        if (callout.isShowing()) {
            callout.dismiss();
        }
        initCallout(transformationPoint(lgtd, lttd), name);
    }

    /**
     * 默认加载水库
     */
    private void initReservoirData() {
        boolean networkConnected = NetUtil.isNetworkConnected(mContext);
        if (networkConnected) {
            requestAllReservoir(0, 0);
        } else {
            //没网络
            dHotelEntityAdapter.setItemNotChecked(0, 0);
            ToastUtils.shortToast("当前没网络");
        }
    }

    private TextView tv_head;
    private LHotelEntityAdapter mAdapter;
    private ArrayList<LTntity> dataList;
    /**
     * 记录recycler可见的第一个group的itemPosition
     */
    int pos = 0;

    /**
     * 初始化滑动列表
     */
    private void initListRecycler() {
        FrameLayout scrollListFl = findView(R.id.scroll_list_fl);
        final RelativeLayout ll = findView(R.id.ll_group);
        ll.setOnClickListener(v -> {
        });
        scrollListFl.setOnClickListener(this::btnClick);
        dataList = new ArrayList<>();
        mAdapter = new LHotelEntityAdapter(mContext);
        tv_head = findView(R.id.tv_title_stick);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        listRecylcler.setLayoutManager(manager);
        listRecylcler.setAdapter(mAdapter);
        mAdapter.setOnSetText((holder, section, position) -> (dataList.get(section).list.get(position)));
        mAdapter.setData(dataList);
        //添加粘性头部
        listRecylcler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickyInfoView = recyclerView.getChildAt(0);
                if (dataList != null) {
                    if (stickyInfoView != null) {
                        if (dataList.size() > 0) {
                            int position = 0;
                            if (stickyInfoView.getTag() != null) {
                                position = (int) stickyInfoView.getTag();
                                pos = position;
                            }
                            if (stickyInfoView.getTag(R.id.tag_groupItemIndex) != null) {
                                position = (int) stickyInfoView.getTag(R.id.tag_groupItemIndex);
                                pos = position;
                            }
                            int size = dataList.get(position).list.size();
                            tv_head.setText(dataList.get(position).tagsName + "(" + size + ")");
                        }
                    }
                }
                View transInfoView = recyclerView.findChildViewUnder(ll.getMeasuredWidth() / 2, ll.getMeasuredHeight() + 1);
                if (transInfoView != null) {
                    //得到group的index
                    if (transInfoView.getTag() != null) {
                        int deltaY = transInfoView.getTop() - ll.getMeasuredHeight();
                        //当前可见的第一个item是分类item
                        if (transInfoView.getTop() > 0) {
                            ll.setTranslationY(deltaY);
                        } else {
                            ll.setTranslationY(0);
                        }
                    } else {
                        ll.setTranslationY(0);
                    }
                }
            }
        });
//        initRecycleData();
        setListAdapterClick();
    }

    /**
     * 设置分类列表点击事件
     */
    private void setListAdapterClick() {
        //列表点击事件
        mAdapter.setmOnRecyclerviewItemClickListener((v, section, position) -> {
            hideListShowDetail();
            mAdapterItemClick(section, position);
            String name = dataList.get(section).list.get(position);
            if (drawerLayoutDatas.get(0).list.get(0).equals(dataList.get(section).tagsName)) {
                //点击的是水库
                String reservoirId = reservoirDatas.get(position).getReservoirId();
//                initReservoirDetailFragment(reservoirId);
            } else if (drawerLayoutDatas.get(0).list.get(1).equals(dataList.get(section).tagsName)) {
                //点击的是流量站
                scrollItemTitle.setText(name + "流量站详情");
                String stcd = stRiverDatas.get(position).getStcd();
                initStRiverDetailFragment(stcd);
            } else if (drawerLayoutDatas.get(0).list.get(2).equals(dataList.get(section).tagsName)) {
                //点击的是水质站
                String stcd = waterQualityDatas.get(position).getStcd();
//                WaterQualityResponse.DataBean bean = waterQualityDatas.get(position);
//                initWaterQualityDetailFragment(stcd, bean);
            } else if (drawerLayoutDatas.get(0).list.get(3).equals(dataList.get(section).tagsName)) {
                //点击的是雨量站
                String stcd = rainfallDatas.get(position).getStcd();
                scrollItemTitle.setText(name + "雨量站详情");
//                RainfallResponse.DataBean.StStbprpBBean dataBean = rainfallDatas.get(position).getStStbprpB();
//                initRainfallDetailFragment(stcd, dataBean);
            } else if (drawerLayoutDatas.get(0).list.get(4).equals(dataList.get(section).tagsName)) {
                //点击的是水位监测点
                scrollItemTitle.setText(name + "水位站详情");
                String stcd = waterLevelDatas.get(position).getStcd();
//                initWaterLevelDetailFragment(stcd, waterLevelDatas.get(position).getStStbprpB());
            } else if (drawerLayoutDatas.get(0).list.get(5).equals(dataList.get(section).tagsName)) {
                //点击的是图像站
                scrollItemTitle.setText(name + "图像站详情");
                String stcd = pictureDatas.get(position).getStcd();
                initPicDetailFragment(stcd);
            } else if (drawerLayoutDatas.get(0).list.get(6).equals(dataList.get(section).tagsName)) {
                //点击的是视频站
                VideoResponse.DataBean dataBean = videoDatas.get(position);
                initVedioDetailFragment(dataBean);
            }
        });
    }

    private void initVedioDetailFragment(VideoResponse.DataBean dataBean) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        VedioFragment detailFragment = VedioFragment.newInstance(dataBean);
        transaction.replace(R.id.fl_container, detailFragment);
//        hideFragment(transaction);
        transaction.show(detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * 列表点击事件公用方法
     *
     * @param section
     * @param position
     */
    private void mAdapterItemClick(int section, int position) {
        double lgtd = dataList.get(section).commonModels.get(position).getLgtd();
        double lttd = dataList.get(section).commonModels.get(position).getLttd();
        String name = dataList.get(section).list.get(position);
//        if (scroll_down_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
//            scroll_down_layout.setToExit();
//        }
        //中国的经纬度范围大约为：纬度3.86~53.55，经度73.66~135.05
        if (73.66 < lgtd && lgtd < 135.05 && lttd > 3.86 && lttd < 53.55) {
            mapView.setViewpointCenterAsync(transformationPoint(lgtd, lttd), itemScale).addDoneListener(() -> {
                if (scroll_item_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
                    //上移地图
                    moveMap(true);
                }
            });
        }
        ivArrowBack.setVisibility(View.VISIBLE);
        tvMapTitle.setText(name);
        tvLlMapTitle.setText(name);
        scrollItemTitle.setText(name + "详情");
        if (callout.isShowing()) {
            callout.dismiss();
        }
        initCallout(transformationPoint(lgtd, lttd), name);
//        scroll_item_layout.scrollToOpen();
//        ObjectAnimator objectAnimator = translationAnimator(llZoom, -(mapHeight / 2 - Px2dpUtils.dip2px(mContext, 110)), 300);
//        objectAnimator.start();
//        if (sc)
    }

    //模拟列表数据
    private void initRecycleData() {
        ArrayList<String> listCheckRiver = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listCheckRiver.add("水质站" + i);
        }
        LTntity lTntity = new LTntity();
        lTntity.list = listCheckRiver;
        lTntity.tagsName = "水质站";
        LTntity lTntity2 = new LTntity();
        lTntity2.list = listCheckRiver;
        lTntity2.tagsName = "雨量站";
        LTntity lTntity3 = new LTntity();
        lTntity3.list = listCheckRiver;
        lTntity3.tagsName = "排污口";
        dataList.add(lTntity);
        dataList.add(lTntity2);
        dataList.add(lTntity3);
        mAdapter.setData(dataList);
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout() {
        List<String> group = new ArrayList<String>();
        ArrayList<ArrayList> lists = new ArrayList<ArrayList>();
        drawerLayoutDatas = new ArrayList<LTntity>();
        SectionData.initSectionDatas(lists, group, drawerLayoutDatas);
        RecyclerView rv = findView(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setHasFixedSize(true);
        dHotelEntityAdapter = new DHotelEntityAdapter(mContext);
        dHotelEntityAdapter.setData(drawerLayoutDatas);
        rv.setAdapter(dHotelEntityAdapter);
        //        设置checkbox点击事件
        layerMap = new HashMap<Integer, SparseBooleanArray>();
//        HashMap<Integer, SparseBooleanArray>  sparseBooleanArrays = new ArrayList<>();
        for (int i = 0; i < drawerLayoutDatas.size(); i++) {
            SparseBooleanArray mCheckList = new SparseBooleanArray();
            for (int j = 0; j < drawerLayoutDatas.get(i).list.size(); j++) {
                mCheckList.put(j, false);
            }
            layerMap.put(i, mCheckList);
        }
        setCheckBoxClick(dHotelEntityAdapter);
    }

    /**
     * 图层是否可见
     *
     * @param section
     * @param position
     * @param isVisible
     */
    private void setlayerMap(int section, int position, boolean isVisible) {
        layerMap.get(section).put(position, isVisible);
    }

    /**
     * 设置checkbox点击事件
     *
     * @param dHotelEntityAdapter
     */
    private void setCheckBoxClick(DHotelEntityAdapter dHotelEntityAdapter) {
     /*   dHotelEntityAdapter.setOnSetItem((holder, groupItemIndex, subItemIndex) -> {
            if (callout != null && callout.isShowing()) {
                callout.dismiss();
            }
            searchOverlay.setVisible(false);
            CheckBox checkBox = (CheckBox) (holder.itemView.findViewById(R.id.checkBox));
            boolean checked = checkBox.isChecked();
//            drawerLayout.closeDrawers();
            if (groupItemIndex == 0) {
                if (subItemIndex == 0) {
                    //水库
                    if (checked) {
                        if (reservoirDatas == null || reservoirDatas.size() == 0) {
                            requestAllReservoir(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(reservoirOverlay, reservoirCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        reservoirOverlay.setVisible(true);
//                        ToastUtils.shortToast("显示水库");
                    } else {
//                        ToastUtils.shortToast("隐藏水库");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        reservoirOverlay.setVisible(false);
                    }
                } else if (subItemIndex == 1) {
                    //流量
                    if (checked) {
                        if (stRiverDatas == null || stRiverDatas.size() == 0) {
                            requestAllStRiver(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(stRiverOverLay, stRiverCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        stRiverOverLay.setVisible(true);
//                        ToastUtils.shortToast("显示流量站");
                    } else {
//                        ToastUtils.shortToast("隐藏流量站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        stRiverOverLay.setVisible(false);
                    }
                } else if (subItemIndex == 2) {
                    //水质站
                    if (checked) {
                        if (waterQualityDatas == null || waterQualityDatas.size() == 0) {
                            requsetAllStWqB(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(stWQOverLay, stWqBCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        stWQOverLay.setVisible(true);
//                        ToastUtils.shortToast("显示水质站");
                    } else {
//                        ToastUtils.shortToast("隐藏水质站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        stWQOverLay.setVisible(false);
                    }
                } else if (subItemIndex == 3) {
                    //雨量站
                    if (checked) {
                        if (rainfallDatas == null || rainfallDatas.size() == 0) {
                            requestAllRainfall(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(rainfallOverLay, rainfallCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        rainfallOverLay.setVisible(true);
//                        ToastUtils.shortToast("显示雨量站");
                    } else {
//                        ToastUtils.shortToast("隐藏雨量站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        rainfallOverLay.setVisible(false);
                    }
                } else if (subItemIndex == 4) {
                    //水位站
                    if (checked) {
                        if (waterLevelDatas == null || waterLevelDatas.size() == 0) {
                            requestAllWaterLevel(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(waterLevelOverlay, waterLevelCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        waterLevelOverlay.setVisible(true);
//                        ToastUtils.shortToast("显示水位站");
                    } else {
//                        ToastUtils.shortToast("隐藏水位站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        waterLevelOverlay.setVisible(false);
                    }
                } else if (subItemIndex == 5) {
                    //图像站
                    if (checked) {
                        if (pictureDatas == null || pictureDatas.size() == 0) {
                            requestAllPicture(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(pictureOverlay, pictureCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        pictureOverlay.setVisible(true);
//                        ToastUtils.shortToast("显示图像站");
                    } else {
//                        ToastUtils.shortToast("隐藏图像站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        pictureOverlay.setVisible(false);
                    }
                } else if (subItemIndex == 6) {
                    //视频站
                    if (checked) {
                        if (videoDatas == null || videoDatas.size() == 0) {
                            requestAllVideo(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(videoOverlay, videoCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        videoOverlay.setVisible(true);
                    } else {
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        videoOverlay.setVisible(false);
                    }
                }
            }
        });*/
        dHotelEntityAdapter.setmOnRecyclerviewItemClickListener((v, groupItemIndex, subItemIndex) -> {
            if (callout != null && callout.isShowing()) {
                callout.dismiss();
            }
            searchOverlay.setVisible(false);
            CheckBox checkBox = v.findViewById(R.id.checkBox);
            boolean checked = !(checkBox.isChecked());
//            drawerLayout.closeDrawers();
            if (groupItemIndex == 0) {
                if (subItemIndex == 0) {
                    //水库
                    if (checked) {
                        dHotelEntityAdapter.setItemChecked(groupItemIndex, subItemIndex);
                        if (reservoirDatas == null || reservoirDatas.size() == 0) {
                            requestAllReservoir(groupItemIndex, subItemIndex);
                        } else {
                            setlayerMap(groupItemIndex, subItemIndex, true);
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(reservoirOverlay, reservoirCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        reservoirOverlay.setVisible(true);
//                        ToastUtils.shortToast("显示水库");
                    } else {
                        dHotelEntityAdapter.setItemNotChecked(groupItemIndex, subItemIndex);
                        setlayerMap(groupItemIndex, subItemIndex, false);
//                        ToastUtils.shortToast("隐藏水库");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        reservoirOverlay.setVisible(false);
                    }
                } else if (subItemIndex == 1) {
                    //流量
                    if (checked) {
                        dHotelEntityAdapter.setItemChecked(groupItemIndex, subItemIndex);
                        if (stRiverDatas == null || stRiverDatas.size() == 0) {
                            requestAllStRiver(groupItemIndex, subItemIndex);
                        } else {
                            setlayerMap(groupItemIndex, subItemIndex, true);
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(stRiverOverLay, stRiverCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        stRiverOverLay.setVisible(true);
//                        ToastUtils.shortToast("显示流量站");
                    } else {
                        dHotelEntityAdapter.setItemNotChecked(groupItemIndex, subItemIndex);
                        setlayerMap(groupItemIndex, subItemIndex, false);
//                        ToastUtils.shortToast("隐藏流量站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        stRiverOverLay.setVisible(false);
                    }
                } else if (subItemIndex == 2) {
                    //水质站
                    if (checked) {
                        dHotelEntityAdapter.setItemChecked(groupItemIndex, subItemIndex);
                        if (waterQualityDatas == null || waterQualityDatas.size() == 0) {
                            requsetAllStWqB(groupItemIndex, subItemIndex);
                        } else {
                            setlayerMap(groupItemIndex, subItemIndex, true);
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(stWQOverLay, stWqBCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        stWQOverLay.setVisible(true);
//                        ToastUtils.shortToast("显示水质站");
                    } else {
                        setlayerMap(groupItemIndex, subItemIndex, false);
                        dHotelEntityAdapter.setItemNotChecked(groupItemIndex, subItemIndex);
//                        ToastUtils.shortToast("隐藏水质站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        stWQOverLay.setVisible(false);
                    }
                } else if (subItemIndex == 3) {
                    //雨量站
                    if (checked) {
                        dHotelEntityAdapter.setItemChecked(groupItemIndex, subItemIndex);
                        if (rainfallDatas == null || rainfallDatas.size() == 0) {
                            requestAllRainfall(groupItemIndex, subItemIndex);
                        } else {
                            setlayerMap(groupItemIndex, subItemIndex, true);
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(rainfallOverLay, rainfallCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        rainfallOverLay.setVisible(true);
//                        ToastUtils.shortToast("显示雨量站");
                    } else {
                        dHotelEntityAdapter.setItemNotChecked(groupItemIndex, subItemIndex);
                        setlayerMap(groupItemIndex, subItemIndex, false);
//                        ToastUtils.shortToast("隐藏雨量站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        rainfallOverLay.setVisible(false);
                    }
                } else if (subItemIndex == 4) {
                    //水位站
                    if (checked) {
                        dHotelEntityAdapter.setItemChecked(groupItemIndex, subItemIndex);
                        if (waterLevelDatas == null || waterLevelDatas.size() == 0) {
                            requestAllWaterLevel(groupItemIndex, subItemIndex);
                        } else {
                            setlayerMap(groupItemIndex, subItemIndex, true);
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(waterLevelOverlay, waterLevelCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        waterLevelOverlay.setVisible(true);
//                        ToastUtils.shortToast("显示水位站");
                    } else {
                        dHotelEntityAdapter.setItemNotChecked(groupItemIndex, subItemIndex);
                        setlayerMap(groupItemIndex, subItemIndex, false);
//                        ToastUtils.shortToast("隐藏水位站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        waterLevelOverlay.setVisible(false);
                    }
                } else if (subItemIndex == 5) {
                    //图像站
                    if (checked) {
                        dHotelEntityAdapter.setItemChecked(groupItemIndex, subItemIndex);
                        if (pictureDatas == null || pictureDatas.size() == 0) {
                            requestAllPicture(groupItemIndex, subItemIndex);
                        } else {
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            setlayerMap(groupItemIndex, subItemIndex, true);
                            if (!isContains) {
                                addMarkersAndList(pictureOverlay, pictureCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        pictureOverlay.setVisible(true);
//                        ToastUtils.shortToast("显示图像站");
                    } else {
                        dHotelEntityAdapter.setItemNotChecked(groupItemIndex, subItemIndex);
                        setlayerMap(groupItemIndex, subItemIndex, false);
//                        ToastUtils.shortToast("隐藏图像站");
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        pictureOverlay.setVisible(false);
                    }
                } else if (subItemIndex == 6) {
                    //视频站
                    if (checked) {
                        dHotelEntityAdapter.setItemChecked(groupItemIndex, subItemIndex);
                        if (videoDatas == null || videoDatas.size() == 0) {
                            requestAllVideo(groupItemIndex, subItemIndex);
                        } else {
                            setlayerMap(groupItemIndex, subItemIndex, true);
                            boolean isContains = isListContains(groupItemIndex, subItemIndex);
                            if (!isContains) {
                                addMarkersAndList(videoOverlay, videoCommonModel, groupItemIndex, subItemIndex);
                            }
                        }
                        videoOverlay.setVisible(true);
                    } else {
                        dHotelEntityAdapter.setItemNotChecked(groupItemIndex, subItemIndex);
                        setlayerMap(groupItemIndex, subItemIndex, false);
                        removeRVForName(drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex));
                        videoOverlay.setVisible(false);
                    }
                }
            }
        });
    }

    private List<VideoResponse.DataBean> videoDatas;
    ArrayList<CommonModel> videoCommonModel = new ArrayList<>();

    /**
     * 请求全部视频站
     *
     * @param section
     * @param position
     */
    private void requestAllVideo(int section, int position) {
        mPresenter.attachView(new MainMapContract.View<VideoResponse>() {
            @Override
            public void success(VideoResponse videoResponse) {
                int code = videoResponse.getCode();
                if (code == 0) {
                    videoDatas = videoResponse.getData();
                    if (videoDatas != null && videoDatas.size() > 0) {
                        if (videoCommonModel == null || videoCommonModel.size() == 0) {
                            for (int i = 0; i < videoDatas.size(); i++) {
                                CommonModel commonModel = new CommonModel();
                                String lgtd = videoDatas.get(i).getLgtd();
                                String lttd = videoDatas.get(i).getLttd();
                                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                    commonModel.setLgtd(Double.parseDouble(lgtd));
                                    commonModel.setLttd(Double.parseDouble(lttd));
                                }
                                commonModel.setName(videoDatas.get(i).getVsnm());
                                videoCommonModel.add(commonModel);
                            }
                        }

                    }
                    boolean isContains = isListContains(section, position);
                    if (!isContains) {
                        addMarkersAndList(videoOverlay, videoCommonModel, section, position);
                    }
                }
            }

            @Override
            public void failure(String msg) {
                dHotelEntityAdapter.setItemNotChecked(0, 6);
                setlayerMap(0, 6, false);
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.findAllVsVideo("");
    }

    /**
     * 图像站详情
     *
     * @param stcd
     */
    private void initPicDetailFragment(String stcd) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
//       if (illegalEventDetailFragment == null) {
        ImageFragment detailFragment = ImageFragment.newInstance(stcd);
        transaction.replace(R.id.fl_container, detailFragment);
//        hideFragment(transaction);
        transaction.show(detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private List<MapCommonResponse.DataBean> pictureDatas;
    ArrayList<CommonModel> pictureCommonModel = new ArrayList<>();

    /**
     * 请求全部图像站
     *
     * @param section
     * @param position
     */
    private void requestAllPicture(int section, int position) {
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse pictureResponse) {
                int code = pictureResponse.getCode();
                if (code == 0) {
                    pictureDatas = pictureResponse.getData();
                    if (pictureDatas != null && pictureDatas.size() > 0) {
                        if (pictureCommonModel == null || pictureCommonModel.size() == 0) {
                            for (int i = 0; i < pictureDatas.size(); i++) {
                                CommonModel commonModel = new CommonModel();
                                String lgtd = pictureDatas.get(i).getLgtd();
                                String lttd = pictureDatas.get(i).getLttd();
                                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                    commonModel.setLgtd(Double.parseDouble(lgtd));
                                    commonModel.setLttd(Double.parseDouble(lttd));
                                }
                                commonModel.setName(pictureDatas.get(i).getStnm());
                                pictureCommonModel.add(commonModel);
                            }
                        }

                    }
                    boolean isContains = isListContains(section, position);
                    if (!isContains) {
                        addMarkersAndList(pictureOverlay, pictureCommonModel, section, position);
                    }
                }
            }

            @Override
            public void failure(String msg) {
                dHotelEntityAdapter.setItemNotChecked(0, 5);
                setlayerMap(0, 5, false);
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType("", "ii", "");
    }

    /**
     * 水位站详情
     *
     * @param stcd
     */
    private void initWaterLevelDetailFragment(String stcd, WaterLevelResponse.DataBean.StStbprpBBean dataBean) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
//       if (illegalEventDetailFragment == null) {
        WaterLevelFragment detailFragment = WaterLevelFragment.newInstance(stcd, dataBean);
        transaction.replace(R.id.fl_container, detailFragment);
//        hideFragment(transaction);
        transaction.show(detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private List<MapCommonResponse.DataBean> waterLevelDatas;
    ArrayList<CommonModel> waterLevelCommonModel = new ArrayList<>();

    /**
     * 请求全部水位
     *
     * @param section
     * @param position
     */
    private void requestAllWaterLevel(int section, int position) {
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
                        if (waterLevelCommonModel == null || waterLevelCommonModel.size() == 0) {
                            for (int i = 0; i < waterLevelDatas.size(); i++) {
                                CommonModel commonModel = new CommonModel();
                                String lgtd = waterLevelDatas.get(i).getLgtd();
                                String lttd = waterLevelDatas.get(i).getLttd();
                                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                    commonModel.setLgtd(Double.parseDouble(lgtd));
                                    commonModel.setLttd(Double.parseDouble(lttd));
                                }
                                commonModel.setName(waterLevelDatas.get(i).getStnm());
                                waterLevelCommonModel.add(commonModel);
                            }
                        }

                    }
                    boolean isContains = isListContains(section, position);
                    if (!isContains) {
                        addMarkersAndList(waterLevelOverlay, waterLevelCommonModel, section, position);
                    }
                }
            }

            @Override
            public void failure(String msg) {
                dHotelEntityAdapter.setItemNotChecked(0, 4);
                setlayerMap(0, 4, false);
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType("", "rr", "");
    }


    /**
     * 流量站详情
     *
     * @param stcd
     */
    private void initStRiverDetailFragment(String stcd) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
//       if (illegalEventDetailFragment == null) {
        LiuliangFragment detailFragment = LiuliangFragment.newInstance(stcd);
        transaction.replace(R.id.fl_container, detailFragment);
//        hideFragment(transaction);
        transaction.show(detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private List<MapCommonResponse.DataBean> stRiverDatas;
    ArrayList<CommonModel> stRiverCommonModel = new ArrayList<>();

    /**
     * 请求流量站
     */
    private void requestAllStRiver(int section, int position) {
        Date date = new Date();
        String formatPattern = "yyyy-MM-dd";
        String baseDate = new SimpleDateFormat(formatPattern).format(date);
//        String startDate = baseDate + " 00:00:00";
//        String endDate = baseDate + " 23:59:59";
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse stRiverResponse) {
                int code = stRiverResponse.getCode();
                if (code == 0) {
                    stRiverDatas = stRiverResponse.getData();
                    if (stRiverDatas != null && stRiverDatas.size() > 0) {
                        if (stRiverCommonModel == null || stRiverCommonModel.size() == 0) {
                            for (int i = 0; i < stRiverDatas.size(); i++) {
                                CommonModel commonModel = new CommonModel();
                                String lgtd = stRiverDatas.get(i).getLgtd();
                                String lttd = stRiverDatas.get(i).getLttd();
                                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                    commonModel.setLgtd(Double.parseDouble(lgtd));
                                    commonModel.setLttd(Double.parseDouble(lttd));
                                }
                                commonModel.setName(stRiverDatas.get(i).getStnm());
                                stRiverCommonModel.add(commonModel);
                            }
                        }

                    }
                    boolean isContains = isListContains(section, position);
                    if (!isContains) {
                        addMarkersAndList(stRiverOverLay, stRiverCommonModel, section, position);
                    }
                }
            }

            @Override
            public void failure(String msg) {
                dHotelEntityAdapter.setItemNotChecked(0, 1);
                setlayerMap(0, 1, false);
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType("", "zq", "");
    }

    /**
     * 水质站详情
     *
     * @param stcd
     */
    private void initWaterQualityDetailFragment(String stcd, WaterQualityResponse.DataBean waterQuality_databean) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
//       if (illegalEventDetailFragment == null) {
        WaterQualityFragment detailFragment = WaterQualityFragment.newInstance(stcd, waterQuality_databean);
        transaction.replace(R.id.fl_container, detailFragment);
//        hideFragment(transaction);
        transaction.show(detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private List<MapCommonResponse.DataBean> waterQualityDatas;
    ArrayList<CommonModel> stWqBCommonModel = new ArrayList<>();

    /**
     * 请求所有水质站
     */
    private void requsetAllStWqB(int section, int position) {
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse mapCommonResponse) {
                int code = mapCommonResponse.getCode();
                if (code == 0) {
                    waterQualityDatas = mapCommonResponse.getData();
                    if (waterQualityDatas != null && waterQualityDatas.size() > 0) {
                        if (stWqBCommonModel == null || stWqBCommonModel.size() == 0) {
                            for (int i = 0; i < waterQualityDatas.size(); i++) {
                                CommonModel commonModel = new CommonModel();
                                String lgtd = waterQualityDatas.get(i).getLgtd();
                                String lttd = waterQualityDatas.get(i).getLttd();
                                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                    commonModel.setLgtd(Double.parseDouble(lgtd));
                                    commonModel.setLttd(Double.parseDouble(lttd));
                                }
                                commonModel.setName(waterQualityDatas.get(i).getStnm());
                                stWqBCommonModel.add(commonModel);
                            }
                        }
                    }
                    boolean isContains = isListContains(section, position);
                    if (!isContains) {
                        addMarkersAndList(stWQOverLay, stWqBCommonModel, section, position);
                    }
                }
            }

            @Override
            public void failure(String msg) {
                dHotelEntityAdapter.setItemNotChecked(0, 2);
                setlayerMap(0, 2, false);
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType("", "wq", "");
    }

    /**
     * 水库详情页面
     *
     * @param bean
     */
    private void initReservoirDetailFragment(ReservoirResponse.DataBean bean) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
//       if (illegalEventDetailFragment == null) {
        ReserviorFragment detailFragment = ReserviorFragment.newInstance(bean);
        transaction.replace(R.id.fl_container, detailFragment);
//        hideFragment(transaction);
        transaction.show(detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private List<ReservoirBean> reservoirDatas;
    ArrayList<CommonModel> reservoirCommonModel = new ArrayList<>();

    /**
     * 请求水库信息
     */
    private void requestAllReservoir(int section, int position) {
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
                        if (reservoirCommonModel == null || reservoirCommonModel.size() == 0) {
                            for (int i = 0; i < reservoirDatas.size(); i++) {
                                CommonModel commonModel = new CommonModel();
                                String lgtd = reservoirDatas.get(i).getReservoirLongitude();
                                String lttd = reservoirDatas.get(i).getReservoirLatitude();
                                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                    commonModel.setLgtd(Double.parseDouble(lgtd));
                                    commonModel.setLttd(Double.parseDouble(lttd));
                                }
                                commonModel.setName(reservoirDatas.get(i).getReservoir());
                                reservoirCommonModel.add(commonModel);
                            }
                        }

                    }
                    boolean isContains = isListContains(section, position);
                    if (!isContains) {
                        addMarkersAndList(reservoirOverlay, reservoirCommonModel, section, position);
                    }
                }
            }

            @Override
            public void failure(String msg) {
                //没请求到数据，设置选中状态为未选中
                dHotelEntityAdapter.setItemNotChecked(0, 0);
                setlayerMap(0, 0, false);
                ToastUtils.shortToast(msg);
            }
        });
        mPresenter.findAppAllReservoir("", "",false);
    }

    /**
     * 雨量站详情
     *
     * @param stcd
     */
    private void initRainfallDetailFragment(String stcd, RainfallResponse.DataBean.StStbprpBBean dataBean) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
//       if (illegalEventDetailFragment == null) {
        RainFullFragment detailFragment = RainFullFragment.newInstance(stcd, dataBean);
        transaction.replace(R.id.fl_container, detailFragment);
//        hideFragment(transaction);
        transaction.show(detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private List<MapCommonResponse.DataBean> rainfallDatas;
    ArrayList<CommonModel> rainfallCommonModel = new ArrayList<>();

    /**
     * 请求雨量信息
     *
     * @param section
     * @param position
     */
    private void requestAllRainfall(int section, int position) {
        Date date = new Date();
        String formatPattern = "yyyy-MM-dd";
        String baseDate = new SimpleDateFormat(formatPattern).format(date);
        mPresenter.attachView(new MainMapContract.View<MapCommonResponse>() {
            @Override
            public void success(MapCommonResponse rainfallResponse) {
                int code = rainfallResponse.getCode();
                if (code == 0) {
                    rainfallDatas = rainfallResponse.getData();
                    if (rainfallDatas != null && rainfallDatas.size() > 0) {
                        if (rainfallCommonModel == null || rainfallCommonModel.size() == 0) {
                            for (int i = 0; i < rainfallDatas.size(); i++) {
                                CommonModel commonModel = new CommonModel();
                                String lgtd = rainfallDatas.get(i).getLgtd();
                                String lttd = rainfallDatas.get(i).getLttd();
                                if (null != lgtd && null != lttd && lgtd.length() > 0 && lttd.length() > 0) {
                                    commonModel.setLgtd(Double.parseDouble(lgtd));
                                    commonModel.setLttd(Double.parseDouble(lttd));
                                }
                                commonModel.setName(rainfallDatas.get(i).getStnm());
                                rainfallCommonModel.add(commonModel);
                            }
                        }

                    }
                    boolean isContains = isListContains(section, position);
                    if (!isContains) {
                        addMarkersAndList(rainfallOverLay, rainfallCommonModel, section, position);
                    }
                }
            }

            @Override
            public void failure(String msg) {
                dHotelEntityAdapter.setItemNotChecked(0, 3);
                setlayerMap(0, 3, false);
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getStStbprpBByType("", "pp", "");
    }

    /**
     * 地图坐标转换
     *
     * @param lgtd
     * @param lttd
     * @return
     */
    public Point transformationPoint(double lgtd, double lttd) {
        Point point1 = new Point(lgtd, lttd, SpatialReference.create(4326));
        Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
        return point;
    }

    /**
     * 添加地图图标和刷新列表
     *
     * @param graphicsOverlay 要素图层
     * @param mListData       数据
     * @param section         组
     * @param position        对应组的坐标
     */
    private void addMarkersAndList(GraphicsOverlay graphicsOverlay, ArrayList<CommonModel> mListData, int section, int position) {
        ArrayList<String> list = new ArrayList<>();
        if (mListData != null && mListData.size() > 0) {
            for (int i = 0; i < mListData.size(); i++) {
                list.add(mListData.get(i).getName());
            }
            Observable.create((ObservableOnSubscribe<String>) e -> {
                if (graphicsOverlay.getGraphics().size() != mListData.size()) {
                    for (int i = 0; i < mListData.size(); i++) {
                        HashMap<String, Object> map = new HashMap<>();
                        //传入对应集合的position
                        map.put("id", i);
                        //传入对应类型的position
                        map.put("groupId", position);
                        Point point = transformationPoint(mListData.get(i).getLgtd(), mListData.get(i).getLttd());
                        addPic(graphicsOverlay, picMap.get(section)[position], point, map);
                    }
                    e.onNext("完成");
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            });
        }
        LTntity lTntity = new LTntity();
        lTntity.tagsName = drawerLayoutDatas.get(section).list.get(position);
        lTntity.list = list;
        lTntity.commonModels = mListData;
        if (!dataList.contains(lTntity)) {
            dataList.add(lTntity);
            mAdapter.setData(dataList);
        }
    }

    /**
     * 添加图片
     *
     * @param id    图片id
     * @param point 坐标点
     */
    public void addPic(GraphicsOverlay graphicsOverlay, int id, Point point) {
        PictureMarkerSymbol pictureMarkerSymbol1 = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            pictureMarkerSymbol1 = PictureMarkerSymbol.createAsync(new BitmapDrawable(getResources(), bitmap)).get();
            Graphic picGraphic = new Graphic(point, pictureMarkerSymbol1);
            graphicsOverlay.getGraphics().add(picGraphic);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加图片
     * @param graphicsOverlay
     * @param id         图片id
     * @param point      坐标点
     * @param attributes 要素传值
     */
    public void addPic(GraphicsOverlay graphicsOverlay, int id, Point point, Map<String, Object> attributes) {
        /*PictureMarkerSymbol pictureMarkerSymbol1 = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            pictureMarkerSymbol1 = PictureMarkerSymbol.createAsync(new BitmapDrawable(getResources(), bitmap)).get();
            Graphic picGraphic = new Graphic(point, attributes, pictureMarkerSymbol1);
            graphicsOverlay.getGraphics().add(picGraphic);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        PictureMarkerSymbol pictureMarkerSymbol1 = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            if (bitmap == null) {return;}
            Bitmap result =   Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight()*2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            canvas.drawBitmap(bitmap, 0, 0, null);
//            canvas.drawBitmap(bitmap, bitmap.getHeight(), 0, null);
            pictureMarkerSymbol1 = PictureMarkerSymbol.createAsync(new BitmapDrawable(getResources(), result)).get();
            Graphic picGraphic = new Graphic(point, attributes, pictureMarkerSymbol1);
            graphicsOverlay.getGraphics().add(picGraphic);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 地图CallOut
     *
     * @param point
     */
    private void initCallout(Point point, String name) {
        TextView tv = new TextView(mContext);
        tv.setText(name);
        callout.setContent(tv);
        callout.getStyle().setLeaderPosition(Callout.Style.LeaderPosition.AUTOMATIC);
        callout.getStyle().setBorderWidth(1);
        callout.getStyle().setCornerRadius(3);
        callout.getStyle().setLeaderWidth(5);
        callout.getStyle().setLeaderLength(15);
        callout.setLocation(point);
        callout.show();
    }

    /**
     * 隐藏列表，显示详情
     */
    public void hideListShowDetail() {
//        ObjectAnimator objectAnimator2 = objectAnimation(scroll_item_layout, 0f, 1f, 100);
        ObjectAnimator objectAnimator1 = objectAnimation(scroll_down_layout, 1f, 0f, 100);
        objectAnimator1.start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(scroll_item_layout, "alpha", 0f, 1f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (scroll_item_layout.getCurrentStatus() == ScrollLayout.Status.CLOSED) {
                    double height = Px2dpUtils.dip2px(mContext, 50) + ScreenUtil.getStatusBarHeight();
                    ViewGroup.LayoutParams layoutParams = detailTitleLl.getLayoutParams();
                    layoutParams.height = (int) height;
                    detailTitleLl.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                scroll_item_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.setDuration(200);
        animator.start();
    }

    /**
     * 隐藏详情，显示列表
     */
    public void hideDetailShowList() {
//        LogUtil.i("scrollView当前滑动的位置:"+itemScrollView.getScrollY());
        if (itemScrollView.getScrollY() != 0) {
            //如果itemScrollView不在顶部，设置itemScrollView定位到顶部
            itemScrollView.scrollTo(0, 0);
        }
        ObjectAnimator objectAnimatorList = objectAnimation(scroll_down_layout, 0f, 1f, 100);
        objectAnimatorList.start();
        ObjectAnimator objectAnimator = objectAnimation(scroll_item_layout, 1f, 0f, 300);
        objectAnimator.start();
        ivArrowBack.setVisibility(View.GONE);
    }

    /**
     * 渐变动画
     *
     * @param view
     * @param start
     * @param end
     * @param time
     * @return
     */
    private ObjectAnimator objectAnimation(final View view, final float start, final float end, int time) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", start, end);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (start < end) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.setDuration(time);
        return animator;
    }

    /**
     * 放大缩小控件的平移动画
     *
     * @param view
     * @param tranY
     * @param time
     * @return
     */
    private ObjectAnimator translationAnimator(final View view, final float tranY, int time) {
        float translationY1 = view.getTranslationY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", translationY1, tranY + translationY1);
        animator.setDuration(time);
        return animator;
    }

    /**
     * 判断列表是否包含选的的点
     *
     * @param groupItemIndex
     * @param subItemIndex
     * @return
     */
    private boolean isListContains(int groupItemIndex, int subItemIndex) {
        boolean isContains = false;
        String subTar = drawerLayoutDatas.get(groupItemIndex).list.get(subItemIndex);
        if (dataList != null && dataList.size() != 0) {
            for (int i = 0; i < dataList.size(); i++) {
                String tagsName = dataList.get(i).tagsName;
                if (tagsName.equals(subTar)) {
                    isContains = true;
                }
            }
        } else {
            isContains = false;
        }
        return isContains;
    }

    /**
     * 根据名字清除列表
     *
     * @param name
     */
    private void removeRVForName(String name) {
        if (dataList != null && dataList.size() != 0) {
            for (int i = 0; i < dataList.size(); i++) {
                String tagsName = dataList.get(i).tagsName;
                if (tagsName.equals(name)) {
                    dataList.remove(i);
                }
            }
        }
        if (dataList == null || dataList.size() == 0) {
            tv_head.setText("列表");
        }
        mAdapter.setData(dataList);
    }

    private void removeGraphicsByOverlay(GraphicsOverlay graphicsOverlay) {
        if (graphicsOverlay.getGraphics().size() > 0) {
            graphicsOverlay.getGraphics().clear();
        }
    }

    /**
     * 默认选中矢量图
     */
    private void setTransparency() {
        mIvVector.setBackgroundResource(R.drawable.bg_view_state_shape);
        mIvRaster.setBackgroundResource(R.drawable.bg_view_unstate_shape);
        mImTvVector.setBackgroundResource(R.color.color_tab_checked);
        mTvVector.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_tab_checked));
        mImTvRaster.setBackgroundResource(R.color.color_c8c8c8);
        mTvRaster.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_c8c8c8));
    }

    /**
     * 初始化按钮点击事件
     *
     * @param view
     */
    private void initOnClick(View view) {
        drawerLayout = view.findViewById(R.id.drawer_layout);
        ImageView zoomBtnIn = view.findViewById(R.id.zoomBtnIn);
        ImageView zoomBtnOut = view.findViewById(R.id.zoomBtnOut);
        ImageView ivDrawer = view.findViewById(R.id.iv_drawer);
        imgLayer1 = view.findViewById(R.id.img_layer);
        LinearLayout mLayerRaster = drawerLayout.findViewById(R.id.layer_raster);
        LinearLayout mIvVector = drawerLayout.findViewById(R.id.layer_vector);
        ivArrowBack = findView(R.id.iv_arrow_back);
        ivArrowBack.setVisibility(View.GONE);
        ImageView mImageViewLocation = findView(R.id.img_location);
        zoomBtnIn.setOnClickListener(this::btnClick);
        zoomBtnOut.setOnClickListener(this::btnClick);
        ivDrawer.setOnClickListener(this::btnClick);
        mLayerRaster.setOnClickListener(this::btnClick);
        mIvVector.setOnClickListener(this::btnClick);
        mImageViewLocation.setOnClickListener(this::btnClick);
        ivArrowBack.setOnClickListener(this::btnClick);
        imgLayer1.setOnClickListener(this::btnClick);
    }

    /**
     * 按钮点击事件
     *
     * @param view
     */
    private void btnClick(View view) {
        int id = view.getId();
        if (id == R.id.zoomBtnIn) {
            //放大
            if (isLoaded) {
                double mScale = mapView.getMapScale();
                mapView.setViewpointScaleAsync(mScale * 0.5);
            }
        } else if (id == R.id.zoomBtnOut) {
            //缩小
            if (isLoaded) {
                double mScale = mapView.getMapScale();
                mapView.setViewpointScaleAsync(mScale * 2);
            }
        } else if (id == R.id.iv_drawer) {
            //打开侧边栏
            drawerLayout.openDrawer(Gravity.RIGHT);
        } else if (id == R.id.img_layer) {
            //打开侧边栏
            drawerLayout.openDrawer(Gravity.RIGHT);
//            initMap();
           /* layer = new ArcGISTiledLayer("http://map.geoq.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer");
            imgLayer = new ArcGISTiledLayer("https://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer");
            imgLayer.setVisible(false);
            Basemap basemap = new Basemap();
            ArcGISMap arcGISMap = new ArcGISMap(basemap);
            arcGISMap.getOperationalLayers().clear();
            arcGISMap.getOperationalLayers().add(layer);
            arcGISMap.getOperationalLayers().add(imgLayer);
            mapView.setMap(arcGISMap);
            mapView.getMap().addLoadStatusChangedListener(loadStatusChangedEvent -> {
                LoadStatus newLoadStatus = loadStatusChangedEvent.getNewLoadStatus();
                LogUtil.i("地图加载状态!"+newLoadStatus.toString());
                if (newLoadStatus==LoadStatus.LOADED){
                    isLoaded = true;
                }
            });*/
        } else if (id == R.id.layer_vector) {
            mIvVector.setBackgroundResource(R.drawable.bg_view_state_shape);
            mIvRaster.setBackgroundResource(R.drawable.bg_view_unstate_shape);
            mImTvVector.setBackgroundResource(R.color.color_tab_checked);
            mTvVector.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_tab_checked));
            mImTvRaster.setBackgroundResource(R.color.color_c8c8c8);
            mTvRaster.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_c8c8c8));
            imgLayer.setVisible(false);
        } else if (id == R.id.layer_raster) {
            mIvRaster.setBackgroundResource(R.drawable.bg_view_state_shape);
            mIvVector.setBackgroundResource(R.drawable.bg_view_unstate_shape);
            mImTvVector.setBackgroundResource(R.color.color_c8c8c8);
            mTvVector.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_c8c8c8));
            mImTvRaster.setBackgroundResource(R.color.color_tab_checked);
            mTvRaster.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_tab_checked));
            imgLayer.setVisible(true);
        } else if (id == R.id.img_location) {
            //android 6.0动态申请权限
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                if (mLocationDisplay != null && !mLocationDisplay.isStarted()) {
                    mLocationDisplay.startAsync();//开始定位
                }
            }
            Point mapLocation = mLocationDisplay.getMapLocation();
            if (mapLocation != null) {
                if (isLoaded) {
                    mapView.setViewpointCenterAsync(mapLocation);
                }
            }
        } else if (id == R.id.iv_arrow_back) {
            backClickFun();
        } else if (id == R.id.iv_ll_arrow_back) {
            backClickFun();
        }
    }

    /**
     * 返回键的公共方法
     */
    public void backClickFun() {
        searchOverlay.setVisible(false);
        if (callout.isShowing()) {
            callout.dismiss();
        }
        Map<Integer, SparseBooleanArray> map = dHotelEntityAdapter.getMap();
        SparseBooleanArray sparseBooleanArray = map.get(0);
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            boolean visible = overlayList.get(i).isVisible();
            if (sparseBooleanArray.get(i)) {
                if (!visible) {
                    overlayList.get(i).setVisible(true);
                }
            }
        }
        hideDetailShowList();
        Point point = null;
        if (scroll_item_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
            android.graphics.Point screenPoint = new android.graphics.Point(0, 0);
            if (mapView.screenToLocation(screenPoint) != null) {
                double y = mapView.screenToLocation(screenPoint).getY();
                android.graphics.Point bottomPoint = new android.graphics.Point(0, mapHeight);
                double bottomY = mapView.screenToLocation(bottomPoint).getY();
                double translationY = (bottomY - y) / 2 - (bottomY - y) / 4;
//                mapView.visibleArea   返回一个Polygon，表示当前在MapView中可见的ArcGISMap区域。
                Viewpoint viewPoint = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
                Point centerPoint = viewPoint.getTargetGeometry().getExtent().getCenter();
                //下移
                point = new Point(centerPoint.getX(), centerPoint.getY() - translationY);
            }
        } else {
            Viewpoint viewPoint = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
            point = viewPoint.getTargetGeometry().getExtent().getCenter();
        }
        if (point != null) {
            mapView.setViewpointCenterAsync(point, groupScale).addDoneListener(() -> {
                if (scroll_down_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
                    moveMap(true);
                }
            });
        }
        ivArrowBack.setVisibility(View.GONE);
        if (!"地图".equals(tvMapTitle.getText().toString())) {
            tvMapTitle.setText("地图");
        }
        ViewGroup.LayoutParams layoutParams = detailTitleLl.getLayoutParams();
        layoutParams.height = 0;
        detailTitleLl.setLayoutParams(layoutParams);
        //动态改变地图标题高度
        int mapTitleHeigh = Px2dpUtils.dip2px(mContext, 40);
        ViewGroup.LayoutParams mapParams = llTitleMap.getLayoutParams();
        mapParams.height = (mapTitleHeigh);
        llTitleMap.setLayoutParams(mapParams);
    }

    private int mapHeight;

    private Boolean isMoveUp = false;
    private Boolean isMoveDown = true;
    /**
     * 列表滑动监听
     */
    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if (text_foot.getVisibility() == View.VISIBLE) {
                text_foot.setVisibility(View.GONE);
            }
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus == ScrollLayout.Status.EXIT) {
                text_foot.setVisibility(View.VISIBLE);
                if (!isMoveDown) {
                    moveMap(false);
                    isMoveDown = true;
//                    ObjectAnimator objectAnimator = translationAnimator(llZoom, (mapHeight / 2 - Px2dpUtils.dip2px(mContext, 110)), 0);
//                    objectAnimator.start();
                }
                isMoveUp = false;
            } else if (currentStatus == ScrollLayout.Status.OPENED) {
                if (!isMoveUp) {
                    moveMap(true);
                    isMoveUp = true;
//                    ObjectAnimator objectAnimator = translationAnimator(llZoom, -(mapHeight / 2 - Px2dpUtils.dip2px(mContext, 110)), 0);
//                    objectAnimator.start();
                }
                isMoveDown = false;
            }
        }

        @Override
        public void onChildScroll(int top) {

        }
    };

    private boolean isItemMoveUp = false;
    private boolean isItemMoveDown = true;

    /**
     * 详情滑动监听
     */
    private ScrollLayout.OnScrollChangedListener mItemOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            //1.0-0
//            LogUtil.i("currentProgress:" + currentProgress);
            if (currentProgress > 0) {
                //动态改表详情标题高度
                double height = Px2dpUtils.dip2px(mContext, 50) + ScreenUtil.getStatusBarHeight();
                ViewGroup.LayoutParams layoutParams = detailTitleLl.getLayoutParams();
                layoutParams.height = (int) (height * (1 - currentProgress));
                detailTitleLl.setLayoutParams(layoutParams);
                //动态改变地图标题高度
                int mapTitleHeigh = Px2dpUtils.dip2px(mContext, 40);
                ViewGroup.LayoutParams mapParams = llTitleMap.getLayoutParams();
                mapParams.height = (int) (mapTitleHeigh * currentProgress);
                llTitleMap.setLayoutParams(mapParams);
                //动态改表地图标题栏透明度
                float precent = 255 * currentProgress;
                if (precent > 255) {
                    precent = 255;
                } else if (precent < 0) {
                    precent = 0;
                }
//                llTitleMap.getBackground().setAlpha((int)0);
            }
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
//            LogUtil.i("currentStatus:" + currentStatus);
            if (currentStatus == ScrollLayout.Status.EXIT) {
                if (!isItemMoveDown) {
                    moveMap(false);
                    isItemMoveDown = true;
//                    ObjectAnimator objectAnimator = translationAnimator(llZoom, (mapHeight / 2 - Px2dpUtils.dip2px(mContext, 110)), 300);
//                    objectAnimator.start();
                }
                isItemMoveUp = false;
            } else if (currentStatus == ScrollLayout.Status.OPENED) {
                if (!isItemMoveUp) {
                    moveMap(true);
                    isItemMoveUp = true;
//                    ObjectAnimator objectAnimator = translationAnimator(llZoom, -(mapHeight / 2 - Px2dpUtils.dip2px(mContext, 110)), 300);
//                    objectAnimator.start();
                }
                isItemMoveDown = false;
            }

        }

        @Override
        public void onChildScroll(int top) {

        }
    };

    /**
     * 地图滑动
     *
     * @param status true上移  false 下移
     */
    private void moveMap(Boolean status) {
        android.graphics.Point screenPoint = new android.graphics.Point(0, 0);
        if (mapView.screenToLocation(screenPoint) != null) {
            double y = mapView.screenToLocation(screenPoint).getY();
            android.graphics.Point bottomPoint = new android.graphics.Point(0, mapHeight);
            double bottomY = mapView.screenToLocation(bottomPoint).getY();
            double translationY = (bottomY - y) / 2 - (bottomY - y) / 4;
//                mapView.visibleArea   返回一个Polygon，表示当前在MapView中可见的ArcGISMap区域。
            Viewpoint viewPoint = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
            Point centerPoint = viewPoint.getTargetGeometry().getExtent().getCenter();
            if (status) {
                //中心点上移
                mapView.setViewpointCenterAsync(new Point(centerPoint.getX(), centerPoint.getY() + translationY));
            } else {
                //下移
                mapView.setViewpointCenterAsync(new Point(centerPoint.getX(), centerPoint.getY() - translationY));
            }
        }
    }

    /**
     * 初始化滑动布局
     *
     * @param view
     */
    private void initScrollLayout(View view, int imgLocationTop) {
        scroll_down_layout = view.findViewById(R.id.scroll_down_layout);
        scroll_item_layout = view.findViewById(R.id.scroll_item_layout);
        itemScrollView = findView(R.id.item_scroll_view_layout);
        FrameLayout flContainer = findView(R.id.fl_container);
        scrollItemTitle = findView(R.id.scroll_item_title);
        //设置列表滑动布局
        //关闭状态时最上方预留高度
        scroll_down_layout.setMinOffset(Px2dpUtils.dip2px(mContext, 100));
        //打开状态时内容显示区域的高度
        scroll_down_layout.setMaxOffset(mapHeight / 2);
        //最低部退出状态时可看到的高度，0为不可见
        scroll_down_layout.setExitOffset(ScreenUtil.getScreenHeightPix(mContext) - imgLocationTop - Px2dpUtils.dip2px(mContext, 36 + 5) - ScreenUtil.getStatusBarHeight());
        //解决recycleView底部显示不全的问题
        listRecylcler.setPadding(0, 0, 0, ScreenUtil.getScreenHeightPix(mContext) - imgLocationTop - Px2dpUtils.dip2px(mContext, 36 + 55));
        //是否支持下滑退出，支持会有下滑到最底部时的回调
        scroll_down_layout.setIsSupportExit(true);
        //是否支持横向滚动
        scroll_down_layout.setAllowHorizontalScroll(true);
        scroll_down_layout.setOnScrollChangedListener(mOnScrollChangedListener);
        scroll_down_layout.setToExit();
        scroll_down_layout.getBackground().setAlpha(0);
        text_foot.setOnClickListener(v -> scroll_down_layout.scrollToOpen());
        tvListTitle.setOnClickListener(v -> scroll_down_layout.scrollToOpen());
        //设置详情滑动布局
        //关闭状态时最上方预留高度
        scroll_item_layout.setMinOffset(ScreenUtil.getStatusBarHeight() + Px2dpUtils.dip2px(mContext, 50));
        //打开状态时内容显示区域的高度
        scroll_item_layout.setMaxOffset(mapHeight / 2);
        //最低部退出状态时可看到的高度，0为不可见
        scroll_item_layout.setExitOffset(ScreenUtil.getScreenHeightPix(mContext) - imgLocationTop - Px2dpUtils.dip2px(mContext, 36 + 5) - ScreenUtil.getStatusBarHeight());
        //是否支持下滑退出，支持会有下滑到最底部时的回调
        scroll_item_layout.setIsSupportExit(true);
        //是否支持横向滚动
        scroll_item_layout.setAllowHorizontalScroll(true);
        scroll_item_layout.setOnScrollChangedListener(mItemOnScrollChangedListener);
        scroll_item_layout.setToExit();
        scroll_item_layout.getBackground().setAlpha(0);
        //解决fl_container底部显示不全的问题
        flContainer.setPadding(0, 0, 0, ScreenUtil.getScreenHeightPix(mContext) - imgLocationTop - Px2dpUtils.dip2px(mContext, 36 + 55));
        flContainer.setOnClickListener(v -> {
        });
        scrollItemTitle.setOnClickListener(v -> scroll_item_layout.scrollToOpen());
    }

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private GraphicsOverlay reservoirOverlay;
    //图层集合
    private List<GraphicsOverlay> overlayList;
    private SparseBooleanArray overlayStateMap;

    /**
     * 初始化arcgis地图
     */
    private void initMap() {
        //去水印
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud4163659509,none,1JPJD4SZ8L4HC2EN0229");
        mapView.setAttributionTextVisible(false);
//        layer = new ArcGISTiledLayer("http://map.geoq.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer");
        layer = new ArcGISTiledLayer("http://cache1.arcgisonline.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer");
        imgLayer = new ArcGISTiledLayer("https://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer");
        OpenStreetMapLayer streetlayer = new OpenStreetMapLayer();
        Basemap basemap = new Basemap(streetlayer);
        layer.setVisible(false);
        imgLayer.setVisible(false);
        ArcGISMap arcGISMap = new ArcGISMap(basemap);
        arcGISMap.getOperationalLayers().add(imgLayer);
        mapView.setMap(arcGISMap);
        //定位
        mLocationDisplay = mapView.getLocationDisplay();
        mLocationDisplay.addLocationChangedListener(locationChangedEvent -> {
            //getMapLocation获取的点是基于当前地图坐标系的点
            //getPosition是获取基于GPS的位置信息，再获取的点是基于WGS84的经纬度坐标。
            LocationDataSource.Location location = locationChangedEvent.getLocation();
            Point point = mLocationDisplay.getMapLocation();
//            LogUtil.i(" LocationDataSource.Location:"+location.getPosition());
//            LogUtil.i("point:"+point.toString());
        });
        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
        //android 6.0动态申请权限
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            if (mLocationDisplay != null && !mLocationDisplay.isStarted()) {
                mLocationDisplay.startAsync();//开始定位
            }
        }
        //初始化要素图层
        overlayList = new ArrayList<>();
        overlayStateMap = new SparseBooleanArray();
        //水库图层
        reservoirOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(reservoirOverlay);
        overlayList.add(reservoirOverlay);
        //流量站图层
        stRiverOverLay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(stRiverOverLay);
        overlayList.add(stRiverOverLay);
        //水质站图层
        stWQOverLay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(stWQOverLay);
        overlayList.add(stWQOverLay);
        //水质站图层
        rainfallOverLay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(rainfallOverLay);
        overlayList.add(rainfallOverLay);
        //水位站图层
        waterLevelOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(waterLevelOverlay);
        overlayList.add(waterLevelOverlay);
        //图像图层
        pictureOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(pictureOverlay);
        overlayList.add(pictureOverlay);
        //视频图层
        videoOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(videoOverlay);
        overlayList.add(videoOverlay);
        //搜索图层
        searchOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(searchOverlay);
        callout = mapView.getCallout();
        //设置地图点击事件
        mapView.setOnTouchListener(new DefaultMapViewOnTouchListener(mContext, mapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (isLoaded) {
                    mapTouchEvent(e);
                }
                return super.onSingleTapConfirmed(e);
            }
        });
        mapView.getMap().addLoadStatusChangedListener(loadStatusChangedEvent -> {
//            LoadStatus newLoadStatus = loadStatusChangedEvent.getNewLoadStatus();
////            LogUtil.i("地图加载状态!"+newLoadStatus.toString());
//            if (newLoadStatus==LoadStatus.LOADED){
//                isLoaded = true;
//            }
        });
        basemap.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                isLoaded = true;
            }
        });
    }

    /**
     * 地图点击事件
     *
     * @param e
     */
    private void mapTouchEvent(MotionEvent e) {
        if (callout.isShowing()) {
            callout.dismiss();
        }
        android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
        Point clickPoint = mapView.screenToLocation(screenPoint);
        SpatialReference spatialReference = mapView.getSpatialReference();
//        LogUtil.i("坐标系:"+spatialReference);
        //要素图层点击事件
        overlayOnClick(screenPoint);
    }

    /**
     * 要素图层点击事件
     *
     * @param screenPoint
     */
    private void overlayOnClick(android.graphics.Point screenPoint) {
        ScrollLayout.Status currentStatus = scroll_item_layout.getCurrentStatus();
//        LogUtil.i("currentStatus"+currentStatus);
        ListenableFuture<List<IdentifyGraphicsOverlayResult>> listListenableFuture = mapView.identifyGraphicsOverlaysAsync(screenPoint, 3.0, false);
        try {
            List<IdentifyGraphicsOverlayResult> overlayResults = listListenableFuture.get();
            if (overlayResults != null && overlayResults.size() > 0) {
                List<Graphic> graphics = overlayResults.get(0).getGraphics();
                if (graphics != null && graphics.size() > 0) {
                    Map<String, Object> attributes = graphics.get(0).getAttributes();
                    if (attributes != null) {
                        int position = (int) attributes.get("id");
                        int groupId = (int) attributes.get("groupId");
//                        LogUtil.i("id:" + position + "groupId:" + groupId);
                        if (groupId == 0) {
//                          点击的是水库
                            ReservoirBean dataBean = reservoirDatas.get(position);
                            String reservoirLongitude = dataBean.getReservoirLongitude();
                            String reservoirLatitude = dataBean.getReservoirLatitude();
                            if (null != reservoirLongitude && null != reservoirLatitude && reservoirLongitude.length() > 0 && reservoirLatitude.length() > 0) {
                                double lgtd = Double.parseDouble(reservoirLongitude);
                                double lttd = Double.parseDouble(reservoirLatitude);
                                String name = dataBean.getReservoir();
                                commonOverlayOnClick(lgtd, lttd, name);
                            }
//                            initReservoirDetailFragment(dataBean);
                        } else if (groupId == 1) {
                            //点击的是流量站
//                            StRiverResponse.DataBean dataBean = stRiverDatas.get(position);
                            MapCommonResponse.DataBean dataBean = stRiverDatas.get(position);
                            String sLgtd = dataBean.getLgtd();
                            String sLttd = dataBean.getLttd();
                            if (null != sLgtd && null != sLttd && sLgtd.length() > 0 && sLttd.length() > 0) {
                                double lgtd = Double.parseDouble(sLgtd);
                                double lttd = Double.parseDouble(sLttd);
                                String name = dataBean.getStnm();
                                commonOverlayOnClick(lgtd, lttd, name);
                                scrollItemTitle.setText(name + "流量站详情");
                            }
                            initStRiverDetailFragment(dataBean.getStcd());
                        } else if (groupId == 2) {
                            //点击的是水质站
                            MapCommonResponse.DataBean dataBean = waterQualityDatas.get(position);
                            String sLgtd = dataBean.getLgtd();
                            String sLttd = dataBean.getLttd();
                            if (null != sLgtd && null != sLttd && sLgtd.length() > 0 && sLttd.length() > 0) {
                                double lgtd = Double.parseDouble(sLgtd);
                                double lttd = Double.parseDouble(sLttd);
                                String name = dataBean.getStnm();
                                commonOverlayOnClick(lgtd, lttd, name);
                            }
//                            initWaterQualityDetailFragment(dataBean.getStcd(), dataBean);
                        } else if (groupId == 3) {
                            //点击的是雨量站
                            String stcd = rainfallDatas.get(position).getStcd();
                            MapCommonResponse.DataBean dataBean = rainfallDatas.get(position);
                            String sLgtd = dataBean.getLgtd();
                            String sLttd = dataBean.getLttd();
                            if (null != sLgtd && null != sLttd && sLgtd.length() > 0 && sLttd.length() > 0) {
                                double lgtd = Double.parseDouble(sLgtd);
                                double lttd = Double.parseDouble(sLttd);
                                String name = dataBean.getStnm();
                                commonOverlayOnClick(lgtd, lttd, name);
                                scrollItemTitle.setText(name + "雨量站详情");
                            }
//                            initRainfallDetailFragment(stcd, dataBean);
                        } else if (groupId == 4) {
                            //点击的是水位监测点
                            String stcd = waterLevelDatas.get(position).getStcd();
                            MapCommonResponse.DataBean stStbprpB = waterLevelDatas.get(position);
                            String sLgtd = stStbprpB.getLgtd();
                            String sLttd = stStbprpB.getLttd();
                            if (null != sLgtd && null != sLttd && sLgtd.length() > 0 && sLttd.length() > 0) {
                                double lgtd = Double.parseDouble(sLgtd);
                                double lttd = Double.parseDouble(sLttd);
                                String name = stStbprpB.getStnm();
                                commonOverlayOnClick(lgtd, lttd, name);
                                scrollItemTitle.setText(name + "水位站详情");
                            }
//                            initWaterLevelDetailFragment(stcd, stStbprpB);
                        } else if (groupId == 5) {
                            //点击的是图像站
                            String stcd = pictureDatas.get(position).getStcd();
                            String sLgtd = pictureDatas.get(position).getLgtd();
                            String sLttd = pictureDatas.get(position).getLttd();
                            if (null != sLgtd && null != sLttd && sLgtd.length() > 0 && sLttd.length() > 0) {
                                double lgtd = Double.parseDouble(sLgtd);
                                double lttd = Double.parseDouble(sLttd);
                                String name = pictureDatas.get(position).getStnm();
                                commonOverlayOnClick(lgtd, lttd, name);
                                scrollItemTitle.setText(name + "图像站详情");
                            }
                            initPicDetailFragment(stcd);
                        } else if (groupId == 6) {
                            //点击的是视频站
                            String sLgtd = videoDatas.get(position).getLgtd();
                            String sLttd = videoDatas.get(position).getLttd();
                            if (null != sLgtd && null != sLttd && sLgtd.length() > 0 && sLttd.length() > 0) {
                                double lgtd = Double.parseDouble(sLgtd);
                                double lttd = Double.parseDouble(sLttd);
                                String name = videoDatas.get(position).getVsnm();
                                commonOverlayOnClick(lgtd, lttd, name);
                            }
                            VideoResponse.DataBean dataBean = videoDatas.get(position);
                            initVedioDetailFragment(dataBean);
                        }
                        if (scroll_item_layout.getVisibility() == View.GONE) {
                            hideListShowDetail();
//                            if (scroll_down_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
//                                scroll_down_layout.setToExit();
//                            }
                            if (ivArrowBack.getVisibility() != View.VISIBLE) {
                                ivArrowBack.setVisibility(View.VISIBLE);
                            }
                        }
//                        if (currentStatus != ScrollLayout.Status.OPENED) {
//                            scroll_item_layout.setToOpen();
//                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图层点击事件公用方法
     *
     * @param lgtd
     * @param lttd
     * @param name
     */
    private void commonOverlayOnClick(double lgtd, double lttd, String name) {
//        mapView.setViewpointCenterAsync(transformationPoint(lgtd, lttd));
        tvMapTitle.setText(name);
        scrollItemTitle.setText(name + "详情");
        if (callout.isShowing()) {
            callout.dismiss();
        }
        initCallout(transformationPoint(lgtd, lttd), name);
        Point point = null;
        if (scroll_down_layout.getVisibility() == View.VISIBLE) {
            if (scroll_item_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
                android.graphics.Point screenPoint = new android.graphics.Point(0, 0);
                if (mapView.screenToLocation(screenPoint) != null) {
                    double y = mapView.screenToLocation(screenPoint).getY();
                    android.graphics.Point bottomPoint = new android.graphics.Point(0, mapHeight);
                    double bottomY = mapView.screenToLocation(bottomPoint).getY();
                    double translationY = (bottomY - y) / 2 - (bottomY - y) / 4;
//                mapView.visibleArea   返回一个Polygon，表示当前在MapView中可见的ArcGISMap区域。
                    Point centerPoint = transformationPoint(lgtd, lttd);
                    //下移
                    point = new Point(centerPoint.getX(), centerPoint.getY() + translationY);
                }
            } else {
                point = transformationPoint(lgtd, lttd);
            }
            if (point != null) {
                mapView.setViewpointCenterAsync(point).addDoneListener(() -> {
//                    if (scroll_down_layout.getVisibility() == View.VISIBLE) {
//                        if (scroll_item_layout.getCurrentStatus() != ScrollLayout.Status.EXIT) {
//                            moveMap(true);
//                        } else {
//                            moveMap(false);
//                        }
//                    }
                });
            }
        }
    }

    /**
     * 水库图层点击事件
     *
     * @param screenPoint
     */
    private void reservoirOverlayOnClick(android.graphics.Point screenPoint) {
        try {
            IdentifyGraphicsOverlayResult identifyGraphicsOverlayResult = mapView.identifyGraphicsOverlayAsync(reservoirOverlay, screenPoint, 1.0, false).get();
            List<Graphic> graphics = identifyGraphicsOverlayResult.getGraphics();
            if (graphics.size() > 0) {
                Map<String, Object> attributes = graphics.get(0).getAttributes();
                Point point = (Point) graphics.get(0).getGeometry();
                mapView.setViewpointCenterAsync(point);
                if (scroll_item_layout.getVisibility() == View.GONE) {
                    hideListShowDetail();
                }
                if (ivArrowBack.getVisibility() == View.GONE) {
                    ivArrowBack.setVisibility(View.VISIBLE);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 动态权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length >= 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mLocationDisplay != null && !mLocationDisplay.isStarted()) {
                        mLocationDisplay.startAsync();//开始定位
                    }
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("定位权限被拒绝,将导致定位功能无法正常使用，需要到设置页面手动授权")
                            .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //引导用户至设置页手动授权
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //引导用户手动授权，权限请求失败
                                    ToastUtils.longToast("拒绝了定位权限，将不能使用定位功能！！！");
                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //引导用户手动授权，权限请求失败
                            ToastUtils.longToast("拒绝了定位权限，将不能使用定位功能！！！");
                        }
                    }).show();
                    // Permission Denied
                }
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLocationDisplay != null && !mLocationDisplay.isStarted()) {
            mLocationDisplay.isStarted();
        }
        if (null != lastPoint && isLoaded) {
            mapView.setViewpointCenterAsync(lastPoint, lastScale);
        }
        mapView.resume();
//        LogUtil.i("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isLoaded) {
            Viewpoint viewPoint = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
            lastPoint = viewPoint.getTargetGeometry().getExtent().getCenter();
            lastScale = viewPoint.getTargetScale();
            mapView.pause();
        }
//        LogUtil.i("onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.dispose();
    }
}
