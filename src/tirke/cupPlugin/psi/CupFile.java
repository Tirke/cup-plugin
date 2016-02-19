package tirke.cupPlugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.CupFileType;
import tirke.cupPlugin.CupLanguage;

import javax.swing.*;

/**
 * Created by Tirke on 18/02/2016
 */
public class CupFile extends PsiFileBase {


    public CupFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, CupLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CupFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Cup File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
