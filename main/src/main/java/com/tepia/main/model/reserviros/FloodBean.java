package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午3:51
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       防汛物资实体
 **/
public class FloodBean extends BaseResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * meName : 锣鼓
         * meType : 1
         * meTotals : 3
         * position : 水库管理办公室
         */

        /**
         * 物资名
         */
        private String meName;
        /**
         * 物资类型
         */
        private String meType;
        /**
         * 数量
         */
        private String meTotals;
        /**
         * 存放位置
         */
        private String position;


        private String manageName;
        private String phoneNum;
        private String remark;

        /**
         * 文件信息list
         */
        private List<FloodBean.DataBean.FileInfoBean> fileInfo;

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

        public List<FloodBean.DataBean.FileInfoBean> getFileInfo() {
            return fileInfo;
        }

        public void setFileInfo(List<FloodBean.DataBean.FileInfoBean> fileInfo) {
            this.fileInfo = fileInfo;
        }

        public static class FileInfoBean implements Serializable{
            /**
             * fileName : xhr35_19201.jpg
             * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/reservoirDevice/2018-09/18/xhr35_19201.jpg
             */

            private String fileName;
            /**
             * 文件路径
             */
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
