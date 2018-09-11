package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;

/**
 * APP查询当前登录用户信息
 * @author ly
 * @date 2018/7/31
 */
public class UserInfoBean extends BaseResponse {


    /**
     * data : {"userCode":"a623db0bdb92434ebe6553561aaf2eef","loginCode":"bozhou","userName":"2222222","email":"bozhou@163.com","mobile":"15107132761","sex":"1","address":"贵州遵义播州区111号","userType":"0","officeCode":"741fab72b9b74ed0a06424d93c4d7ee5","officeName":"太比雅贵州分公司","loginScope":"1","lastLoginIp":"127.0.0.1","lastLoginDate":"2018-07-19 12:04:18","status":"0","createDate":"2018-07-19 10:18:02","updateDate":"2018-07-19 12:04:18"}
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
         * userCode : a623db0bdb92434ebe6553561aaf2eef
         * loginCode : bozhou
         * userName : 2222222
         * email : bozhou@163.com
         * mobile : 15107132761
         * sex : 1
         * address : 贵州遵义播州区111号
         * userType : 0
         * officeCode : 741fab72b9b74ed0a06424d93c4d7ee5
         * officeName : 太比雅贵州分公司
         * loginScope : 1
         * lastLoginIp : 127.0.0.1
         * lastLoginDate : 2018-07-19 12:04:18
         * status : 0
         * createDate : 2018-07-19 10:18:02
         * updateDate : 2018-07-19 12:04:18
         */

        private String userCode;
        private String loginCode;
        private String userName;
        private String email;
        private String mobile;
        private String sex;
        private String address;
        private String userType;
        private String officeCode;
        private String officeName;
        private String loginScope;
        private String lastLoginIp;
        private String lastLoginDate;
        private String status;
        private String createDate;
        private String updateDate;

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getLoginCode() {
            return loginCode;
        }

        public void setLoginCode(String loginCode) {
            this.loginCode = loginCode;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getOfficeCode() {
            return officeCode;
        }

        public void setOfficeCode(String officeCode) {
            this.officeCode = officeCode;
        }

        public String getOfficeName() {
            return officeName;
        }

        public void setOfficeName(String officeName) {
            this.officeName = officeName;
        }

        public String getLoginScope() {
            return loginScope;
        }

        public void setLoginScope(String loginScope) {
            this.loginScope = loginScope;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public String getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(String lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }
}
