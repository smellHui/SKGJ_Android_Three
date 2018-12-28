package com.tepia.main.view.maincommon.reservoirs;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.CustomLinearLayoutManager;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.maincommon.reservoirs.detail.CapacityActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.VisitLogActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.FloodActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.IntroduceOfReservoirsActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.SafeRunningActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.SupportingActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.VedioOfReservoirActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.WaterLevelActivity;
import com.tepia.main.view.maincommon.setting.ChoiceReservoirActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-21
 * Time            :       下午6:01
 * Version         :       1.2.4
 * location        :       武汉研发中心
 * 功能描述         :       主页四 —— 水库页
 **/
@Route(path = AppRoutePath.app_main_fragment_reservoirs)
public class ReservoirsFragment extends BaseCommonFragment {

    public static final String RESERVOIRId = "RESERVOIRId";
    public static final String RESERVOIRNAME = "RESERVOIRNAME";
    private RecyclerView resviorRec;
    private AdapterMainReservoirs adapterMainReservoirs;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();
    private Banner banner;
    private List<Integer> images = new ArrayList<>();
    private List<String> imagesList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private TextView tv_reservoir_name;
    private TextView switchTv;
    private String oldReserviorId;

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
        EventBus.getDefault().register(this);
        banner = findView(R.id.banner);
        initBanner();
        resviorRec = findView(R.id.resviorRec);
        switchTv = findView(R.id.switchTv);
        tv_reservoir_name = findView(R.id.tv_reservoir_name);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.jianjie1);
        setResviorRec(getString(R.string.waterlevel_name), "FLOOD CONTROL LEVER", R.drawable.jianjie_xunqi);
        setResviorRec("水库视频", "RESERVOIRS VEDIO", R.drawable.jianjie2);
        setResviorRec("水位库容曲线", "CAPACITY CURVE", R.drawable.jianjie3);
        setResviorRec("水库配套设施", "RESERVOIRS SUPPORTING", R.drawable.jianjie4);
        setResviorRec("防汛物资", "FLOOD-FIGHTING MATERIALS", R.drawable.jianjie5);
        setResviorRec("调度运行方案", "DISPATCHING OPERATION PLAN", R.drawable.jianjie6);
        setResviorRec("水库安全管理应急预案", "CONTINGENCY PLAN", R.drawable.jianjie7);
        setResviorRec("水库安全运行管理", "ADMINISTRATIVE SITUATION", R.drawable.jianjie8);
        setResviorRec("到访水库日志", "RESERVOIRS SUPPORTING", R.drawable.jianjie_visitlog);
        CustomLinearLayoutManager customLinearLayoutManager = new CustomLinearLayoutManager(getContext());
        customLinearLayoutManager.setScrollEnabled(false);
