package com.tepia.main.model.route;

import org.litepal.crud.DataSupport;

/**
 * Created by Joeshould on 2018/7/30.
 */

public class RoutepointDataBean extends DataSupport {
    private String wordOrderId;
    private String lgtd;
    private String lttd;

    public RoutepointDataBean() {
    }

    public long getID(){
        return this.getBaseObjId();
    }
    public RoutepointDataBean(String wordOrderId, String lgtd, String lttd) {
        this.wordOrderId = wordOrderId;
        this.lgtd = lgtd;
        this.lttd = lttd;
    }

    public String getWordOrderId() {
        return wordOrderId;
    }

    public void setWordOrderId(String wordOrderId) {
        this.wordOrderId = wordOrderId;
    }

    public String getLgtd() {
        return lgtd;
    }

    public void setLgtd(String lgtd) {
        this.lgtd = lgtd;
    }

    public String getLttd() {
        return lttd;
    }

    public void setLttd(String lttd) {
        this.lttd = lttd;
    }

    public int compareTo(RoutepointDataBean o2) {
        if (this.getBaseObjId() < o2.getBaseObjId()) {
            return -1;
        } else if (this.getBaseObjId() > o2.getBaseObjId())
            return 1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getBaseObjId() == ((RoutepointDataBean)obj).getBaseObjId();
    }
}
