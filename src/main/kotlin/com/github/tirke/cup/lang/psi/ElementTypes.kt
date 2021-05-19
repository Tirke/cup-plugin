package com.github.tirke.cup.lang.psi

import com.github.tirke.cup.ide.icons.CupIcons
import com.github.tirke.cup.lang.CupLanguage
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object CupFileType : LanguageFileType(CupLanguage) {
    override fun getName(): String = "Cup File"

    override fun getDescription(): String = "Cup parser file"

    override fun getDefaultExtension(): String = "cup"

    override fun getIcon(): Icon = CupIcons.FILE
}