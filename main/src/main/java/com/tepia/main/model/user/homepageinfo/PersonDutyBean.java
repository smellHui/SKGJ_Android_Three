package com.tepia.main.model.user.homepageinfo;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-21
 * Time            :       15:01
 * Version         :       1.0
 * 功能描述        :   首页工作人员 实体
 **/
public class PersonDutyBean {
    /**
     * jobName : 行政负责人
     * mobile :
     * userName : 播州区管理科长
     */

    private String jobName;
    private String mobile;
    private String userName;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
