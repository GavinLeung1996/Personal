package org.gavin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

@org.springframework.boot.test.context.SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootTest {
    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void testJedis(){
        System.out.println(jedisCluster);
    }
}
