package com.loveseriously.service;

import com.intellij.openapi.components.ServiceManager;

/**
 * @author lw
 * @date 2019-08-22
 */
public interface IOneService {
    static IOneService getInstance() {
        return ServiceManager.getService(IOneService.class);
    }

    String getEitContent();

    void setEitContent(final String eitContent);
}
