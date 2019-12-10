package com.tepia.main.view.update;

import com.tepia.base.http.BaseResponse;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/11/26
 * Time :    10:20
 * Describe :
 */
public class VersionInfoResponse extends BaseResponse {

    private VersionBean data;

    public VersionBean getData() {
        return data;
    }

    public void setData(VersionBean data) {
        this.data = data;
    }

    public class VersionBean {
        private String appId; // 9ED843B12FCF47F6BC2ECD1DD216A0E2
        private String appName; // 测试APP
        private String appDownLoadUrl; // http://asdfjalsjfsadf
        private String appDownloadCodeUrl; //
        private String preAppVersionIndex; // 3
        private int appVersionIndex; // 4
        private String appVersion; // 114
        private String levelDescpt; // 114
        private String publishTime; // 2019-11-25 17:04:51.0
        private String validTime; // 2019-11-25 17:04:51.0

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppDownLoadUrl() {
            return appDownLoadUrl;
        }

        public void setAppDownLoadUrl(String appDownLoadUrl) {
            this.appDownLoadUrl = appDownLoadUrl;
        }

        public String getAppDownloadCodeUrl() {
            return appDownloadCodeUrl;
        }

        public void setAppDownloadCodeUrl(String appDownloadCodeUrl) {
            this.appDownloadCodeUrl = appDownloadCodeUrl;
        }

        public String getPreAppVersionIndex() {
            return preAppVersionIndex;
        }

        public void setPreAppVersionIndex(String preAppVersionIndex) {
            this.preAppVersionIndex = preAppVersionIndex;
        }

        public int getAppVersionIndex() {
            return appVersionIndex;
        }

        public void setAppVersionIndex(int appVersionIndex) {
            this.appVersionIndex = appVersionIndex;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getLevelDescpt() {
            return levelDescpt;
        }

        public void setLevelDescpt(String levelDescpt) {
            this.levelDescpt = levelDescpt;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getValidTime() {
            return validTime;
        }

        public void setValidTime(String validTime) {
            this.validTime = validTime;
        }
    }
}
