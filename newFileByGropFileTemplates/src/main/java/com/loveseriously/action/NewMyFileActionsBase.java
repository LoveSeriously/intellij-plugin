package com.loveseriously.action;

import com.intellij.CommonBundle;
import com.intellij.ide.actions.CreateElementActionBase;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import com.loveseriously.utils.fileTemplates.MyCreateTemplateFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * 用于创建新文件元素的操作的基类。
 * @author lw
 * @date 2019-08-25
 */
public abstract class NewMyFileActionsBase extends CreateElementActionBase {

    /**
     *
     * @param text menu 名字
     * @param description 说明
     * @param icon 图标
     */
    public NewMyFileActionsBase(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    @NotNull
    @Override
    protected PsiElement[] invokeDialog(Project project, PsiDirectory psiDirectory) {
        MyInputValidator inputValidator = new MyInputValidator(project, psiDirectory);
        Messages.showInputDialog(project, getDialogPrompt(), getDialogTitle(), null, "", inputValidator);
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

    protected PsiFile[] createJsp(final PsiDirectory directory, String name) throws IncorrectOperationException {
        return MyCreateTemplateFactory.createJsp(directory, name);
    }

    protected PsiClass[] createJava(final PsiDirectory directory, String name) throws IncorrectOperationException {
        return MyCreateTemplateFactory.createClass(directory, name);
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
