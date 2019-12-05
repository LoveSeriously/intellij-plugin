package com.cloudting.intellij.plugin.core.utils.fileTemplates;

import com.cloudting.intellij.plugin.bean.DocumentsBean;
import com.cloudting.intellij.plugin.bean.FormatDocument;
import com.cloudting.intellij.plugin.core.service.ISettingJspDirectoryService;
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

    public static Map<String, Object> getVariable(@NotNull FormatDocument formatDocument) {
        final String name = formatDocument.getName();
        final String tableName = formatDocument.getTableName();
        String description = "";
        if (null != formatDocument.getDescription()) {
            description = formatDocument.getDescription();
        }
        final List<DocumentsBean> documentsBeans = formatDocument.getDocumentsBeans();

        TEMPLATE_VARIABLE.put("upperName", name.substring(0, 1).toUpperCase() + name.substring(1));// 首字母自动转大写
        TEMPLATE_VARIABLE.put("lowerName", name.substring(0, 1).toLowerCase() + name.substring(1));// 首字母自动转小写
        TEMPLATE_VARIABLE.put("tableName", tableName);// 表名
        TEMPLATE_VARIABLE.put("description", description);// 注释

        TEMPLATE_VARIABLE.put("documentsBeans", documentsBeans);

        ISettingJspDirectoryService service = ISettingJspDirectoryService.getInstance();
        final String jspPootPath = service.getTextWebDir();
        final int indexStart = jspPootPath.indexOf("WEB-INF") + 8;

        final String jspPath = jspPootPath.substring(indexStart);
        TEMPLATE_VARIABLE.put("jspPath", jspPath); // jsp路径

        return TEMPLATE_VARIABLE;
    }
}
