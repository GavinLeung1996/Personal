package org.gavin.util;

import lombok.extern.slf4j.Slf4j;
import org.gavin.pojo.User;

@Slf4j
public class UserThreadLocal {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void set(User user){
        log.info("UserThreadLocal.set");
        threadLocal.set(user);
    }

    public static User get(){
        log.info("UserThreadLocal.get");
        return threadLocal.get();
    }

    public static void remove(){
        log.info("UserThreadLocal.remove");
        threadLocal.remove();
    }
}
