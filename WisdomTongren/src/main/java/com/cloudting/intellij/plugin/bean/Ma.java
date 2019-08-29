package com.cloudting.intellij.plugin.bean;

import java.util.List;

/**
 * @author lw
 * @date 2019-08-28
 */
public class Ma {
    private String name;
    private String tableName;
    private String description;
    private List<DocumentsBean> documentsBeans;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DocumentsBean> getDocumentsBeans() {
        return documentsBeans;
    }

    public void setDocumentsBeans(List<DocumentsBean> documentsBeans) {
        this.documentsBeans = documentsBeans;
    }
}
