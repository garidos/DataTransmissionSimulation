package Components;

import Data.IData;

public interface IComponent<I extends IData, O extends IData> {
    public O process(I data);
    public String getInputDataType();
    public String getOutputDataType();
}
