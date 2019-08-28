package com.cloudting.intellij.plugin.core.action;

import com.intellij.ide.IdeView;
import com.intellij.ide.actions.CreateInDirectoryActionBase;
import com.intellij.ide.actions.ElementCreator;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.WriteActionAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-28
 */
public abstract class CreateElementActionBase  extends CreateInDirectoryActionBase implements WriteActionAware {

    protected CreateElementActionBase() {
    }

    protected CreateElementActionBase(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    /**
     * @return created elements. Never null.
     */
    @NotNull
    protected abstract PsiElement[] invokeDialog(Project project, PsiDirectory directory);

    /**
     * @return created elements. Never null.
     */
    @NotNull
    protected abstract PsiElement[] create(@NotNull String newName, PsiDirectory directory) throws Exception;

    protected abstract String getErrorTitle();

    protected abstract String getCommandName();

    protected abstract String getActionName(PsiDirectory directory, String newName);

    @Override
    public final void actionPerformed(@NotNull final AnActionEvent e) {
        final IdeView view = getIdeView(e);
        if (view == null) {
            return;
        }
        final Project project = e.getProject();
        final PsiDirectory dir = view.getOrChooseDirectory();
        if (dir == null) return;
        final PsiElement[] createdElements = invokeDialog(project, dir);

//        for (PsiElement createdElement : createdElements) {
//            view.selectElement(createdElement);
//        }
    }

    @Nullable
    protected IdeView getIdeView(@NotNull AnActionEvent e) {
        return e.getData(LangDataKeys.IDE_VIEW);
    }

    public static String filterMessage(String message) {
        if (message == null) return null;
        @NonNls final String ioExceptionPrefix = "java.io.IOException:";
        message = StringUtil.trimStart(message, ioExceptionPrefix);
        return message;
    }

    public class MyInputValidator extends ElementCreator implements InputValidator {
        private final PsiDirectory myDirectory;
        private PsiElement[] myCreatedElements = PsiElement.EMPTY_ARRAY;

        public MyInputValidator(final Project project, final PsiDirectory directory) {
            super(project, getErrorTitle());
            myDirectory = directory;
        }

        public PsiDirectory getDirectory() {
            return myDirectory;
        }

        @Override
        public boolean checkInput(final String inputString) {
            return true;
        }

        @Override
        public PsiElement[] create(@NotNull String newName) throws Exception {
            return CreateElementActionBase.this.create(newName, myDirectory);
        }

        @Override
        public boolean startInWriteAction() {
            return CreateElementActionBase.this.startInWriteAction();
        }

        @Override
        public String getActionName(String newName) {
            return CreateElementActionBase.this.getActionName(myDirectory, newName);
        }

        @Override
        public boolean canClose(final String inputString) {
            myCreatedElements = tryCreate(inputString);
            return myCreatedElements.length > 0;
        }

        public final PsiElement[] getCreatedElements() {
            return myCreatedElements;
        }
    }
}