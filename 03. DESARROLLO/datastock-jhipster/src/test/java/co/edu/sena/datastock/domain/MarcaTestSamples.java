package co.edu.sena.datastock.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MarcaTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Marca getMarcaSample1() {
        return new Marca().id(1L).nombreMarca("nombreMarca1");
    }

    public static Marca getMarcaSample2() {
        return new Marca().id(2L).nombreMarca("nombreMarca2");
    }

    public static Marca getMarcaRandomSampleGenerator() {
        return new Marca().id(longCount.incrementAndGet()).nombreMarca(UUID.randomUUID().toString());
    }
}
