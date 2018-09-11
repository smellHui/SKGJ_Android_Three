package com.tepia.main.view.main.jihua;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.model.plan.PlanListResponse;

import java.io.Serializable;

/**
 * 计划详情
 *
 * @author 44822
 */
public class PlanDetailActivity extends BaseActivity {

    private PlanListResponse.DataBean.ListBean item;
    private TextView tvPlanName;
    private TextView tvPlanType;
    private TextView tvOperationType;
    private TextView tvReservoirName;
    private TextView tvPlanTimes;
    private TextView etRemarks;
    private TextView tvCreateDate;
    private TextView tvIsgenerate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_plan_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("巡检计划详情");
        showBack();
        tvPlanName = findViewById(R.id.tv_plan_name);
        tvPlanType = findViewById(R.id.tv_plan_type);
        tvOperationType = findViewById(R.id.tv_operation_type);
        tvReservoirName = findViewById(R.id.tv_reservoir_name);
        tvPlanTimes = findViewById(R.id.tv_plan_times);
        etRemarks = findViewById(R.id.et_remarks);
//        etRemarks.setFocusable(false);
//        etRemarks.setFocusableInTouchMode(false);
        tvCreateDate = findViewById(R.id.tv_create_date);
        tvIsgenerate = findViewById(R.id.tv_isGenerate);
        ScrollView sv= findViewById(R.id.sv);
        Intent intent = getIntent();
        item = (PlanListResponse.DataBean.ListBean) intent.getSerializableExtra("item");
        if (item != null) {
//            sv.postDelayed(this::initDetail,500);
            initDetail();
        }
    }

    private void initDetail() {
        tvPlanName.setText(item.getPlanName());
        //        (1：日常；2：定期；3：特别)
        switch (item.getPlanType()) {
            case "1":
                tvPlanType.setText("日常巡检");
                break;
            case "2":
                tvPlanType.setText("定期巡检");
                break;
            case "3":
                tvPlanType.setText("特别巡检");
                break;
            default:
                break;
        }
//        运维类别1：巡检；2：维修养护；3：保洁
        switch (item.getOperationType()) {
            case "1":
                tvOperationType.setText("巡检");
                break;
            case "2":
                tvOperationType.setText("维修养护");
                break;
            case "3":
                tvOperationType.setText("保洁");
                break;
            default:
                break;
        }
        tvReservoirName.setText(item.getReservoirName());
        String planTimes = item.getPlanTimes();
        StringBuffer newPlanTimes = new StringBuffer();
        String[] split = planTimes.split(",");
        if (split.length>0){
            for (int i = 0; i < split.length; i++) {
                String[] split1 = split[i].split("—");
                for (int j = 0; j < split1.length; j++) {
//                System.out.println(split1[j]);
                }
                if (i==split.length-1){
                    newPlanTimes.append(split[i]);
                }else {
                    newPlanTimes.append(split[i] + "\n");
                }
            }
        }
        tvPlanTimes.setText(newPlanTimes);
        etRemarks.setText(item.getRemarks());
        tvCreateDate.setText(item.getCreateDate());
        if ("1".equals(item.getIsGenerate())) {
            tvIsgenerate.setText("已生成");
            tvIsgenerate.setBackgroundColor(Color.parseColor("#009688"));
        } else {
            tvIsgenerate.setText("未生成");
            tvIsgenerate.setBackgroundColor(Color.parseColor("#CCD7D6"));
        }
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
