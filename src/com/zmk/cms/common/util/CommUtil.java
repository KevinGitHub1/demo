package com.zmk.cms.common.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class CommUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String getCurrentTime2() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }

    public static String getCurrentTime3() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 判断字符串是否为空，为空，返回true。
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static String obj2Str(Object object) {
        if (object != null) {
            return object.toString();
        } else {
            return "--";
        }
    }

    /**
     * 向HttpServletResponse中写数据,设置ContentType为html/txt;charset=utf-8
     * 
     * @param response
     * @param text
     *            要写入的数据
     */
    public static void writeUtf8Text(HttpServletResponse response, String text) {
        response.setContentType("html/text;charset=utf-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(text);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(getIntervalDays(df.parse("2016-10-25 14:22:57"),
                new Date()));
    }

    @SuppressWarnings("unchecked")
    public static List json2list(String s) {
        JSONArray json = JSONArray.fromObject(s);
        List lt = (List) JSONArray.toCollection(json);
        return lt;
    }

    public static String getKiloLocation(String str) {
        double m = Double.parseDouble(str);
        double km = 0.0D;
        km = m / 1000.0D;
        DecimalFormat df = new DecimalFormat("############0.000");
        String kmkilo = df.format(km);
        return kmkilo;
    }

    /**
     * 比较两个日期相差天数
     * 
     * @param tdate
     * @param odate
     * @return
     */
    public static int getIntervalDays(Date tdate, Date odate) {

        if (null == tdate || null == odate) {
            return -1;
        }
        long intervalMinute = odate.getTime() - tdate.getTime();
        return (int) (intervalMinute / (24 * 60 * 60 * 1000));

    }

    public static String parseString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    public static String getDateZD(String dateStr) {
        if (dateStr == null) {
            return "null";
        } else {
            return " TO_DATE('" + dateStr + "','yyyy-mm-dd hh24:mi:ss') ";
        }
    }

    @SuppressWarnings("deprecation")
    public static String formatDate(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(Date.parse(time));
    }
}
