package Destinations;

import Data.IData;

public interface IDestination {
    public void putData(IData data);
    public String getDataType();
}
