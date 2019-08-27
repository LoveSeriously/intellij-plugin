package com.loveSeriously.service;

import com.intellij.openapi.components.ServiceManager;

/**
 * @author lw
 * @date 2019-08-27
 */
public interface DirPathService {
    static DirPathService getInstance() {
        return ServiceManager.getService(DirPathService.class);
    }

    String getTextWebDir();

    void setTextWebDir(String textWebDir);
}
