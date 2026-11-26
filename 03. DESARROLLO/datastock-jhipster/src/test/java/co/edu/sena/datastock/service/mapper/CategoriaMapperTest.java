package co.edu.sena.datastock.service.mapper;

import static co.edu.sena.datastock.domain.CategoriaAsserts.*;
import static co.edu.sena.datastock.domain.CategoriaTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoriaMapperTest {

    private CategoriaMapper categoriaMapper;

    @BeforeEach
    void setUp() {
        categoriaMapper = new CategoriaMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCategoriaSample1();
        var actual = categoriaMapper.toEntity(categoriaMapper.toDto(expected));
        assertCategoriaAllPropertiesEquals(expected, actual);
    }
}
