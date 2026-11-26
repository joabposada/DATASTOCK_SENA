package co.edu.sena.datastock.service.mapper;

import static co.edu.sena.datastock.domain.InventarioActualAsserts.*;
import static co.edu.sena.datastock.domain.InventarioActualTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventarioActualMapperTest {

    private InventarioActualMapper inventarioActualMapper;

    @BeforeEach
    void setUp() {
        inventarioActualMapper = new InventarioActualMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getInventarioActualSample1();
        var actual = inventarioActualMapper.toEntity(inventarioActualMapper.toDto(expected));
        assertInventarioActualAllPropertiesEquals(expected, actual);
    }
}
