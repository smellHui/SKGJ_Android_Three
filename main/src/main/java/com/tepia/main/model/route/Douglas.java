package com.tepia.main.model.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Joeshould on 2018/8/1.
 */

public class Douglas {
    private int end;
    private int start;
    private double dMax;
    private ArrayList<RoutepointDataBean> mLineInit = new ArrayList<>();
    private ArrayList<RoutepointDataBean> mLineFilter = new ArrayList<>();


    public Douglas(ArrayList<RoutepointDataBean> mLineInit, double dmax) {
        if (mLineInit == null) {
            throw new IllegalArgumentException("传入的经纬度坐标list == null");
        }
        this.dMax = dmax;
        this.start = 0;
        this.end = mLineInit.size() - 1;
        for (int i = 0; i < mLineInit.size(); i++) {
            this.mLineInit.add(mLineInit.get(i));
        }
    }

    /**
     * 压缩经纬度点
     *
     * @return
     */
    public ArrayList<RoutepointDataBean> compress() {
        if (mLineInit == null || mLineInit.size() == 0) return null;
        int size = mLineInit.size();
        ArrayList<RoutepointDataBean> latLngPoints = compressLine(mLineInit.toArray(new RoutepointDataBean[size]), mLineFilter, start, end, dMax);
        latLngPoints.add(mLineInit.get(0));
        latLngPoints.add(mLineInit.get(size - 1));
        //对抽稀之后的点进行排序
        Collections.sort(latLngPoints, new Comparator<RoutepointDataBean>() {
            @Override
            public int compare(RoutepointDataBean o1, RoutepointDataBean o2) {
                return o1.compareTo(o2);
            }
        });
        ArrayList<RoutepointDataBean> latLngs = new ArrayList<>();
        for (RoutepointDataBean point : latLngPoints) {
            latLngs.add(point);
        }
        return latLngs;
    }


    /**
     * 根据最大距离限制，采用DP方法递归的对原始轨迹进行采样，得到压缩后的轨迹
     * x
     *
     * @param originalLatLngs 原始经纬度坐标点数组
     * @param endLatLngs      保持过滤后的点坐标数组
     * @param start           起始下标
     * @param end             结束下标
     * @param dMax            预先指定好的最大距离误差
     */
    private ArrayList<RoutepointDataBean> compressLine(RoutepointDataBean[] originalLatLngs, ArrayList<RoutepointDataBean> endLatLngs, int start, int end, double dMax) {
        if (start < end) {
            //递归进行调教筛选
            double maxDist = 0;
            int currentIndex = 0;
            for (int i = start + 1; i < end; i++) {
                double currentDist = distToSegment(originalLatLngs[start], originalLatLngs[end], originalLatLngs[i]);
                if (currentDist > maxDist) {
                    maxDist = currentDist;
                    currentIndex = i;
                }
            }
            //若当前最大距离大于最大距离误差
            if (maxDist >= dMax) {
                //将当前点加入到过滤数组中
                endLatLngs.add(originalLatLngs[currentIndex]);
                //将原来的线段以当前点为中心拆成两段，分别进行递归处理
                compressLine(originalLatLngs, endLatLngs, start, currentIndex, dMax);
                compressLine(originalLatLngs, endLatLngs, currentIndex, end, dMax);
            }
        }
        return endLatLngs;
    }

    private double distToSegment(RoutepointDataBean start, RoutepointDataBean end, RoutepointDataBean center) {
        double a = Math.abs(calculateLineDistance(start, end));
        double b = Math.abs(calculateLineDistance(start, center));
        double c = Math.abs(calculateLineDistance(end, center));
        double p = (a + b + c) / 2.0;
        double s = Math.sqrt(Math.abs(p * (p - a) * (p - b) * (p - c)));
        double d = s * 2.0 / a;
        return d;
    }

    private double calculateLineDistance(RoutepointDataBean start, RoutepointDataBean end) {
        double lat1 = Double.parseDouble(start.getLttd());
        double lon1 = Double.parseDouble(start.getLgtd());
        double lat2 = Double.parseDouble(end.getLttd());
        double lon2 = Double.parseDouble(end.getLgtd());
        return distHaversineRAD(lat1, lon1, lat2, lon2);
    }

    private static final double EARTH_RADIUS = 6378.137;
    // 返回单位是:米
    public static double distHaversineRAD( double latitude1,double longitude1,
                                           double latitude2,double longitude2 ) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //有小数的情况;注意这里的10000d中的“d”
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;//单位：米
        return s;
    }
    //
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
