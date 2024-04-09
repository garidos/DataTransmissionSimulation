package Test;

import Components.IComponent;
import Data.IData;
import Data.SymbolData;


public class TestComponentR implements IComponent {
    @Override
    public IData process(IData data) {
        SymbolData s = new SymbolData();
        s.data = "Evo me tu sam eura pun sam";
        return s;
    }

    @Override
    public String getInputDataType() {
        return "Data.BinaryData";
    }

    @Override
    public String getOutputDataType() {
        return "Data.SymbolData";
    }
}
