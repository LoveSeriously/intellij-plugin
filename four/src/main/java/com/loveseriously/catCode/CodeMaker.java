package com.loveseriously.catCode;

import com.intellij.openapi.components.ApplicationComponent;

/**
 * @author lw
 * @date 2019-08-20
 */
public class CodeMaker implements ApplicationComponent {
    public CodeMaker() {
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    public String getComponentName() {
        return "com.xiaohansong.codemaker.CodeMaker";
    }
}
