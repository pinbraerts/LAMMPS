package com.pinbraerts.lammps

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class LAMMPSParserDefinition: ParserDefinition {
    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(LAMMPSTypes.COMMENT)
        val FILE = IFileElementType(LAMMPSLanguage)
    }

    override fun getFileNodeType() = FILE

    override fun createFile(vp: FileViewProvider?) = LAMMPSFile(vp!!)

    override fun getStringLiteralElements() = TokenSet.EMPTY

    override fun getWhitespaceTokens() = WHITE_SPACES

    override fun createLexer(project: Project?) = LAMMPSLexerAdapter()

    override fun createElement(node: ASTNode?) = LAMMPSTypes.Factory.createElement(node)

    override fun getCommentTokens() = COMMENTS

    override fun createParser(project: Project?) = LAMMPSParser()
}