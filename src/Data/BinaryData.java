package Data;

import java.util.BitSet;

public class BinaryData implements IData{
    public BitSet bits = new BitSet();
    public int size = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < size; i++ ) {
            sb.append( bits.get(i) ? "1" : "0" );
        }
        return sb.toString();
    }
}
