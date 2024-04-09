package Channels;

import Data.IData;

public interface IChannel<T extends IData> {
    public T simulateTransmission(T data);
    public String getInputDataType();
    public String getOutputDataType();
}
