package com.pinbraerts.lammps.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class LAMMPSColorSettingsPage: ColorSettingsPage {
    companion object {
        val DESCRIPTORS = arrayOf(
            AttributesDescriptor("NUMBER", DefaultLanguageHighlighterColors.NUMBER),
            AttributesDescriptor("VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE),
            AttributesDescriptor("PREDEFINED", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL),
            AttributesDescriptor("COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT),
            AttributesDescriptor("BAD_CHAR", HighlighterColors.BAD_CHARACTER),
            AttributesDescriptor("COMMAND", DefaultLanguageHighlighterColors.KEYWORD),
            AttributesDescriptor("FILE_NAME", DefaultLanguageHighlighterColors.LINE_COMMENT)
        )
    }

    override fun getHighlighter() = LAMMPSSyntaxHighlighter()

    override fun getAdditionalHighlightingTagToDescriptorMap() = null

    override fun getIcon() = null

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = "LAMMPS"

    override fun getDemoText() = """
        # 3d Lennard-Jones melt

        variable	x index 1
        variable	y index 1
        variable	z index 1
        variable        t index 100
        
        variable	xx equal 20*${'$'}x
        variable	yy equal 20*${'$'}y
        variable	zz equal 20*${'$'}z
        
        units		lj
        atom_style	atomic
        
        lattice		fcc 0.8442
        region		box block 0 ${'$'}{xx} 0 ${'$'}{yy} 0 ${'$'}{zz}
        create_box	1 box
        create_atoms	1 box
        mass		1 1.0
        
        velocity	all create 1.44 87287 loop geom
        
        pair_style	lj/cut 2.5
        pair_coeff	1 1 1.0 1.0 2.5
        
        neighbor	0.3 bin
        neigh_modify	delay &
            0 every 20 check no
        
        fix		1 all nve
        
        thermo          100
        
        run		${'$'}t
    """.trimIndent()

}