package com.jokeep.twodemo;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by wbq501 on 2015-12-28 15:45.
 * twodemo
 */
public class PostExecutor {
        private String url;
        private String key = "lGuOSy8PPOsy1IoFAGbo3A==";

        private Map<String, Object> postData;
        private String postStr;
        private String respStr;
        private Map<String, Object> respMap;
        private HttpURLConnection conn;
        private String json;
        private String signature;
        public static byte[] buffer = new byte[64];

        /**
         * 发送Post请求
         *
         * @param data 响应的Map数据
         * @return
         */
        public Map<String, Object> postFor(Map<String, Object> data) throws Exception {

            this.postData = data;

            initPostStr();

            executePost();

            parseRespStr();

            check();

            return respMap;
        }

        /**
         * 第四步
         * @throws Exception
         */
        private void check() throws Exception {
            if (!MD5Util.calcMD5(json + key).equalsIgnoreCase(signature)) {

                this.respStr = null;
                this.respMap = null;
                throw new Exception("签名验证失败");
            }
        }

        /**
         * 第三步
         */
        @SuppressWarnings("unchecked")
        private void parseRespStr() {

            Log.e("TAG", "respStr="+respStr);
            json = respStr.substring("json=".length(), respStr.indexOf("&signature="));
            signature = respStr.substring(respStr.indexOf("signature=")
                    + "signature=".length());

            respMap = (Map<String, Object>) JSON.parse(json);
        }

        /**
         * 第二步
         */
        private void executePost() {

            try {

                initConn();
                Log.e("TAG", "postStr=" + postStr);
                conn.getOutputStream().write(postStr.getBytes(StandardCharsets.UTF_8));
                conn.getOutputStream().flush();
                respStr = readFor(new BufferedInputStream(conn.getInputStream()));
            } catch (Exception e) {

                return;
            } finally {

                conn.disconnect();
            }
        }

        /**
         * 2.2
         * @param is
         * @return
         * @throws Exception
         */
        private synchronized String readFor(BufferedInputStream is)
                throws Exception {

            String rs = "";

            for (int i = 0;;) {

                clearBuffer();

                i = is.read(buffer);

                rs += new String(copyNewFor(i), "UTF-8");

                if (i < buffer.length)
                    break;
            }

            return rs;
        }

        private byte[] copyNewFor(int length) {

            byte[] rs = new byte[length];

            for (int i = 0; i < length; i++)
                rs[i] = buffer[i];

            return rs;
        }

        private void clearBuffer() {

            for (int i = 0; i < buffer.length; i++)
                buffer[i] = 0;
        }

        /**
         * 2.1
         */
        private void initConn() throws Exception {

            conn = (HttpURLConnection) new URL(URLEncoder.encode(url)).openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Host", url);
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Connection", "close");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(18000);
        }

        /**
         * 第一步
         */
        private void initPostStr() {

            postStr = "json=" + getPostJson() + "&signature="
                    + MD5Util.calcMD5(getPostJson() + key);
        }

        /**
         * 1.1
         */
        private String getPostJson() {

            return JSON.toJSONString(this.postData,
                    SerializerFeature.BrowserCompatible);
        }

        /**
         * 获得对象
         *
         * @param url 需要传入服务器端的Url
         * @return
         */
        public static PostExecutor getInstance(String url) {

            PostExecutor rs = new PostExecutor();
            rs.url = url;
            return rs;
        }
}
