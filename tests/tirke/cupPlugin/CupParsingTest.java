package tirke.cupPlugin;

import com.intellij.testFramework.ParsingTestCase;

/**
 * Created by Tirke on 21/02/2016
 */
public class CupParsingTest extends ParsingTestCase {

    public CupParsingTest() {
        super("", "cup", new CupParserDefinition());
    }

    public void testParsingTestData(){
        doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
