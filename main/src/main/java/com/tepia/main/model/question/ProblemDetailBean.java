package com.tepia.main.model.question;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 事件日志列表
 * @author liying
 */
public class ProblemDetailBean extends BaseResponse{

    /**
     * data : {"problemId":"43a3e54c97634528b34df59b71905744","problemType":"1","problemTypeName":"巡检","reservoirId":"66fb3d579d084daf8a7d35d9d9612211","problemTitle":"2018-08-04事件上报测试","problemDescription":"2018-08-04事件上报测试","problemSource":"2","problemSourceUserId":"a623db0bdb92434ebe6553561aaf2eef","problemCofirmType":"2","problemStatus":"4","isMakePlan":"0","status":"0","createBy":"bozhou","createDate":"2018-08-04 14:23:35","updateBy":"bozhou","updateDate":"2018-08-04 14:23:35","reservoirName":"枫青水库","isVerify":"0","problemStatusName":"严重问题","iSysFileUploads":[{"fileId":"56ef954c4e4e4ca8894f273964c2bb01","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/56ef954c4e4e4ca8894f273964c2bb01.jpg"},{"fileId":"b43ef8a936334dacae31f9ff1f025998","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/b43ef8a936334dacae31f9ff1f025998.jpg"},{"fileId":"7f2cd31cc0c6433a8743d12312510903","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/7f2cd31cc0c6433a8743d12312510903.jpg"},{"fileId":"6327f5ca45af445cb23750cc4489c328","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/6327f5ca45af445cb23750cc4489c328.jpg"}],"bizProblemFlows":[{"id":"03ad024d0b464adf928c65ede00ed953","toExcuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","toExcuteRoleNm":"运维中心主管","configOrder":3,"configOrderNm":"反馈","addTime":"2018-08-04 16:58:08","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","excuteRoleNn":"运维中心主管","excuteBeginTime":"2018-08-04 16:58:08","excuteEndTime":"2018-08-04 16:59:08","excuteDes":"反馈意见了","excuteStatus":2,"toExcuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","toExcuteUserNm":"播州区管理员"},{"id":"60519d3dcf0b4091b31785a636a0581e","toExcuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","toExcuteRoleNm":"运维中心主管","configOrder":2,"configOrderNm":"业主审核","addTime":"2018-08-04 16:57:24","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"31ed81f394334e9a951990f04f5b2038","excuteRoleNn":"业主负责人","excuteEndTime":"2018-08-04 16:58:24","excuteStatus":2,"excuteDes":"业主来审核了"},{"id":"254a58440c014498abdfa3bba4375788","toExcuteRoleId":"31ed81f394334e9a951990f04f5b2038","toExcuteRoleNm":"业主负责人","toExcuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","toExcuteUserNm":"播州区管理员","configOrder":1,"configOrderNm":"审核事件","addTime":"2018-08-04 16:56:25","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","excuteRoleNn":"运维中心主管","excuteEndTime":"2018-08-04 16:57:25","excuteStatus":2,"excuteDes":"审核重大事件， 下一步业主审核"},{"id":"e124bcee34864640af670e93e342a5a7","toExcuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","toExcuteRoleNm":"运维中心主管","toExcuteUserId":"","toExcuteUserNm":"","configOrder":0,"configOrderNm":"确认问题","addTime":"2018-08-04 16:55:40","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","excuteRoleNn":"运维中心主管","excuteBeginTime":"2018-08-04 16:55:40","excuteEndTime":"2018-08-04 16:56:40","excuteStatus":1}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * problemId : 43a3e54c97634528b34df59b71905744
         * problemType : 1
         * problemTypeName : 巡检
         * reservoirId : 66fb3d579d084daf8a7d35d9d9612211
         * problemTitle : 2018-08-04事件上报测试
         * problemDescription : 2018-08-04事件上报测试
         * problemSource : 2
         * problemSourceUserId : a623db0bdb92434ebe6553561aaf2eef
         * problemCofirmType : 2
         * problemStatus : 4
         * isMakePlan : 0
         * status : 0
         * createBy : bozhou
         * createDate : 2018-08-04 14:23:35
         * updateBy : bozhou
         * updateDate : 2018-08-04 14:23:35
         * reservoirName : 枫青水库
         * isVerify : 0
         * problemStatusName : 严重问题
         * iSysFileUploads : [{"fileId":"56ef954c4e4e4ca8894f273964c2bb01","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/56ef954c4e4e4ca8894f273964c2bb01.jpg"},{"fileId":"b43ef8a936334dacae31f9ff1f025998","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/b43ef8a936334dacae31f9ff1f025998.jpg"},{"fileId":"7f2cd31cc0c6433a8743d12312510903","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/7f2cd31cc0c6433a8743d12312510903.jpg"},{"fileId":"6327f5ca45af445cb23750cc4489c328","bizKey":"43a3e54c97634528b34df59b71905744","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/6327f5ca45af445cb23750cc4489c328.jpg"}]
         * bizProblemFlows : [{"id":"03ad024d0b464adf928c65ede00ed953","toExcuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","toExcuteRoleNm":"运维中心主管","configOrder":3,"configOrderNm":"反馈","addTime":"2018-08-04 16:58:08","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","excuteRoleNn":"运维中心主管","excuteBeginTime":"2018-08-04 16:58:08","excuteEndTime":"2018-08-04 16:59:08","excuteDes":"反馈意见了"},{"id":"60519d3dcf0b4091b31785a636a0581e","toExcuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","toExcuteRoleNm":"运维中心主管","configOrder":2,"configOrderNm":"业主审核","addTime":"2018-08-04 16:57:24","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"31ed81f394334e9a951990f04f5b2038","excuteRoleNn":"业主负责人","excuteEndTime":"2018-08-04 16:58:24","excuteStatus":2,"excuteDes":"业主来审核了"},{"id":"254a58440c014498abdfa3bba4375788","toExcuteRoleId":"31ed81f394334e9a951990f04f5b2038","toExcuteRoleNm":"业主负责人","toExcuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","toExcuteUserNm":"播州区管理员","configOrder":1,"configOrderNm":"审核事件","addTime":"2018-08-04 16:56:25","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","excuteRoleNn":"运维中心主管","excuteEndTime":"2018-08-04 16:57:25","excuteStatus":2,"excuteDes":"审核重大事件， 下一步业主审核"},{"id":"e124bcee34864640af670e93e342a5a7","toExcuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","toExcuteRoleNm":"运维中心主管","toExcuteUserId":"","toExcuteUserNm":"","configOrder":0,"configOrderNm":"确认问题","addTime":"2018-08-04 16:55:40","excuteUserId":"a4e0ea61ccf8428ba84e092af1a84dcd","excuteUserNm":"播州区管理员","excuteRoleId":"100891f9f67e4b3a9e46d6a980b146d7","excuteRoleNn":"运维中心主管","excuteBeginTime":"2018-08-04 16:55:40","excuteEndTime":"2018-08-04 16:56:40","excuteStatus":1}]
         */

