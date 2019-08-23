import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;

/**
 * @author lw
 * @date 2019-08-22
 */
public class SayHelloAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        HelloWorldApplicationComponent helloWorldComponent = HelloWorldApplicationComponent.getInstance();
        helloWorldComponent.sayHello();
    }
}
