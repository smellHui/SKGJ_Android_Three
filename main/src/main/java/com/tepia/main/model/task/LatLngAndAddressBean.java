package com.tepia.main.model.task;


import com.esri.arcgisruntime.geometry.Point;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/12/16
 * Time :    11:36
 * Describe :
 */
public class LatLngAndAddressBean {
    private String city;
    private Point point;
    private String reservoirName;

    public String getReservoirName() {
        return reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
