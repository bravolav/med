package com.themocker.med.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUitl {
    public static String getTime(Timestamp timestamp){
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        tsStr = sdf.format(timestamp);
        return tsStr;
    }

    public static Timestamp StringToTimestamp(String strDate){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts = Timestamp.valueOf(strDate+":00");
        return ts;
    }



    public static String getWeek(String strDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(strDate);
        String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    public static void main(String[] args) throws Exception {

        System.out.println(StringToTimestamp("2019-05-08 16:00"));
    }


}
