package org.gavin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.gavin.mapper")
public class SpringBootRunSSO {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunSSO.class,args);
    }
}
