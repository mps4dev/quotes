package pl.mps.quotes.utils;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static long nextLong(long n) {
        long bits, val;
        do {
            bits = (RANDOM.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }

    public static long nextLong(long start, long end) {
        return (long)(RANDOM.nextDouble() * (end - start));
    }

}
