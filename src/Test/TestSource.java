package Test;

import Data.IData;
import Data.SymbolData;
import Sources.ISource;

public class TestSource implements ISource {

    @Override
    public IData getData() {
        return new SymbolData();
    }
}
