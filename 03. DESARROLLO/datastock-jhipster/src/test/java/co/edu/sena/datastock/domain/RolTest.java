package co.edu.sena.datastock.domain;

import static co.edu.sena.datastock.domain.RolTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.datastock.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RolTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rol.class);
        Rol rol1 = getRolSample1();
        Rol rol2 = new Rol();
        assertThat(rol1).isNotEqualTo(rol2);

        rol2.setId(rol1.getId());
        assertThat(rol1).isEqualTo(rol2);

        rol2 = getRolSample2();
        assertThat(rol1).isNotEqualTo(rol2);
    }
}
