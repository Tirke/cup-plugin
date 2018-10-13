package tirke.cupPlugin;

import com.intellij.lang.Language;

/**
 * Created by Tirke on 13/02/2016
 */
public class CupLanguage extends Language {

  public static final CupLanguage INSTANCE = new CupLanguage();

  private CupLanguage() {
    super("Cup");
  }
}
