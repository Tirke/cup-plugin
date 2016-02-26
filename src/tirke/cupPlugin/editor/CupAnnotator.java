package tirke.cupPlugin.editor;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.highlighting.CupSyntaxHighlighter;
import tirke.cupPlugin.psi.CupLabelId;
import tirke.cupPlugin.psi.CupSymbolId;
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
            public void visitSymbolId(@NotNull CupSymbolId o) {
                super.visitSymbolId(o);
                if (isUpperCase(o.getText())) {
                    setHighlighting(o, holder, CupSyntaxHighlighter.TERM_ID);
                } else {
                    setHighlighting(o, holder, CupSyntaxHighlighter.NON_TERM_ID);
                }

            }
        });
    }

    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                        @NotNull TextAttributesKey key) {
        holder.createInfoAnnotation(element, null).setTextAttributes(key);
    }

    private static boolean isUpperCase(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
