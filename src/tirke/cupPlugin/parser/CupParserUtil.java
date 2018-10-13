package tirke.cupPlugin.parser;

import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Tirke on 20/02/2016
 */
public class CupParserUtil extends GeneratedParserUtilBase {

  @NotNull
  public static String getName(PsiNameIdentifierOwner o) {
    return ObjectUtils.assertNotNull(o.getNameIdentifier()).getText();
  }


}
