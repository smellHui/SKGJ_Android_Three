package com.tepia.main.model.setting;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-28
 * Time            :       下午8:37
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       工作职责
 **/
public class DutyOfWorkBean extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * jobName : 维修养护人员
         * jobFunction : 维修养护工作
         */

        private String jobName;
        private String jobFunction;

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getJobFunction() {
            return jobFunction;
        }

        public void setJobFunction(String jobFunction) {
            this.jobFunction = jobFunction;
        }
    }
}
