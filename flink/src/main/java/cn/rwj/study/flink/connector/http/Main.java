package cn.rwj.study.flink.connector.http;

import cn.hutool.core.lang.UUID;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * 参考自来：
 *      1、https://nightlies.apache.org/flink/flink-docs-release-1.14/zh/docs/dev/table/sourcessinks/
 *      2、https://www.cnblogs.com/Springmoon-venn/p/15392511.html
 * @author rwj
 * @since 2023/8/21
 */
public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 10);
        server.createContext("/", new TestHandler());
        server.start();
    }

    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "hello world";

            try {
                //获得表单提交数据(post)
                String postString = IOUtils.toString(exchange.getRequestBody());

                exchange.sendResponseHeaders(200, 0);
                OutputStream os = exchange.getResponseBody();
                String result = UUID.randomUUID().toString();
                result = System.currentTimeMillis() + ",name," + result;
                os.write(result.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
