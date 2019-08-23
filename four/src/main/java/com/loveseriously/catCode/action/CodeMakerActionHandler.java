package com.loveseriously.catCode.action;

import com.intellij.ide.actions.CreateFileAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.loveseriously.catCode.ClassEntry;
import com.loveseriously.catCode.CodeTemplate;
import com.loveseriously.catCode.setting.CodeMakerSettings;
import com.loveseriously.catCode.utils.CodeMakerUtil;
import com.loveseriously.catCode.utils.VelocityUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author lw
 * @date 2019-08-20
 */
public class CodeMakerActionHandler extends EditorWriteActionHandler {
    /** logger */
    private static final Logger LOGGER = CodeMakerUtil.getLogger(CodeMakerActionHandler.class);
    private CodeMakerSettings   settings;
    private String              templateKey;
    public CodeMakerActionHandler(String templateKey) {
        this.templateKey = templateKey;
        this.settings = ServiceManager.getService(CodeMakerSettings.class);
    }

    @Override
    public void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext) {
        Project project = DataKeys.PROJECT.getData(dataContext);
        PsiJavaFile javaFile = CodeMakerUtil.getSelectedJavaFile(dataContext);
        if (javaFile == null) {
            LOGGER.error("get java file failed" + dataContext);
            return;
        }
        PsiClass[] classes = javaFile.getClasses();
        if (classes.length < 1) {
            LOGGER.error("get class failed " + dataContext);
            return;
        }
        PsiClass currentClass = classes[0];
        List<PsiClass> selectClasses = new ArrayList<>();
        //添加当前选择的类
        selectClasses.add(currentClass);
        CodeTemplate codeTemplate = settings.getCodeTemplate(templateKey);
        //根据配置的数量，通过选择框选择需要的类
        for (int i = 1; i < codeTemplate.getClassNumber(); i++) {
            PsiClass psiClass = CodeMakerUtil.chooseClass(project, currentClass);
            //如果没有选择对应类，不做处理
            if (psiClass == null) {
                return;
            }
            selectClasses.add(psiClass);
        }

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < selectClasses.size(); i++) {
            map.put("class" + i, ClassEntry.create(selectClasses.get(i)));
        }
        Date now = new Date();
        map.put("YEAR", DateFormatUtils.format(now, "yyyy"));
        map.put("TIME", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        map.put("USER", System.getProperty("user.name"));
        String className = VelocityUtil.evaluate(codeTemplate.getClassNameVm(), map);
        map.put("ClassName", className);

        String content = VelocityUtil.evaluate(codeTemplate.getCodeTemplate(), map);

        //异步进行写操作
//        ApplicationManager.getApplication().runWriteAction(new CreateFileAction(CodeMakerUtil.generateClassPath(CodeMakerUtil.getSourcePath(currentClass), className), content, dataContext));

    }

    /**
     * Getter method for property <tt>templateKey</tt>.
     *
     * @return property value of templateKey
     */
    public String getTemplateKey() {
        return templateKey;
    }

    /**
     * Setter method for property <tt>templateKey</tt>.
     *
     * @param templateKey value to be assigned to property templateKey
     */
    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
    }
}
