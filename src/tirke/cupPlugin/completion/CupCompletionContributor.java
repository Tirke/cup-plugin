package tirke.cupPlugin.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionInitializationContext;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.util.ProcessingContext;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tirke.cupPlugin.CupLanguage;
import tirke.cupPlugin.psi.CupFile;
import tirke.cupPlugin.psi.CupTypes;

/**
 * Created by Tirke on 19/02/2016
 */
public class CupCompletionContributor extends CompletionContributor {


  public CupCompletionContributor() {
    extend(CompletionType.BASIC,
        PlatformPatterns.psiElement().inFile(StandardPatterns.instanceOf(CupFile.class)),
        new CompletionProvider<CompletionParameters>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters,
              ProcessingContext context,
              @NotNull CompletionResultSet result) {
            for (String keyword : suggestKeywords(parameters.getPosition())) {
              result.addElement(LookupElementBuilder.create(keyword));
            }
          }
        }
    );
  }


  private static Collection<String> suggestKeywords(PsiElement position) {
    TextRange posRange = position.getTextRange();
    CupFile posFile = (CupFile) position.getContainingFile();
    TextRange range = new TextRange(0, posRange.getStartOffset());
    String text = range.isEmpty() ? CompletionInitializationContext.DUMMY_IDENTIFIER
        : range.substring(posFile.getText());
    int completionOffset =
        posRange.getStartOffset() - range.getStartOffset(); // = posRange.getStartOffset() ...

    PsiFile file = PsiFileFactory.getInstance(posFile.getProject())
        .createFileFromText("a.cup", CupLanguage.INSTANCE, text, true, false);
    GeneratedParserUtilBase.CompletionState state = new GeneratedParserUtilBase.CompletionState(
        completionOffset) {
      @Nullable
      @Override
      public String convertItem(Object o) {
        if (o == CupTypes.IDENTIFIER) {
          return null;
        }
        return o.toString();
      }
    };
    file.putUserData(GeneratedParserUtilBase.COMPLETION_STATE_KEY, state);
    TreeUtil.ensureParsed(file.getNode());

    return state.items;
  }


}
