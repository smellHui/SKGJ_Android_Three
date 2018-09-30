package com.tepia.main.model.worknotification;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       11:19
 * Version         :       1.0
 * 功能描述        :
 **/
public class WorkNoticeFeedbackBean {

    /**
     * feedbackContent : 反馈信息
     * status : 1
     * feedbackDate : 2018-09-28 20:01:20
     * reservoirName : 东风水库
     * feedBackUserName : 杨明炎
     */

    private String feedbackContent;
    private String status;
    private String feedbackDate;
    private String reservoirName;
    private String feedBackUserName;

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getReservoirName() {
        return reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }

    public String getFeedBackUserName() {
        return feedBackUserName;
    }

    public void setFeedBackUserName(String feedBackUserName) {
        this.feedBackUserName = feedBackUserName;
    }
}
