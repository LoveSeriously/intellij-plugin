package com.loveseriously.catCode;

import com.intellij.openapi.util.text.StringUtil;

/**
 * @author lw
 * @date 2019-08-20
 */
public class CodeTemplate {
    /**
     * 代码模板名称，英文命名
     */
    private String name;

    /**
     * 生成代码的类名称(vm格式)
     */
    private String classNameVm;

    /**
     * vm格式代码模板
     */
    private String codeTemplate;

    /**
     * 导入vm上下文的类数量
     */
    private int    classNumber;

    public CodeTemplate() {
    }

    public CodeTemplate(String name, String classNameVm, String codeTemplate, int classNumber) {
        this.name = name;
        this.classNameVm = classNameVm;
        this.codeTemplate = codeTemplate;
        this.classNumber = classNumber;
    }

    public boolean isValid() {
        return StringUtil.isNotEmpty(getClassNameVm()) && StringUtil.isNotEmpty(getName())
                && StringUtil.isNotEmpty(getCodeTemplate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CodeTemplate that = (CodeTemplate) o;

        if (classNumber != that.classNumber)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (classNameVm != null ? !classNameVm.equals(that.classNameVm) : that.classNameVm != null)
            return false;
        return codeTemplate != null ? codeTemplate.equals(that.codeTemplate)
                : that.codeTemplate == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (classNameVm != null ? classNameVm.hashCode() : 0);
        result = 31 * result + (codeTemplate != null ? codeTemplate.hashCode() : 0);
        result = 31 * result + classNumber;
        return result;
    }

    public static final CodeTemplate EMPTY_TEMPLATE = new CodeTemplate("", "", "", 1);

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>codeTemplate</tt>.
     *
     * @return property value of codeTemplate
     */
    public String getCodeTemplate() {
        return codeTemplate;
    }

    /**
     * Setter method for property <tt>codeTemplate</tt>.
     *
     * @param codeTemplate value to be assigned to property codeTemplate
     */
    public void setCodeTemplate(String codeTemplate) {
        this.codeTemplate = codeTemplate;
    }

    /**
     * Getter method for property <tt>classNumber</tt>.
     *
     * @return property value of classNumber
     */
    public int getClassNumber() {
        return classNumber;
    }

    /**
     * Setter method for property <tt>classNumber</tt>.
     *
     * @param classNumber value to be assigned to property classNumber
     */
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    /**
     * Getter method for property <tt>classNameVm</tt>.
     *
     * @return property value of classNameVm
     */
    public String getClassNameVm() {
        return classNameVm;
    }

    /**
     * Setter method for property <tt>classNameVm</tt>.
     *
     * @param classNameVm value to be assigned to property classNameVm
     */
    public void setClassNameVm(String classNameVm) {
        this.classNameVm = classNameVm;
    }
}
