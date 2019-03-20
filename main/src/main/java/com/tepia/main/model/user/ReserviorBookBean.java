package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2019-03-19
 * Time            :       上午9:53
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       水库通讯录
 **/
public class ReserviorBookBean extends BaseResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * reservoir : 三口塘水库
         * userList : [{"userName":"李树明","mobile":"13539131155","jobName":"行政责任人"},{"userName":"钟航","mobile":"15216938669","jobName":"技术责任人"},{"userName":"叶锦辉","mobile":"15113452698","jobName":"巡查责任人"}]
         */

        private String reservoir;
        private List<ContactBean> userList;

        public String getReservoir() {
            return reservoir;
        }

        public void setReservoir(String reservoir) {
            this.reservoir = reservoir;
        }

        public List<ContactBean> getUserList() {
            return userList;
        }

        public void setUserList(List<ContactBean> userList) {
            this.userList = userList;
        }


    }
}
