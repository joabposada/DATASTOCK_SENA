package co.edu.sena.datastock.web.rest;

import static co.edu.sena.datastock.domain.HistorialRolUsuarioAsserts.*;
import static co.edu.sena.datastock.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.datastock.IntegrationTest;
import co.edu.sena.datastock.domain.HistorialRolUsuario;
import co.edu.sena.datastock.domain.Rol;
import co.edu.sena.datastock.domain.Usuario;
import co.edu.sena.datastock.repository.HistorialRolUsuarioRepository;
import co.edu.sena.datastock.service.HistorialRolUsuarioService;
import co.edu.sena.datastock.service.dto.HistorialRolUsuarioDTO;
import co.edu.sena.datastock.service.mapper.HistorialRolUsuarioMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link HistorialRolUsuarioResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class HistorialRolUsuarioResourceIT {

    private static final LocalDate DEFAULT_FECHA_ASIGNACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ASIGNACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/historial-rol-usuarios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HistorialRolUsuarioRepository historialRolUsuarioRepository;

    @Mock
    private HistorialRolUsuarioRepository historialRolUsuarioRepositoryMock;

    @Autowired
    private HistorialRolUsuarioMapper historialRolUsuarioMapper;

    @Mock
    private HistorialRolUsuarioService historialRolUsuarioServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistorialRolUsuarioMockMvc;

    private HistorialRolUsuario historialRolUsuario;

    private HistorialRolUsuario insertedHistorialRolUsuario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialRolUsuario createEntity(EntityManager em) {
        HistorialRolUsuario historialRolUsuario = new HistorialRolUsuario()
            .fechaAsignacion(DEFAULT_FECHA_ASIGNACION)
            .estado(DEFAULT_ESTADO);
        // Add required entity
        Usuario usuario;
        if (TestUtil.findAll(em, Usuario.class).isEmpty()) {
            usuario = UsuarioResourceIT.createEntity();
            em.persist(usuario);
            em.flush();
        } else {
            usuario = TestUtil.findAll(em, Usuario.class).get(0);
        }
        historialRolUsuario.setUsuario(usuario);
        // Add required entity
        Rol rol;
        if (TestUtil.findAll(em, Rol.class).isEmpty()) {
            rol = RolResourceIT.createEntity();
            em.persist(rol);
            em.flush();
        } else {
            rol = TestUtil.findAll(em, Rol.class).get(0);
        }
        historialRolUsuario.setRol(rol);
        return historialRolUsuario;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialRolUsuario createUpdatedEntity(EntityManager em) {
        HistorialRolUsuario updatedHistorialRolUsuario = new HistorialRolUsuario()
            .fechaAsignacion(UPDATED_FECHA_ASIGNACION)
            .estado(UPDATED_ESTADO);
        // Add required entity
        Usuario usuario;
        if (TestUtil.findAll(em, Usuario.class).isEmpty()) {
            usuario = UsuarioResourceIT.createUpdatedEntity();
            em.persist(usuario);
            em.flush();
        } else {
            usuario = TestUtil.findAll(em, Usuario.class).get(0);
        }
        updatedHistorialRolUsuario.setUsuario(usuario);
        // Add required entity
        Rol rol;
        if (TestUtil.findAll(em, Rol.class).isEmpty()) {
            rol = RolResourceIT.createUpdatedEntity();
            em.persist(rol);
            em.flush();
        } else {
            rol = TestUtil.findAll(em, Rol.class).get(0);
        }
        updatedHistorialRolUsuario.setRol(rol);
        return updatedHistorialRolUsuario;
    }

    @BeforeEach
    void initTest() {
        historialRolUsuario = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedHistorialRolUsuario != null) {
            historialRolUsuarioRepository.delete(insertedHistorialRolUsuario);
            insertedHistorialRolUsuario = null;
        }
    }

    @Test
    @Transactional
    void createHistorialRolUsuario() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HistorialRolUsuario
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);
        var returnedHistorialRolUsuarioDTO = om.readValue(
            restHistorialRolUsuarioMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialRolUsuarioDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HistorialRolUsuarioDTO.class
        );

        // Validate the HistorialRolUsuario in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHistorialRolUsuario = historialRolUsuarioMapper.toEntity(returnedHistorialRolUsuarioDTO);
        assertHistorialRolUsuarioUpdatableFieldsEquals(
            returnedHistorialRolUsuario,
            getPersistedHistorialRolUsuario(returnedHistorialRolUsuario)
        );

        insertedHistorialRolUsuario = returnedHistorialRolUsuario;
    }

    @Test
    @Transactional
    void createHistorialRolUsuarioWithExistingId() throws Exception {
        // Create the HistorialRolUsuario with an existing ID
        historialRolUsuario.setId(1L);
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistorialRolUsuarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialRolUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFechaAsignacionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        historialRolUsuario.setFechaAsignacion(null);

        // Create the HistorialRolUsuario, which fails.
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        restHistorialRolUsuarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialRolUsuarioDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEstadoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        historialRolUsuario.setEstado(null);

        // Create the HistorialRolUsuario, which fails.
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        restHistorialRolUsuarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialRolUsuarioDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllHistorialRolUsuarios() throws Exception {
        // Initialize the database
        insertedHistorialRolUsuario = historialRolUsuarioRepository.saveAndFlush(historialRolUsuario);

        // Get all the historialRolUsuarioList
        restHistorialRolUsuarioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historialRolUsuario.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaAsignacion").value(hasItem(DEFAULT_FECHA_ASIGNACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllHistorialRolUsuariosWithEagerRelationshipsIsEnabled() throws Exception {
        when(historialRolUsuarioServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restHistorialRolUsuarioMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(historialRolUsuarioServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllHistorialRolUsuariosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(historialRolUsuarioServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restHistorialRolUsuarioMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(historialRolUsuarioRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getHistorialRolUsuario() throws Exception {
        // Initialize the database
        insertedHistorialRolUsuario = historialRolUsuarioRepository.saveAndFlush(historialRolUsuario);

        // Get the historialRolUsuario
        restHistorialRolUsuarioMockMvc
            .perform(get(ENTITY_API_URL_ID, historialRolUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historialRolUsuario.getId().intValue()))
            .andExpect(jsonPath("$.fechaAsignacion").value(DEFAULT_FECHA_ASIGNACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }

    @Test
    @Transactional
    void getNonExistingHistorialRolUsuario() throws Exception {
        // Get the historialRolUsuario
        restHistorialRolUsuarioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHistorialRolUsuario() throws Exception {
        // Initialize the database
        insertedHistorialRolUsuario = historialRolUsuarioRepository.saveAndFlush(historialRolUsuario);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the historialRolUsuario
        HistorialRolUsuario updatedHistorialRolUsuario = historialRolUsuarioRepository.findById(historialRolUsuario.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHistorialRolUsuario are not directly saved in db
        em.detach(updatedHistorialRolUsuario);
        updatedHistorialRolUsuario.fechaAsignacion(UPDATED_FECHA_ASIGNACION).estado(UPDATED_ESTADO);
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(updatedHistorialRolUsuario);

        restHistorialRolUsuarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, historialRolUsuarioDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(historialRolUsuarioDTO))
            )
            .andExpect(status().isOk());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHistorialRolUsuarioToMatchAllProperties(updatedHistorialRolUsuario);
    }

    @Test
    @Transactional
    void putNonExistingHistorialRolUsuario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialRolUsuario.setId(longCount.incrementAndGet());

        // Create the HistorialRolUsuario
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistorialRolUsuarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, historialRolUsuarioDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(historialRolUsuarioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHistorialRolUsuario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialRolUsuario.setId(longCount.incrementAndGet());

        // Create the HistorialRolUsuario
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialRolUsuarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(historialRolUsuarioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHistorialRolUsuario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialRolUsuario.setId(longCount.incrementAndGet());

        // Create the HistorialRolUsuario
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialRolUsuarioMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialRolUsuarioDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHistorialRolUsuarioWithPatch() throws Exception {
        // Initialize the database
        insertedHistorialRolUsuario = historialRolUsuarioRepository.saveAndFlush(historialRolUsuario);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the historialRolUsuario using partial update
        HistorialRolUsuario partialUpdatedHistorialRolUsuario = new HistorialRolUsuario();
        partialUpdatedHistorialRolUsuario.setId(historialRolUsuario.getId());

        partialUpdatedHistorialRolUsuario.fechaAsignacion(UPDATED_FECHA_ASIGNACION).estado(UPDATED_ESTADO);

        restHistorialRolUsuarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistorialRolUsuario.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHistorialRolUsuario))
            )
            .andExpect(status().isOk());

        // Validate the HistorialRolUsuario in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHistorialRolUsuarioUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHistorialRolUsuario, historialRolUsuario),
            getPersistedHistorialRolUsuario(historialRolUsuario)
        );
    }

    @Test
    @Transactional
    void fullUpdateHistorialRolUsuarioWithPatch() throws Exception {
        // Initialize the database
        insertedHistorialRolUsuario = historialRolUsuarioRepository.saveAndFlush(historialRolUsuario);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the historialRolUsuario using partial update
        HistorialRolUsuario partialUpdatedHistorialRolUsuario = new HistorialRolUsuario();
        partialUpdatedHistorialRolUsuario.setId(historialRolUsuario.getId());

        partialUpdatedHistorialRolUsuario.fechaAsignacion(UPDATED_FECHA_ASIGNACION).estado(UPDATED_ESTADO);

        restHistorialRolUsuarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistorialRolUsuario.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHistorialRolUsuario))
            )
            .andExpect(status().isOk());

        // Validate the HistorialRolUsuario in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHistorialRolUsuarioUpdatableFieldsEquals(
            partialUpdatedHistorialRolUsuario,
            getPersistedHistorialRolUsuario(partialUpdatedHistorialRolUsuario)
        );
    }

    @Test
    @Transactional
    void patchNonExistingHistorialRolUsuario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialRolUsuario.setId(longCount.incrementAndGet());

        // Create the HistorialRolUsuario
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistorialRolUsuarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, historialRolUsuarioDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(historialRolUsuarioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHistorialRolUsuario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialRolUsuario.setId(longCount.incrementAndGet());

        // Create the HistorialRolUsuario
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialRolUsuarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(historialRolUsuarioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHistorialRolUsuario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialRolUsuario.setId(longCount.incrementAndGet());

        // Create the HistorialRolUsuario
        HistorialRolUsuarioDTO historialRolUsuarioDTO = historialRolUsuarioMapper.toDto(historialRolUsuario);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialRolUsuarioMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(historialRolUsuarioDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistorialRolUsuario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHistorialRolUsuario() throws Exception {
        // Initialize the database
        insertedHistorialRolUsuario = historialRolUsuarioRepository.saveAndFlush(historialRolUsuario);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the historialRolUsuario
        restHistorialRolUsuarioMockMvc
            .perform(delete(ENTITY_API_URL_ID, historialRolUsuario.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return historialRolUsuarioRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected HistorialRolUsuario getPersistedHistorialRolUsuario(HistorialRolUsuario historialRolUsuario) {
        return historialRolUsuarioRepository.findById(historialRolUsuario.getId()).orElseThrow();
    }

    protected void assertPersistedHistorialRolUsuarioToMatchAllProperties(HistorialRolUsuario expectedHistorialRolUsuario) {
        assertHistorialRolUsuarioAllPropertiesEquals(
            expectedHistorialRolUsuario,
            getPersistedHistorialRolUsuario(expectedHistorialRolUsuario)
        );
    }

    protected void assertPersistedHistorialRolUsuarioToMatchUpdatableProperties(HistorialRolUsuario expectedHistorialRolUsuario) {
        assertHistorialRolUsuarioAllUpdatablePropertiesEquals(
            expectedHistorialRolUsuario,
            getPersistedHistorialRolUsuario(expectedHistorialRolUsuario)
        );
    }
}
