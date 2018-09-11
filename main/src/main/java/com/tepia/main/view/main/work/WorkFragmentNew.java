package com.tepia.main.view.main.work;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.tepia.main.model.user.MenuBean;
import com.tepia.main.model.user.MenuData;
import com.tepia.main.view.login.LoginPresenter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 首页功能入口
 *
 * @author by BLiYing on 2018/7/10.
 */

public class WorkFragmentNew extends MVPBaseFragment<WorkContract.View, WorkPresenter> implements WorkContract.View {

    private AdapterMainMenu adapterMainModellist;
    private RecyclerView rv_work_order_list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_main_workbench_new;
    }

    @Override
    protected void initData() {


    }



    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.text_tabmain_title));
        rv_work_order_list = findView(R.id.rv_work_order_list);
        MenuBean menuBean_get = LoginPresenter.getMenu(Utils.getContext(),LoginPresenter.prefence_menu,LoginPresenter.key_menu);
        if(menuBean_get != null) {
            initModellistA(menuBean_get.getData(), rv_work_order_list);
        }else{
            LogUtil.e("WorkFragmentNew","动态菜单为空------");
        }
    }


    @Override
    protected void initRequestData() {
        // TODO: 2018/8/28 获取任务个数暂未体现
//        mPresenter.getTaskNum();
    }


    private void initModellistA(List<MenuData> data, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMainModellist = new AdapterMainMenu(getContext(), R.layout.fragment_tab_main_workbench_item, data);
        recyclerView.setAdapter(adapterMainModellist);

    }



    @Override
    public void getTaskNumSuccess(TaskNumResponse.DataBean data) {
        if (data == null) {
            return;
        }
    }


}
