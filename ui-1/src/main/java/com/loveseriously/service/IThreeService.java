package com.loveseriously.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author lw
 * @date 2019-08-23
 */
public interface IThreeService {
    static IThreeService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, IThreeService.class);
    }
}
