package com.tepia.main.model.image;

/**
 * Created by Joeshould on 2018/5/28.
 */

public class ImageUpDateResponse {

    /**
     * picId : 9b83fb73cfdd44ce9620dd28f9cdcf35
     * success : true
     * msg : Screenshot_2018-03-14-09-49-55.png上传成功
     */

    private String picId;
    private boolean success;
    private String msg;

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
