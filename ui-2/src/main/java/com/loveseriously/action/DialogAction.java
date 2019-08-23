package com.loveseriously.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.loveseriously.ui.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author lw
 * @date 2019-08-23
 */
public class DialogAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Dialog dialog = new Dialog();
        dialog.pack();//调整窗口的大小，使其适应组件的大小和布局。如果该窗口或其所有者仍不可显示，则两者在计算首选大小之前变得可显示。在计算首选大小之后，将会验证该Window。窗口自动适应大小，使窗口能正好显示里面所有的控件。
        dialog.setVisible(true);//设置显示窗口
        dialog.setLocationRelativeTo(null);// 把窗口位置设置到屏幕的中心
        dialog.setSize(800,400);
    }
}
