package co.edu.sena.datastock.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HistorialMovimientoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static HistorialMovimiento getHistorialMovimientoSample1() {
        return new HistorialMovimiento().id(1L).idSitioDestino(1).tipoMovimiento("tipoMovimiento1").cantidad(1);
    }

    public static HistorialMovimiento getHistorialMovimientoSample2() {
        return new HistorialMovimiento().id(2L).idSitioDestino(2).tipoMovimiento("tipoMovimiento2").cantidad(2);
    }

    public static HistorialMovimiento getHistorialMovimientoRandomSampleGenerator() {
        return new HistorialMovimiento()
            .id(longCount.incrementAndGet())
            .idSitioDestino(intCount.incrementAndGet())
            .tipoMovimiento(UUID.randomUUID().toString())
            .cantidad(intCount.incrementAndGet());
    }
}
