package Components;

import Algorithms.RepetitionCode;
import Data.BinaryData;

public class RepetitionDecoderComponent implements IComponent<BinaryData, BinaryData> {

    private RepetitionCode repetitionCode;

    public RepetitionDecoderComponent(int n) {
        repetitionCode = new RepetitionCode(n);
    }

    @Override
    public BinaryData process(BinaryData data) {
        return repetitionCode.decode(data);
    }

    @Override
    public String getInputDataType() {
        return BinaryData.class.getSimpleName();
    }

    @Override
    public String getOutputDataType() {
        return BinaryData.class.getSimpleName();
    }
}
