package co.edu.sena.datastock.service.mapper;

import static co.edu.sena.datastock.domain.RolAsserts.*;
import static co.edu.sena.datastock.domain.RolTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RolMapperTest {

    private RolMapper rolMapper;

    @BeforeEach
    void setUp() {
        rolMapper = new RolMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getRolSample1();
        var actual = rolMapper.toEntity(rolMapper.toDto(expected));
        assertRolAllPropertiesEquals(expected, actual);
    }
}
