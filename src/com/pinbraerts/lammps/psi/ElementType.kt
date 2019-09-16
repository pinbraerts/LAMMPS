package com.pinbraerts.lammps.psi

import com.intellij.psi.tree.IElementType
import com.pinbraerts.lammps.LAMMPSLanguage

class LAMMPSElementType(debugName: String): IElementType(debugName, LAMMPSLanguage)

class LAMMPSTokenType(debugName: String): IElementType(debugName, LAMMPSLanguage) {
    override fun toString() = "LAMMPSTokenType." + super.toString()
}
