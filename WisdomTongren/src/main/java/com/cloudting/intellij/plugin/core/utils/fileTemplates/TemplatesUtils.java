package com.cloudting.intellij.plugin.core.utils.fileTemplates;

import com.cloudting.intellij.plugin.bean.DocumentsBean;
import com.cloudting.intellij.plugin.bean.Ma;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lw
 * @date 2019-08-27
 */
public class TemplatesUtils {
    public static final String[] TEMPLATE_JSP_NAME = {"list", "edit","detail"};
    public static final String[] TEMPLATE_JAVA_NAME = {"module", "controller", "vo", "service", "serviceImpl", "dao", "daoImpl"};

    public static Map<String, Object> TEMPLATE_VARIABLE = new HashMap<>();

    public static Map<String, Object> getVariable(@NotNull Ma ma) {
        final String name = ma.getName();
        final String tableName = ma.getTableName();
        String description = "";
        if (null != ma.getDescription()) {
            description = ma.getDescription();
        }
        final List<DocumentsBean> documentsBeans = ma.getDocumentsBeans();

        TEMPLATE_VARIABLE.put("upperName", name.substring(0, 1).toUpperCase() + name.substring(1));// 首字母自动转大写
        TEMPLATE_VARIABLE.put("lowerName", name.substring(0, 1).toLowerCase() + name.substring(1));// 首字母自动转小写
        TEMPLATE_VARIABLE.put("tableName", tableName);// 表名
        TEMPLATE_VARIABLE.put("description", description);// 注释


        TEMPLATE_VARIABLE.put("documentsBeans", documentsBeans);
        TEMPLATE_VARIABLE.put("documentsBeans", documentsBeans);


        return TEMPLATE_VARIABLE;
    }
}
