package com.cloudting.intellij.plugin.bean;

/**
 * @author lw
 * @date 2019-08-28
 */
public class DocumentsBean {
    private String name;                // 名字
    private String field;               // 字段
    private String type;                // 类型
    private String length;              // 长度
    private String remark;              // 备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
