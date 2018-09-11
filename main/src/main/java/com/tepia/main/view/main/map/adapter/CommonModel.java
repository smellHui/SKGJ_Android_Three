package com.tepia.main.view.main.map.adapter;

/**
 * 公共的Model
 * @author 44822
 */
public class CommonModel {
    private String name;//名称
    private double lgtd;
    private double lttd;
    private double endLgtd;
    private   double endLttd;
    private String route;//巡河日志坐标集合

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public double getEndLgtd() {
        return endLgtd;
    }

    public void setEndLgtd(double endLgtd) {
        this.endLgtd = endLgtd;
    }

    public double getEndLttd() {
        return endLttd;
    }

    public void setEndLttd(double endLttd) {
        this.endLttd = endLttd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLgtd() {
        return lgtd;
    }

    public void setLgtd(double lgtd) {
        this.lgtd = lgtd;
    }

    public double getLttd() {
        return lttd;
    }

    public void setLttd(double lttd) {
        this.lttd = lttd;
    }

}
