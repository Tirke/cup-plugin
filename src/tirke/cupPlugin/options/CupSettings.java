package tirke.cupPlugin.options;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Tirke on 28/02/2016
 */

@State(
    name = "CupSettings",
    storages = {
        @Storage("cup.xml")
    }
)
public class CupSettings implements PersistentStateComponent<CupSettings> {

  public boolean ENABLE_JAVA_INJECTION = true;

  public static CupSettings getInstance() {
    return ServiceManager.getService(CupSettings.class);
  }

  @Nullable
  @Override
  public CupSettings getState() {
    return this;
  }

  @Override
  public void loadState(CupSettings state) {
    XmlSerializerUtil.copyBean(state, this);
  }
}
