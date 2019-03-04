package com.hiekn.metaboot.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class ConfigManage {

    private String sseLocation;

    public String getSseLocation() {
        return sseLocation;
    }

    public void setSseLocation(String sseLocation) {
        this.sseLocation = sseLocation;
    }
}
