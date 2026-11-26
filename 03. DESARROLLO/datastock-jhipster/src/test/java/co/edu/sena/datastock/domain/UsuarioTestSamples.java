package co.edu.sena.datastock.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UsuarioTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Usuario getUsuarioSample1() {
        return new Usuario()
            .id(1L)
            .numeroDocumento("numeroDocumento1")
            .nombre("nombre1")
            .apellido("apellido1")
            .celular("celular1")
            .correo("correo1")
            .password("password1");
    }

    public static Usuario getUsuarioSample2() {
        return new Usuario()
            .id(2L)
            .numeroDocumento("numeroDocumento2")
            .nombre("nombre2")
            .apellido("apellido2")
            .celular("celular2")
            .correo("correo2")
            .password("password2");
    }

    public static Usuario getUsuarioRandomSampleGenerator() {
        return new Usuario()
            .id(longCount.incrementAndGet())
            .numeroDocumento(UUID.randomUUID().toString())
            .nombre(UUID.randomUUID().toString())
            .apellido(UUID.randomUUID().toString())
            .celular(UUID.randomUUID().toString())
            .correo(UUID.randomUUID().toString())
            .password(UUID.randomUUID().toString());
    }
}
