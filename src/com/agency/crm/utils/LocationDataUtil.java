package com.agency.crm.utils;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

public class LocationDataUtil {


    public static void main(String[] args){
        LocationDataUtil ld = new LocationDataUtil();
        ld.testDB();
    }

    public Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://47.105.169.49:3306/agency?characterEncoding=utf8";
        String username = "root";
        String password = "^j9UaqkV";
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(dbUrl, username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }



    public void insertProvinces() {
        String key = "EBNBZ-ELC64-536UJ-XGRBP-FTFGK-OZBMF";

        String urlString = "https://apis.map.qq.com/ws/district/v1/list?key="+key;
        String result = "";
        URL url;

        {
            try {
                url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line + "\n";
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSON jsonObject = JSON.parseObject(result);
            System.out.println(jsonObject.toJSONString());
        }
    }

    public void testDB() {
        Connection conn = this.getConnection();

        String sql = "select * from goods";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
