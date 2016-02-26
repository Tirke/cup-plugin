package tirke.cupPlugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.sun.istack.internal.NotNull;
import tirke.cupPlugin.CupFileType;
import tirke.cupPlugin.psi.impl.CupPsiImplUtil;

/**
 * Created by Tirke on 22/02/2016
 */
public class CupPsiElementFactory {

    public static CupFile createFile(Project project, String text) {
        String name = "dummy.cup";
        return (CupFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, CupFileType.INSTANCE, text);
    }

    public static PsiElement createSymbolFromText(@NotNull Project project, @NotNull String text) {
        return CupPsiImplUtil.computeDefinitions(createFile(project, "terminal " + text + ";"), CupSymbolDefinition.class).
                get(0).getIdentifier();

    }

}
