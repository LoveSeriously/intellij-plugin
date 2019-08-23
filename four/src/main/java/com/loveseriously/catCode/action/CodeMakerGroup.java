package com.loveseriously.catCode.action;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.loveseriously.catCode.setting.CodeMakerSettings;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 * @date 2019-08-20
 */
public class CodeMakerGroup  extends ActionGroup implements DumbAware {
    private CodeMakerSettings settings;

    public CodeMakerGroup() {
        settings = ServiceManager.getService(CodeMakerSettings.class);
    }

    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent anActionEvent) {
        if (anActionEvent == null) {
            return AnAction.EMPTY_ARRAY;
        }
        Project project = PlatformDataKeys.PROJECT.getData(anActionEvent.getDataContext());
        if (project == null) {
            return AnAction.EMPTY_ARRAY;
        }
        final List<AnAction> children = new ArrayList<>();
        settings.getCodeTemplates().forEach((key, value) -> children.add(getOrCreateAction(key)));

        return children.toArray(new AnAction[children.size()]);
    }

    private AnAction getOrCreateAction(String templateName) {
        final String actionId = "CodeMaker.Menu.Action." + templateName;
        AnAction action = ActionManager.getInstance().getAction(actionId);
        if (action == null) {
            action = new CodeMakerAction(templateName);
            ActionManager.getInstance().registerAction(actionId, action);
        }
        return action;
    }
}
