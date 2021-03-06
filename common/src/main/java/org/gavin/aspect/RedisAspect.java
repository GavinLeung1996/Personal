package org.gavin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.gavin.anno.Cache_Query;
import org.gavin.eunm.KEY_ENUM;
import org.gavin.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

@Aspect
@Component
@Slf4j
public class RedisAspect {
    @Autowired(required = false)
    private JedisCluster jedisCluster;

    @Around(value = "@annotation(cache_query)")
    public Object cache(ProceedingJoinPoint joinPoint, Cache_Query cache_query){
        try {
            Object resultData = null;
            String key = getKey(joinPoint, cache_query);
            String result = jedisCluster.get(key);
            if (!StringUtils.isEmpty(result)) {
                Class targetClass = getClass(joinPoint);
                resultData = ObjectMapperUtil.toObject(result, targetClass);
                log.info("查询缓存");
                return resultData;
            }
            resultData = joinPoint.proceed();
            result = ObjectMapperUtil.toString(resultData);
            if (cache_query.secondes()==0){
                jedisCluster.set(key,result);
            }else{
                jedisCluster.setex(key,cache_query.secondes(),result);
            }
            log.info("查询数据库");
            return resultData;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }
    }

    private Class getClass(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature= (MethodSignature)joinPoint.getSignature();
        return methodSignature.getReturnType();
    }

    private String getKey(ProceedingJoinPoint joinPoint, Cache_Query cache_query) {
        if (KEY_ENUM.CUSTOM.equals(cache_query.keyType())){
            return cache_query.key();
        }
        String methodName = joinPoint.getSignature().getName();
        String arg0 = String.valueOf(joinPoint.getArgs()[0]);
        return methodName + "::" + arg0;
    }
}
