package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
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
public class SafeRunningBean extends BaseResponse implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : bd23f34c66bf43e5b4dd6cd1d67f447c
         * reportName : 安全鉴定报告
         * reportType : 1
         */

        private String id;
        /**
         * 报告名
         */
        private String reportName;
        /**
         * 报告类型
         */
        private String reportType;

        /**
         * 报告时间
         */
        private String reportDate;
        /**
         * 添加时间
         */
        private String createTime;
        /**
         * 管理员
         */
        private String userName;



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

        public String getReportDate() {
            return reportDate;
        }

        public void setReportDate(String reportDate) {
            this.reportDate = reportDate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
