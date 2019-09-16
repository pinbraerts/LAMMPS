package com.pinbraerts.lammps

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class LAMMPSFile(vp: FileViewProvider): PsiFileBase(vp, LAMMPSLanguage) {
    override fun getFileType() = LAMMPSFileType

    override fun toString() = "LAMMPS File"
}