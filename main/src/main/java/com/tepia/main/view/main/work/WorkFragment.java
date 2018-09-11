package com.tepia.main.view.main.work;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.MainItemBean;
import com.tepia.main.model.task.response.TaskNumResponse;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 首页功能入口
 *
 * @author by BLiYing on 2018/7/10.
 */

public class WorkFragment extends MVPBaseFragment<WorkContract.View, WorkPresenter> implements WorkContract.View {

    private AdapterMainModellist adapterMainModellist;
    private RecyclerView rv_work_order_list;
    private RecyclerView rv_work_plan_list;
    private RecyclerView rv_othet_list;
    private RecyclerView rv_work_order_list2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_main_workbench;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.text_tabmain_title));
        rv_work_order_list = findView(R.id.rv_work_order_list);
        rv_work_order_list2 = findView(R.id.rv_work_order_list2);
        rv_othet_list = findView(R.id.rv_othet_list);
        rv_work_plan_list = findView(R.id.rv_work_plan_list);


        zonglan = getString(R.string.zonglanstr);
        xunjian = getString(R.string.xunjianstr);
        weihu = getString(R.string.weihustr);
        baojie = getString(R.string.baojiestr);
        shuikuxunjian = getString(R.string.shuikuxunjian);
        initModellistA(getMainItemListA(new TaskNumResponse.DataBean()), rv_work_order_list);
        initModellistB(getMainItemListB(), rv_othet_list);
        initModellistC(getMainItemListC(new TaskNumResponse.DataBean()), rv_work_plan_list);
        initModellistD(getMainItemListA(new TaskNumResponse.DataBean()), rv_work_order_list2);
    }


    @Override
    protected void initRequestData() {
        mPresenter.getTaskNum();
    }

    private String zonglan;
    private String xunjian;
    private String weihu;
    private String baojie;
    private String shuikuxunjian;
    private static final String shangbao = "事件上报";
    private static final String tongji = "事件日志";
    private static final String sehngtaill = "生态流量";
    private static final String dabajiance = "大坝监测";
    private static final String daichuli = "待处理事件";
    private static final String daishenhe = "待审核事件";

    private List<MainItemBean> getMainItemListA(TaskNumResponse.DataBean countData) {
        List<MainItemBean> data = new ArrayList<>();
        data.add(new MainItemBean(countData.getTotalCount() + "", R.drawable.a_all, zonglan));
        data.add(new MainItemBean(countData.getRoutingCount() + "", R.drawable.a_xunjian, xunjian));
        data.add(new MainItemBean(countData.getMaintainCount() + "", R.drawable.a_weihu, weihu));
        data.add(new MainItemBean(countData.getCleaningCount() + "", R.drawable.a_baojie, baojie));
//        data.add(new MainItemBean("", R.drawable.a_baojie, shuikuxunjian));
        return data;
    }

    private List<MainItemBean> getMainItemListC(TaskNumResponse.DataBean countData) {
        List<MainItemBean> data = new ArrayList<>();
        data.add(new MainItemBean("", R.drawable.a_all, zonglan));
        data.add(new MainItemBean("", R.drawable.a_xunjian, xunjian));
        data.add(new MainItemBean("", R.drawable.a_weihu, weihu));
        data.add(new MainItemBean("", R.drawable.a_baojie, baojie));
//        data.add(new MainItemBean("", R.drawable.a_baojie, shuikuxunjian));
        return data;
    }

    private List<MainItemBean> getMainItemListB() {
        List<MainItemBean> data = new ArrayList<>();
        data.add(new MainItemBean(R.drawable.a_push, shangbao));
        data.add(new MainItemBean(R.drawable.a_log, tongji));
        data.add(new MainItemBean(R.drawable.a_dabajiance, daichuli));
        data.add(new MainItemBean(R.drawable.a_dabajiance, daishenhe));
        data.add(new MainItemBean(R.drawable.a_shengtailiuliang, sehngtaill));
        data.add(new MainItemBean(R.drawable.a_dabajiance, dabajiance));
        return data;
    }

    private void initModellistA(List<MainItemBean> data, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapterMainModellist = new AdapterMainModellist(getContext(), R.layout.item_modellist, data);
        recyclerView.setAdapter(adapterMainModellist);
        adapterMainModellist.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (JPushInterface.isPushStopped(Utils.getContext()) || !JPushInterface.getConnectionState(Utils.getContext())) {
                    LogUtil.e("MainActivity", "MainActivity极光推送已停止，正在重新开启");
                    //极光推送
                    JPushInterface.setDebugMode(true);
                    JPushInterface.init(Utils.getContext());
                    JPushInterface.resumePush(Utils.getContext());
                }
                MainItemBean mainItemBean = (MainItemBean) adapter.getData().get(position);
                String iconname = mainItemBean.getIconName();

                boolean xunweibao = null != mainItemBean &&
                        (zonglan.equals(iconname) || xunjian.equals(iconname) || baojie.equals(iconname) || weihu.equals(iconname));
                if (xunweibao) {
                    if (DoubleClickUtil.isFastDoubleClick()) {
                        return;
                    }
                    ARouter.getInstance().build(AppRoutePath.app_task_list)
                            .withString("TYPEKEY", mainItemBean.getIconName())
                            .withString("TYPE", String.valueOf(position))
                            .navigation();
                } else if (shuikuxunjian.equals(iconname)) {
                    ARouter.getInstance().build(AppRoutePath.app_gongdan_jihua)
                            .withString("TYPEKEY", mainItemBean.getIconName())
                            .withString("TYPE", String.valueOf(position))
                            .navigation();
                }
            }
        });
    }
    private void initModellistD(List<MainItemBean> data, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapterMainModellist = new AdapterMainModellist(getContext(), R.layout.item_modellist, data);
        recyclerView.setAdapter(adapterMainModellist);
        adapterMainModellist.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (JPushInterface.isPushStopped(Utils.getContext()) || !JPushInterface.getConnectionState(Utils.getContext())) {
                    LogUtil.e("MainActivity", "MainActivity极光推送已停止，正在重新开启");
                    //极光推送
                    JPushInterface.setDebugMode(true);
                    JPushInterface.init(Utils.getContext());
                    JPushInterface.resumePush(Utils.getContext());
                }
                MainItemBean mainItemBean = (MainItemBean) adapter.getData().get(position);
                String iconname = mainItemBean.getIconName();

                boolean xunweibao = null != mainItemBean &&
                        (zonglan.equals(iconname) || xunjian.equals(iconname) || baojie.equals(iconname) || weihu.equals(iconname));
                if (xunweibao) {
                    if (DoubleClickUtil.isFastDoubleClick()) {
                        return;
                    }
                    ARouter.getInstance().build(AppRoutePath.app_task_list_2)
                            .withString("TYPEKEY", mainItemBean.getIconName())
                            .withString("TYPE", String.valueOf(position))
                            .navigation();
                } else if (shuikuxunjian.equals(iconname)) {
                    ARouter.getInstance().build(AppRoutePath.app_gongdan_jihua)
                            .withString("TYPEKEY", mainItemBean.getIconName())
                            .withString("TYPE", String.valueOf(position))
                            .navigation();
                }
            }
        });
    }

    private void initModellistC(List<MainItemBean> data, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapterMainModellist = new AdapterMainModellist(getContext(), R.layout.item_modellist, data);
        recyclerView.setAdapter(adapterMainModellist);
        adapterMainModellist.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainItemBean mainItemBean = (MainItemBean) adapter.getData().get(position);
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_plan_list)
                        .withString("TYPEKEY", mainItemBean.getIconName())
                        .withString("TYPE", String.valueOf(position))
                        .navigation();

            }
        });
    }


    private void initModellistB(List<MainItemBean> data, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapterMainModellist = new AdapterMainModellist(getContext(), R.layout.item_modellist, data);
        recyclerView.setAdapter(adapterMainModellist);
        adapterMainModellist.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainItemBean mainItemBean = (MainItemBean) adapter.getData().get(position);
                String iconname = mainItemBean.getIconName();
                if (null != mainItemBean) {
                    if (shangbao.equals(iconname)) {
                        if (!NetUtil.isNetworkConnected(getBaseActivity())) {
                            ToastUtils.shortToast(R.string.no_network);
                            return;
                        }
                        ARouter.getInstance().build(AppRoutePath.app_question)
                                .navigation();
                    } else if (tongji.equals(iconname)) {
                        if (!NetUtil.isNetworkConnected(getBaseActivity())) {
                            ToastUtils.shortToast(R.string.no_network);
                            return;
                        }
                        ARouter.getInstance().build(AppRoutePath.app_question_list)
                                .navigation();
                    } else if(daichuli.equals(iconname)){
                        if (!NetUtil.isNetworkConnected(getBaseActivity())) {
                            ToastUtils.shortToast(R.string.no_network);
                            return;
                        }
                        ARouter.getInstance().build(AppRoutePath.app_up_question_list)
                                .navigation();
                    }else if(daishenhe.equals(iconname)){
                        if (!NetUtil.isNetworkConnected(getBaseActivity())) {
                            ToastUtils.shortToast(R.string.no_network);
                            return;
                        }
                        ARouter.getInstance().build(AppRoutePath.app_up_question_configure_list)
                                .navigation();
                    }else {
                        ToastUtils.shortToast("待开发功能");
                    }
                }


            }
        });
    }

    @Override
    public void getTaskNumSuccess(TaskNumResponse.DataBean data) {
        if (data == null) {
            return;
        }
        initModellistA(getMainItemListA(data), rv_work_order_list);
    }


}
