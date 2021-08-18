package tirke.cup.lang

import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.vfs.VfsUtil
import junit.framework.TestCase
import tirke.cup.CupTestBase
import tirke.cup.lang.psi.CupFileType


class CupFileTypeTest : CupTestBase() {
    fun `test cup file by extension`() {
        val root = myFixture.findFileInTempDir(".")
        val dirPath = "example".substringBeforeLast(VfsUtil.VFS_SEPARATOR_CHAR)
        val fileName = "example.cup".substringAfterLast(VfsUtil.VFS_SEPARATOR_CHAR)
        val file = runWriteAction {
            val dir = VfsUtil.createDirectoryIfMissing(root, dirPath)
            dir.createChildData(this, fileName).also {
                VfsUtil.saveText(it, "import java_cup.runtime.*;")
            }
        }
        TestCase.assertEquals(CupFileType, file.fileType)
    }
}