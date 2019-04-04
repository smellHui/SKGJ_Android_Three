package com.tepia.main.model.route;

import android.databinding.DataBindingUtil;

import com.tepia.voice.xunfei.TalkBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joeshould on 2018/7/30.
 */

public class RoutepointDataManager {
    private static final RoutepointDataManager ourInstance = new RoutepointDataManager();

    public static RoutepointDataManager getInstance() {
        return ourInstance;
    }

    private RoutepointDataManager() {
    }

    public void addPoint(RoutepointDataBean dataBean) {
        dataBean.save();
    }

    public List<RoutepointDataBean> getRoutePointList(String wordOrderId) {
        List<RoutepointDataBean> data = DataSupport.order("id asc").where("wordOrderId=?", wordOrderId).find(RoutepointDataBean.class);
        List<RoutepointDataBean> temp = new Douglas((ArrayList<RoutepointDataBean>) data, 1).compress();
        for (RoutepointDataBean bean : data) {
            if (temp.contains(bean)) {
            } else {
                DataSupport.delete(RoutepointDataBean.class, bean.getID());
            }
        }
        return data;
    }

    public List<RoutepointDataBean> getRoutePointListWithOutD(String wordOrderId) {
        List<RoutepointDataBean> data = DataSupport.order("id asc").where("wordOrderId=?", wordOrderId).find(RoutepointDataBean.class);
        return data;
    }

    public String getRoutePointListString(String wordOrderId) {
        List<RoutepointDataBean> data = getRoutePointList(wordOrderId);
        if (data == null) {
            return null;
        }
        String temp = "[";
        for (RoutepointDataBean bean : data) {
            temp = temp + "{" + bean.getLgtd() + "," + bean.getLttd() + "}" + ",";
        }
        if (data != null && data.size() > 0) {
            temp = temp.substring(0, temp.length() - 1);
        }

        temp = temp + "]";
        return temp;
    }
}
