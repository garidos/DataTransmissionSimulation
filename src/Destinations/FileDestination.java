package Destinations;

import Data.IData;
import Data.SymbolData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileDestination implements IDestination {

    protected String fileName;
    protected BufferedWriter writer;

    public FileDestination(String fileName) throws IOException {
        this.fileName = fileName;
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    @Override
    public void putData(IData data) {
        try {
            writer.write(data.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
        }
    }

    public void close() throws IOException {
        writer.close();
    }

    @Override
    public String getDataType() {
        return SymbolData.class.getSimpleName();
    }

    @Override
    public String toString() {
        return "Text file destination";
    }
}
