package org.gavin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class redisConfig {

    @Value("${redis.nodes}")
    private String nodes;

    @Bean
    @Lazy
    public JedisCluster jedisCluster(){
        Set<HostAndPort> set = new HashSet<>();
        String[] strNodes = nodes.split(",");
        for (String node:strNodes
             ) {
            String host = node.split(":")[0].trim();
            int port = Integer.parseInt(node.split(":")[1].trim());
            HostAndPort hostAndPort = new HostAndPort(host,port);
            set.add(hostAndPort);
        }
        return new JedisCluster(set);
    }
}
