package com.tepia.main.model.user.homepageinfo;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-21
 * Time            :       15:15
 * Version         :       1.0
 * 功能描述        :      防汛物资实体
 **/
public class MaterialBean {
    /**
     * id : ab432a971b87488091fea949e2c0633e
     * meName : 器材1
     * meType : 1
     * meTotals : 1套
     * position : 绿竹坝仓库
     */

    private String id;
    private String meName;
    private String meType;
    private String meTotals;
    private String position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeName() {
        return meName;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public String getMeType() {
        return meType;
    }

    public void setMeType(String meType) {
        this.meType = meType;
    }

    public String getMeTotals() {
        return meTotals;
    }

    public void setMeTotals(String meTotals) {
        this.meTotals = meTotals;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
