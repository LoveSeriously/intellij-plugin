package com.loveSeriously.configurable;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.loveSeriously.service.DirPathService;
import com.loveSeriously.ui.ConfigurableUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-26
 */
public class UIConfigurableMy implements Configurable {

    private ConfigurableUI ui;
    private DirPathService service;

    public UIConfigurableMy() {
        this.service = DirPathService.getInstance();
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "UI";
    }

    /**
     *  getHelpTopic返回对应帮助文档中的ID。如果返回值不为空，Help按钮就会呈现
     *
     * @return
     */
    @Nullable
    @Override
    public String getHelpTopic() {
        return "UI";
    }


    @Nullable
    @Override
    public JComponent createComponent() {
        if (ui == null) {
             ui = new ConfigurableUI(service);
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
        boolean flg = ui != null && ui.isModified(service);
        return flg;
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

