package com.hiekn.metaboot.util;

import com.google.gson.Gson;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.exception.JsonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.Objects;

@Configuration
public class JsonUtils {

    private static Gson gson ;

    @Autowired
    public void setGson(Gson gson) {
        JsonUtils.gson = gson;
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        if(Objects.isNull(json)){
            throw JsonException.newInstance(ErrorCodes.PARAM_PARSE_ERROR);
        }
		try {
			return gson.fromJson(json, cls);
		} catch (Exception e) {
			throw JsonException.newInstance();
		}
	}

	public static <T> T fromJson(String json, Type typeOfT) {
        if(Objects.isNull(json)){
            throw JsonException.newInstance(ErrorCodes.PARAM_PARSE_ERROR);
        }
		try {
			return gson.fromJson(json, typeOfT);
		} catch (Exception e) {
			throw JsonException.newInstance(); 
		}
	}

	public static String toJson(Object obj) {
		try {
			return gson.toJson(obj);
		} catch (Exception e) {
			throw JsonException.newInstance(); 
		}
	}

}