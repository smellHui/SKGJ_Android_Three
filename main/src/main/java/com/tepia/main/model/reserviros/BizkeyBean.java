package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-26
 * Time            :       下午4:35
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class BizkeyBean extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fileId : 8cfb1b285ca04393a82117b0a3c74b7f
         * fileName : 04200170041121440060 (1).pdf
         * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/safeReport/2018-09/18/04200170041121440060 (1).pdf
         */

        private String fileId;
        private String fileName;
        private String filePath;

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

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }
}
