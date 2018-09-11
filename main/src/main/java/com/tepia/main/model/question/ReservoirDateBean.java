package com.tepia.main.model.question;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 事件上报时接口调用水库实体
 * @author BLiYing
 * @date 2018/8/8
 */
public class ReservoirDateBean extends DataSupport {
    private String reservoirId;
    private String reservoirCode;
    @Column(unique = true, defaultValue = "unknown")
    private String reservoir;
    private Integer count;
    private String userCode;

    public String getReservoirId() {
        return reservoirId;
    }

    public void setReservoirId(String reservoirId) {
        this.reservoirId = reservoirId;
    }

    public String getReservoirCode() {
        return reservoirCode;
    }

    public void setReservoirCode(String reservoirCode) {
        this.reservoirCode = reservoirCode;
    }

    public String getReservoir() {
        return reservoir;
    }

    public void setReservoir(String reservoir) {
        this.reservoir = reservoir;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
