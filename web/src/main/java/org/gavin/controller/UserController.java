package org.gavin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.http.HttpResponse;
import org.gavin.pojo.User;
import org.gavin.service.DubboUserService;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Reference(check = false , timeout = 3000)
    private DubboUserService dubboUserService;

    @Autowired(required = false)
    private JedisCluster jedisCluster;

    @RequestMapping("/{moduleName}")
    public String moduleName(@PathVariable("moduleName") String moduleName){
        return moduleName;
    }

    @RequestMapping("doRegister")
    @ResponseBody
    public SysResut doRegister(User user){
        dubboUserService.doRegister(user);
        return SysResut.success();
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public SysResut doLogin(User user, HttpServletResponse response){
        String key = "JT_TICKET";
        String token = dubboUserService.doLogin(user);
        if (StringUtils.isEmpty(token)){
            return SysResut.fail();
        }
        Cookie cookie = new Cookie(key,token);
        cookie.setMaxAge(1000*60*30);
        cookie.setDomain("jt.com");
        cookie.setPath("/");
        response.addCookie(cookie);
        return SysResut.success();
    }

    @RequestMapping("logout")
    public String doLogOut(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies.length!=0) {
            for (Cookie cookie : cookies
            ) {
                if ("JT_TICKET".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    jedisCluster.del(token);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setDomain("jt.com");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/";
    }
}
