package com.tepia.main.model.task;

/**
 * Created by Joeshould on 2018/5/23.
 */

public class BaseSucessResponse {

    /**
     * status : true
     * msg : 操作数据成功
     */

    private boolean status;
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
