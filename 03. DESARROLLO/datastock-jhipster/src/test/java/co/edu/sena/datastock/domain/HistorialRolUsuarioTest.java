package co.edu.sena.datastock.domain;

import static co.edu.sena.datastock.domain.HistorialRolUsuarioTestSamples.*;
import static co.edu.sena.datastock.domain.RolTestSamples.*;
import static co.edu.sena.datastock.domain.UsuarioTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistorialRolUsuarioTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialRolUsuario.class);
        HistorialRolUsuario historialRolUsuario1 = getHistorialRolUsuarioSample1();
        HistorialRolUsuario historialRolUsuario2 = new HistorialRolUsuario();
        assertThat(historialRolUsuario1).isNotEqualTo(historialRolUsuario2);

        historialRolUsuario2.setId(historialRolUsuario1.getId());
        assertThat(historialRolUsuario1).isEqualTo(historialRolUsuario2);

        historialRolUsuario2 = getHistorialRolUsuarioSample2();
        assertThat(historialRolUsuario1).isNotEqualTo(historialRolUsuario2);
    }

    @Test
    void usuarioTest() {
        HistorialRolUsuario historialRolUsuario = getHistorialRolUsuarioRandomSampleGenerator();
        Usuario usuarioBack = getUsuarioRandomSampleGenerator();

        historialRolUsuario.setUsuario(usuarioBack);
        assertThat(historialRolUsuario.getUsuario()).isEqualTo(usuarioBack);

        historialRolUsuario.usuario(null);
        assertThat(historialRolUsuario.getUsuario()).isNull();
    }

    @Test
    void rolTest() {
        HistorialRolUsuario historialRolUsuario = getHistorialRolUsuarioRandomSampleGenerator();
        Rol rolBack = getRolRandomSampleGenerator();

        historialRolUsuario.setRol(rolBack);
        assertThat(historialRolUsuario.getRol()).isEqualTo(rolBack);

        historialRolUsuario.rol(null);
        assertThat(historialRolUsuario.getRol()).isNull();
    }
}
