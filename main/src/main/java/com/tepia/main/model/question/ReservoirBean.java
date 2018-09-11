package com.tepia.main.model.question;

import com.tepia.base.http.BaseResponse;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 事件上报时接口调用水库实体
 * @author BLiYing
 * @date 2018/5/28
 */

public class ReservoirBean extends BaseResponse implements Serializable {
   /* private int code;
    private int count;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }*/

    private List<ReservoirDateBean> data;

    public List<ReservoirDateBean> getData() {
        return data;
    }


    /*public class DateBean  implements Serializable{
        private String reservoirId;
        private String reservoirCode;
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
    }*/
}
