package com.cloudting.intellij.plugin.core.configurable;

import com.cloudting.intellij.plugin.core.config.Bundle;
import com.cloudting.intellij.plugin.core.service.ISettingJspDirectoryService;
import com.cloudting.intellij.plugin.core.ui.SettingJspDirectoryUI;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-27
 */
public class SettingJspDirectoryConfigurable implements Configurable {

    private SettingJspDirectoryUI ui;
    private ISettingJspDirectoryService service;

    public SettingJspDirectoryConfigurable() {
        this.service = ISettingJspDirectoryService.getInstance();
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return Bundle.message("fileTemplates.setting.configurable.directory.display.name");
    }

    /**
     *  getHelpTopic返回对应帮助文档中的ID。如果返回值不为空，Help按钮就会呈现
     *
     * @return
     */
    @Nullable
    @Override
    public String getHelpTopic() {
        return Bundle.message("fileTemplates.setting.configurable.directory.help.topic");
    }


    @Nullable
    @Override
    public JComponent createComponent() {
        if (ui == null) {
            ui = new SettingJspDirectoryUI(service);
        }
        return ui.getRootPanel();
    }

    /**
     * Form会定期调用isModified 函数更新component状态。 如果函数返回 false,Apply 按钮 将不会生效
     *
     * @return
     */
    @Override
    public boolean isModified() {
        return ui != null && ui.isModified(service);
    }

    @Override
    public void apply() throws ConfigurationException {
        if (ui != null) {
            ui.getData(service);
        }
    }

    /**
     *  当form被实例化后，用户点击Cancel按钮, reset 函数将会被调用， form的文本框中的值将会被重置。
     *
     */
    @Override
    public void reset() {
        if (ui != null) {
            ui.setData(service);
        }
    }

    /**
     * 当用户关闭Form时，disposeUIResources 函数将会被调用
     */
    @Override
    public void disposeUIResources() {
        ui = null;
    }
}
