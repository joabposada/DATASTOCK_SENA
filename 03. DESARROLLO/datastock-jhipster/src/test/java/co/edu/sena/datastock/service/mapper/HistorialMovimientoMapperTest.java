package co.edu.sena.datastock.service.mapper;

import static co.edu.sena.datastock.domain.HistorialMovimientoAsserts.*;
import static co.edu.sena.datastock.domain.HistorialMovimientoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistorialMovimientoMapperTest {

    private HistorialMovimientoMapper historialMovimientoMapper;

    @BeforeEach
    void setUp() {
        historialMovimientoMapper = new HistorialMovimientoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHistorialMovimientoSample1();
        var actual = historialMovimientoMapper.toEntity(historialMovimientoMapper.toDto(expected));
        assertHistorialMovimientoAllPropertiesEquals(expected, actual);
    }
}
