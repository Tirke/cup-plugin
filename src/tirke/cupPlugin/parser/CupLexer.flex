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

%state OPT_JAVA_CODE



LineTerminator = \r|\n|\r\n
WhiteSpace = [ \t]
AnySpace = {LineTerminator} | {WhiteSpace} | [\f]

IDENTIFIER=[a-zA-Z_0-9]+
//JAVA_CODE="{:"~":}"

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
  "{:"               { yybegin(OPT_JAVA_CODE); return  LEFTCUPBRACES; }
  ":}"               { return  RIGHTCUPBRACES; }
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
  //{JAVA_CODE}        { return JAVA_CODE; }
  {COMMENT}          { return COMMENT; }


  //Special

  {AnySpace}       { return TokenType.WHITE_SPACE; }
  [^]                { return TokenType.BAD_CHARACTER; }
}

<OPT_JAVA_CODE> {

    ":}"                   { yybegin(YYINITIAL); yypushback(yylength()); }
    [^]                    { yybegin(OPT_JAVA_CODE); return JAVA; }
}
