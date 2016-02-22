package tirke.cupPlugin.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.parser.CupLexerAdapter;
import tirke.cupPlugin.psi.CupTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static tirke.cupPlugin.psi.CupTypes.*;

/**
 * Created by Tirke on 18/02/2016
 */
public class CupSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey ILLEGAL = createTextAttributesKey("CUP_ILLEGAL", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("CUP_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("CUP_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey TOKEN = createTextAttributesKey("CUP_TOKEN", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMA = createTextAttributesKey("CUP_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey SEMI = createTextAttributesKey("CUP_SEMI", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey LABEL_ID = createTextAttributesKey("CUP_LABEL", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey RULE_ID = createTextAttributesKey("CUP_RULE", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey PUNCTUATION = createTextAttributesKey("CUP_PUNC", DefaultLanguageHighlighterColors.OPERATION_SIGN);



    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new CupLexerAdapter();
    }

    private static final TokenSet KEYWORDS = TokenSet.create(
            ACTION,
            CLASS,
            CODE,
            IMPORT,
            INIT,
            LEFT,
            NON,
            NONASSOC,
            NONTERMINAL,
            PACKAGE,
            PARSER,
            PERCENT_PREC,
            PRECED,
            RIGHT,
            SCAN,
            START,
            STATIC,
            TERMINAL,
            WITH
    );

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(CupTypes.COMMENT)) {
            return pack(COMMENT);
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return pack(ILLEGAL);
        } else if (KEYWORDS.contains(tokenType)) {
            return pack(KEYWORD);
        } else if (tokenType.equals(CupTypes.COMMA)) {
            return pack(COMMA);
        } else if (tokenType.equals(CupTypes.SEMI)) {
            return pack(SEMI);
        } else if (tokenType.equals(CupTypes.CCEQ) || tokenType.equals(CupTypes.BAR) || tokenType.equals(CupTypes.COLON)){
            return pack(PUNCTUATION);
        }
        return EMPTY;
    }
}
