package com.cloudting.intellij.plugin.utils;

import com.cloudting.intellij.plugin.bean.DocumentsBean;
import com.cloudting.intellij.plugin.core.config.Bundle;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author lw
 * @date 2019-08-28
 */
public class ResolverUtils {
    // 根据文档[名字	字段	类型	长度	备注] 获取 解析表格式对象
    public static List<DocumentsBean> getDocumentsBeans(@NotNull String docStr) {
        List<DocumentsBean> list = new ArrayList<DocumentsBean>();
        String[] result = docStr.split("\n");
        int length = result.length;
        for (int i = 0; i < length; i++) {
            // Go to a blank line
            if (result[i].trim().isEmpty()) {
                continue;
            }
            DocumentsBean documentsBean = new DocumentsBean();
            String[] split = result[i].split("\t");
            int len = split.length;
            // 名字
            if (len >= 1) {
                documentsBean.setName(split[0].trim());
            }
            // 字段
            if (len >= 2) {
                documentsBean.setField(split[1].trim());
            }
            // 类型
            if (len >= 3) {
                List<Map<String, String>> dateType = getDateType(Bundle.message("file.path.dateType"));
                for (Map<String, String> map : dateType) {
                    if (map.containsKey(split[2])) {
                        documentsBean.setType(map.get(split[2].trim()));
                    }
                }
            }
            // 长度
            if (len >= 4) {
                documentsBean.setLength(split[3].trim());
            }
            // 备注
            if (len >= 5) {
                documentsBean.setRemark(split[4]);
            }
            list.add(documentsBean);
        }
        return list;
    }

    private static List<Map<String, String>> getDateType(String path) {
        List<Map<String, String>> list = new ArrayList<>();

        //读取属性文件a.properties
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(ResolverUtils.class.getClassLoader().getResourceAsStream(path));
            prop.load(in);
            ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> map = new HashMap<>();
                map.put(key, prop.getProperty(key));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
