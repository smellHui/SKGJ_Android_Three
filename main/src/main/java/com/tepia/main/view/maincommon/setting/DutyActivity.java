package com.tepia.main.view.maincommon.setting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityDutyBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.setting.DutyOfWorkBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detail.IntroduceOfReservoirsActivity;
import com.tepia.main.view.maincommon.setting.adapter.DutyAdapter;
import com.tepia.main.view.maincommon.setting.adapter.DutyJobAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-9
 * Version :1.2.4
 * 功能描述 :工作职责
 **/
public class DutyActivity extends BaseActivity {

    private DutyAdapter dutyAdapter;
    private DutyJobAdapter adapterDutyjob;
    private RecyclerView jobRec;
    private RecyclerView reserviorRec;
    private TextView workContentTv;
    private List<Map<String, String>> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_duty_new;
    }


    @Override
    public void initView() {
        setCenterTitle("工作职责");
        showBack();
        reserviorRec = findViewById(R.id.reserviorRec);
        workContentTv = findViewById(R.id.workContentTv);

        List<ReservoirBean> reservoirBeans = UserManager.getInstance().getLocalReservoirList();
        reserviorRec.setLayoutManager(new LinearLayoutManager(this));
        dutyAdapter = new DutyAdapter(R.layout.duty_item, reservoirBeans);
        reserviorRec.setAdapter(dutyAdapter);
        dutyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra(ReservoirsFragment.RESERVOIRId, reservoirBeans.get(position).getReservoirId());
                intent.putExtra(ReservoirsFragment.RESERVOIRNAME, reservoirBeans.get(position).getReservoir());
                intent.setClass(DutyActivity.this, IntroduceOfReservoirsActivity.class);
                startActivity(intent);

            }
        });
        if (reservoirBeans != null && reservoirBeans.size() == 0) {
            dutyAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
        }
        jobRec = findViewById(R.id.jobRec);
        jobRec.setLayoutManager(new GridLayoutManager(this, 3));
        adapterDutyjob = new DutyJobAdapter(this, R.layout.duty_job_item, list);
        jobRec.setAdapter(adapterDutyjob);
        UserManager.getInstance().getUserJob().subscribe(new LoadingSubject<DutyOfWorkBean>(true, "正在加载中...") {
            @Override
            protected void _onNext(DutyOfWorkBean dutyOfWorkBean) {
                if (dutyOfWorkBean != null) {
                    if (dutyOfWorkBean.getCode() == 0) {
                        String dutyStr = "", workcontentStr = "";
                        if (dutyOfWorkBean.getData() != null) {
                            int size = dutyOfWorkBean.getData().size();
                            list.clear();
                            for (int i = 0; i < size; i++) {
                                Map<String, String> map = new HashMap<>();
                                map.put("jobName", dutyOfWorkBean.getData().get(i).getJobName());
                                map.put("jobFunction", dutyOfWorkBean.getData().get(i).getJobFunction());
                                if (i == 0) {
                                    map.put("check", "1");
                                } else {
                                    map.put("check", "0");

                                }
                                list.add(map);

                            }

                            adapterDutyjob.notifyDataSetChanged();
                            if(list.size() > 0) {
                                workContentTv.setText(list.get(0).get("jobFunction"));
                            }
                        }

                        adapterDutyjob.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                int size = list.size();
                                for (int i = 0; i < size; i++) {
                                    Map<String, String> map = list.get(i);
                                    if (i == position) {
                                        map.put("check", "1");
                                        workContentTv.setText(map.get("jobFunction"));
                                    } else {
                                        map.put("check", "0");
                                    }
                                }
                                adapterDutyjob.notifyDataSetChanged();

                            }
                        });

                    } else {
                        ToastUtils.longToast(dutyOfWorkBean.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                LogUtil.e(message + " ");

            }
        });
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
