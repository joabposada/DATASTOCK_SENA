package co.edu.sena.datastock.domain;

import static co.edu.sena.datastock.domain.HistorialMovimientoTestSamples.*;
import static co.edu.sena.datastock.domain.ProductoTestSamples.*;
import static co.edu.sena.datastock.domain.SitioTestSamples.*;
import static co.edu.sena.datastock.domain.UsuarioTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistorialMovimientoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialMovimiento.class);
        HistorialMovimiento historialMovimiento1 = getHistorialMovimientoSample1();
        HistorialMovimiento historialMovimiento2 = new HistorialMovimiento();
        assertThat(historialMovimiento1).isNotEqualTo(historialMovimiento2);

        historialMovimiento2.setId(historialMovimiento1.getId());
        assertThat(historialMovimiento1).isEqualTo(historialMovimiento2);

        historialMovimiento2 = getHistorialMovimientoSample2();
        assertThat(historialMovimiento1).isNotEqualTo(historialMovimiento2);
    }

    @Test
    void productoTest() {
        HistorialMovimiento historialMovimiento = getHistorialMovimientoRandomSampleGenerator();
        Producto productoBack = getProductoRandomSampleGenerator();

        historialMovimiento.setProducto(productoBack);
        assertThat(historialMovimiento.getProducto()).isEqualTo(productoBack);

        historialMovimiento.producto(null);
        assertThat(historialMovimiento.getProducto()).isNull();
    }

    @Test
    void sitioTest() {
        HistorialMovimiento historialMovimiento = getHistorialMovimientoRandomSampleGenerator();
        Sitio sitioBack = getSitioRandomSampleGenerator();

        historialMovimiento.setSitio(sitioBack);
        assertThat(historialMovimiento.getSitio()).isEqualTo(sitioBack);

        historialMovimiento.sitio(null);
        assertThat(historialMovimiento.getSitio()).isNull();
    }

    @Test
    void usuarioTest() {
        HistorialMovimiento historialMovimiento = getHistorialMovimientoRandomSampleGenerator();
        Usuario usuarioBack = getUsuarioRandomSampleGenerator();

        historialMovimiento.setUsuario(usuarioBack);
        assertThat(historialMovimiento.getUsuario()).isEqualTo(usuarioBack);

        historialMovimiento.usuario(null);
        assertThat(historialMovimiento.getUsuario()).isNull();
    }
}
