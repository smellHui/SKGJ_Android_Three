package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 视频
 * @author 44822
 */
public class VideoResponse extends BaseResponse{

    /**
     * code : 0
     * count : 0
     * data : []
     * */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * vscd : 98bfe161ba004dd88c471e882e831808
         * addvcd : 520321
         * vsnm : 测试视频
         * rscd : 345345
         * lgtd : 120
         * lttd : 32
         * account :
         * pwd :
         * address : 遵义市
         * isOnline :
         * ip : 119.1.151.132
         * port : 10000
         * channelId : 1
         * defaultDvrId : 0000180601
         * localMode : 0
         * accessType : 1
         * videoType : 0
         * reservoir : 测试水库
         * areaName : 播州区
         */

        private String vscd;
        private String addvcd;
        private String vsnm;
        private String rscd;
        private String lgtd;
        private String lttd;
        private String account;
        private String pwd;
        private String address;
        private String isOnline;
        private String ip;
        private int port;
        private String channelId;
        private String defaultDvrId;
        private String localMode;
        private String accessType;
        private String videoType;
        private String reservoir;
        private String areaName;
        private String codeBase;
        private String classId;

        public String getVscd() {
            return vscd;
        }

        public void setVscd(String vscd) {
            this.vscd = vscd;
        }

        public String getAddvcd() {
            return addvcd;
        }

        public void setAddvcd(String addvcd) {
            this.addvcd = addvcd;
        }

        public String getVsnm() {
            return vsnm;
        }

        public void setVsnm(String vsnm) {
            this.vsnm = vsnm;
        }

        public String getRscd() {
            return rscd;
        }

        public void setRscd(String rscd) {
            this.rscd = rscd;
        }

        public String getLgtd() {
            return lgtd;
        }

        public void setLgtd(String lgtd) {
            this.lgtd = lgtd;
        }

        public String getLttd() {
            return lttd;
        }

        public void setLttd(String lttd) {
            this.lttd = lttd;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(String isOnline) {
            this.isOnline = isOnline;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getDefaultDvrId() {
            return defaultDvrId;
        }

        public void setDefaultDvrId(String defaultDvrId) {
            this.defaultDvrId = defaultDvrId;
        }

        public String getLocalMode() {
            return localMode;
        }

        public void setLocalMode(String localMode) {
            this.localMode = localMode;
        }

        public String getAccessType() {
            return accessType;
        }

        public void setAccessType(String accessType) {
            this.accessType = accessType;
        }

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }

        public String getReservoir() {
            return reservoir;
        }

        public void setReservoir(String reservoir) {
            this.reservoir = reservoir;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getCodeBase() {
            return codeBase;
        }

        public void setCodeBase(String codeBase) {
            this.codeBase = codeBase;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }
    }
}
