package com.hiekn.metaboot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

public final class CommonUtils {
	
	public static String getRandomUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static Date getTime(){
		return new Date();
	}

    public static Properties getProperties(Class<?> clazz, String fileName) {
        InputStream in;
        Properties props = new Properties();
        try {
            in = clazz.getClassLoader().getResourceAsStream(fileName);
            props.load(in);
        } catch (IOException e) {

        }

        return props;
    }
}
