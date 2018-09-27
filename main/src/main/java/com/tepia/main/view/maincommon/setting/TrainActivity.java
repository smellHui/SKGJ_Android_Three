package com.tepia.main.view.maincommon.setting;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.plan.PlanListResponse;
import com.tepia.main.model.train.TrainListResponse;
import com.tepia.main.view.maincommon.setting.adapter.AdapterTrainDetail;
import com.tepia.main.view.maincommon.setting.adapter.TrainListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-9-19
 * Version :1.0
 * 功能描述 :工作培训
 **/
public class TrainActivity extends BaseActivity {

    private List<Integer> images = new ArrayList<>();

    private AdapterTrainDetail adapterPeixunDetail;
    private RecyclerView peixunRec;
    private RecyclerView rvTrainList;
    private List<TrainListResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private TrainListAdapter trainListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_train_list;
    }

    @Override
    public void initView() {
        setCenterTitle("我的培训");
        showBack();
     /*     peixunRec = findViewById(R.id.peixunRec);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);

        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置
        peixunRec.setLayoutManager(manager);
        adapterPeixunDetail = new AdapterTrainDetail(this,R.layout.activity_pei_xun_item,images);
        peixunRec.setAdapter(adapterPeixunDetail);
*/
        for (int i = 0; i < 10; i++) {
            dataList.add(new TrainListResponse.DataBean.ListBean());
        }
        rvTrainList = findViewById(R.id.rv_train_list);
        rvTrainList.setLayoutManager(new LinearLayoutManager(this));
        trainListAdapter = new TrainListAdapter(R.layout.train_list_item, dataList);
        rvTrainList.setAdapter(trainListAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
