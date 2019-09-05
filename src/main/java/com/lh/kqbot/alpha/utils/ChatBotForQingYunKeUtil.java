package com.lh.kqbot.alpha.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @auther: loneyfall
 * @date: 2019/8/21
 * @description: 青云客机器人接口
 */
public class ChatBotForQingYunKeUtil {
    private static final String BASE_URL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    public static String sendTalk(String req) {
        try {
            String msg = URLEncoder.encode(req, "UTF-8");
            URLConnection conn = new URL(BASE_URL + msg).openConnection();
            conn.setDoInput(true);
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            if (JSONUtil.isJsonObj(sb.toString())) {
                JSONObject json = JSONUtil.parseObj(sb.toString());
                return json.getStr("content");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
