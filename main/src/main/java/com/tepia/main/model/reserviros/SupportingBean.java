package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午2:53
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       配套设施页面实体
 **/
public class SupportingBean extends BaseResponse{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * deName : aa
         * deFunction : da
         * fileInfo : [{"fileName":"xhr35_19201.jpg","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/reservoirDevice/2018-09/18/xhr35_19201.jpg"}]
         */

        /**
         * 设备名称
         */
        private String deName;
        /**
         * 设备类型
         */
        private String deType;
        /**
         * 设备功能
         */
        private String deFunction;
        /**
         * 投入使用时间
         */
        private String beginUseDate;
        /**
         * 位置
         */
        private String position;
        /**
         * 数量
         */
        private String deTotals;
        /**
         * 文件信息list
         */
        private List<FileInfoBean> fileInfo;

        public String getDeTotals() {
            return deTotals;
        }

        public void setDeTotals(String deTotals) {
            this.deTotals = deTotals;
        }

        public String getDeName() {
            return deName;
        }

        public void setDeName(String deName) {
            this.deName = deName;
        }

        public String getDeFunction() {
            return deFunction;
        }

        public void setDeFunction(String deFunction) {
            this.deFunction = deFunction;
        }

        public List<FileInfoBean> getFileInfo() {
            return fileInfo;
        }

        public void setFileInfo(List<FileInfoBean> fileInfo) {
            this.fileInfo = fileInfo;
        }

        public String getDeType() {
            return deType;
        }

        public void setDeType(String deType) {
            this.deType = deType;
        }

        public String getBeginUseDate() {
            return beginUseDate;
        }

        public void setBeginUseDate(String beginUseDate) {
            this.beginUseDate = beginUseDate;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
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
