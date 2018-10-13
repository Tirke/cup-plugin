package tirke.cupPlugin.parser;

import static tirke.cupPlugin.psi.CupTypes.BLOCK_COMMENT;
import static tirke.cupPlugin.psi.CupTypes.LINE_COMMENT;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.CupLanguage;
import tirke.cupPlugin.psi.CupFile;
import tirke.cupPlugin.psi.CupTypes;

/**
 * Created by Tirke on 18/02/2016
 */
public class CupParserDefinition implements ParserDefinition {

  public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
  public static final TokenSet COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT);

  public static final IFileElementType FILE = new IFileElementType(
      Language.<CupLanguage>findInstance(CupLanguage.class));


  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new CupLexerAdapter();
  }

  @NotNull
  @Override
  public TokenSet getWhitespaceTokens() {
    return WHITE_SPACES;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return COMMENTS;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @Override
  public PsiParser createParser(Project project) {
    return new CupParser();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new CupFile(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    return CupTypes.Factory.createElement(node);
  }

}
