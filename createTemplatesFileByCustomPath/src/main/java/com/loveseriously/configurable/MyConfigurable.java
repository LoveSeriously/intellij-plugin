package com.loveseriously.configurable;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.loveseriously.config.MyBundle;
import com.loveseriously.service.IMyService;
import com.loveseriously.ui.MySettingUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-22
 */
public class MyConfigurable implements Configurable {

    private IMyService oneService;
    private MySettingUI mySettingUI;

    public MyConfigurable() {
        this.oneService = IMyService.getInstance();
    }

    /**
     * getDisplayName 函数显示该component在设置面板中的名称
     *
     * @return
     */
    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return MyBundle.message("configurable.displayName");
    }

    /**
     *  getHelpTopic返回对应帮助文档中的ID。如果返回值不为空，Help按钮就会呈现
     *
     * @return
     */
    @Nullable
    @Override
    public String getHelpTopic() {
        return MyBundle.message("configurable.helpToPic");
    }

    /**
     * 创建一个Form，返回其根元素。
     *
     * @return
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        if (mySettingUI == null) {
            this.mySettingUI = new MySettingUI();
        }
        return mySettingUI.getRootComponent();
    }

    /**
     * Form会定期调用isModified 函数更新component状态。 如果函数返回 false,Apply 按钮 将不会生效
     *
     * @return
     */
    @Override
    public boolean isModified() {
        return mySettingUI != null && mySettingUI.isModified(oneService);
    }

    /**
     * 当用户点击 OK按钮或Apply按钮，apply 函数将会被调用，form中的文本框将自动会更新
     *
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {
        if (mySettingUI != null) {
            mySettingUI.getData(oneService);
        }
    }

    /**
     *  当form被实例化后，用户点击Cancel按钮, reset 函数将会被调用， form的文本框中的值将会被重置。
     *
     */
    @Override
    public void reset() {
        if (mySettingUI != null) {
            mySettingUI.setData(oneService);
        }
    }

    /**
     * 当用户关闭Form时，disposeUIResources 函数将会被调用
     */
    @Override
    public void disposeUIResources() {
        mySettingUI = null;
    }
}
