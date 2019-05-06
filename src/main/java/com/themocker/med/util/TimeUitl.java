package com.themocker.med.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeUitl {
    public static String getTime(Timestamp timestamp){
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        tsStr = sdf.format(timestamp);
        return tsStr;
    }
}
