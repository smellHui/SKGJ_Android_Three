package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午4:12
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       调度运行实体
 **/
public class OperationPlanBean extends BaseResponse{

    /**
     * data : {"fileName":"蚂蚁河新增视频模块使用说明.docx","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/reservoirAttachment/2018-09/25/蚂蚁河新增视频模块使用说明.docx"}
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
         * fileName : 蚂蚁河新增视频模块使用说明.docx
         * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/reservoirAttachment/2018-09/25/蚂蚁河新增视频模块使用说明.docx
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
