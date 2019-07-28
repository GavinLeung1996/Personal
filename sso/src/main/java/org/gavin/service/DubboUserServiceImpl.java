package org.gavin.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gavin.mapper.UserMapper;
import org.gavin.pojo.User;
import org.gavin.util.ObjectMapperUtil;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisCluster;

import java.util.Date;

@Service
@RequestMapping("/user/")
public class DubboUserServiceImpl implements DubboUserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired(required = false)
    private JedisCluster jedisCluster;

    @Override
    public void doRegister(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setEmail("null").setCreated(new Date()).setUpdated(user.getCreated());
        userMapper.insert(user);
    }

    @Override
    public String doLogin(User user) {
        String token = null;
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        User userDB = userMapper.selectOne(queryWrapper);
        if (userDB!=null){
            token = "JT_TICKET_" + System.currentTimeMillis() + userDB.getUsername();
            token = DigestUtils.md5DigestAsHex(token.getBytes());
            userDB.setPassword("************");
            String userJSON = ObjectMapperUtil.toString(userDB);
            jedisCluster.setex(token,1000*60*30,userJSON);
        }
        return token;
    }
}
