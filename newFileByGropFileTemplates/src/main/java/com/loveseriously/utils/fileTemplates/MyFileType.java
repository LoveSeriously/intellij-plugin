package com.loveseriously.utils.fileTemplates;


import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.loveseriously.config.MyBundle;
import com.loveseriously.config.MyIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-25
 */
public class MyFileType extends LanguageFileType {
    private static final Logger LOG = Logger.getInstance("#com.loveseriously.utils.fileTemplates.MyFileType");

    public static final MyFileType JAVA_INSTANCE = new MyFileType(JavaLanguage.INSTANCE);
    public static final MyFileType JSP_INSTANCE = new MyFileType();

    private MyFileType() {
        super(null);
    }

    /**
     * 为指定的语言创建语言文件类型。
     *
     * @param language 该类型的文件中使用的语言。
     */
    private MyFileType(@NotNull Language language) {
        super(language);
    }

    @NotNull
    @Override
    public String getName() {
        return MyBundle.message("fileTemplates.fileType.name");
    }

    @NotNull
    @Override
    public String getDescription() {
        return MyBundle.message("fileTemplates.fileType.description");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        String res = this.getLanguage().getAssociatedFileType().getDefaultExtension();
        return res == null ? "jsp" : res;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return MyIcons.PLUGIN;
    }
}
