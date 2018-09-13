package com.tepia.main.model.weather;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-12
 * Time            :       21:41
 * Version         :       1.0
 * 功能描述        :
 **/
public  class IndexBean {
    /**
     * iname : 空调指数
     * ivalue : 部分时间开启
     * detail : 天气热，到中午的时候您将会感到有点热，因此建议在午后较热时开启制冷空调。
     */

    private String iname;
    private String ivalue;
    private String detail;

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIvalue() {
        return ivalue;
    }

    public void setIvalue(String ivalue) {
        this.ivalue = ivalue;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
