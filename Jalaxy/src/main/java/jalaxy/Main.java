package jalaxy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jalaxy.util.LoggerUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

    static final Logger LOG = LoggerUtil.getDefaultLogger();

    public static void main(String[] arg) throws Exception {
        int port = 8080;
        String startInfo = String
                .format("Jalaxy应用程序已启动\nhttp://localhost:%d", port);
        LOG.info(startInfo);

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        server.createContext("/test", new TestHandler());
        server.start();
    }

    static class TestHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) {
            String response = "hello world";

            try{
                //获得查询字符串(get)
                String queryString =  exchange.getRequestURI().getQuery();
                Map<String,String> queryStringInfo = formData2Dic(queryString);
                //获得表单提交数据(post)
                String postString = exchange.getRequestBody().toString();
                Map<String,String> postInfo = formData2Dic(postString);

                exchange.sendResponseHeaders(200,0);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException ie) {

            } catch (Exception e) {

            }
        }
    }

    public static Map<String,String> formData2Dic(String formData ) {
        Map<String,String> result = new HashMap<>();
        if(formData== null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        Arrays.stream(items).forEach(item ->{
            final String[] keyAndVal = item.split("=");
            if( keyAndVal.length == 2) {
                try{
                    final String key = URLDecoder.decode( keyAndVal[0],"utf8");
                    final String val = URLDecoder.decode( keyAndVal[1],"utf8");
                    result.put(key,val);
                }catch (UnsupportedEncodingException e) {}
            }
        });
        return result;
    }
}
