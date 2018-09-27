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
     * data : {"id":"2e729e0243904a66864824a53ee31408","userCode":"a9ee6587f9ef46c2a6a8953a2c6dde62","trainTitle":"人工智能","position":"上海","trainDate":"2018-09-19 10:00:00","trainContent":"世界人工智能大会召开","organizeCompany":"政府","status":"0","createBy":"a9ee6587f9ef46c2a6a8953a2c6dde62","createDate":"2018-09-27 15:11:35","files":[{"fileName":"视频播放脚本.doc","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/train/2018-09/27/视频播放脚本.doc","fileType":"doc"},{"fileName":"cover_img.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/train/2018-09/27/cover_img.jpg","fileType":"jpg"},{"fileName":"图层6.png","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/train/2018-09/27/图层6.png","fileType":"png"}]}
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
         * id : 2e729e0243904a66864824a53ee31408
         * userCode : a9ee6587f9ef46c2a6a8953a2c6dde62
         * trainTitle : 人工智能
         * position : 上海
         * trainDate : 2018-09-19 10:00:00
         * trainContent : 世界人工智能大会召开
         * organizeCompany : 政府
         * status : 0
         * createBy : a9ee6587f9ef46c2a6a8953a2c6dde62
         * createDate : 2018-09-27 15:11:35
         * files : [{"fileName":"视频播放脚本.doc","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/train/2018-09/27/视频播放脚本.doc","fileType":"doc"},{"fileName":"cover_img.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/train/2018-09/27/cover_img.jpg","fileType":"jpg"},{"fileName":"图层6.png","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/train/2018-09/27/图层6.png","fileType":"png"}]
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

        public static class FilesBean {
            /**
             * fileName : 视频播放脚本.doc
             * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/train/2018-09/27/视频播放脚本.doc
             * fileType : doc
             */

            private String fileName;
            private String filePath;
            private String fileType;

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

            public String getFileType() {
                return fileType;
            }

            public void setFileType(String fileType) {
                this.fileType = fileType;
            }
        }
    }
}
