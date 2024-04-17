package Algorithms;

import Data.BinaryData;
import Data.SymbolData;

import java.util.*;


public class LZW {

    private static final int DICTIONARY_SIZE = 512;
    private static final int DICTIONARY_SIZE_BITS = (int)(Math.log(DICTIONARY_SIZE) / Math.log(2));


    public static BinaryData encode(SymbolData text) {
        int size = 26, index = 0;
        HashMap<String, Integer> dictionary = new HashMap<>();
        BinaryData codes = new BinaryData();

        for ( int i = 0; i < size; i++ ) {
            dictionary.put(Character.toString('A' + i), i);
        }

        String k = "";
        for ( char w : text.gatData() ) {
            String kw = k + w;
            if ( dictionary.containsKey(kw) ) {
                k = kw;
            } else {
                int code = dictionary.get(k);
                for (int j = 0; j < DICTIONARY_SIZE_BITS; j++) {
                    if ( (code & (0x1 << j)) != 0 ) {
                        codes.getData().set(index*DICTIONARY_SIZE_BITS + j);
                    }
                }
                index++;
                if ( size < DICTIONARY_SIZE ) {
                    dictionary.put(kw, size++);
                }
                k = Character.toString(w);
            }

        }

        if ( !k.isEmpty() ) {
            int code = dictionary.get(k);
            for (int j = 0; j < DICTIONARY_SIZE_BITS; j++) {
                if ( (code & (0x1 << j)) != 0 ) {
                    codes.getData().set(index*DICTIONARY_SIZE_BITS + j);
                }
            }
            index++;
        }

        codes.setSize(index * DICTIONARY_SIZE_BITS);

        return codes;
    }


    public static SymbolData decode(BinaryData b) {
        int size = 26, index = 1;
        HashMap<Integer, String> dictionary = new HashMap<>();

        for (int i = 0; i < size; i++) {
            dictionary.put(i, Character.toString('A' + i));
        }

        int firstCode = 0;
        for (int j = 0; j < DICTIONARY_SIZE_BITS; j++) {
            if (b.getData().get(j)) {
                firstCode |= 1 << j;
            }
        }

        String k = dictionary.get(firstCode);
        if ( k == null ) k = "";
        StringBuilder text =  new StringBuilder(k);

        while( index < b.getSize() / DICTIONARY_SIZE_BITS ) {
            int code = 0;
            for (int j = 0; j < DICTIONARY_SIZE_BITS; j++) {
                if (b.getData().get(index * DICTIONARY_SIZE_BITS + j)) {
                    code |= 1 << j;
                }
            }
            index++;

            String kw = "";
            if (dictionary.containsKey(code)) {
                kw = dictionary.get(code);
                text.append(kw);
                if ( size < DICTIONARY_SIZE ) {
                    dictionary.put(size++, k + (kw.isEmpty()?"":kw.charAt(0)));
                }
            } else {
                kw = k + (k.isEmpty()?"":k.charAt(0));
                text.append(kw);
                if ( size < DICTIONARY_SIZE ) {
                    dictionary.put(size++, kw);
                }
            }
            k = kw;
        }

        return new SymbolData(text.toString());
    }

    public static void main(String[] args) {
        SymbolData s = new SymbolData("KILOGOREKILODOLE");
        BinaryData compressed = encode(s);
        System.out.println("Compressed: " + compressed.toString());
        SymbolData decompressed = decode(compressed);
        System.out.println("Decompressed: " + decompressed.toString());
    }
}
