package tirke.cup.lang.psi

import tirke.cup.lang.CupLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class CupFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, CupLanguage) {
    override fun getFileType(): FileType = CupFileType

    override fun toString(): String = "Cup File"
}