package co.edu.sena.datastock.service.mapper;

import static co.edu.sena.datastock.domain.MarcaAsserts.*;
import static co.edu.sena.datastock.domain.MarcaTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarcaMapperTest {

    private MarcaMapper marcaMapper;

    @BeforeEach
    void setUp() {
        marcaMapper = new MarcaMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getMarcaSample1();
        var actual = marcaMapper.toEntity(marcaMapper.toDto(expected));
        assertMarcaAllPropertiesEquals(expected, actual);
    }
}
