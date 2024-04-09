package Test;

import Data.IData;
import Data.SymbolData;
import Destinations.IDestination;

public class TestDestination implements IDestination {
    @Override
    public void putData(IData data) {
        SymbolData s = (SymbolData) data;
        System.out.println(s.toString());
    }
}