//        resviorRec.setLayoutManager(new LinearLayoutManager(getContext()));
        resviorRec.setLayoutManager(customLinearLayoutManager);
        adapterMainReservoirs = new AdapterMainReservoirs(getBaseActivity(), R.layout.fragment_reservoirs_item, myReservoirsItemBeanList);
        resviorRec.setAdapter(adapterMainReservoirs);
        adapterMainReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast(R.string.no_network);
                    return;
                }
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
                String reservoirId = reservoirBean.getReservoirId();
                String reservoirName = reservoirBean.getReservoir();
                Intent intent = new Intent();
                intent.putExtra(ReservoirsFragment.RESERVOIRId, reservoirId);
                intent.putExtra(ReservoirsFragment.RESERVOIRNAME, reservoirName);
                String nameStr = myReservoirsItemBeanList.get(position).getTitle();
                if (position == 0) {
                    intent.setClass(getBaseActivity(), IntroduceOfReservoirsActivity.class);
                    startActivity(intent);
                } else if(position == 1){
                    intent.setClass(getBaseActivity(), WaterLevelActivity.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    intent.setClass(getBaseActivity(), VedioOfReservoirActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    intent.setClass(getBaseActivity(), CapacityActivity.class);
                    startActivity(intent);
                } else if (position == 4) {
                    intent.setClass(getBaseActivity(), SupportingActivity.class);
                    startActivity(intent);
                } else if (position == 5) {
                    intent.setClass(getBaseActivity(), FloodActivity.class);
                    startActivity(intent);
                } else if (position == 6) {
                    //调度运行方案
                    intent.setClass(getBaseActivity(), OperationPlanActivity.class);
                    intent.putExtra("select", "1");
                    startActivity(intent);
                } else if (position == 7) {
                    //水库安全管理应急预案
                    intent.setClass(getBaseActivity(), OperationPlanActivity.class);
                    intent.putExtra("select", "2");
                    startActivity(intent);
                } else if (position == 8) {
                    intent.setClass(getBaseActivity(), SafeRunningActivity.class);
                    startActivity(intent);
                } else if (position == 9) {
                    intent.setClass(getBaseActivity(), VisitLogActivity.class);
                    startActivity(intent);
                }
            }
        });

        switchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showSelectReservoir();
                startActivityForResult(new Intent(getBaseActivity(), ChoiceReservoirActivity.class), ChoiceReservoirActivity.resultCode);
            }
        });
        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        oldReserviorId = reservoirBean.getReservoirId();
        startBanner(reservoirBean);


    }

    private void initBanner() {


        //设置圆形指示器与标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置标题源
//        banner.setBannerTitles(titles);


    }

    /**
     * 开始循环
     *
     * @param reservoirBean
     */
    private void startBanner(ReservoirBean reservoirBean) {
        LogUtil.e("-----------执行轮播切换");
        if (imagesList != null) {
            imagesList.clear();
            banner.stopAutoPlay();
        }
        if (images != null) {
            images.clear();
            banner.stopAutoPlay();
        }
        List<ReservoirBean.FilesBean> filesBeanList = reservoirBean.getFiles();
        if (filesBeanList != null && filesBeanList.size() > 0) {
            for (ReservoirBean.FilesBean filesBean : reservoirBean.getFiles()) {
                if (filesBean != null) {
                    imagesList.add(filesBean.getFilePath());
                }
            }
            //设置图片源
            banner.setImages(imagesList);
        } else {
            images.add(R.drawable.jianjie_banner0);
            images.add(R.drawable.jianjie_banner1);
//            images.add(R.drawable.jianjie_banner2);
//            images.add(R.drawable.jianjie_banner3);
            //设置图片源
            banner.setImages(images);
        }
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

    @Subscribe
    public void getEventBus(Integer num) {
        if (num != null && num == 100) {
            ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
            tv_reservoir_name.setText(reservoirBean.getReservoir());
//            LogUtil.e("当前默认水库id--------------" + reservoirBean.getReservoirId());
            String reservoirId = reservoirBean.getReservoirId();
            if (!(reservoirId.equals(oldReserviorId))) {
                oldReserviorId = reservoirId;
                startBanner(reservoirBean);
            }
        }
    }

    @Override
    protected void initRequestData() {
        //ReservoirBean reservoirBean;
        LogUtil.e("--------------initRequestData");

        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        tv_reservoir_name.setText(reservoirBean.getReservoir());
        LogUtil.e("当前默认水库id--------------" + reservoirBean.getReservoirId());
        String reservoirId = reservoirBean.getReservoirId();
        if (!reservoirId.equals(oldReserviorId)) {
            oldReserviorId = reservoirId;
            startBanner(reservoirBean);
        }


    }

    /**
     * 切换水库
     */
    private void showSelectReservoir() {
        ArrayList<ReservoirBean> reservoirBeans = UserManager.getInstance().getLocalReservoirList();
        if (reservoirBeans != null) {
            String[] stringItems = new String[reservoirBeans.size()];
            for (int i = 0; i < reservoirBeans.size(); i++) {
                stringItems[i] = reservoirBeans.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ReservoirBean selectedResrvoir = reservoirBeans.get(position);
                    com.tepia.main.model.user.UserManager.getInstance().saveDefaultReservoir(selectedResrvoir);
                    tv_reservoir_name.setText(selectedResrvoir.getReservoir());
                    startBanner(selectedResrvoir);
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
                ReservoirBean defaultReservoir = UserManager.getInstance().getDefaultReservoir();
                if (!oldReserviorId.equals(defaultReservoir.getReservoirId())) {
                    tv_reservoir_name.setText(defaultReservoir.getReservoir());
                    oldReserviorId = defaultReservoir.getReservoirId();
                    startBanner(defaultReservoir);

                }
                break;
            default:
                break;
        }
    }


}
