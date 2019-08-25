package com.loveseriously.action;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.loveseriously.config.MyBundle;
import com.loveseriously.config.MyIcons;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 * @date 2019-08-25
 */
public class NewMyFileActions extends NewMyFileActionsBase {

    public NewMyFileActions() {
        super(MyBundle.message("newfile.menu.action.text"), MyBundle.message("newfile.menu.action.description"), MyIcons.PLUGIN);
    }

    @Override
    protected String getCommandName() {
        return MyBundle.message("newfile.command.name");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName) {
        return MyBundle.message("newfile.menu.action.text");
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
        return MyBundle.message("newfile.dialog.prompt");
    }

    @Override
    protected String getDialogTitle() {
        return MyBundle.message("newfile.dialog.title");
    }
}
