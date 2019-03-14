package com.tepia.main.model.task.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/3/11
 * Time            :       17:05
 * Version         :       1.0
 * 功能描述        :
 **/
public class BizPlanInfoBean extends DataSupport implements Serializable {

    /**
     * planType : 2
     * planName : 2018-07-20东风水库日常巡检计划
     * operationType : 1
     */

    private String planType;
    private String planName;
    private String operationType;

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
