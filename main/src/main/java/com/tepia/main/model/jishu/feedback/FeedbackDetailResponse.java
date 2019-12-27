package com.tepia.main.model.jishu.feedback;

import java.util.List;

/**
 * Author:xch
 * Date:2019/12/27
 * Description:
 */
public class FeedbackDetailResponse {

    private String id;
    private String problemId;
    private String userName;
    private String feedbackTypeName;
    private String feedbackType;
    private String feedbackContent;
    private String feedbackUserCode;
    private String feedbackTime;

    private List<FileFile> fileFile;
    private List<ImgFile> imgFile;

    public String getFeedbackTypeName() {
        return feedbackTypeName;
    }

    public void setFeedbackTypeName(String feedbackTypeName) {
        this.feedbackTypeName = feedbackTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackUserCode() {
        return feedbackUserCode;
    }

    public void setFeedbackUserCode(String feedbackUserCode) {
        this.feedbackUserCode = feedbackUserCode;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public List<FileFile> getFileFile() {
        return fileFile;
    }

    public void setFileFile(List<FileFile> fileFile) {
        this.fileFile = fileFile;
    }

    public List<ImgFile> getImgFile() {
        return imgFile;
    }

    public void setImgFile(List<ImgFile> imgFile) {
        this.imgFile = imgFile;
    }

    @Override
    public String toString() {
        return "FeedbackDetailResponse{" +
                "id='" + id + '\'' +
                ", problemId='" + problemId + '\'' +
                ", userName='" + userName + '\'' +
                ", feedbackTypeName='" + feedbackTypeName + '\'' +
                ", feedbackType='" + feedbackType + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", feedbackUserCode='" + feedbackUserCode + '\'' +
                ", feedbackTime='" + feedbackTime + '\'' +
                ", fileFile=" + fileFile +
                ", imgFile=" + imgFile +
                '}';
    }
}
