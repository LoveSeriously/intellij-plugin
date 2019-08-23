package com.loveseriously;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author lw
 * @date 2019-08-12
 */
public interface TT {
    static TT getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, TT.class);
    }
}
