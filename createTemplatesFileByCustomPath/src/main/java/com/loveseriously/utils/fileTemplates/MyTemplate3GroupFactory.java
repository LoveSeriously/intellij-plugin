package com.loveseriously.utils.fileTemplates;

import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;
import com.loveseriously.config.MyBundle;
import com.loveseriously.config.MyIcons;

/**
 * @author lw
 * @date 2019-08-25
 */
public class MyTemplate3GroupFactory implements FileTemplateGroupDescriptorFactory {

    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor(MyBundle.message("fileTemplates.fileTemplateGroupRoot"), MyIcons.PLUGIN);

        FileTemplateGroupDescriptor groupA = new FileTemplateGroupDescriptor(MyBundle.message("fileTemplates.fileTemplateGroupJava"), null);
        FileTemplateGroupDescriptor groupB = new FileTemplateGroupDescriptor(MyBundle.message("fileTemplates.fileTemplateGroupJsp"), null);

        String[] templateJavaNames = MyTemplatesUtils.TEMPLATE_JAVA_NAME;
        String extA = MyBundle.message("fileTemplates.fileType.java.ext");
        for (String templateJavaName: templateJavaNames) {
            final String fileName = templateJavaName + "." + extA;
            groupA.addTemplate(new FileTemplateDescriptor(fileName));
        }

        String[] templateJspNames = MyTemplatesUtils.TEMPLATE_JSP_NAME;
        String extB = MyBundle.message("fileTemplates.fileType.jsp.ext");
        for (String templateJspName : templateJspNames) {
            final String fileName = templateJspName + "." + extB;
            groupB.addTemplate(new FileTemplateDescriptor(fileName));
        }

        group.addTemplate(groupA);
        group.addTemplate(groupB);

        return group;
    }
}
