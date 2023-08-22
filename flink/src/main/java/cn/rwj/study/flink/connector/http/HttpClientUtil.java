package cn.rwj.study.flink.connector.http;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 接收 table properties 中 format 格式的数据，序列号成 RowData 类型，从 SourceFunction 输出
 * @author rwj
 * @since 2023/8/21
 */
@Slf4j
public class HttpClientUtil {

    public static String doGet(String httpurl) throws IOException {
        // 返回结果字符串
        String result = null;

        // 创建远程url连接对象
        URL url = new URL(httpurl);
        // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 设置连接方式：get
        connection.setRequestMethod("GET");
        // 设置连接主机服务器的超时时间：15000毫秒
        connection.setConnectTimeout(15000);
        // 设置读取远程返回的数据时间：60000毫秒
        connection.setReadTimeout(60000);
        // 发送请求
        connection.connect();

        // 通过connection连接，获取输入流
        if (connection.getResponseCode() == 200) {
            try (
                    InputStream is = connection.getInputStream();
                    // 封装输入流is，并指定字符集
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            ) {
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        }
        log.info("--->" + result);
        return result;
    }

}
