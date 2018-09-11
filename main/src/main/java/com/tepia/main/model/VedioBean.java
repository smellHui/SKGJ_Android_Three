package com.tepia.main.model;

import java.io.Serializable;

/**
 * 极光推送自定义消息返回实体
 * @author liying
 * @date 2018/8/20
 */
public class VedioBean implements Serializable{
    private String loginCode;
    //"loginCode":"bozhouadmin","userName":"播州管理员","port":1089,"roomId":10035,"ip":"192.168.10.99"
    private String userName;
    private String port;
    private String roomId;
    private String ip;
    private String userCode;

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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
