package com.intellij.openapi.vfs.encoding;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableProvider;
import com.intellij.openapi.project.Project;


/**
 * @author lw
 * @date 2019-08-26
 */
public class MyFileEncodingConfigurableProvider extends ConfigurableProvider {
    private final Project myProject;

    public MyFileEncodingConfigurableProvider(Project project) {
        myProject = project;
    }

    @Override
    public Configurable createConfigurable() {
        return new MyFileEncodingConfigurable(myProject);
    }
}
