package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class UserLoginResponse extends BaseResponse {

    /**
     * userName : ZYSYWZXGCXJ1
     * userId : 9517D806FD5D4CF09AB0E6206281F84D
     * sparent : dba41afabdbc484c879faf07411e2a18
     * orgId : null
     * name : 遵义市运维中心工程巡检1
     * phone : 18511836685
     * email :
     * address :
     */

    private String data;
    private String userName;
    private String userPass;
    private String userId;
    private String sparent;
    private Object orgId;
    private String name;
    private String phone;
    private String email;
    private String address;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSparent() {
        return sparent;
    }

    public void setSparent(String sparent) {
        this.sparent = sparent;
    }

    public Object getOrgId() {
        return orgId;
    }

    public void setOrgId(Object orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
