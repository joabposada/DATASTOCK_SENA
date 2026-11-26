package co.edu.sena.datastock.domain;

import static co.edu.sena.datastock.domain.LoteEntradaTestSamples.*;
import static co.edu.sena.datastock.domain.ProductoTestSamples.*;
import static co.edu.sena.datastock.domain.SitioTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoteEntradaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoteEntrada.class);
        LoteEntrada loteEntrada1 = getLoteEntradaSample1();
        LoteEntrada loteEntrada2 = new LoteEntrada();
        assertThat(loteEntrada1).isNotEqualTo(loteEntrada2);

        loteEntrada2.setId(loteEntrada1.getId());
        assertThat(loteEntrada1).isEqualTo(loteEntrada2);

        loteEntrada2 = getLoteEntradaSample2();
        assertThat(loteEntrada1).isNotEqualTo(loteEntrada2);
    }

    @Test
    void productoTest() {
        LoteEntrada loteEntrada = getLoteEntradaRandomSampleGenerator();
        Producto productoBack = getProductoRandomSampleGenerator();

        loteEntrada.setProducto(productoBack);
        assertThat(loteEntrada.getProducto()).isEqualTo(productoBack);

        loteEntrada.producto(null);
        assertThat(loteEntrada.getProducto()).isNull();
    }

    @Test
    void sitioTest() {
        LoteEntrada loteEntrada = getLoteEntradaRandomSampleGenerator();
        Sitio sitioBack = getSitioRandomSampleGenerator();

        loteEntrada.setSitio(sitioBack);
        assertThat(loteEntrada.getSitio()).isEqualTo(sitioBack);

        loteEntrada.sitio(null);
        assertThat(loteEntrada.getSitio()).isNull();
    }
}
