package com.loveseriously.configurable;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.loveseriously.service.IThreeService;
import com.loveseriously.ui.ThreeUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-23
 */
public class ConfigurableThree implements Configurable {

    private ThreeUI threeUI;
    private IThreeService threeService;

    public ConfigurableThree(@NotNull Project project)  {
        threeService = IThreeService.getInstance(project);
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "ConfigurableThree";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "ConfigurableThree";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (threeUI == null) {
            threeUI = new ThreeUI();
        }
        return threeUI.$$$getRootComponent$$$();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
