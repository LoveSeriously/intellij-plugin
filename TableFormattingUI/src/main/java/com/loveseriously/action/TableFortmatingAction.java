package com.loveseriously.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.loveseriously.ui.FormattingUI;

/**
 * @author lw
 * @date 2019-08-27
 */
public class TableFortmatingAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        FormattingUI dialog = new FormattingUI(this);
    }
}
