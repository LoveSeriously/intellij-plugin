package com.loveseriously.configurable;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.loveseriously.service.ITwoService;
import com.loveseriously.ui.TwoSettingUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * This ProjectConfigurable class appears on Settings dialog,
 * to let user to configure this plugin's behavior.
 */
public class ConfigurableTwo implements SearchableConfigurable {

    private ITwoService twoService;
    private TwoSettingUI twoSettingUI;

    public ConfigurableTwo() {
        Application application = ApplicationManager.getApplication();
    }

    /**
     * getDisplayName 函数显示该component在设置面板中的名称
     *
     * @return
     */
    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "ConfigurableTwo";
    }
    @NotNull

    /**
     * 还回id
     */
    @Override
    public String getId() {
        return "ConfigurableTwo";
    }

    /**
     * getHelpTopic返回对应帮助文档中的ID。如果返回值不为空，Help按钮就会呈现
     *
     * @return
     */
    @Nullable
    @Override
    public String getHelpTopic() {
        return "ConfigurableTwo";
    }

    /**
     * 创建一个Form，返回其根元素。
     *
     * @return
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        if (twoSettingUI == null) {
            twoSettingUI = new TwoSettingUI();
        }
        return twoSettingUI.$$$getRootComponent$$$();
    }

    /**
     *  Form会定期调用isModified 函数更新component状态。 如果函数返回 false,Apply 按钮 将不会生效
     *
     * @return
     */
    @Override
    public boolean isModified() {
        return false;
    }

    /**
     * 当用户点击 OK按钮或Apply按钮，apply 函数将会被调用
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {

    }

    /**
     * 当form被实例化后，用户点击Cancel按钮, reset 函数将会被调用
     */
    @Override
    public void reset() {

    }

    /**
     * 当用户关闭Form时，disposeUIResources 函数将会被调用
     */
    @Override
    public void disposeUIResources() {

    }
}
