package co.edu.sena.datastock.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LoteEntradaTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static LoteEntrada getLoteEntradaSample1() {
        return new LoteEntrada().id(1L).cantidadIngresada(1).facturaProveedor("facturaProveedor1");
    }

    public static LoteEntrada getLoteEntradaSample2() {
        return new LoteEntrada().id(2L).cantidadIngresada(2).facturaProveedor("facturaProveedor2");
    }

    public static LoteEntrada getLoteEntradaRandomSampleGenerator() {
        return new LoteEntrada()
            .id(longCount.incrementAndGet())
            .cantidadIngresada(intCount.incrementAndGet())
            .facturaProveedor(UUID.randomUUID().toString());
    }
}
