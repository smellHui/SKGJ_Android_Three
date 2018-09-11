package com.tepia.main.model.image;

import java.io.Serializable;

/**
 * Created by Joeshould on 2018/6/1.
 */

public class ImageInfoBean implements Serializable {

    /**
     * id : b9c7fe90da2940eca62ec65a23af9ad9
     * fileId : b0a0d83381f64191bde9762ba142d9ea
     * fileName : u=2936254456,1602353344&fm=27&gp=0.jpg
     * fileType : jpg
     * bizKey : 8c5fa5f84035460db6245146f7efeb39
     * bizType : start
     * status : 0
     * createBy : 播州test1
     * createDate : 2018-07-19 15:44:11
     * updateBy : 播州test1
     * updateDate : 2018-07-19 15:44:13
     * filePath : http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Cleaning/2018-07/19/b0a0d83381f64191bde9762ba142d9ea.jpg
     * fileContentType : jpg
     * fileExtension : jpg
     * fileSize : 20930
     */

    private String id;
    private String fileId;
    private String fileName;
    private String fileType;
    private String bizKey;
    private String bizType;
    private String status;
    private String createBy;
    private String createDate;
    private String updateBy;
    private String updateDate;
    private String filePath;
    private String fileContentType;
    private String fileExtension;
    private int fileSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
}
