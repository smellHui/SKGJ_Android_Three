package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午3:54
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       水库安全管理预案
 **/
public class SafeManagerPlanBean extends BaseResponse {

    /**
     * data : {"fileName":"视频播放脚本.doc","filePath":"http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/reservoirAttachment/2018-09/25/视频播放脚本.doc"}
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
         * fileName : 视频播放脚本.doc
         * filePath : http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/reservoirAttachment/2018-09/25/视频播放脚本.doc
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
