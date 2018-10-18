package com.tepia.main.view.main.work.task.taskdeal;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DrawStatus;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedEvent;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.tepia.base.AppRoutePath;
import com.tepia.base.common.CommonFragmentPagerAdapter;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.ScrollLayout.ScrollLayout;
import com.tepia.base.view.arcgisLayout.ArcgisLayout;
import com.tepia.base.view.dialog.loading.LoadingDialog;
import com.tepia.main.R;
import com.tepia.main.common.KeyboardLayout;
import com.tepia.main.databinding.ActivityTaskDeal2Binding;
import com.tepia.main.model.task.bean.PositionNamesBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdeal.taskitemdeal.TaskItemDealFragment;
import com.tepia.main.view.main.work.task.taskdetail.AdapterPositionTitle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * MVPPlu
 * 邮箱 784787081@qq.com
 */
@Route(path = AppRoutePath.app_task_deal)
public class TaskDealActivity extends MVPBaseActivity<TaskDealContract.View, TaskDealPresenter> implements TaskDealContract.View {

    private ActivityTaskDeal2Binding mBinding;

    @Autowired(name = "workOrderId")
    String workOrderId;
    @Autowired(name = "taskBean")
    String temp;
    @Autowired(name = "position")
    int position = 0;
    private TaskBean taskBean;
    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener;
    /**
     * 当前位置
     */
    private Point currentPoint;
    private boolean isFirstInitMap = false;
    private int initCount;
    private CommonFragmentPagerAdapter adapter;
    private Point positionPoint;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstInitMap = true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_deal2;
    }

    @Override
    public void initView() {

        mBinding = DataBindingUtil.bind(mRootView);
        ImmersionBar.setTitleBar(this, mBinding.loTitle);
        initScrllLayout();
        initMapView();
    }

    private void initMapView() {
        mBinding.alMapview.startLocation();
        mBinding.alMapview.setOnAddLocationChangedListener(new ArcgisLayout.OnAddLocationChangedListener() {
            @Override
            public void getLocation(Point point) {
                currentPoint = point;
            }
        });

        mBinding.alMapview.getMapView().addDrawStatusChangedListener(new DrawStatusChangedListener() {
            @Override
            public void drawStatusChanged(DrawStatusChangedEvent drawStatusChangedEvent) {
                DrawStatus drawStatus = drawStatusChangedEvent.getDrawStatus();
                if (drawStatus == DrawStatus.COMPLETED) {
                    if (isFirstInitMap) {
                        if (currentPoint != null) {
                            if ("2".equals(taskBean.getExecuteStatus())) {
                                mBinding.alMapview.getMapView().setViewpointCenterAsync(currentPoint, mBinding.alMapview.itemScale).addDoneListener(() -> {
                                    moveMap(true);
                                });
                            } else {
                                if (positionPoint != null) {
                                    mBinding.alMapview.getMapView().setViewpointCenterAsync(positionPoint, mBinding.alMapview.itemScale).addDoneListener(() -> {
                                        moveMap(true);
                                    });
                                }
                            }
                        }
                        isFirstInitMap = false;
                    }
                }
            }
        });
        mBinding.alMapview.setLayoutParams(
                new FrameLayout.LayoutParams(ScreenUtil.getScreenWidthPix(getBaseContext()),
                        ScreenUtil.getScreenHeightPix(getBaseContext()) - ScreenUtil.dp2px(getBaseContext(), 100)));
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        mBinding.kblContainer.setOnkbdStateListener(new KeyboardLayout.onKeyboaddsChangeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case KeyboardLayout.KEYBOARD_STATE_INIT:
                        break;
                    case KeyboardLayout.KEYBOARD_STATE_HIDE:
                        ToastUtils.shortToast("软键盘隐藏");
                        break;
                    case KeyboardLayout.KEYBOARD_STATE_SHOW:
                        ToastUtils.shortToast("软键盘弹起");
                        break;
                    default:
                        break;
                }
            }
        });
        mBinding.tvSaveAndNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                int itemCur = mBinding.viewPager.getCurrentItem();
                TaskItemDealFragment taskItemDealFragment = (TaskItemDealFragment) ((CommonFragmentPagerAdapter) mBinding.viewPager.getAdapter())
                        .getItem(itemCur);
                if ("2".equals(taskBean.getExecuteStatus()) && (taskBean.getExecuteId() != null && taskBean.getExecuteId().equals(UserManager.getInstance().getUserBean().getData().getUserCode()))) {
                    TaskItemDealFragment.ReturnData data = taskItemDealFragment.getDealContent();
                    if (data.isFinish()) {
                        if (currentPoint != null) {
                            LoadingDialog.with(getContext()).setMessage(ResUtils.getString(R.string.data_saving)).show();
                            mPresenter.appReservoirWorkOrderItemCommitOne(data.getWorkOrderId(),
                                    data.getItemId(), data.getExResult(), data.getExDesc(), currentPoint.getX() + "",
                                    currentPoint.getY() + "", data.getFiles(), data.getEndfiles(),
                                    false, ResUtils.getString(R.string.data_saving));
                        }
                    }
                } else {
                    nextPage();
                }

            }
        });
        mBinding.tvPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                prePage();
            }
        });
    }

    private void prePage() {
        int count = mBinding.viewPager.getCurrentItem();
        if (count - 1 >= 0) {
            position = mBinding.viewPager.getCurrentItem() - 1;
            mBinding.viewPager.setCurrentItem(mBinding.viewPager.getCurrentItem() - 1);
            initPositoinTitle(taskBean.getBizReservoirWorkOrderItems().get(position).getPositionTreeNames());
            initBottomBtn(taskBean);
        } else {
            finish();
        }
    }

    private void nextPage() {
        int count = mBinding.viewPager.getCurrentItem();
        if (count + 1 < taskBean.getBizReservoirWorkOrderItems().size()) {
            position = mBinding.viewPager.getCurrentItem() + 1;
            mBinding.viewPager.setCurrentItem(mBinding.viewPager.getCurrentItem() + 1);

            initPositoinTitle(taskBean.getBizReservoirWorkOrderItems().get(position).getPositionTreeNames());
            initBottomBtn(taskBean);
        } else {
            finish();
        }
    }

    @Override
    protected void initRequestData() {
        setStatusBarTextDark();
        if (taskBean == null) {
            taskBean = new Gson().fromJson(temp, TaskBean.class);
        }
        if (taskBean != null) {
            refreshView(taskBean);
        }

        if (taskBean == null) {
            mPresenter.getTaskDetail(workOrderId, true, getString(R.string.data_loading));
        } else {
            mPresenter.getTaskDetail(workOrderId, false, "");
        }
    }

    private void refreshView(TaskBean taskBean) {
        if (taskBean.getBizReservoirWorkOrderItems() == null || taskBean.getBizReservoirWorkOrderItems().size() == 0) {
            return;
        }

        initPositoinTitle(taskBean.getBizReservoirWorkOrderItems().get(position).getPositionTreeNames());

        initViewPager();
        initBottomBtn(taskBean);
        refreshMapView();
    }

    private void initViewPager() {
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager());
        for (TaskItemBean bean : taskBean.getBizReservoirWorkOrderItems()) {
            adapter.addFragment(TaskItemDealFragment.newInstance(taskBean, workOrderId, bean.getItemId(), bean, taskBean.getExecuteStatus()));
        }
        mBinding.viewPager.setAdapter(adapter);
