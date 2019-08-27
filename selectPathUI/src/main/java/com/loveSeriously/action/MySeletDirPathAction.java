// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.loveSeriously.action;

import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.PathChooserDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.impl.welcomeScreen.NewWelcomeScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MySeletDirPathAction extends AnAction implements DumbAware {
  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    final Project project = e.getProject();
    final FileChooserDescriptor descriptor =  new ProjectOnlyFileChooserDescriptor();
    descriptor.putUserData(PathChooserDialog.PREFER_LAST_OVER_EXPLICIT, false);
    FileChooser.chooseFiles(descriptor, project, getPathToSelect(), files -> {
      for (VirtualFile file : files) {
        Messages.showInfoMessage(project, file.getPath(), IdeBundle.message("title.cannot.open.project"));
      }
    });
  }

  @Nullable
  protected VirtualFile getPathToSelect() {
    return VfsUtil.getUserHomeDir();
  }

  @Override
  public boolean startInTransaction() {
    return true;
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    if (NewWelcomeScreen.isNewWelcomeScreen(e)) {
      e.getPresentation().setIcon(AllIcons.Actions.Menu_open);
    }
  }

  private static class ProjectOnlyFileChooserDescriptor extends OpenProjectFileChooserDescriptor {
    ProjectOnlyFileChooserDescriptor() {
      super(false);
      setTitle(IdeBundle.message("title.open.project"));
    }
  }
}