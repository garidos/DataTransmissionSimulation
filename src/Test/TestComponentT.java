package Test;

import Components.IComponent;
import Data.BinaryData;
import Data.IData;
import Data.SymbolData;

public class TestComponentT implements IComponent {
    @Override
    public IData process(IData data) {
        SymbolData s = (SymbolData) data;
        BinaryData b = new BinaryData();
        b.size = 100;
        for ( int i = 0; i < 100; i++) {
            if ( i % 2 == 0 ) b.bits.set(i);
        }
        return b;
    }

    @Override
    public String getInputDataType() {
        return "Data.SymbolData";
    }

    @Override
    public String getOutputDataType() {
        return "Data.BinaryData";
    }
}
