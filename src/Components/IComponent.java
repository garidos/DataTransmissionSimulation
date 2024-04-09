package Components;

import Data.IData;

public interface IComponent {
    public IData process(IData data);
    public String getInputDataType();
    public String getOutputDataType();
}
