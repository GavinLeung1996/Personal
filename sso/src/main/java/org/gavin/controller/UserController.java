package org.gavin.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.gavin.pojo.User;
import org.gavin.service.UserService;
import org.gavin.util.ObjectMapperUtil;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private UserService userService;

    @RequestMapping("check/{data}/{dataType}")
    public JSONPObject check(String callback, @PathVariable("data")String data,@PathVariable("dataType")String dataType){
        boolean flag = userService.check(data,dataType);
        return new JSONPObject(callback, SysResut.success(flag));
    }

    @RequestMapping("query/{token}")
    public JSONPObject query(String callback,@PathVariable("token") String token){
        String result = jedisCluster.get(token);
        if (!StringUtils.isEmpty(result)){
            return new JSONPObject(callback,SysResut.success(result));
        }
        return new JSONPObject(callback,SysResut.fail());
    }
}
