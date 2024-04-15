package Data;

import java.util.BitSet;

public class BinaryData implements IData{
    private BitSet bits = new BitSet();
    private int size = 0;

    public BitSet getData() {
        return bits;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < size; i++ ) {
            sb.append( bits.get(i) ? "1" : "0" );
        }
        return sb.toString();
    }
}
