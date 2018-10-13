package tirke.cupPlugin.editor;

import static tirke.cupPlugin.psi.CupTypes.LEFTCUPBRACES;
import static tirke.cupPlugin.psi.CupTypes.RIGHTCUPBRACES;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Created by Tirke on 20/02/2016
 */
public class CupBracesMatcher implements PairedBraceMatcher {

  private static final BracePair[] PAIRS = new BracePair[]{
      new BracePair(LEFTCUPBRACES, RIGHTCUPBRACES, true),
  };


  @Override
  public BracePair[] getPairs() {
    return PAIRS;
  }

  @Override
  public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType,
      @Nullable IElementType contextType) {
    return true;
  }

  @Override
  public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
    return openingBraceOffset;
  }
}
