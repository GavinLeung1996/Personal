package org.gavin;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class TestHttpClient {

    @Test
    public void testHttoClient() throws IOException {
//      1.建立工具API
        CloseableHttpClient client = HttpClients.createDefault();
//      2.定义连接路劲
        String url = "http://www.baidu.com";
//      3.定义请求类型对象
        HttpGet get = new HttpGet(url);
//      4.发起HTTP请求,获取响应结果
        CloseableHttpResponse response = client.execute(get);
//      5.判断相应是否正确
        if (response.getStatusLine().getStatusCode()==200) {
//          6.处理相应结果
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }
    }
}
