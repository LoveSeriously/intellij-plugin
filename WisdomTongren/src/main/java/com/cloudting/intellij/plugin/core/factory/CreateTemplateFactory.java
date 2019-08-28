package com.cloudting.intellij.plugin.core.factory;

import com.cloudting.intellij.plugin.core.config.Bundle;
import com.cloudting.intellij.plugin.core.utils.fileTemplates.TemplatesUtils;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.ide.fileTemplates.actions.CreateFromTemplateActionBase;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Properties;

/**
 * @author lw
 * @date 2019-08-27
 */
public class CreateTemplateFactory {
    private static final Logger LOG = Logger.getInstance("#com.cloudting.intellij.plugin.core.factory.CreateTemplateFactory");

    public static PsiClass[] createClass(@NotNull PsiDirectory dir, String name) {
        return createClassFromTemplate(dir, name);
    }

    public static PsiFile[] createJsp(@NotNull PsiDirectory dir, @NotNull String name) throws IncorrectOperationException {
        return createJspFromTemplate(dir, name);
    }

    private static PsiFile[] createJspFromTemplate(@NotNull PsiDirectory dir, String name) throws IncorrectOperationException {
        String[] templateNames = TemplatesUtils.TEMPLATE_JSP_NAME;
        PsiFile[] psiFiles = new PsiFile[templateNames.length];
        Map<String, Object> templateJspVariable = TemplatesUtils.getVariable(name);
        for (int i = 0; i < templateNames.length; i ++) {
            PsiFile jsp = createJspFromTemplate(dir, name, templateNames[i], false, templateJspVariable);
            psiFiles[i] = jsp;
        }
        return psiFiles;
    }

    private static PsiClass[] createClassFromTemplate(@NotNull PsiDirectory dir, String name) {
        String[] templateNames = TemplatesUtils.TEMPLATE_JAVA_NAME;
        PsiClass[] psiClasses = new PsiClass[templateNames.length];
        Map<String, Object> templateJavaVariable = TemplatesUtils.getVariable(name);
        for (int i = 0; i < templateNames.length; i ++) {
            PsiClass psiClass = createClassFromTemplate(dir, name, templateNames[i], false, templateJavaVariable);
            psiClasses[i] = psiClass;
        }
        return psiClasses;

    }

    private static PsiFile createJspFromTemplate(@NotNull PsiDirectory dir, String name, String templateName, boolean askToDefineVariables, @NotNull Map<String, Object> additionalProperties) throws IncorrectOperationException {

        if (askToDefineVariables) {
            LOG.assertTrue(!ApplicationManager.getApplication().isWriteAccessAllowed());
        }
        Project project = dir.getProject();
        FileTemplate template = FileTemplateManager.getInstance(project).getInternalTemplate(templateName);

        Properties defaultProperties = FileTemplateManager.getInstance(project).getDefaultProperties();
        Properties properties = new Properties(defaultProperties);
        properties.setProperty(FileTemplate.ATTRIBUTE_NAME, name);
        FileTemplateUtil.putAll(additionalProperties, properties);

        String ext = Bundle.message("fileTemplates.fileType.jsp.ext");
        String fileName = name + "_" + templateName + "." + ext;


        String path = String.format("%s/%s", dir.getVirtualFile().getPath(), name);
        dir = DirectoryUtil.mkdirs(PsiManager.getInstance(project), path);
        PsiElement element;
        try {
            element = FileTemplateUtil.createFromTemplate(template, fileName, additionalProperties, dir, null);
        }
        catch (IncorrectOperationException e) {
            throw e;
        }
        catch (Exception e) {
            LOG.error(e);
            return null;
        }
        if (element == null) return null;
        PsiFile psiFile = element.getContainingFile();
        return psiFile;
    }

    private static PsiClass createClassFromTemplate(@NotNull PsiDirectory dir, String name, String templateName, boolean askToDefineVariables, @NotNull Map<String, Object> additionalProperties) throws IncorrectOperationException {
        if (askToDefineVariables) {
            LOG.assertTrue(!ApplicationManager.getApplication().isWriteAccessAllowed());
        }

        Project project = dir.getProject();
        FileTemplate template = FileTemplateManager.getInstance(project).getInternalTemplate(templateName);

        Properties defaultProperties = FileTemplateManager.getInstance(project).getDefaultProperties();
        Properties properties = new Properties(defaultProperties);
        properties.setProperty(FileTemplate.ATTRIBUTE_NAME, name);
        FileTemplateUtil.putAll(additionalProperties, properties);

        String ext = Bundle.message("fileTemplates.fileType.java.ext");
        String fileName = name + "." + ext;

        if (templateName.contains("Impl")) {
            templateName = templateName.replaceAll("Impl","");
            String path = String.format("%s/%s/%s/Impl/", dir.getVirtualFile().getPath(), name, templateName);
            dir = DirectoryUtil.mkdirs(PsiManager.getInstance(project), path);
        } else {
            String path = String.format("%s/%s/%s/", dir.getVirtualFile().getPath(), name, templateName);
            dir = DirectoryUtil.mkdirs(PsiManager.getInstance(project), path);
        }

        PsiElement element;
        try {
            element = FileTemplateUtil.createFromTemplate(template, fileName, additionalProperties, dir, null);
        }
        catch (IncorrectOperationException e) {
            throw e;
        }
        catch (Exception e) {
            LOG.error(e);
            return null;
        }
        if (element == null) return null;
        final PsiJavaFile file = (PsiJavaFile)element.getContainingFile();
        PsiClass[] classes = file.getClasses();
        if (classes.length < 1) {
            throw new IncorrectOperationException(getIncorrectTemplateMessage(templateName, project));
        }
        if (template.isLiveTemplateEnabled()) {
            CreateFromTemplateActionBase.startLiveTemplate(file);
        }
        return classes[0];
    }

    private static String getIncorrectTemplateMessage(String templateName, Project project) {
        return PsiBundle.message("psi.error.incorrect.class.template.message", FileTemplateManager.getInstance(project).internalTemplateToSubject(templateName), templateName);
    }
}
