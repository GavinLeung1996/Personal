package org.gavin.interceptor;

import org.gavin.pojo.User;
import org.gavin.util.ObjectMapperUtil;
import org.gavin.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies
            ) {
                if ("JT_TICKET".equals(cookie.getName())){
                    String result = jedisCluster.get(cookie.getValue());
                    if (!StringUtils.isEmpty(result)) {
                        User user = ObjectMapperUtil.toObject(result, User.class);
                        UserThreadLocal.set(user);
                        return true;
                    }
                }
            }
        }
        response.sendRedirect("/user/login.html");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
