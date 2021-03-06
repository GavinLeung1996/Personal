package org.gavin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "org.gavin.mapper")
public class SpringBootRunCart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunCart.class,args);
    }
}
