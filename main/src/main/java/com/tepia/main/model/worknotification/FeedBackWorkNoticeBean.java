package com.tepia.main.model.worknotification;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       10:48
 * Version         :       1.0
 * 功能描述        :
 **/
public class FeedBackWorkNoticeBean {
    /**
     * id : 8e5c1b07e4db473da24df85fb16663bb
     * reservoirId : 6942292cad144bds97fa6c31f96ee698
     * status : 1
     * bizWorkNotice : {"id":"371d769c56384a41a18332e9bd9798e3","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee687,6942292cad144bds97fa6c31f96ee698","noticeTitle":"紧急通知紧急通知","noticeContent":"反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-28 17:34:15","userName":"超级管理员"}
     * reservoirName : 东风水库
     */

    private String id;
    private String reservoirId;
    private String status;
    private WorkNoticeBean bizWorkNotice;
    private String reservoirName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReservoirId() {
        return reservoirId;
    }

    public void setReservoirId(String reservoirId) {
        this.reservoirId = reservoirId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WorkNoticeBean getBizWorkNotice() {
        return bizWorkNotice;
    }

    public void setBizWorkNotice(WorkNoticeBean bizWorkNotice) {
        this.bizWorkNotice = bizWorkNotice;
    }

    public String getReservoirName() {
        return reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }


}
