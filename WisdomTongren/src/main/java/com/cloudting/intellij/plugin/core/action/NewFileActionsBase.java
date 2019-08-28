package com.cloudting.intellij.plugin.core.action;

import com.cloudting.intellij.plugin.bean.Ma;
import com.cloudting.intellij.plugin.core.factory.CreateTemplateFactory;
import com.cloudting.intellij.plugin.core.ui.FormattingUI;
import com.intellij.CommonBundle;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-27
 */
public abstract class NewFileActionsBase extends CreateElementActionBase {

    public NewFileActionsBase(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    @NotNull
    @Override
    protected PsiElement[] invokeDialog(Project project, PsiDirectory psiDirectory) {
        MyInputValidator inputValidator = new MyInputValidator(project, psiDirectory);
        FormattingUI formattingUI = new FormattingUI(inputValidator);
        return inputValidator.getCreatedElements();
    }

    @NotNull
    @Override
    protected PsiElement[] create(@NotNull String newName, PsiDirectory psiDirectory) throws Exception {
        return doCreate(newName, psiDirectory);
    }

    @Override
    protected String getErrorTitle() {
        return CommonBundle.getErrorTitle();
    }

    protected PsiFile[] createJsp(final PsiDirectory directory, @NotNull Ma ma) throws IncorrectOperationException {
        return CreateTemplateFactory.createJsp(directory, ma);
    }

    protected PsiClass[] createJava(final PsiDirectory directory, @NotNull Ma ma) throws IncorrectOperationException {
        return CreateTemplateFactory.createClass(directory, ma);
    }

    /**
     * 创造Psi元素数组
     *
     * @param name
     * @param directory
     * @return
     */
    protected abstract PsiElement[] doCreate(String name, PsiDirectory directory);

    /**
     * 得到对话提示
     *
     * @return
     */
    protected abstract String getDialogPrompt();

    /**
     * 获得Dialog标题
     *
     * @return
     */
    protected abstract String getDialogTitle();
}
