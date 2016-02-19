package tirke.cupPlugin.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tirke.cupPlugin.icons.CupIcons;

import javax.swing.*;
import java.util.Map;

import static tirke.cupPlugin.highlighting.CupSyntaxHighlighter.*;

/**
 * Created by Tirke on 19/02/2016
 */
public class CupColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] ATTRS = new AttributesDescriptor[]{
            new AttributesDescriptor("Illegal character", ILLEGAL),
            new AttributesDescriptor("Comment", COMMENT),
            new AttributesDescriptor("Comma", COMMA),
            new AttributesDescriptor("Semicolon", SEMI),
            new AttributesDescriptor("Keyword", KEYWORD),
            new AttributesDescriptor("Label identifier", LABEL_ID),
            new AttributesDescriptor("Rule identifier", RULE_ID),
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
                "package cup.package; \n" +
                "import java_cup.runtime.*; \n" +
                "init with {: scanner.init();              :}; \n" +
                "scan with {: return scanner.next_token(); :}; \n" +
                "\n" +
                "terminal           PLUS, MINUS;\n" +
                "\n" +
                "non terminal Integer    expr;\n" +
                "\n" +
                "precedence left PLUS,MINUS;\n" +
                "<r>expr</r>      ::= expr PLUS expr \n" +
                "            | expr:<l>e</l> MINUS expr  \n" +
                "\t    ;"
                ;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        Map<String, TextAttributesKey> map = new THashMap<>();
        map.put("l",LABEL_ID);
        map.put("r",RULE_ID);
        return map;
    }

}
