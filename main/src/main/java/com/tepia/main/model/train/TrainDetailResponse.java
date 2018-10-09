package com.tepia.main.model.train;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 * 培训详情
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       16:47
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainDetailResponse extends BaseResponse{


    /**
     * data : {"id":"d467d2e237144a38900b35e1f3b53a75","userCode":"501bb19b45d44caf9807d6ba55db27ae","trainTitle":"安全专会","position":"播州水务局","trainDate":"2018-09-11 10:00:00","trainContent":"安全第一","organizeCompany":"水利局","status":"0","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-18 10:47:05","files":[{"fileId":"fc15eb12036543018bb0cc4694727e6c","fileName":"04200170041121440060.pdf","bizType":"trainFile","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/trainFiles/2018-10/08/04200170041121440060.pdf"}],"images":[{"fileId":"b52831632b964560b1bdf134188423d6","fileName":"锣鼓.png","bizType":"trainImg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/trainImg/2018-10/08/锣鼓.png"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : d467d2e237144a38900b35e1f3b53a75
         * userCode : 501bb19b45d44caf9807d6ba55db27ae
         * trainTitle : 安全专会
         * position : 播州水务局
         * trainDate : 2018-09-11 10:00:00
         * trainContent : 安全第一
         * organizeCompany : 水利局
         * status : 0
         * createBy : 501bb19b45d44caf9807d6ba55db27ae
         * createDate : 2018-09-18 10:47:05
         * files : [{"fileId":"fc15eb12036543018bb0cc4694727e6c","fileName":"04200170041121440060.pdf","bizType":"trainFile","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/trainFiles/2018-10/08/04200170041121440060.pdf"}]
         * images : [{"fileId":"b52831632b964560b1bdf134188423d6","fileName":"锣鼓.png","bizType":"trainImg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/trainImg/2018-10/08/锣鼓.png"}]
         */

        private String id;
        private String userCode;
        private String trainTitle;
        private String position;
        private String trainDate;
        private String trainContent;
        private String organizeCompany;
        private String status;
        private String createBy;
        private String createDate;
        private List<FilesBean> files;
        private List<ImagesBean> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getTrainTitle() {
            return trainTitle;
        }

        public void setTrainTitle(String trainTitle) {
            this.trainTitle = trainTitle;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getTrainDate() {
            return trainDate;
        }

        public void setTrainDate(String trainDate) {
            this.trainDate = trainDate;
        }

        public String getTrainContent() {
            return trainContent;
        }

        public void setTrainContent(String trainContent) {
            this.trainContent = trainContent;
        }

        public String getOrganizeCompany() {
            return organizeCompany;
        }

        public void setOrganizeCompany(String organizeCompany) {
            this.organizeCompany = organizeCompany;
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

        public List<FilesBean> getFiles() {
            return files;
        }

        public void setFiles(List<FilesBean> files) {
            this.files = files;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class FilesBean {
            /**
             * fileId : fc15eb12036543018bb0cc4694727e6c
             * fileName : 04200170041121440060.pdf
             * bizType : trainFile
             * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/trainFiles/2018-10/08/04200170041121440060.pdf
             */

            private String fileId;
            private String fileName;
            private String bizType;
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

            public String getBizType() {
                return bizType;
            }

            public void setBizType(String bizType) {
                this.bizType = bizType;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }
        }

        public static class ImagesBean {
            /**
             * fileId : b52831632b964560b1bdf134188423d6
             * fileName : 锣鼓.png
             * bizType : trainImg
             * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/trainImg/2018-10/08/锣鼓.png
             */

            private String fileId;
            private String fileName;
            private String bizType;
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

            public String getBizType() {
                return bizType;
            }

            public void setBizType(String bizType) {
                this.bizType = bizType;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }
        }
    }
}
