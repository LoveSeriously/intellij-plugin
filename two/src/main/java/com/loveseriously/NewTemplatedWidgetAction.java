package com.loveseriously;

import com.intellij.ide.fileTemplates.*;
import com.intellij.ide.fileTemplates.impl.FileTemplateBase;
import com.intellij.ide.fileTemplates.impl.FileTemplateHighlighter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.SaveProjectAsTemplateAction;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.refactoring.introduceVariable.InputValidator;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author lw
 * @date 2019-08-20
 */
public class NewTemplatedWidgetAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final VirtualFile virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        final VirtualFile directory;
        if (virtualFile.isDirectory()) {
            directory = virtualFile;
        } else {
            directory = virtualFile.getParent();
        }
        final PsiDirectory psiDirectory = PsiManager.getInstance(e.getProject()).findDirectory(directory);
        final FileTemplate template1 = FileTemplateManager.getDefaultInstance().getTemplate("TestClass.java");;
        final FileTemplate template2 = FileTemplateManager.getDefaultInstance().getTemplate("TestJsp.jsp");;
//        FileTemplate[] allCodeTemplates = FileTemplateManager.getDefaultInstance().getAllCodeTemplates();
//        for (FileTemplate allCodeTemplate : allCodeTemplates) {
//            System.out.println("allCodeTemplate = " + allCodeTemplate.getName());
//        }
        FileTemplate[] allJ2eeTemplates = FileTemplateManager.getDefaultInstance().getAllJ2eeTemplates();
        for (FileTemplate allJ2eeTemplate : allJ2eeTemplates) {
            System.out.println("allJ2eeTemplate = " + allJ2eeTemplate.getName());
            System.out.println("allJ2eeTemplate.getExtension = " + allJ2eeTemplate.getExtension());
            System.out.println("allJ2eeTemplate.getDescription = " + allJ2eeTemplate.getDescription());
            FileTemplate j2eeTemplate = FileTemplateManager.getDefaultInstance().getJ2eeTemplate(allJ2eeTemplate.getName());
            System.out.println("=====================================================================");
            System.out.println("j2eeTemplate.getDescription = " + j2eeTemplate.getDescription());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
//        FileTemplate[] allTemplates = FileTemplateManager.getDefaultInstance().getAllTemplates();
//        for (FileTemplate allTemplate : allTemplates) {
//            System.out.println("allTemplate = " + allTemplate.getName());
//        }
//        FileTemplate[] allPatterns = FileTemplateManager.getDefaultInstance().getAllPatterns();
//        for (FileTemplate allPattern : allPatterns) {
//            System.out.println("allPattern = " + allPattern.getName());
//        }
//        FileTemplate[] internalTemplates = FileTemplateManager.getDefaultInstance().getInternalTemplates();
//        for (FileTemplate internalTemplate : internalTemplates) {
//            System.out.println("internalTemplate.getName() = " + internalTemplate.getName());
//        }
//        String name = FileTemplateManager.getDefaultInstance().getCurrentScheme().getName();
//        System.out.println("name = " + name);

        final PsiElement templateFile;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("TEST", UUID.class);
            FileTemplateUtil.createFromTemplate(template1, "test", map, psiDirectory, NewTemplatedWidgetAction.class.getClassLoader());
            FileTemplateUtil.createFromTemplate(template2, "TestJsp", map, psiDirectory, NewTemplatedWidgetAction.class.getClassLoader());
            //templateFile.getContainingFile().navigate(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
