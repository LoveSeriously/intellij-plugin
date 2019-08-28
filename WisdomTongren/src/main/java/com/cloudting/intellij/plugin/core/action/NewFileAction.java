package com.cloudting.intellij.plugin.core.action;

import com.cloudting.intellij.plugin.core.config.Bundle;
import com.cloudting.intellij.plugin.core.config.Icons;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 * @date 2019-08-27
 */
public class NewFileAction extends NewFileActionsBase {

    public NewFileAction() {
        super(Bundle.message("newfile.menu.action.text"), Bundle.message("newfile.menu.action.description"), Icons.PLUGIN);
    }

    @Override
    protected String getCommandName() {
        return Bundle.message("newfile.command.name");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName) {
        return Bundle.message("newfile.menu.action.text");
    }


    @Override
    protected PsiElement[] doCreate(String name, PsiDirectory directory) {

        PsiFile[] jsp = createJsp(directory, name);
        PsiClass[] psiClass = createJava(directory, name);
        List<PsiElement> array = new ArrayList<>();
        for (PsiFile psiFile : jsp) {
            array.add(psiFile);
        }
        for (PsiClass aClass : psiClass) {
            array.add(aClass);
        }

        return array.toArray(new PsiElement[0]);
    }

    @Override
    protected String getDialogPrompt() {
        return Bundle.message("newfile.dialog.prompt");
    }

    @Override
    protected String getDialogTitle() {
        return Bundle.message("newfile.dialog.title");
    }
}
