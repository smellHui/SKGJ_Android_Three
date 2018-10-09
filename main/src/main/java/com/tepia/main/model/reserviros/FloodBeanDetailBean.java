package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-10-09
 * Time            :       下午5:00
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       防汛物资详情实体
 **/
public class FloodBeanDetailBean extends BaseResponse {

    /**
     * data : {"meName":"钠灯","meType":"7","meTotals":"10","position":"后勤仓库","manageName":"大头峰","phoneNum":"1888871212","remark":"萝卜","fileInfo":[{"fileName":"Java学习图片.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/Cleaning/2018-09/30/76fd5fe2642542e2b21334b377bcf5b9.jpg"}],"meTypeName":"照明设备"}
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
         * meName : 钠灯
         * meType : 7
         * meTotals : 10
         * position : 后勤仓库
         * manageName : 大头峰
         * phoneNum : 1888871212
         * remark : 萝卜
         * fileInfo : [{"fileName":"Java学习图片.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/Cleaning/2018-09/30/76fd5fe2642542e2b21334b377bcf5b9.jpg"}]
         * meTypeName : 照明设备
         */

        private String meName;
        private String meType;
        private String meTotals;
        private String position;
        private String manageName;
        private String phoneNum;
        private String remark;
        private String meTypeName;
        private List<FileInfoBean> fileInfo;

        public String getMeName() {
            return meName;
        }

        public void setMeName(String meName) {
            this.meName = meName;
        }

        public String getMeType() {
            return meType;
        }

        public void setMeType(String meType) {
            this.meType = meType;
        }

        public String getMeTotals() {
            return meTotals;
        }

        public void setMeTotals(String meTotals) {
            this.meTotals = meTotals;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getManageName() {
            return manageName;
        }

        public void setManageName(String manageName) {
            this.manageName = manageName;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getMeTypeName() {
            return meTypeName;
        }

        public void setMeTypeName(String meTypeName) {
            this.meTypeName = meTypeName;
        }

        public List<FileInfoBean> getFileInfo() {
            return fileInfo;
        }

        public void setFileInfo(List<FileInfoBean> fileInfo) {
            this.fileInfo = fileInfo;
        }

        public static class FileInfoBean {
            /**
             * fileName : Java学习图片.jpg
             * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/APP/Cleaning/2018-09/30/76fd5fe2642542e2b21334b377bcf5b9.jpg
             */

            private String fileName;
            private String filePath;

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
