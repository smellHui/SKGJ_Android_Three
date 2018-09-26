package com.tepia.main.view.maincommon.reservoirs;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.CustomLinearLayoutManager;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.view.maincommon.reservoirs.detail.CapacityActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.VisitLogActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.FloodActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.IntroduceOfReservoirsActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.SafeManagerPlanActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.SafeRunningActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.SupportingActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.VedioOfReservoirActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :
 * Version         :       1.0
 * 功能描述        :  主页四 —— 水库页
 **/
@Route(path = AppRoutePath.app_main_fragment_reservoirs)
public class ReservoirsFragment extends BaseCommonFragment {

    public static final String RESERVOIRId = "RESERVOIRId";
    public static final String RESERVOIRNAME = "RESERVOIRNAME";
    private RecyclerView resviorRec;
    private AdapterMainReservoirs adapterMainReservoirs;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();
    private Banner banner;
    private List<Integer> images = new ArrayList<Integer>();
    private List<String> titles = new ArrayList<>();

    public ReservoirsFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reservoirs;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_reservoirs));
        getRightTianqi().setVisibility(View.VISIBLE);
        initBanner();
        resviorRec = findView(R.id.resviorRec);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.jianjie1);
        setResviorRec("水库视频", "RESERVOIRS VEDIO", R.drawable.jianjie2);
        setResviorRec("水库库容曲线", "CAPACITY CURVE", R.drawable.jianjie3);
        setResviorRec("水库配套设施", "RESERVOIRS SUPPORTING", R.drawable.jianjie4);
        setResviorRec("防汛物资", "FLOOD-FIGHTING MATERIALS", R.drawable.jianjie5);
        setResviorRec("调度运行方案", "DISPATCHING OPERATION PLAN", R.drawable.jianjie6);
        setResviorRec("水库安全管理应急预案", "CONTINGENCY PLAN", R.drawable.jianjie7);
        setResviorRec("水库安全运行管理情况", "ADMINISTRATIVE SITUATION", R.drawable.jianjie8);
        setResviorRec("到访水库日志", "RESERVOIRS SUPPORTING", R.drawable.jianjie8);
        CustomLinearLayoutManager customLinearLayoutManager = new CustomLinearLayoutManager(getContext());
        customLinearLayoutManager.setScrollEnabled(false);
//        resviorRec.setLayoutManager(new LinearLayoutManager(getContext()));
        resviorRec.setLayoutManager(customLinearLayoutManager);
        adapterMainReservoirs = new AdapterMainReservoirs(getBaseActivity(), R.layout.fragment_reservoirs_item, myReservoirsItemBeanList);
        resviorRec.setAdapter(adapterMainReservoirs);
        adapterMainReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
                String reservoirId =  reservoirBean.getReservoirId();
                String reservoirName = reservoirBean.getReservoir();
                Intent intent = new Intent();
                intent.putExtra(ReservoirsFragment.RESERVOIRId,reservoirId);
                intent.putExtra(ReservoirsFragment.RESERVOIRNAME,reservoirName);
                if (position == 0) {
                    intent.setClass(getBaseActivity(), IntroduceOfReservoirsActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    intent.setClass(getBaseActivity(), VedioOfReservoirActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    intent.setClass(getBaseActivity(), CapacityActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    intent.setClass(getBaseActivity(), SupportingActivity.class);
                    startActivity(intent);
                } else if (position == 4) {
                    intent.setClass(getBaseActivity(), FloodActivity.class);
                    startActivity(intent);
                } else if (position == 5) {
                    //调度运行方案
                    intent.setClass(getBaseActivity(), OperationPlanActivity.class);
                    intent.putExtra("select","1");
                    startActivity(intent);
                } else if (position == 6) {
                    //水库安全管理应急预案
                    intent.setClass(getBaseActivity(), OperationPlanActivity.class);
                    intent.putExtra("select","2");
                    startActivity(intent);
                } else if (position == 7) {
                    intent.setClass(getBaseActivity(), SafeRunningActivity.class);
                    startActivity(intent);
                } else if (position == 8) {
                    // TODO: 2018/9/20 待更换图标 
                    intent.setClass(getBaseActivity(), VisitLogActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void initBanner() {
        banner = findView(R.id.banner);
        for (int i = 0; i < 4; i++) {

        }
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);
        titles.add("2018-02-09");
        titles.add("");
        titles.add("2017-02-09");
        titles.add("");

        //设置圆形指示器与标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置图片源
        banner.setImages(images);
        //设置标题源
//        banner.setBannerTitles(titles);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context)
                        .load(path)
                        .apply(ConfigConsts.options)
                        .thumbnail(0.5f)
                        .into(imageView);
            }
        });
        banner.start();

    }

    private void setResviorRec(String title, String middle_title, int resourceImg) {
        MyReservoirsItemBean myReservoirsItemBean = new MyReservoirsItemBean();
        myReservoirsItemBean.setTitle(title);
        myReservoirsItemBean.setMiddle_title(middle_title);
        myReservoirsItemBean.setResourceImg(resourceImg);
        myReservoirsItemBeanList.add(myReservoirsItemBean);
    }

    @Override
    protected void initRequestData() {

    }
}
