package com.tepia.main.model.image;

/**
 * Created by Joeshould on 2018/6/7.
 */

public class DeleteImageResponse {

    /**
     * status : true
     * message : 删除成功
     */

    private boolean status;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
