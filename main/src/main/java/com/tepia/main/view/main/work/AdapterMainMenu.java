package com.tepia.main.view.main.work;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.AppRoutePath;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.BadgeView;
import com.tepia.main.R;
import com.tepia.main.model.MainItemBean;
import com.tepia.main.model.task.response.TaskNumResponse;
import com.tepia.main.model.user.ChildrenBean;
import com.tepia.main.model.user.MenuBean;
import com.tepia.main.model.user.MenuData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;


/**
 * 首页菜单适配器
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-08-28
 *         Time    :       下午4:02
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

public class AdapterMainMenu extends BaseQuickAdapter<MenuData, BaseViewHolder> {


    private Context context;
    private TextView oneTitle;
    private RecyclerView rv_work_order_list_item;
    private AdapterMainModellist adapterMainModellist;
    Map<String,Integer> maps = new HashMap<>();


    public AdapterMainMenu(Context context, int layoutResId, List<MenuData> data) {
        super(layoutResId, data);
        this.context = context;
        maps.put("10",R.drawable.a_all);
        maps.put("11",R.drawable.a_xunjian);
        maps.put("12",R.drawable.a_weihu);
        maps.put("13",R.drawable.a_baojie);
        maps.put("14",R.drawable.a_all);


        maps.put("21",R.drawable.a_push);
        maps.put("22",R.drawable.a_log);
        maps.put("23",R.drawable.a_shengtailiuliang);
        maps.put("24",R.drawable.a_dabajiance);

    }


    @Override
    protected void convert(BaseViewHolder helper, MenuData item) {

        oneTitle = helper.getView(R.id.oneTitle);
        rv_work_order_list_item = helper.getView(R.id.rv_work_order_list_item);
        oneTitle.setText(item.getMenuName());
        initModellistA(getMainItemListA(item),rv_work_order_list_item);
    }

    private List<MainItemBean> getMainItemListA(MenuData item) {
        List<MainItemBean> data = new ArrayList<>();
        if(item != null) {
            for (ChildrenBean childrenBean : item.getChildren()) {
                if (childrenBean != null) {
                    data.add(new MainItemBean("", maps.get(childrenBean.getMenuIcon()), childrenBean.getMenuName(),childrenBean.getMenuHref()));
                }
            }
        }

        return data;
    }

    private void initModellistA(List<MainItemBean> data, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        adapterMainModellist = new AdapterMainModellist(context, R.layout.item_modellist, data);
        recyclerView.setAdapter(adapterMainModellist);
        adapterMainModellist.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!NetUtil.isNetworkConnected(context)) {
                    ToastUtils.shortToast(R.string.no_network);
                    return;
                }
                if(DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                if(JPushInterface.isPushStopped(Utils.getContext()) || !JPushInterface.getConnectionState(Utils.getContext())){
                    LogUtil.e("MainActivity","MainActivity极光推送已停止，正在重新开启");
                    //极光推送
                    JPushInterface.setDebugMode(true);
                    JPushInterface.init(Utils.getContext());
                    JPushInterface.resumePush(Utils.getContext());
                }
                String menuhref = data.get(position).getMenuHref();
                if (TextUtils.isEmpty(menuhref)) {
                    ToastUtils.shortToast("待开发功能");

                }else {
                    ARouter.getInstance().build(menuhref)
                            .withString("TYPEKEY", data.get(position).getIconName())
                            .withString("TYPE", String.valueOf(position))
                            .navigation();
                }

            }
        });
    }

}


