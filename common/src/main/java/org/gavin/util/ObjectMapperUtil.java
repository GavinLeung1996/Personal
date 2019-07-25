package org.gavin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
public class ObjectMapperUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object obj){
        String result = null;
        try {
            result = mapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        log.info("ObjectMapperUtil.toString success");
        return result;
    }

    public static <T> T toObject(String json,Class<T> cls){
        T object = null;
        try {
            object = mapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        log.info("ObjectMapperUtil.toObject success");
        return object;
    }
}
