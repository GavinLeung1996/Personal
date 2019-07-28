package org.gavin.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

@Service
public class HttpClientService {
    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    public String doGet(String url){

        return doGet(url,null,null);
    }

    public String doGet(String url, Map<String,String> params){

        return doGet(url,params,null);
    }

    public String doGet(@NotNull String url, Map<String,String> params, String  charset){
        String result = null;
        if (StringUtils.isEmpty(charset)){
            charset = "UTF-8";
        }

        if (params!=null){
            url += "?";
            for (Map.Entry<String,String> entry: params.entrySet()
                 ) {
                String key = entry.getKey();
                String value = entry.getValue();
                url = url + key + "=" +value + "&";
            }
            url = url.substring(0,url.length()-1);
        }

        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity(),charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
