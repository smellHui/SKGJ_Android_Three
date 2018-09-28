package com.tepia.main.view.maincommon.setting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.http.BaseResponse;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-9
 * Version :1.0
 * 功能描述 :工作职责
 **/
public class DutyActivity extends BaseActivity {

    ActivityDutyBinding activityDutyBinding;
    private DutyAdapter dutyAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_duty;
    }


    @Override
    public void initView() {
        setCenterTitle("工作职责");
        showBack();
        activityDutyBinding = DataBindingUtil.bind(mRootView);
        List<ReservoirBean> reservoirBeans = UserManager.getInstance().getLocalReservoirList();
        activityDutyBinding.reserviorRec.setLayoutManager(new LinearLayoutManager(this));
        dutyAdapter = new DutyAdapter(R.layout.duty_item, reservoirBeans);
        activityDutyBinding.reserviorRec.setAdapter(dutyAdapter);
        dutyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra(ReservoirsFragment.RESERVOIRId,reservoirBeans.get(position).getReservoirId());
                intent.putExtra(ReservoirsFragment.RESERVOIRNAME,reservoirBeans.get(position).getReservoir());
                intent.setClass(DutyActivity.this, IntroduceOfReservoirsActivity.class);
                startActivity(intent);

            }
        });
        if(reservoirBeans != null && reservoirBeans.size() == 0){
            dutyAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
        }

        UserManager.getInstance().getUserJob().subscribe(new LoadingSubject<DutyOfWorkBean>(true, "正在加载中...") {
            @Override
            protected void _onNext(DutyOfWorkBean dutyOfWorkBean) {
                if (dutyOfWorkBean != null) {
                    if (dutyOfWorkBean.getCode() == 0) {
                        String dutyStr = "", workcontentStr = "";
                        if(dutyOfWorkBean.getData() != null) {
                            int size = dutyOfWorkBean.getData().size();
                            for (int i = 0; i < size; i++) {
                                if(i < size-1) {
                                    dutyStr += (dutyOfWorkBean.getData().get(i).getJobName() + "\n");
                                    workcontentStr += (dutyOfWorkBean.getData().get(i).getJobFunction() + "\n");
                                }else{
                                    dutyStr += dutyOfWorkBean.getData().get(i).getJobName();
                                    workcontentStr += dutyOfWorkBean.getData().get(i).getJobFunction() ;
                                }
                            }

                            activityDutyBinding.dutyNameTv.setText(dutyStr);
                            activityDutyBinding.workContentTv.setText(workcontentStr);
                        }

                    } else {
                        ToastUtils.longToast(dutyOfWorkBean.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                LogUtil.e(message+" ");

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
