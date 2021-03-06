package com.loveseriously.service.impl;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.loveseriously.configurable.ConfigurableOne;
import com.loveseriously.service.IOneService;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author lw
 * @date 2019-08-22
 */
@State(name = "ConfigurableOne", storages = {@Storage(exclusive = true, value = "$APP_CONFIG$/testUI.xml")})
public class IOneServiceImpl implements IOneService , PersistentStateComponent<Element>{
    private String eitContent;

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("IOneServiceImpl");
        element.setAttribute("content", this.getEitContent());
        return element;
    }

    @Override
    public void loadState(@NotNull Element state) {
        this.setEitContent(state.getAttributeValue("content"));
    }


    public String getEitContent() {
        return eitContent == null ? "这是初始化测试内容！" : eitContent;
    }

    public void setEitContent(final String eitContent) {
        this.eitContent = eitContent;
    }
}
