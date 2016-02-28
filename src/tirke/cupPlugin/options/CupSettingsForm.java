package tirke.cupPlugin.options;

import com.intellij.ui.StateRestoringCheckBox;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by Tirke on 28/02/2016
 */
public final class CupSettingsForm {
    @NonNls
    private static final String CUP_ENABLE_JAVA_INJECTION = "Cup.EnableJavaInjection";

    private JPanel formComponent;
    private JCheckBox enabledJavaInjection;


    private void createUIComponents() {
        enabledJavaInjection = new StateRestoringCheckBox(CUP_ENABLE_JAVA_INJECTION,true);
    }

    public JComponent getFormComponent(){
        return formComponent;
    }

    public boolean isModified(CupSettings state){
        return enabledJavaInjection.isSelected() != state.ENABLE_JAVA_INJECTION;
    }

    public void load(@NotNull CupSettings state){
        enabledJavaInjection.setSelected(state.ENABLE_JAVA_INJECTION);
    }

    public void save(@NotNull CupSettings state){
        state.ENABLE_JAVA_INJECTION = enabledJavaInjection.isSelected();
    }

}
