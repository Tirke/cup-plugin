package tirke.cupPlugin.parser;

import com.intellij.lexer.FlexAdapter;
import tirke.cupPlugin.parser.CupLexer;

import java.io.Reader;

/**
 * Created by Tirke on 18/02/2016
 */
public class CupLexerAdapter extends FlexAdapter {


    public CupLexerAdapter() {
        super(new CupLexer((Reader) null));
    }
}
