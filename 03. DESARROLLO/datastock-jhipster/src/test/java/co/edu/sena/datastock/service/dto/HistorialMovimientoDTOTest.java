package co.edu.sena.datastock.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistorialMovimientoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialMovimientoDTO.class);
        HistorialMovimientoDTO historialMovimientoDTO1 = new HistorialMovimientoDTO();
        historialMovimientoDTO1.setId(1L);
        HistorialMovimientoDTO historialMovimientoDTO2 = new HistorialMovimientoDTO();
        assertThat(historialMovimientoDTO1).isNotEqualTo(historialMovimientoDTO2);
        historialMovimientoDTO2.setId(historialMovimientoDTO1.getId());
        assertThat(historialMovimientoDTO1).isEqualTo(historialMovimientoDTO2);
        historialMovimientoDTO2.setId(2L);
        assertThat(historialMovimientoDTO1).isNotEqualTo(historialMovimientoDTO2);
        historialMovimientoDTO1.setId(null);
        assertThat(historialMovimientoDTO1).isNotEqualTo(historialMovimientoDTO2);
    }
}
