package co.edu.sena.datastock.web.rest;

import static co.edu.sena.datastock.domain.HistorialMovimientoAsserts.*;
import static co.edu.sena.datastock.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.datastock.IntegrationTest;
import co.edu.sena.datastock.domain.HistorialMovimiento;
import co.edu.sena.datastock.domain.Producto;
import co.edu.sena.datastock.domain.Sitio;
import co.edu.sena.datastock.domain.Usuario;
import co.edu.sena.datastock.repository.HistorialMovimientoRepository;
import co.edu.sena.datastock.service.HistorialMovimientoService;
import co.edu.sena.datastock.service.dto.HistorialMovimientoDTO;
import co.edu.sena.datastock.service.mapper.HistorialMovimientoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link HistorialMovimientoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class HistorialMovimientoResourceIT {

    private static final Integer DEFAULT_ID_SITIO_DESTINO = 1;
    private static final Integer UPDATED_ID_SITIO_DESTINO = 2;

    private static final String DEFAULT_TIPO_MOVIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_MOVIMIENTO = "BBBBBBBBBB";

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

    private static final Instant DEFAULT_FECHA_HORA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_HORA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/historial-movimientos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HistorialMovimientoRepository historialMovimientoRepository;

    @Mock
    private HistorialMovimientoRepository historialMovimientoRepositoryMock;

    @Autowired
    private HistorialMovimientoMapper historialMovimientoMapper;

    @Mock
    private HistorialMovimientoService historialMovimientoServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistorialMovimientoMockMvc;

    private HistorialMovimiento historialMovimiento;

    private HistorialMovimiento insertedHistorialMovimiento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialMovimiento createEntity(EntityManager em) {
        HistorialMovimiento historialMovimiento = new HistorialMovimiento()
            .idSitioDestino(DEFAULT_ID_SITIO_DESTINO)
            .tipoMovimiento(DEFAULT_TIPO_MOVIMIENTO)
            .cantidad(DEFAULT_CANTIDAD)
            .fechaHora(DEFAULT_FECHA_HORA);
        // Add required entity
        Producto producto;
        if (TestUtil.findAll(em, Producto.class).isEmpty()) {
            producto = ProductoResourceIT.createEntity(em);
            em.persist(producto);
            em.flush();
        } else {
            producto = TestUtil.findAll(em, Producto.class).get(0);
        }
        historialMovimiento.setProducto(producto);
        // Add required entity
        Sitio sitio;
        if (TestUtil.findAll(em, Sitio.class).isEmpty()) {
            sitio = SitioResourceIT.createEntity();
            em.persist(sitio);
            em.flush();
        } else {
            sitio = TestUtil.findAll(em, Sitio.class).get(0);
        }
        historialMovimiento.setSitio(sitio);
        // Add required entity
        Usuario usuario;
        if (TestUtil.findAll(em, Usuario.class).isEmpty()) {
            usuario = UsuarioResourceIT.createEntity();
            em.persist(usuario);
            em.flush();
        } else {
            usuario = TestUtil.findAll(em, Usuario.class).get(0);
        }
        historialMovimiento.setUsuario(usuario);
        return historialMovimiento;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialMovimiento createUpdatedEntity(EntityManager em) {
        HistorialMovimiento updatedHistorialMovimiento = new HistorialMovimiento()
            .idSitioDestino(UPDATED_ID_SITIO_DESTINO)
            .tipoMovimiento(UPDATED_TIPO_MOVIMIENTO)
            .cantidad(UPDATED_CANTIDAD)
            .fechaHora(UPDATED_FECHA_HORA);
        // Add required entity
        Producto producto;
        if (TestUtil.findAll(em, Producto.class).isEmpty()) {
            producto = ProductoResourceIT.createUpdatedEntity(em);
            em.persist(producto);
            em.flush();
        } else {
            producto = TestUtil.findAll(em, Producto.class).get(0);
        }
        updatedHistorialMovimiento.setProducto(producto);
        // Add required entity
        Sitio sitio;
        if (TestUtil.findAll(em, Sitio.class).isEmpty()) {
            sitio = SitioResourceIT.createUpdatedEntity();
            em.persist(sitio);
            em.flush();
        } else {
            sitio = TestUtil.findAll(em, Sitio.class).get(0);
        }
        updatedHistorialMovimiento.setSitio(sitio);
        // Add required entity
        Usuario usuario;
        if (TestUtil.findAll(em, Usuario.class).isEmpty()) {
            usuario = UsuarioResourceIT.createUpdatedEntity();
            em.persist(usuario);
            em.flush();
        } else {
            usuario = TestUtil.findAll(em, Usuario.class).get(0);
        }
        updatedHistorialMovimiento.setUsuario(usuario);
        return updatedHistorialMovimiento;
    }

    @BeforeEach
    void initTest() {
        historialMovimiento = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedHistorialMovimiento != null) {
            historialMovimientoRepository.delete(insertedHistorialMovimiento);
            insertedHistorialMovimiento = null;
        }
    }

    @Test
    @Transactional
    void createHistorialMovimiento() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HistorialMovimiento
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);
        var returnedHistorialMovimientoDTO = om.readValue(
            restHistorialMovimientoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialMovimientoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HistorialMovimientoDTO.class
        );

        // Validate the HistorialMovimiento in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHistorialMovimiento = historialMovimientoMapper.toEntity(returnedHistorialMovimientoDTO);
        assertHistorialMovimientoUpdatableFieldsEquals(
            returnedHistorialMovimiento,
            getPersistedHistorialMovimiento(returnedHistorialMovimiento)
        );

        insertedHistorialMovimiento = returnedHistorialMovimiento;
    }

    @Test
    @Transactional
    void createHistorialMovimientoWithExistingId() throws Exception {
        // Create the HistorialMovimiento with an existing ID
        historialMovimiento.setId(1L);
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistorialMovimientoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialMovimientoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTipoMovimientoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        historialMovimiento.setTipoMovimiento(null);

        // Create the HistorialMovimiento, which fails.
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        restHistorialMovimientoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialMovimientoDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCantidadIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        historialMovimiento.setCantidad(null);

        // Create the HistorialMovimiento, which fails.
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        restHistorialMovimientoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialMovimientoDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFechaHoraIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        historialMovimiento.setFechaHora(null);

        // Create the HistorialMovimiento, which fails.
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        restHistorialMovimientoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialMovimientoDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllHistorialMovimientos() throws Exception {
        // Initialize the database
        insertedHistorialMovimiento = historialMovimientoRepository.saveAndFlush(historialMovimiento);

        // Get all the historialMovimientoList
        restHistorialMovimientoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historialMovimiento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSitioDestino").value(hasItem(DEFAULT_ID_SITIO_DESTINO)))
            .andExpect(jsonPath("$.[*].tipoMovimiento").value(hasItem(DEFAULT_TIPO_MOVIMIENTO)))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].fechaHora").value(hasItem(DEFAULT_FECHA_HORA.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllHistorialMovimientosWithEagerRelationshipsIsEnabled() throws Exception {
        when(historialMovimientoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restHistorialMovimientoMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(historialMovimientoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllHistorialMovimientosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(historialMovimientoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restHistorialMovimientoMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(historialMovimientoRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getHistorialMovimiento() throws Exception {
        // Initialize the database
        insertedHistorialMovimiento = historialMovimientoRepository.saveAndFlush(historialMovimiento);

        // Get the historialMovimiento
        restHistorialMovimientoMockMvc
            .perform(get(ENTITY_API_URL_ID, historialMovimiento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historialMovimiento.getId().intValue()))
            .andExpect(jsonPath("$.idSitioDestino").value(DEFAULT_ID_SITIO_DESTINO))
            .andExpect(jsonPath("$.tipoMovimiento").value(DEFAULT_TIPO_MOVIMIENTO))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD))
            .andExpect(jsonPath("$.fechaHora").value(DEFAULT_FECHA_HORA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHistorialMovimiento() throws Exception {
        // Get the historialMovimiento
        restHistorialMovimientoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHistorialMovimiento() throws Exception {
        // Initialize the database
        insertedHistorialMovimiento = historialMovimientoRepository.saveAndFlush(historialMovimiento);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the historialMovimiento
        HistorialMovimiento updatedHistorialMovimiento = historialMovimientoRepository.findById(historialMovimiento.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHistorialMovimiento are not directly saved in db
        em.detach(updatedHistorialMovimiento);
        updatedHistorialMovimiento
            .idSitioDestino(UPDATED_ID_SITIO_DESTINO)
            .tipoMovimiento(UPDATED_TIPO_MOVIMIENTO)
            .cantidad(UPDATED_CANTIDAD)
            .fechaHora(UPDATED_FECHA_HORA);
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(updatedHistorialMovimiento);

        restHistorialMovimientoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, historialMovimientoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(historialMovimientoDTO))
            )
            .andExpect(status().isOk());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHistorialMovimientoToMatchAllProperties(updatedHistorialMovimiento);
    }

    @Test
    @Transactional
    void putNonExistingHistorialMovimiento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialMovimiento.setId(longCount.incrementAndGet());

        // Create the HistorialMovimiento
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistorialMovimientoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, historialMovimientoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(historialMovimientoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHistorialMovimiento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialMovimiento.setId(longCount.incrementAndGet());

        // Create the HistorialMovimiento
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialMovimientoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(historialMovimientoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHistorialMovimiento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialMovimiento.setId(longCount.incrementAndGet());

        // Create the HistorialMovimiento
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialMovimientoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(historialMovimientoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHistorialMovimientoWithPatch() throws Exception {
        // Initialize the database
        insertedHistorialMovimiento = historialMovimientoRepository.saveAndFlush(historialMovimiento);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the historialMovimiento using partial update
        HistorialMovimiento partialUpdatedHistorialMovimiento = new HistorialMovimiento();
        partialUpdatedHistorialMovimiento.setId(historialMovimiento.getId());

        partialUpdatedHistorialMovimiento
            .idSitioDestino(UPDATED_ID_SITIO_DESTINO)
            .tipoMovimiento(UPDATED_TIPO_MOVIMIENTO)
            .cantidad(UPDATED_CANTIDAD)
            .fechaHora(UPDATED_FECHA_HORA);

        restHistorialMovimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistorialMovimiento.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHistorialMovimiento))
            )
            .andExpect(status().isOk());

        // Validate the HistorialMovimiento in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHistorialMovimientoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHistorialMovimiento, historialMovimiento),
            getPersistedHistorialMovimiento(historialMovimiento)
        );
    }

    @Test
    @Transactional
    void fullUpdateHistorialMovimientoWithPatch() throws Exception {
        // Initialize the database
        insertedHistorialMovimiento = historialMovimientoRepository.saveAndFlush(historialMovimiento);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the historialMovimiento using partial update
        HistorialMovimiento partialUpdatedHistorialMovimiento = new HistorialMovimiento();
        partialUpdatedHistorialMovimiento.setId(historialMovimiento.getId());

        partialUpdatedHistorialMovimiento
            .idSitioDestino(UPDATED_ID_SITIO_DESTINO)
            .tipoMovimiento(UPDATED_TIPO_MOVIMIENTO)
            .cantidad(UPDATED_CANTIDAD)
            .fechaHora(UPDATED_FECHA_HORA);

        restHistorialMovimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistorialMovimiento.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHistorialMovimiento))
            )
            .andExpect(status().isOk());

        // Validate the HistorialMovimiento in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHistorialMovimientoUpdatableFieldsEquals(
            partialUpdatedHistorialMovimiento,
            getPersistedHistorialMovimiento(partialUpdatedHistorialMovimiento)
        );
    }

    @Test
    @Transactional
    void patchNonExistingHistorialMovimiento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialMovimiento.setId(longCount.incrementAndGet());

        // Create the HistorialMovimiento
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistorialMovimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, historialMovimientoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(historialMovimientoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHistorialMovimiento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialMovimiento.setId(longCount.incrementAndGet());

        // Create the HistorialMovimiento
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialMovimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(historialMovimientoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHistorialMovimiento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        historialMovimiento.setId(longCount.incrementAndGet());

        // Create the HistorialMovimiento
        HistorialMovimientoDTO historialMovimientoDTO = historialMovimientoMapper.toDto(historialMovimiento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistorialMovimientoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(historialMovimientoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistorialMovimiento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHistorialMovimiento() throws Exception {
        // Initialize the database
        insertedHistorialMovimiento = historialMovimientoRepository.saveAndFlush(historialMovimiento);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the historialMovimiento
        restHistorialMovimientoMockMvc
            .perform(delete(ENTITY_API_URL_ID, historialMovimiento.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return historialMovimientoRepository.count();
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

    protected HistorialMovimiento getPersistedHistorialMovimiento(HistorialMovimiento historialMovimiento) {
        return historialMovimientoRepository.findById(historialMovimiento.getId()).orElseThrow();
    }

    protected void assertPersistedHistorialMovimientoToMatchAllProperties(HistorialMovimiento expectedHistorialMovimiento) {
        assertHistorialMovimientoAllPropertiesEquals(
            expectedHistorialMovimiento,
            getPersistedHistorialMovimiento(expectedHistorialMovimiento)
        );
    }

    protected void assertPersistedHistorialMovimientoToMatchUpdatableProperties(HistorialMovimiento expectedHistorialMovimiento) {
        assertHistorialMovimientoAllUpdatablePropertiesEquals(
            expectedHistorialMovimiento,
            getPersistedHistorialMovimiento(expectedHistorialMovimiento)
        );
    }
}
