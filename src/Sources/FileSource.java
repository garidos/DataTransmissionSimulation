package Sources;

import Data.IData;
import Data.SymbolData;

import java.io.*;

public class FileSource implements ISource {

    protected String fileName;
    BufferedReader reader;

    public FileSource(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        reader = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public IData getData() {
        try {
            return new SymbolData(reader.readLine());
        } catch (IOException e) {
        }
        return null;
    }

    public void close() throws IOException {
        reader.close();
    }

    @Override
    public String getDataType() {
        return SymbolData.class.getSimpleName();
    }

    @Override
    public String toString() {
        return "Text file source";
    }
}
