package com.agency.crm.utils;

public class MapUtils {
    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个经纬度之间的距离
     * @param longitude 经度
     * @param latitude 纬度
     * @return
     */
    public static double GetDistance(double longitude, double latitude, double longitude2, double latitude2) {
        double long1 = rad(longitude);
        double long2 = rad(longitude2);
        double a = long1 - long2;
        double b = rad(latitude) - rad(latitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(long1)*Math.cos(long2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s);
        return s;
    }

    public static void main(String[] args) {
       System.out.println(MapUtils.GetDistance(39.90469,116.40717, 39.9075635, 116.468896));
    }

}
