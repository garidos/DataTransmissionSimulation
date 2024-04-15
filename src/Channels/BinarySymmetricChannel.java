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

        for ( int i = 0; i < data.getSize(); i++ ) {
            if (rand.nextDouble() <= crossoverProbability) {
                data.getData().flip(i);
            }
        }

        return data;
    }

    public double getCrossoverProbability() {
        return crossoverProbability;
    }

    public void setCrossoverProbability(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
    }

    @Override
    public String getInputDataType() {
        return BinaryData.class.getSimpleName();
    }

    @Override
    public String getOutputDataType() {
        return BinaryData.class.getSimpleName();
    }

    @Override
    public String toString() {
        return "Binary Symmetric Channel";
    }
}
