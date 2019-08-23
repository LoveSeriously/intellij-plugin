package com.loveseriously.catCode.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;

/**
 * @author lw
 * @date 2019-08-20
 */
public class CodeMakerAction extends EditorAction {

    public CodeMakerAction() {
        super(null);
    }

    public CodeMakerAction(String name) {
        super(new CodeMakerActionHandler(name));
        getTemplatePresentation().setDescription("description");
        getTemplatePresentation().setText(name, false);
    }

    protected CodeMakerAction(EditorActionHandler defaultHandler) {
        super(defaultHandler);
    }
}
