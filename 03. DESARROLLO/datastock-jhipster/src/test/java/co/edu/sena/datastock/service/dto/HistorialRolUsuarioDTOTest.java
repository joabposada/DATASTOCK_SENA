package co.edu.sena.datastock.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistorialRolUsuarioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialRolUsuarioDTO.class);
        HistorialRolUsuarioDTO historialRolUsuarioDTO1 = new HistorialRolUsuarioDTO();
        historialRolUsuarioDTO1.setId(1L);
        HistorialRolUsuarioDTO historialRolUsuarioDTO2 = new HistorialRolUsuarioDTO();
        assertThat(historialRolUsuarioDTO1).isNotEqualTo(historialRolUsuarioDTO2);
        historialRolUsuarioDTO2.setId(historialRolUsuarioDTO1.getId());
        assertThat(historialRolUsuarioDTO1).isEqualTo(historialRolUsuarioDTO2);
        historialRolUsuarioDTO2.setId(2L);
        assertThat(historialRolUsuarioDTO1).isNotEqualTo(historialRolUsuarioDTO2);
        historialRolUsuarioDTO1.setId(null);
        assertThat(historialRolUsuarioDTO1).isNotEqualTo(historialRolUsuarioDTO2);
    }
}
