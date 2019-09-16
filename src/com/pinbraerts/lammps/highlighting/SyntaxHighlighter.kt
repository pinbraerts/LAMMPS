package com.pinbraerts.lammps.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import com.intellij.psi.TokenType
import com.pinbraerts.lammps.LAMMPSLexerAdapter
import com.pinbraerts.lammps.LAMMPSTypes

class LAMMPSSyntaxHighlighter: SyntaxHighlighterBase() {
    companion object {
        val NUMBER      = createTextAttributesKey("NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val VARIABLE    = createTextAttributesKey("VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
        val PREDEFINED  = createTextAttributesKey("PREDEFINED", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
        val COMMENT     = createTextAttributesKey("COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHAR    = createTextAttributesKey("BAD_CHAR", HighlighterColors.BAD_CHARACTER)
        val COMMAND     = createTextAttributesKey("COMMAND", DefaultLanguageHighlighterColors.KEYWORD)
        var FILE_PATH   = createTextAttributesKey("FILE_NAME", DefaultLanguageHighlighterColors.LINE_COMMENT)

        val NUMBER_KEYS = arrayOf(NUMBER)
        val VARIABLE_KEYS = arrayOf(VARIABLE)
        val PREDEFINED_KEYS = arrayOf(PREDEFINED)
        val COMMENT_KEYS = arrayOf(COMMENT)
        val COMMAND_KEYS = arrayOf(COMMAND)
        val FILE_PATH_KEYS = arrayOf(FILE_PATH)
        val BAD_CHAR_KEYS = arrayOf(BAD_CHAR)
        val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }

    override fun getTokenHighlights(elementType: IElementType) = when(elementType) {
        LAMMPSTypes.COMMENT -> COMMENT_KEYS
        LAMMPSTypes.NUMBER -> NUMBER_KEYS
        LAMMPSTypes.COMMENT -> COMMENT_KEYS
        LAMMPSTypes.IDENTIFIER -> PREDEFINED_KEYS
        LAMMPSTypes.VARIABLE -> VARIABLE_KEYS
        LAMMPSTypes.FILE_PATH -> FILE_PATH_KEYS
        LAMMPSTypes.COMMAND -> COMMAND_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

    override fun getHighlightingLexer() = LAMMPSLexerAdapter()

}