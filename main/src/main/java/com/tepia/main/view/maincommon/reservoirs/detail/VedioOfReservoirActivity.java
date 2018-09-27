package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.VedioBean;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.model.reserviros.CapacityBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.detail.vedio.VedioFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.VideoListAdapter;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author         :       ly(from Center Of Wuhan)
  * Date            :       2018-9-18
  * Version         :       1.0
  * 功能描述         :       水库视频
 **/
public class VedioOfReservoirActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> implements  ReserviorContract.View<VideoResponse> {

    private RecyclerView recycleIdRec;
    private VideoListAdapter videoListAdapter;
    private TextView nameTv;
    @Override
    public int getLayoutId() {
        return R.layout.activity_vedio_of_reservoirs;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("视频列表");
        showBack();
        recycleIdRec = findViewById(R.id.recycleIdRec);
        recycleIdRec.setLayoutManager(new LinearLayoutManager(this));

        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        // TODO: 2018/9/27 删除
        reservoirId = "6942292cad144bds97fa6c31f96ee692";
        nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);
        mPresenter.getReservoirVideo(reservoirId);
    }

    @Override
    public void initView() {

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



    @Override
    public void success(VideoResponse videoResponse) {
        videoListAdapter = new VideoListAdapter(this,R.layout.adapter_video_items,videoResponse.getData());
        recycleIdRec.setAdapter(videoListAdapter);
        videoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast(R.string.no_network);
                    return;
                }
                if(DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(VedioOfReservoirActivity.this,VedioListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("vedio",videoResponse.getData().get(position));
                bundle.putString("vsnm",videoResponse.getData().get(position).getVsnm());
                bundle.putString("name",nameTv.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void failure(String msg) {
        videoListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
    }
}
