package tirke.cupPlugin.highlighting;

import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.BLOCK_COMMENT;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.COMMA;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.ILLEGAL;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.KEYWORD;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.LABEL_ID;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.LINE_COMMENT;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.NON_TERM_ID;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.PUNCTUATION;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.SEMI;
import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.TERM_ID;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import gnu.trove.THashMap;
import java.util.Map;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tirke.cupPlugin.icons.CupIcons;

/**
 * Created by Tirke on 19/02/2016
 */
public class CupColorSettingsPage implements ColorSettingsPage {

  private static final AttributesDescriptor[] ATTRS = new AttributesDescriptor[]{
      new AttributesDescriptor("Illegal character", ILLEGAL),
      new AttributesDescriptor("Line Comment", LINE_COMMENT),
      new AttributesDescriptor("Block Comment", BLOCK_COMMENT),
      new AttributesDescriptor("Comma", COMMA),
      new AttributesDescriptor("Semicolon", SEMI),
      new AttributesDescriptor("Keyword", KEYWORD),
      new AttributesDescriptor("Label identifier", LABEL_ID),
      new AttributesDescriptor("Terminal identifier", TERM_ID),
      new AttributesDescriptor("Non Terminal identifier", NON_TERM_ID),
      new AttributesDescriptor("Operators", PUNCTUATION),
  };


  @NotNull
  @Override
  public String getDisplayName() {
    return "Cup";
  }


  @Nullable
  @Override
  public Icon getIcon() {
    return CupIcons.FILE;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new CupSyntaxHighlighter();
  }

  @NotNull
  public AttributesDescriptor[] getAttributeDescriptors() {
    return ATTRS;
  }

  @NotNull
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "// Small example of a CUP specification file\n" +
        "package cup.test; \n" +
        "import java_cup.runtime.*; \n" +
        "init with {: scanner.init();              :}; \n" +
        "scan with {: return scanner.next_token(); :}; \n" +
        "\n" +
        "terminal           PLUS, MINUS;\n" +
        "\n" +
        "non terminal Integer    expr;\n" +
        "\n" +
        "precedence left PLUS,MINUS;\n" +
        "<nt>expr</nt>      ::= <nt>expr</nt> <t>PLUS</t> <nt>expr</nt> \n" +
        "            | <nt>expr</nt>:<l>e</l> <t>MINUS</t> <nt>expr</nt>  \n" +
        "\t    ;"
        ;
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    Map<String, TextAttributesKey> map = new THashMap<>();
    map.put("l", LABEL_ID);
    map.put("nt", NON_TERM_ID);
    map.put("t", TERM_ID);
    return map;
  }

}
