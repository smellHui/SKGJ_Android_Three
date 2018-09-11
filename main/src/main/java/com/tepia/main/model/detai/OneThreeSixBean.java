package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;

/**
 *
 * 雨量站中1,3,6小时雨晴
 * @author liying
 * @date 2018/7/27
 */

public class OneThreeSixBean extends BaseResponse implements Serializable{

    /**
     * data : {"sixH":0,"oneH":0,"threeH":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * sixH : 0
         * oneH : 0
         * threeH : 0
         */

        private int sixH;
        private int oneH;
        private int threeH;

        public int getSixH() {
            return sixH;
        }

        public void setSixH(int sixH) {
            this.sixH = sixH;
        }

        public int getOneH() {
            return oneH;
        }

        public void setOneH(int oneH) {
            this.oneH = oneH;
        }

        public int getThreeH() {
            return threeH;
        }

        public void setThreeH(int threeH) {
            this.threeH = threeH;
        }
    }
}