        private String problemId;
        private String problemType;
        private String problemTypeName;
        private String reservoirId;
        private String problemTitle;
        private String problemDescription;
        private String problemSource;
        private String problemSourceUserId;
        private String problemCofirmType;
        private String problemStatus;
        private String isMakePlan;
        private String status;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String reservoirName;
        private String isVerify;
        private String problemStatusName;
        private String userName;
        private String problemSourceUserName;
        private List<ISysFileUploadsBean> iSysFileUploads;
        private List<BizProblemFlowsBean> bizProblemFlows;

        public String getProblemId() {
            return problemId;
        }

        public void setProblemId(String problemId) {
            this.problemId = problemId;
        }

        public String getProblemType() {
            return problemType;
        }

        public void setProblemType(String problemType) {
            this.problemType = problemType;
        }

        public String getProblemTypeName() {
            return problemTypeName;
        }

        public void setProblemTypeName(String problemTypeName) {
            this.problemTypeName = problemTypeName;
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

        public String getProblemCofirmType() {
            return problemCofirmType;
        }

        public void setProblemCofirmType(String problemCofirmType) {
            this.problemCofirmType = problemCofirmType;
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

        public String getReservoirName() {
            return reservoirName;
        }

        public void setReservoirName(String reservoirName) {
            this.reservoirName = reservoirName;
        }

        public String getIsVerify() {
            return isVerify;
        }

        public void setIsVerify(String isVerify) {
            this.isVerify = isVerify;
        }

        public String getProblemStatusName() {
            return problemStatusName;
        }

        public void setProblemStatusName(String problemStatusName) {
            this.problemStatusName = problemStatusName;
        }

        public List<ISysFileUploadsBean> getISysFileUploads() {
            return iSysFileUploads;
        }

        public void setISysFileUploads(List<ISysFileUploadsBean> iSysFileUploads) {
            this.iSysFileUploads = iSysFileUploads;
        }

        public List<BizProblemFlowsBean> getBizProblemFlows() {
            return bizProblemFlows;
        }

        public void setBizProblemFlows(List<BizProblemFlowsBean> bizProblemFlows) {
            this.bizProblemFlows = bizProblemFlows;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getProblemSourceUserName() {
            return problemSourceUserName;
        }

        public void setProblemSourceUserName(String problemSourceUserName) {
            this.problemSourceUserName = problemSourceUserName;
        }

        public List<ISysFileUploadsBean> getiSysFileUploads() {
            return iSysFileUploads;
        }

        public void setiSysFileUploads(List<ISysFileUploadsBean> iSysFileUploads) {
            this.iSysFileUploads = iSysFileUploads;
        }

        public static class ISysFileUploadsBean {
            /**
             * fileId : 56ef954c4e4e4ca8894f273964c2bb01
             * bizKey : 43a3e54c97634528b34df59b71905744
             * filePath : http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Problem/2018-08/04/56ef954c4e4e4ca8894f273964c2bb01.jpg
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

        public static class BizProblemFlowsBean {
            /**
             * id : 03ad024d0b464adf928c65ede00ed953
             * toExcuteRoleId : 100891f9f67e4b3a9e46d6a980b146d7
             * toExcuteRoleNm : 运维中心主管
             * configOrder : 3
             * configOrderNm : 反馈
             * addTime : 2018-08-04 16:58:08
             * excuteUserId : a4e0ea61ccf8428ba84e092af1a84dcd
             * excuteUserNm : 播州区管理员
             * excuteRoleId : 100891f9f67e4b3a9e46d6a980b146d7
             * excuteRoleNn : 运维中心主管
             * excuteBeginTime : 2018-08-04 16:58:08
             * excuteEndTime : 2018-08-04 16:59:08
             * excuteDes : 反馈意见了
             * excuteStatus : 2
             * toExcuteUserId : a4e0ea61ccf8428ba84e092af1a84dcd
             * toExcuteUserNm : 播州区管理员
             */

            private String id;
            private String toExcuteRoleId;
            private String toExcuteRoleNm;
            private int configOrder;
            private String configOrderNm;
            private String addTime;
            private String excuteUserId;
            private String excuteUserNm;
            private String excuteRoleId;
            private String excuteRoleNn;
            private String excuteBeginTime;
            private String excuteEndTime;
            private String excuteDes;
            private Integer excuteStatus;
            private String toExcuteUserId;
            private String toExcuteUserNm;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getToExcuteRoleId() {
                return toExcuteRoleId;
            }

            public void setToExcuteRoleId(String toExcuteRoleId) {
                this.toExcuteRoleId = toExcuteRoleId;
            }

            public String getToExcuteRoleNm() {
                return toExcuteRoleNm;
            }

            public void setToExcuteRoleNm(String toExcuteRoleNm) {
                this.toExcuteRoleNm = toExcuteRoleNm;
            }

            public int getConfigOrder() {
                return configOrder;
            }

            public void setConfigOrder(int configOrder) {
                this.configOrder = configOrder;
            }

            public String getConfigOrderNm() {
                return configOrderNm;
            }

            public void setConfigOrderNm(String configOrderNm) {
                this.configOrderNm = configOrderNm;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getExcuteUserId() {
                return excuteUserId;
            }

            public void setExcuteUserId(String excuteUserId) {
                this.excuteUserId = excuteUserId;
            }

            public String getExcuteUserNm() {
                return excuteUserNm;
            }

            public void setExcuteUserNm(String excuteUserNm) {
                this.excuteUserNm = excuteUserNm;
            }

            public String getExcuteRoleId() {
                return excuteRoleId;
            }

            public void setExcuteRoleId(String excuteRoleId) {
                this.excuteRoleId = excuteRoleId;
            }

            public String getExcuteRoleNn() {
                return excuteRoleNn;
            }

            public void setExcuteRoleNn(String excuteRoleNn) {
                this.excuteRoleNn = excuteRoleNn;
            }

            public String getExcuteBeginTime() {
                return excuteBeginTime;
            }

            public void setExcuteBeginTime(String excuteBeginTime) {
                this.excuteBeginTime = excuteBeginTime;
            }

            public String getExcuteEndTime() {
                return excuteEndTime;
            }

            public void setExcuteEndTime(String excuteEndTime) {
                this.excuteEndTime = excuteEndTime;
            }

            public String getExcuteDes() {
                return excuteDes;
            }

            public void setExcuteDes(String excuteDes) {
                this.excuteDes = excuteDes;
            }

            public Integer getExcuteStatus() {
                return excuteStatus;
            }

            public void setExcuteStatus(int excuteStatus) {
                this.excuteStatus = excuteStatus;
            }

            public String getToExcuteUserId() {
                return toExcuteUserId;
            }

            public void setToExcuteUserId(String toExcuteUserId) {
                this.toExcuteUserId = toExcuteUserId;
            }

            public String getToExcuteUserNm() {
                return toExcuteUserNm;
            }

            public void setToExcuteUserNm(String toExcuteUserNm) {
                this.toExcuteUserNm = toExcuteUserNm;
            }
        }
    }
}
