package cn.rwj.study.network.http.jvsrc;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author rwj
 * @since 2023/9/4
 */
public class HttpURLConnectionDemo {

    public static String doGet(String httpUrl) {
        //链接
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //设置连接超时时间
            connection.setReadTimeout(15000);
            //开始连接
            connection.connect();
            //获取响应数据
            if (connection.getResponseCode() == 200) {
                //获取返回的数据
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Optional.ofNullable(br).ifPresent(bufferedReader -> {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Optional.ofNullable(is).ifPresent(inputStream -> {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            //关闭远程连接
            Optional.ofNullable(connection).ifPresent(HttpURLConnection::disconnect);
        }
        return result.toString();
    }


    public static String doPost(String httpUrl, String param) {
        StringBuilder result = new StringBuilder();
        //连接
        HttpURLConnection connection = null;
        OutputStream os = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            //创建连接对象
            URL url = new URL(httpUrl);
            //创建连接
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方法
            connection.setRequestMethod("POST");
            //设置连接超时时间
            connection.setConnectTimeout(15000);
            //设置读取超时时间
            connection.setReadTimeout(15000);
            //DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //设置是否可读取
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //开启连接
            connection.connect();

            //拼装参数
            if (null != param && !param.equals("")) {
                //设置参数
                os = connection.getOutputStream();
                //拼装参数
                os.write(param.getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
            }
            //设置权限
            //设置请求头等
            //读取响应
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                        result.append("\r\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            Optional.ofNullable(br).ifPresent(bufferedReader -> {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Optional.ofNullable(is).ifPresent(inputStream -> {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Optional.ofNullable(connection).ifPresent(HttpURLConnection::disconnect);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String message = doGet("http://localhost:8866/test/get");
        System.out.println(message);
        message = doPost("http://localhost:8866/test/post", "name=任武杰");
        System.out.println(message);
        message = doPost("http://localhost:8866/test/post", "{\"name\": \"司老板\"}");
        System.out.println(message);
    }

}
