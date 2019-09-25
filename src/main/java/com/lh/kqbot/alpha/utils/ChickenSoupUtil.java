package com.lh.kqbot.alpha.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @auther: loneyfall
 * @date: 2019/9/24
 * @description: 毒鸡汤接口
 */
public class ChickenSoupUtil {

    private static final String BASE_URL = "https://api.666ylw.cn/yan/api.php?charset=utf-8&encode=json";

    public static String poisoned() {
        try {
            // 忽略ssl检查
            //创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            HttpsURLConnection conn = (HttpsURLConnection) new URL(BASE_URL).openConnection();
            conn.setSSLSocketFactory(ssf);
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
                return json.getStr("text");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(ChickenSoupUtil.poisoned());
        System.out.println(ChickenSoupUtil.poisoned());
        System.out.println(ChickenSoupUtil.poisoned());
    }
}
