package org.gavin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.gavin.mapper")
public class SpringBootRunManage {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunManage.class,args);
    }
}
