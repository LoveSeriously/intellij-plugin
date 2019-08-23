import com.intellij.openapi.application.Application;
import com.intellij.openapi.components.*;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author lw
 * @date 2019-08-22
 */
@State(name = "HelloWorldApplicationComponent", storages = {@Storage(file = "$APP_CONFIG$/phrase.xml")})
public class HelloWorldApplicationComponent implements ApplicationComponent, Configurable, PersistentStateComponent<Element> {

    private HelloWorldConfiguration form;
    private String phrase;

    public static HelloWorldApplicationComponent getInstance() {
        return ServiceManager.getService(HelloWorldApplicationComponent.class);
    }

    public void initComponent() {
        form = new HelloWorldConfiguration();
    }

    public void disposeComponent() {
    }

    public String getComponentName() {
        return "HelloWorldApplicationComponent";
    }

    public void sayHello() {
        // Show dialog with message
        Messages.showMessageDialog(phrase, "Sample", Messages.getInformationIcon());
    }

    public String getDisplayName() {
        // Return name of configuration icon in Settings dialog
        return "HelloWorld";
    }

    public Icon getIcon() {
        return null;
    }

    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        if (form == null) {
            form = new HelloWorldConfiguration();
        }
        return form.getRootComponent();
    }

    public boolean isModified() {
        return form != null && form.isModified(this);
    }

    public void apply() throws ConfigurationException {
        if (form != null) {
            // Get data from form to component
            form.getData(this);
        }
    }

    public void reset() {
        if (form != null) {
            // Reset form data from component
            form.setData(this);
        }
    }

    public void disposeUIResources() {
        form = null;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(final String phrase) {
        this.phrase = phrase;
    }

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("phrase");
        element.setAttribute("phrase", this.phrase);
        return element;
    }

    @Override
    public void loadState(@NotNull Element state) {
        this.setPhrase(state.getAttributeValue("phrase"));
    }
}
