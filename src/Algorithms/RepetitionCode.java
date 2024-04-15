package Algorithms;

import Data.BinaryData;

public class RepetitionCode {

    private int n;

    public RepetitionCode(int n) {
        this.n = n;
    }

    public BinaryData encode(BinaryData data) {
        BinaryData output = new BinaryData();
        output.setSize(data.getSize() * n);

        for (int i = 0; i < data.getSize(); i++) {
            boolean bit = data.getData().get(i);
            for ( int j = 0; j < n; j++) {
                output.getData().set(i*n + j, bit);
            }
        }

        return output;
    }

    public BinaryData decode(BinaryData data) {
        BinaryData output = new BinaryData();
        output.setSize(data.getSize() / n);

        for (int i = 0; i < data.getSize(); i += n) {
            int cnt[] = {0, 0};
            for ( int j = 0; j < n; j++) {
                cnt[data.getData().get(i + j)?1:0]++;
            }
            if ( cnt[1] > cnt[0]) {
                output.getData().set(i / n);
            }
        }

        return output;
    }

}
