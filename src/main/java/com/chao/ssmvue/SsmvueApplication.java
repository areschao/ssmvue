package com.chao.ssmvue;

import com.chao.ssmvue.core.utils.SpringBeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.chao.ssmvue.base.security.mapper")
public class SsmvueApplication {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(SsmvueApplication.class, args);
        DataSource dataSource = SpringBeanUtil.getBean(DataSource.class);
        System.out.println(dataSource);
    }

}
