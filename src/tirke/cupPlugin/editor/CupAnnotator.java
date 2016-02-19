package tirke.cupPlugin.editor;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.highlighting.CupSyntaxHighlighter;
import tirke.cupPlugin.psi.CupLabelId;
import tirke.cupPlugin.psi.CupRuleId;

/**
 * Created by Tirke on 19/02/2016
 */
public class CupAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        PsiElement parent = element.getParent();

        if (parent instanceof CupLabelId) {
            holder.createInfoAnnotation(element, null).setTextAttributes(CupSyntaxHighlighter.LABEL_ID);
        } else if (parent instanceof CupRuleId) {
            holder.createInfoAnnotation(element, null).setTextAttributes(CupSyntaxHighlighter.RULE_ID);
        }
    }
}
