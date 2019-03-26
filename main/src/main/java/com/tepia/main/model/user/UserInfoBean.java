package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * APP查询当前登录用户信息
 * @author ly
 * @date 2018/7/31
 */
public class UserInfoBean extends BaseResponse {


    /**
     * data : {"userCode":"a9adb6787a444c61b7905d7ab490dcc5","loginCode":"dycountyadmin","userName":"东源县责任人","email":"qq","mobile":"","sex":"1","address":"东1","userType":"0","officeCode":"c4d9631f345f474e878fa29f66e216fe","officeName":"东源县水务局","loginScope":"0,1","lastLoginIp":"127.0.0.1","lastLoginDate":"2019-01-15 09:56:51","status":"0","createDate":"2018-11-15 14:59:04","updateDate":"2019-01-15 09:56:51","registId":"120c83f76008d52169a","sysRoles":[{"roleCode":"2f0573c69ae74edf919d8332d04884a9","roleName":"东源县负责人"}],"areaCode":"441625","countSymbol":"1"}
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
         * userCode : a9adb6787a444c61b7905d7ab490dcc5
         * loginCode : dycountyadmin
         * userName : 东源县责任人
         * email : qq
         * mobile :
         * sex : 1
         * address : 东1
         * userType : 0
         * officeCode : c4d9631f345f474e878fa29f66e216fe
         * officeName : 东源县水务局
         * loginScope : 0,1
         * lastLoginIp : 127.0.0.1
         * lastLoginDate : 2019-01-15 09:56:51
         * status : 0
         * createDate : 2018-11-15 14:59:04
         * updateDate : 2019-01-15 09:56:51
         * registId : 120c83f76008d52169a
         * sysRoles : [{"roleCode":"2f0573c69ae74edf919d8332d04884a9","roleName":"东源县负责人"}]
         * areaCode : 441625
         * countSymbol : 1
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
        private String registId;
        private String areaCode;
        private String countSymbol;
        private List<SysRolesBean> sysRoles;

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

        public String getRegistId() {
            return registId;
        }

        public void setRegistId(String registId) {
            this.registId = registId;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getCountSymbol() {
            return countSymbol;
        }

        public void setCountSymbol(String countSymbol) {
            this.countSymbol = countSymbol;
        }

        public List<SysRolesBean> getSysRoles() {
            return sysRoles;
        }

        public void setSysRoles(List<SysRolesBean> sysRoles) {
            this.sysRoles = sysRoles;
        }

        public static class SysRolesBean {
            /**
             * roleCode : 2f0573c69ae74edf919d8332d04884a9
             * roleName : 东源县负责人
             */

            private String roleCode;
            private String roleName;

            public String getRoleCode() {
                return roleCode;
            }

            public void setRoleCode(String roleCode) {
                this.roleCode = roleCode;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }
        }
    }
}
