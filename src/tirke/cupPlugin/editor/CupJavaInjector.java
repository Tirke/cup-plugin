package tirke.cupPlugin.editor;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.options.CupSettings;
import tirke.cupPlugin.psi.impl.CupJavaImpl;

/**
 * Created by Tirke on 22/02/2016
 */
public class CupJavaInjector implements LanguageInjector {

  public static final String PREFIX = "{:";
  public static final String SUFFIX = ":}";
  private CupSettings settings = CupSettings.getInstance();

  @Override
  public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host,
      @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {

    if (!(host instanceof CupJavaImpl) || !(settings.ENABLE_JAVA_INJECTION)) {
      return;
    }
    final CupJavaImpl cupJavaCode = (CupJavaImpl) host;
    final String text = cupJavaCode.getText();
    if (!(text.startsWith(PREFIX) && text.endsWith(SUFFIX))) {
      return;
    }
    injectionPlacesRegistrar.addPlace(JavaLanguage.INSTANCE,
        new TextRange(SUFFIX.length(), text.length() - SUFFIX.length()),
        "public class Dummy { public void dummyMethod(){", "}}");
  }
}
