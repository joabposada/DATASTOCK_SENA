package co.edu.sena.datastock.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SitioTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Sitio getSitioSample1() {
        return new Sitio().id(1L).nombreSitio("nombreSitio1").tipoSitio("tipoSitio1").direccion("direccion1");
    }

    public static Sitio getSitioSample2() {
        return new Sitio().id(2L).nombreSitio("nombreSitio2").tipoSitio("tipoSitio2").direccion("direccion2");
    }

    public static Sitio getSitioRandomSampleGenerator() {
        return new Sitio()
            .id(longCount.incrementAndGet())
            .nombreSitio(UUID.randomUUID().toString())
            .tipoSitio(UUID.randomUUID().toString())
            .direccion(UUID.randomUUID().toString());
    }
}
