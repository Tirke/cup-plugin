package tirke.cupPlugin.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Tirke on 22/02/2016
 */
public interface CupNamedElement extends PsiNameIdentifierOwner {

  @NotNull
  @Override
  String getName();

  @NotNull
  PsiElement getIdentifier();
}
