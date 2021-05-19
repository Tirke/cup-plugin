package com.github.tirke.cup.ide.icons

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object CupIcons {
    val FILE = load("/icons/blueCup16.png")
    private fun load(path: String): Icon = IconLoader.getIcon(path, CupIcons::class.java)
}