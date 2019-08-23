package com.loveseriously.configurable;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.loveseriously.service.IOneService;
import com.loveseriously.ui.OneSettingUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-22
 */
public class ConfigurableOne implements Configurable {

    private IOneService oneService;
    private OneSettingUI oneSettingUI;

    public ConfigurableOne() {
        this.oneService = IOneService.getInstance();
    }

    /**
     * getDisplayName 函数显示该component在设置面板中的名称
     *
     * @return
     */
    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "ConfigurableOne";
    }

    /**
     *  getHelpTopic返回对应帮助文档中的ID。如果返回值不为空，Help按钮就会呈现
     *
     * @return
     */
    @Nullable
    @Override
    public String getHelpTopic() {
        return "UI-1";
    }

    /**
     * 创建一个Form，返回其根元素。
     *
     * @return
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        if (oneSettingUI == null) {
            this.oneSettingUI = new OneSettingUI();
        }
        return oneSettingUI.getRootComponent();
    }

    /**
     * Form会定期调用isModified 函数更新component状态。 如果函数返回 false,Apply 按钮 将不会生效
     *
     * @return
     */
    @Override
    public boolean isModified() {
        return oneSettingUI != null && oneSettingUI.isModified(oneService);
    }

    /**
     * 当用户点击 OK按钮或Apply按钮，apply 函数将会被调用，form中的文本框将自动会更新
     *
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {
        if (oneSettingUI != null) {
            oneSettingUI.getData(oneService);
        }
    }

    /**
     *  当form被实例化后，用户点击Cancel按钮, reset 函数将会被调用， form的文本框中的值将会被重置。
     *
     */
    @Override
    public void reset() {
        if (oneSettingUI != null) {
            oneSettingUI.setData(oneService);
        }
    }

    /**
     * 当用户关闭Form时，disposeUIResources 函数将会被调用
     */
    @Override
    public void disposeUIResources() {
        oneSettingUI = null;
    }
}
