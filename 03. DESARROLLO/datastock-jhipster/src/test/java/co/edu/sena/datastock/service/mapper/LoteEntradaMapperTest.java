package co.edu.sena.datastock.service.mapper;

import static co.edu.sena.datastock.domain.LoteEntradaAsserts.*;
import static co.edu.sena.datastock.domain.LoteEntradaTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoteEntradaMapperTest {

    private LoteEntradaMapper loteEntradaMapper;

    @BeforeEach
    void setUp() {
        loteEntradaMapper = new LoteEntradaMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLoteEntradaSample1();
        var actual = loteEntradaMapper.toEntity(loteEntradaMapper.toDto(expected));
        assertLoteEntradaAllPropertiesEquals(expected, actual);
    }
}
