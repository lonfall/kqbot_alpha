package com.lh.kqbot.alpha.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @auther: loneyfall
 * @date: 2019/9/2
 * @description:
 */
public class UTCFormartUtil {

    public static String formartUTC(String utc) {
        utc = utc.replace("Z", " UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date time = null;
        try {
            time = simpleDateFormat.parse(utc);
        } catch (ParseException e) {
            e.printStackTrace();
            return "抱歉，未能识别这条UTC字符串";
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return simpleDateFormat2.format(time);
    }
}
