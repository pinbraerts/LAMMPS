package com.pinbraerts.lammps

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

object LAMMPSFileType: LanguageFileType(LAMMPSLanguage) {
    override fun getDefaultExtension() = "lammps"

    override fun getIcon(): Icon? = null // TODO

    override fun getCharset(p0: VirtualFile, p1: ByteArray): String? = null

    override fun getName() = "LAMMPS"

    override fun getDescription() = "Scripting language for the LAMMPS system"

    override fun isReadOnly() = false
}
