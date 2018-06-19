package com.hiekn.metaboot.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("${custom.property.path}")
@ConfigurationProperties
public class Constants {

    private Constants(){}

    public static final String VAR = "常量";

}