package com.loveseriously.utils.fileTemplates;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lw
 * @date 2019-08-25
 */
public class MyTemplatesUtils {
    public static final String[] TEMPLATE_JSP_NAME = {"list", "edit","detail"};
    public static final String[] TEMPLATE_JAVA_NAME = {"module", "controller", "vo", "service", "serviceImpl", "dao", "daoImpl"};

    public static Map<String, Object> TEMPLATE_VARIABLE = new HashMap<>();

    public static Map<String, Object> getVariable(String name) {
        TEMPLATE_VARIABLE.put("UPPERNAME", name.substring(0, 1).toUpperCase() + name.substring(1));// 首字母自动转大写
        TEMPLATE_VARIABLE.put("LOWERNAME", name.substring(0, 1).toLowerCase() + name.substring(1));// 首字母自动转小写
        return TEMPLATE_VARIABLE;
    }
}
