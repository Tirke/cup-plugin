package tirke.cupPlugin.editor;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.highlighting.CupSyntaxHighlighter;
import tirke.cupPlugin.psi.CupLabelId;
import tirke.cupPlugin.psi.CupRuleId;
import tirke.cupPlugin.psi.CupVisitor;

/**
 * Created by Tirke on 19/02/2016
 */
public class CupAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        element.accept(new CupVisitor() {
            @Override
            public void visitLabelId(@NotNull CupLabelId o) {
                super.visitLabelId(o);
                setHighlighting(o, holder, CupSyntaxHighlighter.LABEL_ID);
            }

            @Override
            public void visitRuleId(@NotNull CupRuleId o) {
                super.visitRuleId(o);
                setHighlighting(o, holder, CupSyntaxHighlighter.RULE_ID);
            }
        });
    }

    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                        @NotNull TextAttributesKey key) {
        holder.createInfoAnnotation(element, null).setTextAttributes(key);
    }
}
