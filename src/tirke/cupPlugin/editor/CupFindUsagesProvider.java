package tirke.cupPlugin.editor;

import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tirke.cupPlugin.psi.CupNamedElement;
import tirke.cupPlugin.psi.CupSymbolDefinition;

/**
 * Created by Tirke on 26/02/2016
 */
public class CupFindUsagesProvider implements FindUsagesProvider {

  @Nullable
  @Override
  public WordsScanner getWordsScanner() {
    return null;
  }

  @Override
  public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
    return psiElement instanceof CupNamedElement;
  }


  @NotNull
  @Override
  public String getDescriptiveName(@NotNull PsiElement element) {
    if (element instanceof CupSymbolDefinition) {
      return ((CupSymbolDefinition) element).getName();
    } else {
      return "";
    }
  }


  @Nullable
  @Override
  public String getHelpId(@NotNull PsiElement psiElement) {
    return null;
  }

  @NotNull
  @Override
  public String getType(@NotNull PsiElement element) {
    if (element instanceof CupSymbolDefinition) {
      return "Terminal or Non Terminal Definition";
    } else {
      return "";
    }
  }

  @NotNull
  @Override
  public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
    return getDescriptiveName(element);
  }
}
