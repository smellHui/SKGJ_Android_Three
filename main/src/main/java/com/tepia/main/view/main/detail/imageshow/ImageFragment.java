package com.tepia.main.view.main.detail.imageshow;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.APPCostant;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.detai.ImageBean;
import com.tepia.main.model.detai.ImageDetailBean;
import com.tepia.main.model.detai.LiuliangDetailBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.detail.DetailContract;
import com.tepia.main.view.main.detail.DetailPresenter;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.AdapterStationDetailList;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.LiuliangFragment;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.StationDetailResponse;
import com.tepia.photo_picker.PhotoPreview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends MVPBaseFragment<DetailContract.View, DetailPresenter> implements OnDateSetListener {
    private static String typekey_detail = "ImageFragment_";


    private RecyclerView baseinfoRecV;

    private ArrayList<ImageBean.DataBean.PicturesBean.ListBean> dataList = new ArrayList<>();
    private AdapterPatrolLoggerList adapterPatrolLoggerList;
    private OnItemCilckListener onItemCilckListener;

    private int pageSize = 10;
    private int page = 1;
    private int mCurrentCounter = 0;
    private boolean isrefresh;
    private boolean isloadmore;
    private String stcdstr = "";
    private String startTime;
    private String endTime;

    private TextView mstarttimeTv;

    private TextView mendtimeTv;

    private RecyclerView new_baseinfoRecV;
    private AdapterStationDetailList adapterStationDetailList;
    private List<StationDetailResponse> baseinfo_list_new = new ArrayList<>();
    private ImageDetailBean.DataBean imageDataBean;

    private ArrayList<String> selectedPhotos = new ArrayList<>();

    private static ImageFragment f;
    public ImageFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param stcd  测站id
     * @return
     */
    public static ImageFragment newInstance(String stcd){

        f = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(typekey_detail, stcd);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_image;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        if(getArguments() != null && getArguments().containsKey(typekey_detail)) {
            stcdstr = getArguments().getString(typekey_detail);
        }

        baseinfoRecV = findView(R.id.baseinfoRecV);
        ImageView imageIv = findView(R.id.imgIv);
        mstarttimeTv = findView(R.id.mstarttimeTv);
        mendtimeTv = findView(R.id.mendtimeTv);
        mstarttimeTv.setOnClickListener(onClickListener);
        mendtimeTv.setOnClickListener(onClickListener);
        imageIv.setOnClickListener(onClickListener);
        initTimePicker();
        initListView();
        initpresent();

        new_baseinfoRecV = findView(R.id.new_baseinfoRecV);
        new_baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));
        new_baseinfoRecV.setHasFixedSize(true);
        new_baseinfoRecV.setNestedScrollingEnabled(false);
        adapterStationDetailList = new AdapterStationDetailList(getContext(),R.layout.fragment_mine_item_monitordetail,baseinfo_list_new);
        new_baseinfoRecV.setAdapter(adapterStationDetailList);
        getDetail(stcdstr);


    }

    private void getDetail(String stcd) {
        DetailManager.getInstance().getStStbprpBAndNewIIByType(stcd)
                .subscribe(new LoadingSubject<ImageDetailBean>(false, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(ImageDetailBean imageDetailBean) {
                        if (imageDetailBean != null) {
                            if (imageDetailBean.getCode() == 0) {
                                imageDataBean = imageDetailBean.getData();
                                if (imageDataBean != null) {
                                    getStaionDetai(imageDataBean);
                                    adapterStationDetailList.notifyDataSetChanged();
                                }else {
                                    adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                                }

                            } else {
                                adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(message));
                    }
                });
    }

    /**
     * 设置基础数据recycleview数据源
     *
     * @param dataBean
     */
    private void getStaionDetai(ImageDetailBean.DataBean dataBean) {
        baseinfo_list_new.clear();
        if (dataBean != null) {
            list_add_baseinfo_new(getString(R.string.stationName), dataBean.getStnm());
            list_add_baseinfo_new(getString(R.string.manageUnit), dataBean.getAdmauth());
            list_add_baseinfo_new(getString(R.string.belongtown), dataBean.getStlc());

            list_add_baseinfo_new(getString(R.string.latitude), dataBean.getLttd());
            list_add_baseinfo_new(getString(R.string.longtude), dataBean.getLgtd());
            if (dataBean.getStPictureRS() != null && dataBean.getStPictureRS().size() > 0) {
                ImageDetailBean.DataBean.StPictureRSBean stPictureRSBean = dataBean.getStPictureRS().get(0);
                if(stPictureRSBean != null){
                    list_add_baseinfo_new(getString(R.string.time_unit), stPictureRSBean.getTm());

                    adapterStationDetailList.setActivtyandFragment(getBaseActivity(),f);
                    StationDetailResponse stationDetailResponse = new StationDetailResponse();
                    stationDetailResponse.setTitleLeft(getString(R.string.zuixingtuxiang));
                    stationDetailResponse.setImageIvPath(stPictureRSBean.getPicpath());
                    baseinfo_list_new.add(stationDetailResponse);

                }
            }

        }

    }

    private void list_add_baseinfo_new(String letfStr, String rightStr) {
        StationDetailResponse stationDetailResponse = new StationDetailResponse();
        stationDetailResponse.setTitleLeft(letfStr);
        setRightText(stationDetailResponse, rightStr);
        baseinfo_list_new.add(stationDetailResponse);
    }

    private void setRightText(StationDetailResponse stationDetailResponse, String str) {
        if (TextUtils.isEmpty(str)) {
            stationDetailResponse.setTitleRight(getString(R.string.setting_t_null));
        } else {
            stationDetailResponse.setTitleRight(str);
        }

    }

    /**
     * 初始化时间选择器
     */
    private void initTimePicker() {
        timePickerDialogUtil = new TimePickerDialogUtil(getContext(), sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);
        endTime = TimeFormatUtils.getStringDate();
        last_millseconds_end = TimeFormatUtils.strToLong(endTime);
        //往前一个月
        startTime = TimeFormatUtils.getNextDay(endTime, ConfigConsts.timeseriod);
        last_millseconds_start = TimeFormatUtils.strToLong(startTime);
        mstarttimeTv.setText(startTime);
        mendtimeTv.setText(endTime);


    }

    private void initpresent(){
        mPresenter.attachView(new DetailContract.View<ImageBean>() {
            @Override
            public void success(ImageBean imageBean) {
                ImageBean.DataBean dataBean = imageBean.getData();
                adapterPatrolLoggerList.setFileServerUrl(dataBean.getFileServerUrl());
                List<ImageBean.DataBean.PicturesBean.ListBean> data = dataBean.getPictures().getList();
                int totalSize = dataBean.getPictures().getTotal();
                if (isrefresh) {
                    //首次加载
                    if(data == null || data.size() == 0){
//                        showEmptyView();
                        adapterPatrolLoggerList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                    }else{

                        //首次加载
                        dataList.addAll(data);
                        adapterPatrolLoggerList.notifyDataSetChanged();
                    }
                    adapterPatrolLoggerList.setEnableLoadMore(true);
                } else if (isloadmore) {
                    adapterPatrolLoggerList.addData(data);
                    mCurrentCounter = adapterPatrolLoggerList.getData().size();
                    if (mCurrentCounter >= totalSize) {
                        //数据全部加载完毕
                        adapterPatrolLoggerList.loadMoreEnd();
                        return;
                    }
                    adapterPatrolLoggerList.loadMoreComplete();
                }

                if (onItemCilckListener != null) {
                    onItemCilckListener.onGetImages(data);
                }
            }

            @Override
            public void failure(String msg) {
                adapterPatrolLoggerList.setEmptyView(EmptyLayoutUtil.show(msg));
            }

            @Override
            public Context getContext() {
                return null;
            }
        });

    }

    private void initListView() {
        baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterPatrolLoggerList = new AdapterPatrolLoggerList(getContext(), R.layout.fragment_detail_image_itemlist, dataList);
        adapterPatrolLoggerList.openLoadAnimation();
        baseinfoRecV.setAdapter(adapterPatrolLoggerList);
        adapterPatrolLoggerList.setOnItemClickListener((adapter, view, position) -> {
            if (onItemCilckListener != null) {
                onItemCilckListener.onItemClick(dataList.get(position), position);


            }
            ArrayList<String> pathlist = new ArrayList<>();
            for(int i = 0;i < dataList.size(); i++){
                pathlist.add(adapterPatrolLoggerList.getFileServerUrl() + dataList.get(i).getPicpath());
            }
            PhotoPreview.builder()
                    .setPhotos(pathlist)
                    .setShowDeleteButton(false)
                    .setCurrentItem(position)
                    .start(getBaseActivity(), ImageFragment.this);



          /*  Intent intent = new Intent();
            intent.setClass(getContext(),ImageShowActivity.class);
            intent.putParcelableArrayListExtra("girls",dataList);
            intent.putExtra("current",position);
            intent.putExtra("fileurl",adapterPatrolLoggerList.getFileServerUrl());
            startActivity(intent);*/
        });
        adapterPatrolLoggerList.setOnLoadMoreListener(() -> {
            baseinfoRecV.postDelayed(() -> {
                page++;
                isrefresh = false;
                isloadmore = true;
                mPresenter.findPictureRByStcd(stcdstr,String.valueOf(pageSize), String.valueOf(page), startTime, endTime, isrefresh);


            }, 1000);
        }, baseinfoRecV);
    }

    /**
     * 查询
     * @param startTime
     * @param endTime
     */
    private void search(String startTime,String endTime){
        if (DoubleClickUtil.isFastDoubleClick()) {
            return;
        }
        if(!NetUtil.isNetworkConnected(getBaseActivity())){
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        adapterPatrolLoggerList.setEnableLoadMore(false);
        dataList.clear();
        adapterPatrolLoggerList.notifyDataSetChanged();
        page = 1;
        isrefresh = true;
        isloadmore = false;
        mPresenter.findPictureRByStcd(stcdstr,String.valueOf(pageSize), String.valueOf(page), startTime, endTime, isrefresh);


    }

    public interface OnItemCilckListener {
        void onItemClick(ImageBean.DataBean.PicturesBean.ListBean data, int position);
        void onGetImages(List<ImageBean.DataBean.PicturesBean.ListBean> data);


    }

    public void setOnItemCilckListener(OnItemCilckListener onItemCilckListener) {
        this.onItemCilckListener = onItemCilckListener;
    }

    @Override
    protected void initRequestData() {
        search(startTime,endTime);

    }

    // 起始时间选择器
    private boolean startflag = false;
    // 终止时间选择器
    private boolean endflag = false;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private long last_millseconds_start = 0;
    private long last_millseconds_end = 0;

    private TimePickerDialogUtil timePickerDialogUtil;

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        if (startflag) {
            last_millseconds_start = millseconds;
            String text = timePickerDialogUtil.getDateToString(millseconds);
            startTime = text;
            mstarttimeTv.setText(startTime);
            startflag = false;
            LogUtil.e("startTime", text);

        } else if (endflag) {
            last_millseconds_end = millseconds;
            String text = timePickerDialogUtil.getDateToString(millseconds);
            endTime = text;
            mendtimeTv.setText(endTime);
            endflag = false;
            LogUtil.e("endTime", text);

        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int i = view.getId();
            if (i == R.id.mstarttimeTv) {
                if (timePickerDialogUtil.startDialog != null) {
                    timePickerDialogUtil.startDialog = null;
                }
                startflag = true;
                if (last_millseconds_start != 0) {
                    timePickerDialogUtil.builder.setCurrentMillseconds(last_millseconds_start);
                }
                timePickerDialogUtil.builder.setTitleStringId(getContext().getString(R.string.starttimeTitle));
                timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
                timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");


            } else if (i == R.id.mendtimeTv) {
                if (timePickerDialogUtil.startDialog != null) {
                    timePickerDialogUtil.startDialog = null;
                }
                endflag = true;
                if (last_millseconds_end != 0) {
                    timePickerDialogUtil.builder.setCurrentMillseconds(last_millseconds_end);
                }
                timePickerDialogUtil.builder.setTitleStringId(getContext().getString(R.string.endtimeTitle));
                timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();

                timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");

            } else if (i == R.id.imgIv) {
                for (int j = 0; j < 100; j++) {
                    search(startTime,endTime);
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (dataList != null) {
            dataList.clear();
        }
    }
}
