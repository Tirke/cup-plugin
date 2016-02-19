package tirke.cupPlugin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import static tirke.cupPlugin.psi.CupTypes.*;

%%

%public
%class CupLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%eof{  return;
%eof}

WHITESPACE = {LineTerminator} | [ \t\f]
LineTerminator = \n|\r|\r\n;

IDENTIFIER=[a-zA-Z_0-9]+
JAVA_CODE="{:"~":}"

COMMENT = {TraditionalComment} | {DocumentationComment} | {LineComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*
LineComment = [ \t]* "//" .*

%%
<YYINITIAL> {

  "|"                { return BAR; }
  "::="              { return CCEQ; }
  ":"                { return COLON; }
  ","                { return COMMA; }
  "."                { return DOT; }
  ";"                { return SEMI; }
  "*"                { return STAR; }
  "class"            { return CLASS; }
  "import"           { return IMPORT; }
  "package"          { return PACKAGE; }
  "static"           { return STATIC; }
  "action"           { return ACTION; }
  "code"             { return CODE; }
  "init"             { return INIT; }
  "parser"           { return PARSER; }
  "scan"             { return SCAN; }
  "start"            { return START; }
  "with"             { return WITH; }
  "non"              { return NON; }
  "nonterminal"      { return NONTERMINAL; }
  "terminal"         { return TERMINAL; }
  "left"             { return LEFT; }
  "nonassoc"         { return NONASSOC; }
  "%prec"            { return PERCENT_PREC; }
  "precedence"       { return PRECED; }
  "right"            { return RIGHT; }
  "JAVA_CODE"        { return JAVA_CODE; }


  {IDENTIFIER}       { return IDENTIFIER; }
  {JAVA_CODE}        { return JAVA_CODE; }
  {COMMENT}          { return COMMENT; }


  //Special

  {WHITESPACE}       { return TokenType.WHITE_SPACE; }
  [^]                { return TokenType.BAD_CHARACTER; }
}
