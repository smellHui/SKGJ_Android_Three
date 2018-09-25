package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       上午11:50
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       到访水库日志详情
 **/
public class VisitLogDetailBean extends BaseResponse {

    /**
     * data : {"id":"ae4b532e8ab946a19e7436c5ea7c447b","visitTime":"2018-04-07 08:08:09","visitCause":"到访原因111测试修改","workContent":"工作内容测试修改","remark":"备注测试修改","reservoir":"绿竹坝水库","userName":"鲁志平","fileInfos":[{"fileId":"792c002412d14d41ac8c1c636d2fcad3","fileName":"d03baaf97259273ec4990afe5939e00a.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/visit/2018-09/25/d03baaf97259273ec4990afe5939e00a.jpg"},{"fileId":"55e47fc360c04879bc64c87e6186d302","fileName":"fefb82c7c2bbf4f3e38631ba3e2dfdfd.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/visit/2018-09/25/fefb82c7c2bbf4f3e38631ba3e2dfdfd.jpg"}]}
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
         * id : ae4b532e8ab946a19e7436c5ea7c447b
         * visitTime : 2018-04-07 08:08:09
         * visitCause : 到访原因111测试修改
         * workContent : 工作内容测试修改
         * remark : 备注测试修改
         * reservoir : 绿竹坝水库
         * userName : 鲁志平
         * fileInfos : [{"fileId":"792c002412d14d41ac8c1c636d2fcad3","fileName":"d03baaf97259273ec4990afe5939e00a.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/visit/2018-09/25/d03baaf97259273ec4990afe5939e00a.jpg"},{"fileId":"55e47fc360c04879bc64c87e6186d302","fileName":"fefb82c7c2bbf4f3e38631ba3e2dfdfd.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/visit/2018-09/25/fefb82c7c2bbf4f3e38631ba3e2dfdfd.jpg"}]
         */

        private String id;
        private String visitTime;
        private String visitCause;
        private String workContent;
        private String remark;
        private String reservoir;
        private String userName;
        private List<FileInfosBean> fileInfos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(String visitTime) {
            this.visitTime = visitTime;
        }

        public String getVisitCause() {
            return visitCause;
        }

        public void setVisitCause(String visitCause) {
            this.visitCause = visitCause;
        }

        public String getWorkContent() {
            return workContent;
        }

        public void setWorkContent(String workContent) {
            this.workContent = workContent;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getReservoir() {
            return reservoir;
        }

        public void setReservoir(String reservoir) {
            this.reservoir = reservoir;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<FileInfosBean> getFileInfos() {
            return fileInfos;
        }

        public void setFileInfos(List<FileInfosBean> fileInfos) {
            this.fileInfos = fileInfos;
        }

        public static class FileInfosBean {
            /**
             * fileId : 792c002412d14d41ac8c1c636d2fcad3
             * fileName : d03baaf97259273ec4990afe5939e00a.jpg
             * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/visit/2018-09/25/d03baaf97259273ec4990afe5939e00a.jpg
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
}
