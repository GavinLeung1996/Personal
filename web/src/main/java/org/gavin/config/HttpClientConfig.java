package org.gavin.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/resources/properties/httpClient.properties")
public class HttpClientConfig {
    @Value("${http.maxTotal}")
    private Integer maxTotal;						//最大连接数

    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;				//最大并发链接数

    @Value("${http.connectTimeout}")
    private Integer connectTimeout;					//创建链接的最大时间

    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;		//链接获取超时时间

    @Value("${http.socketTimeout}")
    private Integer socketTimeout;			  		//数据传输最长时间

    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled; 	//提交时检查链接是否可用

    @Bean(name = "poolingHttpClientConnectionManager")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(maxTotal);
        manager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return manager;
    }

    
}
