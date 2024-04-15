package Components;

import Algorithms.LZW;
import Data.BinaryData;
import Data.SymbolData;

public class LZWDecoderComponent implements IComponent<BinaryData, SymbolData> {

    @Override
    public SymbolData process(BinaryData data) {
        return LZW.decode(data);
    }

    @Override
    public String getInputDataType() {
        return BinaryData.class.getSimpleName();
    }

    @Override
    public String getOutputDataType() {
        return SymbolData.class.getSimpleName();
    }
}
