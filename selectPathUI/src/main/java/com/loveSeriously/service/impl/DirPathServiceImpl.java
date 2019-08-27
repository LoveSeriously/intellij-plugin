package com.loveSeriously.service.impl;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.loveSeriously.service.DirPathService;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author lw
 * @date 2019-08-27
 */
@State(name = "name", storages = {@Storage(exclusive = true, value = "$APP_CONFIG$/webDir.xml")})
public class DirPathServiceImpl implements DirPathService, PersistentStateComponent<Element> {

    private String textWebDir;


    @Override
    public String getTextWebDir() {
        return textWebDir;
    }

    @Override
    public void setTextWebDir(String textWebDir) {
        this.textWebDir = textWebDir;
    }

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("webDir");
        element.setAttribute("path", this.getTextWebDir());
        return element;
    }

    @Override
    public void loadState(@NotNull Element state) {
        setTextWebDir(state.getAttributeValue("path"));
    }
}
