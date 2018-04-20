package com.hiekn.metaboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hiekn.metaboot.dao")
public class MetaBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaBootApplication.class, args);
    }
}
