package com.lh.kqbot.alpha.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @auther: loneyfall
 * @date: 2019/8/19
 * @description: 计算下班时间工具
 */
public class OffDutyUtil {

    public static String getOffDutyTime(Date date) {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        now.setTime(date);

        Calendar offDuty = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        offDuty.setTime(date);
        offDuty.set(Calendar.HOUR_OF_DAY, 18);
        offDuty.set(Calendar.MINUTE, 0);
        offDuty.set(Calendar.SECOND, 0);
        offDuty.set(Calendar.MILLISECOND, 0);

        if (now.before(offDuty)) {
            long diff = offDuty.getTimeInMillis() - now.getTimeInMillis();
            long diff_hour = diff / (60 * 60 * 1000);
            long diff_minute = diff % (60 * 60 * 1000) / (60 * 1000);
            long diff_second = diff % (60 * 1000) / 1000;

            return "离下班还有" + diff_hour + "小时" + diff_minute + "分钟" + diff_second + "秒";
        }
        return "特么都下班了还问";
    }
}
