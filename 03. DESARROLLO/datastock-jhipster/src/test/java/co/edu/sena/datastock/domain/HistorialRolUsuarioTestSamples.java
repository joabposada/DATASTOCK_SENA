package co.edu.sena.datastock.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class HistorialRolUsuarioTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static HistorialRolUsuario getHistorialRolUsuarioSample1() {
        return new HistorialRolUsuario().id(1L).estado("estado1");
    }

    public static HistorialRolUsuario getHistorialRolUsuarioSample2() {
        return new HistorialRolUsuario().id(2L).estado("estado2");
    }

    public static HistorialRolUsuario getHistorialRolUsuarioRandomSampleGenerator() {
        return new HistorialRolUsuario().id(longCount.incrementAndGet()).estado(UUID.randomUUID().toString());
    }
}
