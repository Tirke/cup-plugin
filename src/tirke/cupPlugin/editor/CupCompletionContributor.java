package tirke.cupPlugin.editor;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;
import tirke.cupPlugin.CupLanguage;

/**
 * Created by Tirke on 19/02/2016
 */
public class CupCompletionContributor extends CompletionContributor {


    public CupCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement().withLanguage(CupLanguage.INSTANCE),
                getProvider()
        );
    }

    private static CompletionProvider<CompletionParameters> getProvider() {

        return new FixedWordsProvider(
                // keywords
                "import",
                "package",
                "action code",
                "parser code",
                "init with",
                "scan with",
                "terminal",
                "non terminal",
                "nonterminal",
                "precedence left",
                "precedence right",
                "precedence nonassoc",
                "start with",
                "%prec"
        );

    }
}
