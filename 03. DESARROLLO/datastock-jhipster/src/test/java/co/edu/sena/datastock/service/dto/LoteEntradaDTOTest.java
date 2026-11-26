package co.edu.sena.datastock.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoteEntradaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoteEntradaDTO.class);
        LoteEntradaDTO loteEntradaDTO1 = new LoteEntradaDTO();
        loteEntradaDTO1.setId(1L);
        LoteEntradaDTO loteEntradaDTO2 = new LoteEntradaDTO();
        assertThat(loteEntradaDTO1).isNotEqualTo(loteEntradaDTO2);
        loteEntradaDTO2.setId(loteEntradaDTO1.getId());
        assertThat(loteEntradaDTO1).isEqualTo(loteEntradaDTO2);
        loteEntradaDTO2.setId(2L);
        assertThat(loteEntradaDTO1).isNotEqualTo(loteEntradaDTO2);
        loteEntradaDTO1.setId(null);
        assertThat(loteEntradaDTO1).isNotEqualTo(loteEntradaDTO2);
    }
}
