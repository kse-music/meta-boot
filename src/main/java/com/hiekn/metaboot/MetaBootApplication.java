package com.hiekn.metaboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MetaBootApplication {

	public static void main(String[] args) {
		System.setProperty("nacos.logging.default.config.enabled", "false");
		SpringApplication.run(MetaBootApplication.class, args);
    }

}
