package co.edu.sena.datastock.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InventarioActualTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static InventarioActual getInventarioActualSample1() {
        return new InventarioActual().id(1L).stockDisponible(1);
    }

    public static InventarioActual getInventarioActualSample2() {
        return new InventarioActual().id(2L).stockDisponible(2);
    }

    public static InventarioActual getInventarioActualRandomSampleGenerator() {
        return new InventarioActual().id(longCount.incrementAndGet()).stockDisponible(intCount.incrementAndGet());
    }
}