//        mBinding.viewPager.setOffscreenPageLimit(0);
        mBinding.viewPager.setCurrentItem(position);
        mBinding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                refreshMapView();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void refreshMapView() {
        mBinding.alMapview.removeGraphics();
        TaskItemBean taskItemBean = taskBean.getBizReservoirWorkOrderItems().get(position);
        try {
            {    //添加执行点
                if (TextUtils.isEmpty(taskItemBean.getExcuteLongitude()) || TextUtils.isEmpty(taskItemBean.getExcuteLatitude())) {
                } else {
                    double longitude = Double.parseDouble(taskItemBean.getExcuteLongitude());
                    double Latitude = Double.parseDouble(taskItemBean.getExcuteLatitude());
                    Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
                    Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                    Map<String, Object> attrs = new HashMap<>();
                    attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                    mBinding.alMapview.addPic(R.drawable.icon_taskitem_exe, point, attrs);
                    positionPoint = point;
                }
            }
            {   //添加任务点
                if (TextUtils.isEmpty(taskItemBean.getPositionLongitude()) || TextUtils.isEmpty(taskItemBean.getPositionLatitude())) {
                } else {
                    double longitude = Double.parseDouble(taskItemBean.getPositionLongitude());
                    double Latitude = Double.parseDouble(taskItemBean.getPositionLatitude());
                    Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
                    Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                    Map<String, Object> attrs = new HashMap<>();
                    attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                    mBinding.alMapview.addPic(R.drawable.icon_taskitem_temp, point, attrs);
                    positionPoint = point;
                }

            }
        } catch (Exception e) {
        }
        mBinding.alMapview.setOnAddPointClickListener(new ArcgisLayout.OnAddPointClickListener() {
            @Override
            public void onCilck(Point point, Map<String, Object> attributes) {
                if (mBinding.scrollDownLayout.getCurrentStatus() == ScrollLayout.Status.EXIT) {
                    mBinding.scrollDownLayout.setToOpen();
                }
                String temp = (String) attributes.get("taskItemBean");
                TaskItemBean taskItemBean = new Gson().fromJson(temp, TaskItemBean.class);
                mBinding.alMapview.showCallout(point, taskItemBean.getSuperviseItemName());
            }
        });
    }

    private Boolean isMoveUp = true;
    private Boolean isMoveDown = false;

    private void initScrllLayout() {

        mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
            @Override
            public void onScrollProgressChanged(float currentProgress) {
//                if (currentProgress >= 0) {
//                    float precent = 255 * currentProgress;
//                    if (precent > 255) {
//                        precent = 255;
//                    } else if (precent < 0) {
//                        precent = 0;
//                    }
//                    mBinding.scrollDownLayout.getBackground().setAlpha(255 - (int) precent);
//                }
                if (mBinding.tvFoot.getVisibility() == View.VISIBLE) {
                    mBinding.tvFoot.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollFinished(ScrollLayout.Status currentStatus) {
                if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
                    mBinding.tvFoot.setVisibility(View.VISIBLE);
                }

                if (currentStatus == ScrollLayout.Status.OPENED) {
                    TaskItemDealFragment cur = (TaskItemDealFragment) adapter.getItem(position);
                    if (cur != null) {
                        cur.clearFocus();
                    }
                }
                changeMapViewLayoutParam(currentStatus);
                if (currentStatus == ScrollLayout.Status.EXIT) {
                    if (!isMoveDown) {
                        moveMap(false);
                        isMoveDown = true;
                    }
                    isMoveUp = false;
                } else if (currentStatus == ScrollLayout.Status.OPENED) {
                    if (!isMoveUp) {
                        moveMap(true);
                        isMoveUp = true;
                    }
                    isMoveDown = false;
                }
            }

            @Override
            public void onChildScroll(int top) {

            }
        };
        mBinding.tvFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.scrollDownLayout.setToOpen();
            }
        });
        /**设置 setting*/
        mBinding.scrollDownLayout.setMinOffset(0);
        mBinding.scrollDownLayout.setMaxOffset((int) (ScreenUtil.getScreenPix(this).heightPixels * 0.6));
        mBinding.scrollDownLayout.setExitOffset(ScreenUtil.dp2px(this, 50));
        mBinding.scrollDownLayout.setIsSupportExit(true);
        mBinding.scrollDownLayout.setAllowHorizontalScroll(true);
        mBinding.scrollDownLayout.setOnScrollChangedListener(mOnScrollChangedListener);
        mBinding.scrollDownLayout.scrollToClose();
        mBinding.scrollDownLayout.getBackground().setAlpha(0);

        mBinding.scrollDownLayout.post(new Runnable() {
            @Override
            public void run() {
                mBinding.scrollDownLayout.setExitOffset(ScreenUtil.dp2px(getContext(), 100) + mBinding.loBottom.getHeight());
            }
        });
    }

    private void changeMapViewLayoutParam(ScrollLayout.Status currentStatus) {
        switch (currentStatus) {
            case EXIT:

                break;
            case OPENED:

                break;
            case CLOSED:
                break;
            default:
                break;

        }
    }

    private void initBottomBtn(TaskBean taskBean) {
        mBinding.numTv.setText((position + 1) + "/" + taskBean.getBizReservoirWorkOrderItems().size());
        switch (taskBean.getExecuteStatus()) {
            case "1":
                mBinding.tvSaveAndNext.setText("下一项");
                break;
            case "2":
                if (taskBean.getExecuteId() != null && taskBean.getExecuteId().equals(UserManager.getInstance().getUserBean().getData().getUserCode())) {
                    mBinding.tvSaveAndNext.setText("保存并下一项");
                } else {
                    mBinding.tvSaveAndNext.setText("下一项");
                }
                break;
            case "3":
                mBinding.tvSaveAndNext.setText("下一项");
                break;
            default:
                mBinding.tvSaveAndNext.setText("下一项");
                break;
        }
    }

    private void initPositoinTitle(String treeNames) {
//        mBinding.tvItemCount.setText(taskBean.getBizReservoirWorkOrderItems().get(position).getReservoirSuperviseSequence() + "");
        mBinding.tvItemCount.setText(position + 1 + "");
        if (TextUtils.isEmpty(treeNames)) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvPositionTitle.setLayoutManager(layoutManager);
        List<PositionNamesBean> positionNames = new ArrayList<>();
        if (treeNames.contains("/")) {
            String[] temps = TextUtils.split(treeNames, "/");
            for (int i = 0; i < temps.length; i++) {
                positionNames.add(new PositionNamesBean(temps[i]));
            }
        }
        AdapterPositionTitle adapterPositionTitle = new AdapterPositionTitle(R.layout.lv_item_position_title, positionNames);
        mBinding.rvPositionTitle.setAdapter(adapterPositionTitle);
    }

    @Override
    public void getTaskDetailSucess(TaskBean data) {
        taskBean = data;
        refreshView(data);
    }

    @Override
    public void commitSucess() {
        nextPage();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.alMapview.onMapResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.alMapview.onMapPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        gaodeEntity.stopTrace();
        mBinding.alMapview.onMapDestroy();
    }


    public void onFocusChange(View view, boolean b) {

        if (b) {
            if (mBinding.scrollDownLayout.getCurrentStatus() == ScrollLayout.Status.OPENED) {
                mBinding.scrollDownLayout.setToClosed();
            }
        } else {
//            if (mBinding.scrollDownLayout.getCurrentStatus() == ScrollLayout.Status.CLOSED) {
//                mBinding.scrollDownLayout.setToOpen();
//            }
        }
    }

    /**
     * 地图滑动
     *
     * @param status true上移  false 下移
     */
    private void moveMap(Boolean status) {
        try {
            MapView mapView = mBinding.alMapview.getMapView();
            android.graphics.Point screenPoint = new android.graphics.Point(0, 0);
            if (mapView.screenToLocation(screenPoint) != null) {
                double y = mapView.screenToLocation(screenPoint).getY();
                android.graphics.Point bottomPoint = new android.graphics.Point(0, mBinding.alMapview.mapHeight);
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
        } catch (Exception e) {

        }
    }

}
