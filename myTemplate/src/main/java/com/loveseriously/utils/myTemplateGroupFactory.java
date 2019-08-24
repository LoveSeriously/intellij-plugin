package com.loveseriously.utils;

import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;

/**
 * @author lw
 * @date 2019-08-24
 */
public class myTemplateGroupFactory implements FileTemplateGroupDescriptorFactory {
    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor("MyTemplate", null);

        group.addTemplate(new FileTemplateDescriptor("myTemplateJava.java"));
        group.addTemplate(new FileTemplateDescriptor("myTemplateJsp.jsp"));

        return group;
    }
}
