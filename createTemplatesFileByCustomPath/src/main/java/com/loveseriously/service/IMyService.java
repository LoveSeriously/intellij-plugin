package com.loveseriously.service;

import com.intellij.openapi.components.ServiceManager;

/**
 * @author lw
 * @date 2019-08-22
 */
public interface IMyService {
    static IMyService getInstance() {
        return ServiceManager.getService(IMyService.class);
    }

    String getEitContent();

    void setEitContent(final String eitContent);
}
