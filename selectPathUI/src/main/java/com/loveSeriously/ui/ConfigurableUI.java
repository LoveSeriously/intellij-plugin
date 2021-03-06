package com.loveSeriously.ui;

import com.intellij.ide.IdeBundle;
import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.PathChooserDialog;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.loveSeriously.service.DirPathService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * @author lw
 * @date 2019-08-26
 */
public class ConfigurableUI {
    private DirPathService service;

    private JPanel rootPanel;
    private JLabel webDir;
    private JTextField textWebDir;
    private JButton selectDirBtn;

    public ConfigurableUI(DirPathService service) {
        this.service = service;
        this.init();
    }

    private void init() {
        event();
    }

    private void event() {
        selectDirBtn.addActionListener(e -> {
            final FileChooserDescriptor descriptor =  new ProjectOnlyFileChooserDescriptor();
            descriptor.putUserData(PathChooserDialog.PREFER_LAST_OVER_EXPLICIT, false);
            FileChooser.chooseFiles(descriptor, null, getPathToSelect(), files -> {
                for (VirtualFile file : files) {
                    textWebDir.setText(file.getPath());
                }
            });
        });
    }

    private void createUIComponents() {
        rootPanel = new JPanel();
        textWebDir = new JTextField();
        selectDirBtn = new JButton();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1, true, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        rootPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        webDir = new JLabel();
        webDir.setText("web目录:");
        panel1.add(webDir, BorderLayout.WEST);
        textWebDir.setColumns(0);
        panel1.add(textWebDir, BorderLayout.CENTER);
        selectDirBtn.setHorizontalTextPosition(2);
        selectDirBtn.setText("选择目录");
        selectDirBtn.putClientProperty("hideActionText", Boolean.TRUE);
        selectDirBtn.putClientProperty("html.disable", Boolean.FALSE);
        panel1.add(selectDirBtn, BorderLayout.EAST);
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    public boolean isModified(DirPathService service) {
        String text = textWebDir.getText();
        String textWebDir = service.getTextWebDir();
        boolean b = !text.equals(textWebDir);
        boolean b1 = service.getTextWebDir() != null;
        boolean flg = text != null ? b : b1;
        return flg;
    }

    public void getData(@NotNull DirPathService service) {
        service.setTextWebDir(this.textWebDir.getText());
    }

    public void setData(@NotNull DirPathService service) {
        textWebDir.setText(service.getTextWebDir());
    }

    @Nullable
    protected VirtualFile getPathToSelect() {
        return VfsUtil.getUserHomeDir();
    }

    private static class ProjectOnlyFileChooserDescriptor extends OpenProjectFileChooserDescriptor {
        ProjectOnlyFileChooserDescriptor() {
            super(false);
            setTitle(IdeBundle.message("title.open.project"));
        }
    }
}
