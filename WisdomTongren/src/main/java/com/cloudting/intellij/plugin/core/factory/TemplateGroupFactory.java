package com.cloudting.intellij.plugin.core.factory;

import com.cloudting.intellij.plugin.core.config.Bundle;
import com.cloudting.intellij.plugin.core.config.Icons;
import com.cloudting.intellij.plugin.core.utils.fileTemplates.TemplatesUtils;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;

/**
 * @author lw
 * @date 2019-08-27
 */
public class TemplateGroupFactory implements FileTemplateGroupDescriptorFactory {
    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor(Bundle.message("fileTemplates.fileTemplateGroupRoot"), Icons.PLUGIN);

        FileTemplateGroupDescriptor groupA = new FileTemplateGroupDescriptor(Bundle.message("fileTemplates.fileTemplateGroupJava"), null);
        FileTemplateGroupDescriptor groupB = new FileTemplateGroupDescriptor(Bundle.message("fileTemplates.fileTemplateGroupJsp"), null);

        String[] templateJavaNames = TemplatesUtils.TEMPLATE_JAVA_NAME;
        String extA = Bundle.message("fileTemplates.fileType.java.ext");
        for (String templateJavaName: templateJavaNames) {
            final String fileName = templateJavaName + "." + extA;
            groupA.addTemplate(new FileTemplateDescriptor(fileName));
        }

        String[] templateJspNames = TemplatesUtils.TEMPLATE_JSP_NAME;
        String extB = Bundle.message("fileTemplates.fileType.jsp.ext");
        for (String templateJspName : templateJspNames) {
            final String fileName = templateJspName + "." + extB;
            groupB.addTemplate(new FileTemplateDescriptor(fileName));
        }

        group.addTemplate(groupA);
        group.addTemplate(groupB);

        return group;
    }
}
