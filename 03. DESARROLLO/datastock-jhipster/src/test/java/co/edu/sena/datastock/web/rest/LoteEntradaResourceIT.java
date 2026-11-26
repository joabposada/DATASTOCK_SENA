package co.edu.sena.datastock.web.rest;

import static co.edu.sena.datastock.domain.LoteEntradaAsserts.*;
import static co.edu.sena.datastock.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.datastock.IntegrationTest;
import co.edu.sena.datastock.domain.LoteEntrada;
import co.edu.sena.datastock.domain.Producto;
import co.edu.sena.datastock.domain.Sitio;
import co.edu.sena.datastock.repository.LoteEntradaRepository;
import co.edu.sena.datastock.service.LoteEntradaService;
import co.edu.sena.datastock.service.dto.LoteEntradaDTO;
import co.edu.sena.datastock.service.mapper.LoteEntradaMapper;
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
 * Integration tests for the {@link LoteEntradaResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class LoteEntradaResourceIT {

    private static final LocalDate DEFAULT_FECHA_INGRESO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INGRESO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_VENCIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_VENCIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_CANTIDAD_INGRESADA = 1;
    private static final Integer UPDATED_CANTIDAD_INGRESADA = 2;

    private static final String DEFAULT_FACTURA_PROVEEDOR = "AAAAAAAAAA";
    private static final String UPDATED_FACTURA_PROVEEDOR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/lote-entradas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoteEntradaRepository loteEntradaRepository;

    @Mock
    private LoteEntradaRepository loteEntradaRepositoryMock;

    @Autowired
    private LoteEntradaMapper loteEntradaMapper;

    @Mock
    private LoteEntradaService loteEntradaServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoteEntradaMockMvc;

    private LoteEntrada loteEntrada;

    private LoteEntrada insertedLoteEntrada;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoteEntrada createEntity(EntityManager em) {
        LoteEntrada loteEntrada = new LoteEntrada()
            .fechaIngreso(DEFAULT_FECHA_INGRESO)
            .fechaVencimiento(DEFAULT_FECHA_VENCIMIENTO)
            .cantidadIngresada(DEFAULT_CANTIDAD_INGRESADA)
            .facturaProveedor(DEFAULT_FACTURA_PROVEEDOR);
        // Add required entity
        Producto producto;
        if (TestUtil.findAll(em, Producto.class).isEmpty()) {
            producto = ProductoResourceIT.createEntity(em);
            em.persist(producto);
            em.flush();
        } else {
            producto = TestUtil.findAll(em, Producto.class).get(0);
        }
        loteEntrada.setProducto(producto);
        // Add required entity
        Sitio sitio;
        if (TestUtil.findAll(em, Sitio.class).isEmpty()) {
            sitio = SitioResourceIT.createEntity();
            em.persist(sitio);
            em.flush();
        } else {
            sitio = TestUtil.findAll(em, Sitio.class).get(0);
        }
        loteEntrada.setSitio(sitio);
        return loteEntrada;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoteEntrada createUpdatedEntity(EntityManager em) {
        LoteEntrada updatedLoteEntrada = new LoteEntrada()
            .fechaIngreso(UPDATED_FECHA_INGRESO)
            .fechaVencimiento(UPDATED_FECHA_VENCIMIENTO)
            .cantidadIngresada(UPDATED_CANTIDAD_INGRESADA)
            .facturaProveedor(UPDATED_FACTURA_PROVEEDOR);
        // Add required entity
        Producto producto;
        if (TestUtil.findAll(em, Producto.class).isEmpty()) {
            producto = ProductoResourceIT.createUpdatedEntity(em);
            em.persist(producto);
            em.flush();
        } else {
            producto = TestUtil.findAll(em, Producto.class).get(0);
        }
        updatedLoteEntrada.setProducto(producto);
        // Add required entity
        Sitio sitio;
        if (TestUtil.findAll(em, Sitio.class).isEmpty()) {
            sitio = SitioResourceIT.createUpdatedEntity();
            em.persist(sitio);
            em.flush();
        } else {
            sitio = TestUtil.findAll(em, Sitio.class).get(0);
        }
        updatedLoteEntrada.setSitio(sitio);
        return updatedLoteEntrada;
    }

    @BeforeEach
    void initTest() {
        loteEntrada = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedLoteEntrada != null) {
            loteEntradaRepository.delete(insertedLoteEntrada);
            insertedLoteEntrada = null;
        }
    }

    @Test
    @Transactional
    void createLoteEntrada() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoteEntrada
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);
        var returnedLoteEntradaDTO = om.readValue(
            restLoteEntradaMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loteEntradaDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoteEntradaDTO.class
        );

        // Validate the LoteEntrada in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoteEntrada = loteEntradaMapper.toEntity(returnedLoteEntradaDTO);
        assertLoteEntradaUpdatableFieldsEquals(returnedLoteEntrada, getPersistedLoteEntrada(returnedLoteEntrada));

        insertedLoteEntrada = returnedLoteEntrada;
    }

    @Test
    @Transactional
    void createLoteEntradaWithExistingId() throws Exception {
        // Create the LoteEntrada with an existing ID
        loteEntrada.setId(1L);
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoteEntradaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loteEntradaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFechaIngresoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        loteEntrada.setFechaIngreso(null);

        // Create the LoteEntrada, which fails.
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        restLoteEntradaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loteEntradaDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFechaVencimientoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        loteEntrada.setFechaVencimiento(null);

        // Create the LoteEntrada, which fails.
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        restLoteEntradaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loteEntradaDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCantidadIngresadaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        loteEntrada.setCantidadIngresada(null);

        // Create the LoteEntrada, which fails.
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        restLoteEntradaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loteEntradaDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFacturaProveedorIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        loteEntrada.setFacturaProveedor(null);

        // Create the LoteEntrada, which fails.
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        restLoteEntradaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loteEntradaDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLoteEntradas() throws Exception {
        // Initialize the database
        insertedLoteEntrada = loteEntradaRepository.saveAndFlush(loteEntrada);

        // Get all the loteEntradaList
        restLoteEntradaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loteEntrada.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaIngreso").value(hasItem(DEFAULT_FECHA_INGRESO.toString())))
            .andExpect(jsonPath("$.[*].fechaVencimiento").value(hasItem(DEFAULT_FECHA_VENCIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].cantidadIngresada").value(hasItem(DEFAULT_CANTIDAD_INGRESADA)))
            .andExpect(jsonPath("$.[*].facturaProveedor").value(hasItem(DEFAULT_FACTURA_PROVEEDOR)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllLoteEntradasWithEagerRelationshipsIsEnabled() throws Exception {
        when(loteEntradaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLoteEntradaMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(loteEntradaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllLoteEntradasWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(loteEntradaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLoteEntradaMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(loteEntradaRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getLoteEntrada() throws Exception {
        // Initialize the database
        insertedLoteEntrada = loteEntradaRepository.saveAndFlush(loteEntrada);

        // Get the loteEntrada
        restLoteEntradaMockMvc
            .perform(get(ENTITY_API_URL_ID, loteEntrada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loteEntrada.getId().intValue()))
            .andExpect(jsonPath("$.fechaIngreso").value(DEFAULT_FECHA_INGRESO.toString()))
            .andExpect(jsonPath("$.fechaVencimiento").value(DEFAULT_FECHA_VENCIMIENTO.toString()))
            .andExpect(jsonPath("$.cantidadIngresada").value(DEFAULT_CANTIDAD_INGRESADA))
            .andExpect(jsonPath("$.facturaProveedor").value(DEFAULT_FACTURA_PROVEEDOR));
    }

    @Test
    @Transactional
    void getNonExistingLoteEntrada() throws Exception {
        // Get the loteEntrada
        restLoteEntradaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLoteEntrada() throws Exception {
        // Initialize the database
        insertedLoteEntrada = loteEntradaRepository.saveAndFlush(loteEntrada);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loteEntrada
        LoteEntrada updatedLoteEntrada = loteEntradaRepository.findById(loteEntrada.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLoteEntrada are not directly saved in db
        em.detach(updatedLoteEntrada);
        updatedLoteEntrada
            .fechaIngreso(UPDATED_FECHA_INGRESO)
            .fechaVencimiento(UPDATED_FECHA_VENCIMIENTO)
            .cantidadIngresada(UPDATED_CANTIDAD_INGRESADA)
            .facturaProveedor(UPDATED_FACTURA_PROVEEDOR);
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(updatedLoteEntrada);

        restLoteEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loteEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loteEntradaDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoteEntradaToMatchAllProperties(updatedLoteEntrada);
    }

    @Test
    @Transactional
    void putNonExistingLoteEntrada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loteEntrada.setId(longCount.incrementAndGet());

        // Create the LoteEntrada
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoteEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loteEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLoteEntrada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loteEntrada.setId(longCount.incrementAndGet());

        // Create the LoteEntrada
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoteEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLoteEntrada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loteEntrada.setId(longCount.incrementAndGet());

        // Create the LoteEntrada
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoteEntradaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loteEntradaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLoteEntradaWithPatch() throws Exception {
        // Initialize the database
        insertedLoteEntrada = loteEntradaRepository.saveAndFlush(loteEntrada);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loteEntrada using partial update
        LoteEntrada partialUpdatedLoteEntrada = new LoteEntrada();
        partialUpdatedLoteEntrada.setId(loteEntrada.getId());

        partialUpdatedLoteEntrada.fechaIngreso(UPDATED_FECHA_INGRESO).facturaProveedor(UPDATED_FACTURA_PROVEEDOR);

        restLoteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoteEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoteEntrada))
            )
            .andExpect(status().isOk());

        // Validate the LoteEntrada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoteEntradaUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoteEntrada, loteEntrada),
            getPersistedLoteEntrada(loteEntrada)
        );
    }

    @Test
    @Transactional
    void fullUpdateLoteEntradaWithPatch() throws Exception {
        // Initialize the database
        insertedLoteEntrada = loteEntradaRepository.saveAndFlush(loteEntrada);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loteEntrada using partial update
        LoteEntrada partialUpdatedLoteEntrada = new LoteEntrada();
        partialUpdatedLoteEntrada.setId(loteEntrada.getId());

        partialUpdatedLoteEntrada
            .fechaIngreso(UPDATED_FECHA_INGRESO)
            .fechaVencimiento(UPDATED_FECHA_VENCIMIENTO)
            .cantidadIngresada(UPDATED_CANTIDAD_INGRESADA)
            .facturaProveedor(UPDATED_FACTURA_PROVEEDOR);

        restLoteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoteEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoteEntrada))
            )
            .andExpect(status().isOk());

        // Validate the LoteEntrada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoteEntradaUpdatableFieldsEquals(partialUpdatedLoteEntrada, getPersistedLoteEntrada(partialUpdatedLoteEntrada));
    }

    @Test
    @Transactional
    void patchNonExistingLoteEntrada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loteEntrada.setId(longCount.incrementAndGet());

        // Create the LoteEntrada
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loteEntradaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLoteEntrada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loteEntrada.setId(longCount.incrementAndGet());

        // Create the LoteEntrada
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLoteEntrada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loteEntrada.setId(longCount.incrementAndGet());

        // Create the LoteEntrada
        LoteEntradaDTO loteEntradaDTO = loteEntradaMapper.toDto(loteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoteEntradaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loteEntradaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoteEntrada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLoteEntrada() throws Exception {
        // Initialize the database
        insertedLoteEntrada = loteEntradaRepository.saveAndFlush(loteEntrada);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loteEntrada
        restLoteEntradaMockMvc
            .perform(delete(ENTITY_API_URL_ID, loteEntrada.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loteEntradaRepository.count();
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

    protected LoteEntrada getPersistedLoteEntrada(LoteEntrada loteEntrada) {
        return loteEntradaRepository.findById(loteEntrada.getId()).orElseThrow();
    }

    protected void assertPersistedLoteEntradaToMatchAllProperties(LoteEntrada expectedLoteEntrada) {
        assertLoteEntradaAllPropertiesEquals(expectedLoteEntrada, getPersistedLoteEntrada(expectedLoteEntrada));
    }

    protected void assertPersistedLoteEntradaToMatchUpdatableProperties(LoteEntrada expectedLoteEntrada) {
        assertLoteEntradaAllUpdatablePropertiesEquals(expectedLoteEntrada, getPersistedLoteEntrada(expectedLoteEntrada));
    }
}
