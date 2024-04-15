package Components;

import Algorithms.LZW;
import Data.BinaryData;
import Data.SymbolData;

public class LZWCoderComponent implements IComponent<SymbolData, BinaryData> {

    @Override
    public BinaryData process(SymbolData data) {
        return LZW.encode(data);
    }

    @Override
    public String getInputDataType() {
        return SymbolData.class.getSimpleName();
    }

    @Override
    public String getOutputDataType() {
        return BinaryData.class.getSimpleName();
    }
}
