package org.gavin.service;

import org.gavin.pojo.User;

public interface DubboUserService {

    void doRegister(User user);

    String doLogin(User user);
}
