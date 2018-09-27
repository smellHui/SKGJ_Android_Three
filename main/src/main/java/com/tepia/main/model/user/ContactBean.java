package com.tepia.main.model.user;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-27
 * Time            :       9:08
 * Version         :       1.0
 * 功能描述        :
 **/
public class ContactBean {

    /**
     * userName : 张一
     * mobile : 13838384382
     * reservoir : 绿竹坝水库
     * jobName : 行政负责人
     */

    private String userName;
    private String mobile;
    private String reservoir;
    private String jobName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReservoir() {
        return reservoir;
    }

    public void setReservoir(String reservoir) {
        this.reservoir = reservoir;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}