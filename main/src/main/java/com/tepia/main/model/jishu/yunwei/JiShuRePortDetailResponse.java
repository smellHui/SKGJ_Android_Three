package com.tepia.main.model.jishu.yunwei;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 * 技术运维上报详情
 * @author :       wwj
 * Date    :       2018-09-26
 * Time    :       10:37
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class JiShuRePortDetailResponse extends BaseResponse{

    /**
     * data : {"problemId":"9b5387aa4e824c56aab891bb1e7c9341","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","problemTitle":"wzm","problemDescription":"wzm","problemSource":"2","problemSourceUserId":"c6fc4372f7e948e38c7e1ea0296facf4","problemSourceUserName":"李维保","problemLgtd":"","problemLttd":"","problemStatus":"4","isMakePlan":"0","status":"0","createBy":"c6fc4372f7e948e38c7e1ea0296facf4","createDate":"2018-09-30 09:26:23","isProcess":"1","reservoirName":"绿竹坝水库","userName":"李维保","isVerify":"0","iSysFileUploads":[{"fileId":"3fee6a4b258f45598c6c2749abd4d504","bizKey":"9b5387aa4e824c56aab891bb1e7c9341","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-09/30/3fee6a4b258f45598c6c2749abd4d504.jpg"}],"bizProblemFlows":[]}
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
         * problemId : 9b5387aa4e824c56aab891bb1e7c9341
         * reservoirId : 66fb3d579d084daf8a7d35d9d9612213
         * problemTitle : wzm
         * problemDescription : wzm
         * problemSource : 2
         * problemSourceUserId : c6fc4372f7e948e38c7e1ea0296facf4
         * problemSourceUserName : 李维保
         * problemLgtd :
         * problemLttd :
         * problemStatus : 4
         * isMakePlan : 0
         * status : 0
         * createBy : c6fc4372f7e948e38c7e1ea0296facf4
         * createDate : 2018-09-30 09:26:23
         * isProcess : 1
         * reservoirName : 绿竹坝水库
         * userName : 李维保
         * isVerify : 0
         * iSysFileUploads : [{"fileId":"3fee6a4b258f45598c6c2749abd4d504","bizKey":"9b5387aa4e824c56aab891bb1e7c9341","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-09/30/3fee6a4b258f45598c6c2749abd4d504.jpg"}]
         * bizProblemFlows : []
         */

        private String problemId;
        private String reservoirId;
        private String problemTitle;
        private String problemDescription;
        private String problemSource;
        private String problemSourceUserId;
        private String problemSourceUserName;
        private String problemLgtd;
        private String problemLttd;
        private String problemStatus;
        private String isMakePlan;
        private String status;
        private String createBy;
        private String createDate;
        private String isProcess;
        private String reservoirName;
        private String userName;
        private String isVerify;
        private List<ISysFileUploadsBean> images;
        private List<ISysFileUploadsBean> videos;
        private List<?> bizProblemFlows;
        private String remarks;

        public String getProblemId() {
            return problemId;
        }

        public void setProblemId(String problemId) {
            this.problemId = problemId;
        }

        public String getReservoirId() {
            return reservoirId;
        }

        public void setReservoirId(String reservoirId) {
            this.reservoirId = reservoirId;
        }

        public String getProblemTitle() {
            return problemTitle;
        }

        public void setProblemTitle(String problemTitle) {
            this.problemTitle = problemTitle;
        }

        public String getProblemDescription() {
            return problemDescription;
        }

        public void setProblemDescription(String problemDescription) {
            this.problemDescription = problemDescription;
        }

        public String getProblemSource() {
            return problemSource;
        }

        public void setProblemSource(String problemSource) {
            this.problemSource = problemSource;
        }

        public String getProblemSourceUserId() {
            return problemSourceUserId;
        }

        public void setProblemSourceUserId(String problemSourceUserId) {
            this.problemSourceUserId = problemSourceUserId;
        }

        public String getProblemSourceUserName() {
            return problemSourceUserName;
        }

        public void setProblemSourceUserName(String problemSourceUserName) {
            this.problemSourceUserName = problemSourceUserName;
        }

        public String getProblemLgtd() {
            return problemLgtd;
        }

        public void setProblemLgtd(String problemLgtd) {
            this.problemLgtd = problemLgtd;
        }

        public String getProblemLttd() {
            return problemLttd;
        }

        public void setProblemLttd(String problemLttd) {
            this.problemLttd = problemLttd;
        }

        public String getProblemStatus() {
            return problemStatus;
        }

        public void setProblemStatus(String problemStatus) {
            this.problemStatus = problemStatus;
        }

        public String getIsMakePlan() {
            return isMakePlan;
        }

        public void setIsMakePlan(String isMakePlan) {
            this.isMakePlan = isMakePlan;
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

        public String getIsProcess() {
            return isProcess;
        }

        public void setIsProcess(String isProcess) {
            this.isProcess = isProcess;
        }

        public String getReservoirName() {
            return reservoirName;
        }

        public void setReservoirName(String reservoirName) {
            this.reservoirName = reservoirName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIsVerify() {
            return isVerify;
        }

        public void setIsVerify(String isVerify) {
            this.isVerify = isVerify;
        }

        public List<ISysFileUploadsBean> getImages() {
            return images;
        }

        public void setImages(List<ISysFileUploadsBean> images) {
            this.images = images;
        }

        public List<ISysFileUploadsBean> getVideos() {
            return videos;
        }

        public void setVideos(List<ISysFileUploadsBean> videos) {
            this.videos = videos;
        }

        public List<?> getBizProblemFlows() {
            return bizProblemFlows;
        }

        public void setBizProblemFlows(List<?> bizProblemFlows) {
            this.bizProblemFlows = bizProblemFlows;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public static class ISysFileUploadsBean {
            /**
             * fileId : 3fee6a4b258f45598c6c2749abd4d504
             * bizKey : 9b5387aa4e824c56aab891bb1e7c9341
             * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-09/30/3fee6a4b258f45598c6c2749abd4d504.jpg
             */

            private String fileId;
            private String bizKey;
            private String filePath;

            public String getFileId() {
                return fileId;
            }

            public void setFileId(String fileId) {
                this.fileId = fileId;
            }

            public String getBizKey() {
                return bizKey;
            }

            public void setBizKey(String bizKey) {
                this.bizKey = bizKey;
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
