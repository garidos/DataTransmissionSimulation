package Channels;

import Data.BinaryData;

import java.util.Random;

public class BinarySymmetricChannel implements IChannel<BinaryData> {
    protected double crossoverProbability;
    protected Random rand;

    public BinarySymmetricChannel(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
        rand = new Random(System.currentTimeMillis());
    }

    @Override
    public BinaryData simulateTransmission(BinaryData data) {

        for ( int i = 0; i < data.size; i++ ) {
            if (rand.nextDouble() <= crossoverProbability) {
                data.bits.flip(i);
            }
        }

        return data;
    }

    @Override
    public String getInputDataType() {
        return "Data.BinaryData";
    }

    @Override
    public String getOutputDataType() {
        return "Data.BinaryData";
    }

    @Override
    public String toString() {
        return "Binary Symmetric Channel";
    }
}
