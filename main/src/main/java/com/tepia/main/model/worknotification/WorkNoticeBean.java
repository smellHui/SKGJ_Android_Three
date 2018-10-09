package com.tepia.main.model.worknotification;

import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.reserviros.FloodBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-29
 * Time            :       17:25
 * Version         :       1.0
 * 功能描述        :
 **/
public class WorkNoticeBean {
    /**
     * id : 89e744302710403d9ad9e322768e507e
     * reservoirIds : "66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684"
     * noticeTitle : "紧急通知"
     * noticeContent : "紧急紧急紧急紧急"
     * createBy : c6fc4372f7e948e38c7e1ea0296facf4
     * createDate : 2018-09-29 17:00:48
     * userName : 李维保
     */

    private String id;
    private String reservoirIds;
    private String noticeTitle;
    private String noticeContent;
    private String createBy;
    private String createDate;
    private String userName;
    private String feedBackStatus;

    public List<ImageInfoBean> files;
    public List<ImageInfoBean> images;

    public List<ImageInfoBean> getFiles() {
        return files;
    }

    public void setFiles(List<ImageInfoBean> files) {
        this.files = files;
    }

    public List<ImageInfoBean> getImages() {
        return images;
    }

    public void setImages(List<ImageInfoBean> images) {
        this.images = images;
    }

    public String getFeedBackStatus() {
        return feedBackStatus;
    }

    public void setFeedBackStatus(String feedBackStatus) {
        this.feedBackStatus = feedBackStatus;
    }

    private List<WorkNoticeFeedbackBean> feedbackList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReservoirIds() {
        return reservoirIds;
    }

    public void setReservoirIds(String reservoirIds) {
        this.reservoirIds = reservoirIds;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<WorkNoticeFeedbackBean> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<WorkNoticeFeedbackBean> feedbackList) {
        this.feedbackList = feedbackList;
    }
}
