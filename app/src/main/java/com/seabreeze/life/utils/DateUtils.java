package com.seabreeze.life.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static String formatTime2(long showTime) {
        StringBuilder sb = new StringBuilder();
        long min = showTime / 60;
        long second = showTime % 60;
        sb.append(min < 10 ? "0" + min : min)
                .append("'")
                .append(second < 10 ? "0" + second : second)
                .append("\"");
        return sb.toString();
    }

    //获得当天0点时间
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static String formatDate2String(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (time == null) {
            return "未知";
        }
        try {
            long createTime = format.parse(time).getTime() / 1000;
            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime - createTime - 24 * 3600 > 0) { //超出一天
                return (currentTime - createTime) / (24 * 3600) + "天前";
            } else {
                return (currentTime - createTime) / 3600 + "小时前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "未知";
    }
}
