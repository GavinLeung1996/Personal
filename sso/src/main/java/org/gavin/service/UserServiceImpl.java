package org.gavin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gavin.mapper.UserMapper;
import org.gavin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean check(String data, String dataType) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String column = dataType.equals("1")? "username" :
                (dataType.equals("2")? "phone" : "email");
        queryWrapper.eq(column,data);
        return userMapper.selectList(queryWrapper).size()!=0;
    }
}
