package co.edu.sena.datastock.domain;

import static co.edu.sena.datastock.domain.InventarioActualTestSamples.*;
import static co.edu.sena.datastock.domain.ProductoTestSamples.*;
import static co.edu.sena.datastock.domain.SitioTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InventarioActualTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InventarioActual.class);
        InventarioActual inventarioActual1 = getInventarioActualSample1();
        InventarioActual inventarioActual2 = new InventarioActual();
        assertThat(inventarioActual1).isNotEqualTo(inventarioActual2);

        inventarioActual2.setId(inventarioActual1.getId());
        assertThat(inventarioActual1).isEqualTo(inventarioActual2);

        inventarioActual2 = getInventarioActualSample2();
        assertThat(inventarioActual1).isNotEqualTo(inventarioActual2);
    }

    @Test
    void productoTest() {
        InventarioActual inventarioActual = getInventarioActualRandomSampleGenerator();
        Producto productoBack = getProductoRandomSampleGenerator();

        inventarioActual.setProducto(productoBack);
        assertThat(inventarioActual.getProducto()).isEqualTo(productoBack);

        inventarioActual.producto(null);
        assertThat(inventarioActual.getProducto()).isNull();
    }

    @Test
    void sitioTest() {
        InventarioActual inventarioActual = getInventarioActualRandomSampleGenerator();
        Sitio sitioBack = getSitioRandomSampleGenerator();

        inventarioActual.setSitio(sitioBack);
        assertThat(inventarioActual.getSitio()).isEqualTo(sitioBack);

        inventarioActual.sitio(null);
        assertThat(inventarioActual.getSitio()).isNull();
    }
}
