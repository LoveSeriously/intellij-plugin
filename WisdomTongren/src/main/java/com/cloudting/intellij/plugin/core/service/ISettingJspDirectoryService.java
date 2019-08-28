package com.cloudting.intellij.plugin.core.service;

import com.intellij.openapi.components.ServiceManager;

/**
 * @author lw
 * @date 2019-08-27
 */
public interface ISettingJspDirectoryService {
    static ISettingJspDirectoryService getInstance() {
        return ServiceManager.getService(ISettingJspDirectoryService.class);
    }

    String getTextWebDir();

    void setTextWebDir(String textWebDir);
}
