package tirke.cupPlugin.options;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by Tirke on 27/02/2016
 */
public class CupConfigurable implements Configurable {

    public static final String CUP_NAME = "Cup";
    private CupSettingsForm settingsForm;


    @Nls
    @Override
    public String getDisplayName() {
        return CUP_NAME;
    }


    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }


    @Nullable
    @Override
    public JComponent createComponent() {
        settingsForm = new CupSettingsForm();
        settingsForm.load(CupSettings.getInstance());
        return settingsForm.getFormComponent();
    }

    @Override
    public boolean isModified() {
        return settingsForm.isModified(CupSettings.getInstance());
    }

    @Override
    public void apply() throws ConfigurationException {
        settingsForm.save(CupSettings.getInstance());

    }

    @Override
    public void reset() {
        settingsForm.load(CupSettings.getInstance());
    }

    @Override
    public void disposeUIResources() {
        settingsForm = null;
    }
}
