package com.kevin.base.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * {@link Resource} 工具类
 *
 * @Author:Kevin
 * @Date:Created in 14:09 2021/1/2
 */
public interface ResourceUtil {

    static String getContent(Resource resource){
        return getContent(resource, "UTF-8");
    }

    static String getContent(Resource resource, String encoding)  {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        try {
            try (Reader reader = encodedResource.getReader()) {
                return IOUtils.toString(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
