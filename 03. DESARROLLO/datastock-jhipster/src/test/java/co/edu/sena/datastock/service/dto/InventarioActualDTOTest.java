package co.edu.sena.datastock.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InventarioActualDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InventarioActualDTO.class);
        InventarioActualDTO inventarioActualDTO1 = new InventarioActualDTO();
        inventarioActualDTO1.setId(1L);
        InventarioActualDTO inventarioActualDTO2 = new InventarioActualDTO();
        assertThat(inventarioActualDTO1).isNotEqualTo(inventarioActualDTO2);
        inventarioActualDTO2.setId(inventarioActualDTO1.getId());
        assertThat(inventarioActualDTO1).isEqualTo(inventarioActualDTO2);
        inventarioActualDTO2.setId(2L);
        assertThat(inventarioActualDTO1).isNotEqualTo(inventarioActualDTO2);
        inventarioActualDTO1.setId(null);
        assertThat(inventarioActualDTO1).isNotEqualTo(inventarioActualDTO2);
    }
}
