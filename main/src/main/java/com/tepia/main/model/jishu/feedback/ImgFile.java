package com.tepia.main.model.jishu.feedback;

/**
 * Author:xch
 * Date:2019/12/27
 * Description:
 */
public class ImgFile {
    private String id;
    private String fileName;
    private String bizType;
    private String createDate;
    private String filePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "ImgFile{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", bizType='" + bizType + '\'' +
                ", createDate='" + createDate + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
