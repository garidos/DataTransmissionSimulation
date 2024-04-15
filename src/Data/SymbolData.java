package Data;

public class SymbolData implements IData {
    private char[] data;
    private int size;

    public SymbolData(String text) {
        this.size = text.length();
        data = new char[size];
        for (int i = 0; i < size; i++) {
            data[i] = text.charAt(i);
        }
    }

    public SymbolData(int size) {
        this.size = size;
        data = new char[size];
    }

    public char[] gatData() {
        return data;
    }

    @Override
    public String toString() {
        return new String(data);
    }
}
