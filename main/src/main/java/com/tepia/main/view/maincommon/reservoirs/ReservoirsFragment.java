package com.tepia.main.view.maincommon.reservoirs;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
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
import com.tepia.main.model.user.UserInfoBean;
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

import cn.jpush.android.api.JPushInterface;

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

    private String RESERVOIR_DESCRIPTION = "水库简介";
    private String RESERVOIRS_VEDIO = "水库视频";
    private String CAPACITY_CURVE = "水位库容曲线";
    private String RESERVOIRS_SUPPORTING = "水库配套设施";
    private String FLOOD_FIGHTING_MATERIALS = "防汛物资";
    private String DISPATCHING_OPERATION_PLAN = "调度运行方案";
    private String CONTINGENCY_PLAN = "水库安全管理应急预案";
    private String ADMINISTRATIVE_SITUATION = "水库安全运行管理";
    private String RESERVOIRS_LOG = "到访水库日志";
    private String FLOOD_CONTROL_LEVER = "汛限水位";

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
        setResviorRec(RESERVOIR_DESCRIPTION, "RESERVOIRS DESCRIPTION", R.drawable.jianjie1);

        if (UserManager.getInstance().getMenuItemBean("汛限水位") != null) {
            setResviorRec(FLOOD_CONTROL_LEVER, "FLOOD CONTROL LEVER", R.drawable.jianjie_xunqi);
        } else {
            LogUtil.e("服务器没有配置汛限水位路径");
        }

        setResviorRec(RESERVOIRS_VEDIO, "RESERVOIRS VEDIO", R.drawable.jianjie2);
        setResviorRec(CAPACITY_CURVE, "CAPACITY CURVE", R.drawable.jianjie3);
        setResviorRec(RESERVOIRS_SUPPORTING, "RESERVOIRS SUPPORTING", R.drawable.jianjie4);
        setResviorRec(FLOOD_FIGHTING_MATERIALS, "FLOOD-FIGHTING MATERIALS", R.drawable.jianjie5);
        setResviorRec(DISPATCHING_OPERATION_PLAN, "DISPATCHING OPERATION PLAN", R.drawable.jianjie6);
        setResviorRec(CONTINGENCY_PLAN, "CONTINGENCY PLAN", R.drawable.jianjie7);
        setResviorRec(ADMINISTRATIVE_SITUATION, "ADMINISTRATIVE SITUATION", R.drawable.jianjie8);
        setResviorRec(RESERVOIRS_LOG, "RESERVOIRS LOG", R.drawable.jianjie_visitlog);
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

                if (RESERVOIR_DESCRIPTION.equals(nameStr)) {
                    intent.setClass(getBaseActivity(), IntroduceOfReservoirsActivity.class);
                    startActivity(intent);
                } else if (FLOOD_CONTROL_LEVER.equals(nameStr)) {
                    intent.setClass(getBaseActivity(), WaterLevelActivity.class);
                    startActivity(intent);
                   /* if (UserManager.getInstance().getMenuItemBean("汛限水位") != null) {
                        ARouter.getInstance().build(UserManager.getInstance().getMenuItemBean("汛限水位").getMenuHref()).navigation();
                    } else {
                        ToastUtils.shortToast("服务器没有配置汛限水位路径");
                    }*/
                } else if (RESERVOIRS_VEDIO.equals(nameStr)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        intent.setClass(getBaseActivity(), VedioOfReservoirActivity.class);
                        startActivity(intent);
                    }else{
                        ToastUtils.shortToast("手机系统版本太低无法查看视频，请将手机系统升级至5.0及以上版本");
                    }

                } else if (CAPACITY_CURVE.equals(nameStr)) {
                    intent.setClass(getBaseActivity(), CapacityActivity.class);
                    startActivity(intent);
                } else if (RESERVOIRS_SUPPORTING.equals(nameStr)) {
                    intent.setClass(getBaseActivity(), SupportingActivity.class);
                    startActivity(intent);
                } else if (FLOOD_FIGHTING_MATERIALS.equals(nameStr)) {
                    intent.setClass(getBaseActivity(), FloodActivity.class);
                    startActivity(intent);
                } else if (DISPATCHING_OPERATION_PLAN.equals(nameStr)) {
                    //调度运行方案
                    intent.setClass(getBaseActivity(), OperationPlanActivity.class);
                    intent.putExtra("select", "1");
                    startActivity(intent);
                } else if (CONTINGENCY_PLAN.equals(nameStr)) {
                    //水库安全管理应急预案
                    intent.setClass(getBaseActivity(), OperationPlanActivity.class);
                    intent.putExtra("select", "2");
                    startActivity(intent);
                } else if (ADMINISTRATIVE_SITUATION.equals(nameStr)) {
                    intent.setClass(getBaseActivity(), SafeRunningActivity.class);
                    startActivity(intent);
                } else if (RESERVOIRS_LOG.equals(nameStr)) {
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
                Glide.with(Utils.getContext())
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
