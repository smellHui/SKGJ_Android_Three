package com.tepia.main.view.maincommon.setting;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.setting.adapter.AdapterTrainDetail;

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
    @Override
    public int getLayoutId() {
        return R.layout.activity_train;
    }

    @Override
    public void initView() {
        setCenterTitle("工作培训详情");
        showBack();
        peixunRec = findViewById(R.id.peixunRec);
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
