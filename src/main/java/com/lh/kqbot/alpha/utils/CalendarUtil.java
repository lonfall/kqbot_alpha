package com.lh.kqbot.alpha.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

/**
 * @auther: loneyfall
 * @date: 2019/9/4
 * @description: 日历工具
 */
public class CalendarUtil {
    private static final String BASE_URL = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6018&format=json&query=";

    public static String getNextHoliday() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            URLConnection conn = new URL(BASE_URL + year + "年" + month + "月").openConnection();
            conn.setDoInput(true);
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            JSONObject json = JSON.parseObject(sb.toString());
            if ("0".equals(json.getString("status"))) {
                Object holidays = json.getJSONArray("data").getJSONObject(0).get("holiday");
                if (holidays instanceof JSONObject) {
                    JSONObject holiday = (JSONObject) holidays;
                    if (null != holiday) {
                        String result = "最近的假期是[" + holiday.getString("name") + "]\n";
                        result += holiday.getString("desc") + "\n";
                        result += holiday.getString("rest");
                        return result;
                    } else {
                        return "你莫得假期了，哈哈！";
                    }
                } else if (holidays instanceof JSONArray) {
                    JSONArray holidayArray = (JSONArray) holidays;
                    if (holidayArray.size() > 0) {
                        JSONObject holiday = holidayArray.getJSONObject(0);
                        String result = "最近的假期是[" + holiday.getString("name") + "]\n";
                        result += holiday.getString("desc") + "\n";
                        result += holiday.getString("rest");
                        return result;
                    } else {
                        return "你莫得假期了，哈哈！";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "没查到额，请确认你是不是写了bug！";
    }
}
