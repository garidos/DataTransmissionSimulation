import Channels.BinarySymmetricChannel;
import Components.LZWCoderComponent;
import Components.LZWDecoderComponent;
import Components.RepetitionCoderComponent;
import Components.RepetitionDecoderComponent;
import Data.SymbolData;
import Destinations.FileDestination;
import Sources.FileSource;

import java.io.*;
import java.util.Random;

public class Main {

    public static final int TEXT_LEN = 500;
    public static final double STEP = 1e-3;
    public static final int ITERATIONS = 50;
    public static final String INPUT_FILE_NAME = "input.txt";
    public static final String OUTPUT_FILE_NAME = "output.txt";
    public static final String DATA_FILE_NAME = "data.txt";
    public static final double LIMIT = 0.3;

    private static void generateData() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < ITERATIONS; i++) {
            SymbolData s = new SymbolData(TEXT_LEN);
            char[] data = s.gatData();

            for ( int j = 0; j < TEXT_LEN; j++ ) {
                data[j] = (char)(rand.nextInt(26) + 'A');
            }

            for ( int j = 0; j < LIMIT / STEP; j++ ) {
                writer.write(s.toString());
                writer.newLine();
            }
        }
        writer.close();
    }

    private static int compare(String s1, String s2) {
        int cnt = Math.abs(s1.length() - s2.length());
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if ( s1.charAt(i) != s2.charAt(i) ) {
                cnt++;
            }
        }

        return cnt;
    }

    private static void processData() throws IOException {
        File input = new File(INPUT_FILE_NAME);
        File output = new File(OUTPUT_FILE_NAME);

        BufferedReader reader1 = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedReader reader2 = new BufferedReader(new FileReader(OUTPUT_FILE_NAME));
        BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_NAME));

        double[] avg = new double[(int)(LIMIT/STEP)];

        for (int i = 0; i < ITERATIONS; i++) {
            for (int j = 0; j < LIMIT / STEP; j++ ) {
                String l1 = reader1.readLine();
                String l2 = reader2.readLine();
                int cnt = compare(l1, l2);
                avg[j] += (double)Math.min(l1.length(), cnt) / TEXT_LEN;
            }
        }

        for (int i = 0; i < avg.length; i++) {
            avg[i] /= ITERATIONS;
        }

        for ( double p = 0; p < LIMIT; p += STEP ) {
            writer.write(p + (p>=LIMIT-STEP?"":","));
        }

        writer.newLine();

        for ( int i = 0; i < avg.length; i++ ) {
            writer.write(avg[i] + (i==avg.length-1?"":","));
        }

        reader2.close();
        reader1.close();
        writer.close();
        input.delete();
        output.delete();
    }

    public static void main(String[] args) {
        try {
            generateData();

            BinarySymmetricChannel channel = new BinarySymmetricChannel(0);
            FileSource source = new FileSource(INPUT_FILE_NAME);
            FileDestination destination = new FileDestination(OUTPUT_FILE_NAME);

            BlockDiagram b = new BlockDiagram();
            b.addComponentPair(new LZWCoderComponent(), new LZWDecoderComponent());
            b.addComponentPair(new RepetitionCoderComponent(10), new RepetitionDecoderComponent(10));
            b.setChannel(channel);
            b.setSource(source);
            b.setDestination(destination);

            for ( int i = 0; i < ITERATIONS; i++) {
                double p = 0;
                for ( int j = 0; j < LIMIT / STEP; j++ ) {
                    channel.setCrossoverProbability(p);
                    p += STEP;
                    b.simulate();
                }
            }

            source.close();
            destination.close();

            processData();

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("python plot.py");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
