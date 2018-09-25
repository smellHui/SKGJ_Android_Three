package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午3:20
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class SafeRunningBean extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : bd23f34c66bf43e5b4dd6cd1d67f447c
         * reportName : 安全
         * reportType : 1
         */

        private String id;
        /**
         * 报告名
         */
        private String reportName;
        private String reportType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReportName() {
            return reportName;
        }

        public void setReportName(String reportName) {
            this.reportName = reportName;
        }

        public String getReportType() {
            return reportType;
        }

        public void setReportType(String reportType) {
            this.reportType = reportType;
        }
    }
}
