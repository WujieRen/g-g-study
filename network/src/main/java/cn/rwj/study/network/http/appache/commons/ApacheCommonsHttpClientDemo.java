package cn.rwj.study.network.http.appache.commons;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author rwj
 * @since 2023/9/4
 * <p>
 * Apache Common 包的 HttpClient；
 */
public class ApacheCommonsHttpClientDemo {

    public static String doGet(String url, String charset) {
        /**
         * 1.生成HttpClient对象并设置参数
         */
        HttpClient httpClient = new HttpClient();
        //设置Http连接超时为5秒
        httpClient
                .getHttpConnectionManager()
                .getParams()
                .setConnectionTimeout(5000);

        /**
         * 2.生成GetMethod对象并设置参数
         */
        GetMethod getMethod = new GetMethod(url);
        //设置get请求超时为5秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        //设置请求重试处理，用的是默认的重试处理：请求三次
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        String response = "";

        /**
         * 3.执行HTTP GET 请求
         */
        try {
            int statusCode = httpClient.executeMethod(getMethod);

            /**
             * 4.判断访问的状态码
             */
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("请求出错：" + getMethod.getStatusLine());
            }

            /**
             * 5.处理HTTP响应内容
             */
            //HTTP响应头部信息，这里简单打印
            System.out.println("/--------------------------------HEADER-----------------------------------/");
            Header[] headers = getMethod.getResponseHeaders();
            for (Header h : headers) {
                System.out.println(h.getName() + "---------------" + h.getValue());
            }
            System.out.println("/--------------------------------RESPONSE-----------------------------------/");
            //读取HTTP响应内容，这里简单打印网页内容
            //读取为字节数组
            byte[] responseBody = getMethod.getResponseBody();
            response = new String(responseBody, charset);
            System.out.println("-----------response:" + response);
            //读取为InputStream，在网页内容数据量大时候推荐使用
            //InputStream response = getMethod.getResponseBodyAsStream();
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("请检查输入的URL!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            System.out.println("发生网络异常!");
        } finally {
            /**
             * 6.释放连接
             */
            getMethod.releaseConnection();
        }
        return response;
    }

    public static String doPost(String url, String jsonStr) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        //设置json格式传送
        postMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
        //必须设置下面这个Header
        postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        //添加请求参数，即 RequestParam —— 该类型的超参数会以 k=v 的形式跟在url后
//        postMethod.addParameter("commentId", jsonStr.getString("commentId"));
        postMethod.addParameter("args", jsonStr);
        postMethod.addParameter("name", "名字");

        //设置body参数
        postMethod.setRequestBody("{\"id\":\"1651865904680783873\",\"key\":\"1651865904680783873\",\"title\":\"BI\",\"parentId\":\"\",\"name\":\"BI\",\"perms\":null,\"permsType\":\"1\",\"icon\":\"area-chart\",\"component\":\"http://localhost:8081/\",\"url\":\"http://localhost:8081/\",\"redirect\":null,\"sortNo\":10,\"menuType\":0,\"isLeaf\":true,\"route\":true,\"keepAlive\":false,\"description\":\"bi\",\"delFlag\":0,\"createBy\":\"admin\",\"createTime\":\"2023-04-28 16:28:17\",\"updateBy\":\"admin\",\"updateTime\":\"2023-04-28 16:45:51\",\"alwaysShow\":false,\"hidden\":false,\"status\":\"1\",\"internalOrExternal\":true,\"singlePointStatus\":true,\"children\":null,\"leaf\":true}");
        /*postMethod.setRequestBody(new NameValuePair[]{
                new NameValuePair("id", "1651865904680783873"),
                ...
        });*/

        String res = "";
        try {
            int code = httpClient.executeMethod(postMethod);
            if (code == 200) {
                res = new String(postMethod.getResponseBody(), StandardCharsets.UTF_8.name());
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        String url = "http://localhost:8866/test/get";
        doGet(url, StandardCharsets.UTF_8.name());

        url = "http://192.168.66.111/";   //nginx
        doGet(url, StandardCharsets.UTF_8.name());


        url = "http://localhost:8866/test/post";
        doPost(url, "jsonStr");

        url = "http://192.168.66.111:9091/guap/sys/permission/edit";
        doPost(url, "");
    }

}
