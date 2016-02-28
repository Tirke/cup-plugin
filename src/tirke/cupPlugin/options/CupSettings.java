package tirke.cupPlugin.options;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Tirke on 28/02/2016
 */

@State(
        name = "CupSettings",
        storages = {
                @Storage(id = "cup", file = StoragePathMacros.APP_CONFIG + "/cup.xml")
        }
)
public class CupSettings implements PersistentStateComponent<CupSettings> {


    //TODO remember to update @State for IDEA16


    public boolean ENABLE_JAVA_INJECTION = true;


    @Nullable
    @Override
    public CupSettings getState() {
        return this;
    }

    @Override
    public void loadState(CupSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static CupSettings getInstance() {
        return ServiceManager.getService(CupSettings.class);
    }
}
