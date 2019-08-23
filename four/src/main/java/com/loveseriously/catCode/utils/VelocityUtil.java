package com.loveseriously.catCode.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;

/**
 * @author lw
 * @date 2019-08-20
 */
public class VelocityUtil {
    private final static VelocityEngine velocityEngine;

    static {
        velocityEngine = new VelocityEngine();
        velocityEngine.init();
    }

    public static String evaluate(String template, Map<String, Object> map) {
        VelocityContext context = new VelocityContext();
        map.forEach(context::put);
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, "", template);
        return writer.toString();
    }
}
