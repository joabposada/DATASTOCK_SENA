package co.edu.sena.datastock.service.mapper;

import static co.edu.sena.datastock.domain.HistorialRolUsuarioAsserts.*;
import static co.edu.sena.datastock.domain.HistorialRolUsuarioTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistorialRolUsuarioMapperTest {

    private HistorialRolUsuarioMapper historialRolUsuarioMapper;

    @BeforeEach
    void setUp() {
        historialRolUsuarioMapper = new HistorialRolUsuarioMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHistorialRolUsuarioSample1();
        var actual = historialRolUsuarioMapper.toEntity(historialRolUsuarioMapper.toDto(expected));
        assertHistorialRolUsuarioAllPropertiesEquals(expected, actual);
    }
}
