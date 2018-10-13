// This is a generated file. Not intended for manual editing.
package tirke.cupPlugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.psi.CupJavaCode;
import tirke.cupPlugin.psi.CupVisitor;

public class CupJavaImpl extends ASTWrapperPsiElement implements CupJavaCode {

  public CupJavaImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof CupVisitor) {
          ((CupVisitor) visitor).visitJavaCode(this);
      } else {
          super.accept(visitor);
      }
  }

  @Override
  public boolean isValidHost() {
    return true;
  }

  @Override
  public PsiLanguageInjectionHost updateText(@NotNull String text) {
    return null;
  }

  @NotNull
  @Override
  public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
    return new LiteralTextEscaper<PsiLanguageInjectionHost>(this) {
      @Override
      public boolean decode(@NotNull TextRange rangeInsideHost, @NotNull StringBuilder outChars) {
        outChars.append(myHost.getText(), rangeInsideHost.getStartOffset(),
            rangeInsideHost.getEndOffset());
        return true;
      }

      @Override
      public int getOffsetInHost(int offsetInDecoded, @NotNull TextRange rangeInsideHost) {
        int offset = offsetInDecoded + rangeInsideHost.getStartOffset();
          if (offset < rangeInsideHost.getStartOffset()) {
              offset = rangeInsideHost.getStartOffset();
          }
          if (offset > rangeInsideHost.getEndOffset()) {
              offset = rangeInsideHost.getEndOffset();
          }
        return offset;
      }

      @Override
      public boolean isOneLine() {
        return true;
      }
    };
  }
}
