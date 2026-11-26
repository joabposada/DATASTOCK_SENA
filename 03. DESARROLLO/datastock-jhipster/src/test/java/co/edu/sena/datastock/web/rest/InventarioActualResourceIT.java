package co.edu.sena.datastock.web.rest;

import static co.edu.sena.datastock.domain.InventarioActualAsserts.*;
import static co.edu.sena.datastock.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.datastock.IntegrationTest;
import co.edu.sena.datastock.domain.InventarioActual;
import co.edu.sena.datastock.domain.Producto;
import co.edu.sena.datastock.domain.Sitio;
import co.edu.sena.datastock.repository.InventarioActualRepository;
import co.edu.sena.datastock.service.InventarioActualService;
import co.edu.sena.datastock.service.dto.InventarioActualDTO;
import co.edu.sena.datastock.service.mapper.InventarioActualMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link InventarioActualResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class InventarioActualResourceIT {

    private static final Integer DEFAULT_STOCK_DISPONIBLE = 0;
    private static final Integer UPDATED_STOCK_DISPONIBLE = 1;

    private static final String ENTITY_API_URL = "/api/inventario-actuals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InventarioActualRepository inventarioActualRepository;

    @Mock
    private InventarioActualRepository inventarioActualRepositoryMock;

    @Autowired
    private InventarioActualMapper inventarioActualMapper;

    @Mock
    private InventarioActualService inventarioActualServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInventarioActualMockMvc;

    private InventarioActual inventarioActual;

    private InventarioActual insertedInventarioActual;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InventarioActual createEntity(EntityManager em) {
        InventarioActual inventarioActual = new InventarioActual().stockDisponible(DEFAULT_STOCK_DISPONIBLE);
        // Add required entity
        Producto producto;
        if (TestUtil.findAll(em, Producto.class).isEmpty()) {
            producto = ProductoResourceIT.createEntity(em);
            em.persist(producto);
            em.flush();
        } else {
            producto = TestUtil.findAll(em, Producto.class).get(0);
        }
        inventarioActual.setProducto(producto);
        // Add required entity
        Sitio sitio;
        if (TestUtil.findAll(em, Sitio.class).isEmpty()) {
            sitio = SitioResourceIT.createEntity();
            em.persist(sitio);
            em.flush();
        } else {
            sitio = TestUtil.findAll(em, Sitio.class).get(0);
        }
        inventarioActual.setSitio(sitio);
        return inventarioActual;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InventarioActual createUpdatedEntity(EntityManager em) {
        InventarioActual updatedInventarioActual = new InventarioActual().stockDisponible(UPDATED_STOCK_DISPONIBLE);
        // Add required entity
        Producto producto;
        if (TestUtil.findAll(em, Producto.class).isEmpty()) {
            producto = ProductoResourceIT.createUpdatedEntity(em);
            em.persist(producto);
            em.flush();
        } else {
            producto = TestUtil.findAll(em, Producto.class).get(0);
        }
        updatedInventarioActual.setProducto(producto);
        // Add required entity
        Sitio sitio;
        if (TestUtil.findAll(em, Sitio.class).isEmpty()) {
            sitio = SitioResourceIT.createUpdatedEntity();
            em.persist(sitio);
            em.flush();
        } else {
            sitio = TestUtil.findAll(em, Sitio.class).get(0);
        }
        updatedInventarioActual.setSitio(sitio);
        return updatedInventarioActual;
    }

    @BeforeEach
    void initTest() {
        inventarioActual = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedInventarioActual != null) {
            inventarioActualRepository.delete(insertedInventarioActual);
            insertedInventarioActual = null;
        }
    }

    @Test
    @Transactional
    void createInventarioActual() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the InventarioActual
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);
        var returnedInventarioActualDTO = om.readValue(
            restInventarioActualMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventarioActualDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            InventarioActualDTO.class
        );

        // Validate the InventarioActual in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedInventarioActual = inventarioActualMapper.toEntity(returnedInventarioActualDTO);
        assertInventarioActualUpdatableFieldsEquals(returnedInventarioActual, getPersistedInventarioActual(returnedInventarioActual));

        insertedInventarioActual = returnedInventarioActual;
    }

    @Test
    @Transactional
    void createInventarioActualWithExistingId() throws Exception {
        // Create the InventarioActual with an existing ID
        inventarioActual.setId(1L);
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInventarioActualMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventarioActualDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStockDisponibleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        inventarioActual.setStockDisponible(null);

        // Create the InventarioActual, which fails.
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        restInventarioActualMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventarioActualDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInventarioActuals() throws Exception {
        // Initialize the database
        insertedInventarioActual = inventarioActualRepository.saveAndFlush(inventarioActual);

        // Get all the inventarioActualList
        restInventarioActualMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inventarioActual.getId().intValue())))
            .andExpect(jsonPath("$.[*].stockDisponible").value(hasItem(DEFAULT_STOCK_DISPONIBLE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllInventarioActualsWithEagerRelationshipsIsEnabled() throws Exception {
        when(inventarioActualServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInventarioActualMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(inventarioActualServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllInventarioActualsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(inventarioActualServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInventarioActualMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(inventarioActualRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getInventarioActual() throws Exception {
        // Initialize the database
        insertedInventarioActual = inventarioActualRepository.saveAndFlush(inventarioActual);

        // Get the inventarioActual
        restInventarioActualMockMvc
            .perform(get(ENTITY_API_URL_ID, inventarioActual.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inventarioActual.getId().intValue()))
            .andExpect(jsonPath("$.stockDisponible").value(DEFAULT_STOCK_DISPONIBLE));
    }

    @Test
    @Transactional
    void getNonExistingInventarioActual() throws Exception {
        // Get the inventarioActual
        restInventarioActualMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInventarioActual() throws Exception {
        // Initialize the database
        insertedInventarioActual = inventarioActualRepository.saveAndFlush(inventarioActual);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventarioActual
        InventarioActual updatedInventarioActual = inventarioActualRepository.findById(inventarioActual.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedInventarioActual are not directly saved in db
        em.detach(updatedInventarioActual);
        updatedInventarioActual.stockDisponible(UPDATED_STOCK_DISPONIBLE);
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(updatedInventarioActual);

        restInventarioActualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, inventarioActualDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(inventarioActualDTO))
            )
            .andExpect(status().isOk());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInventarioActualToMatchAllProperties(updatedInventarioActual);
    }

    @Test
    @Transactional
    void putNonExistingInventarioActual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventarioActual.setId(longCount.incrementAndGet());

        // Create the InventarioActual
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventarioActualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, inventarioActualDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(inventarioActualDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInventarioActual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventarioActual.setId(longCount.incrementAndGet());

        // Create the InventarioActual
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventarioActualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(inventarioActualDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInventarioActual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventarioActual.setId(longCount.incrementAndGet());

        // Create the InventarioActual
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventarioActualMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventarioActualDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInventarioActualWithPatch() throws Exception {
        // Initialize the database
        insertedInventarioActual = inventarioActualRepository.saveAndFlush(inventarioActual);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventarioActual using partial update
        InventarioActual partialUpdatedInventarioActual = new InventarioActual();
        partialUpdatedInventarioActual.setId(inventarioActual.getId());

        partialUpdatedInventarioActual.stockDisponible(UPDATED_STOCK_DISPONIBLE);

        restInventarioActualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInventarioActual.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInventarioActual))
            )
            .andExpect(status().isOk());

        // Validate the InventarioActual in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInventarioActualUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInventarioActual, inventarioActual),
            getPersistedInventarioActual(inventarioActual)
        );
    }

    @Test
    @Transactional
    void fullUpdateInventarioActualWithPatch() throws Exception {
        // Initialize the database
        insertedInventarioActual = inventarioActualRepository.saveAndFlush(inventarioActual);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventarioActual using partial update
        InventarioActual partialUpdatedInventarioActual = new InventarioActual();
        partialUpdatedInventarioActual.setId(inventarioActual.getId());

        partialUpdatedInventarioActual.stockDisponible(UPDATED_STOCK_DISPONIBLE);

        restInventarioActualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInventarioActual.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInventarioActual))
            )
            .andExpect(status().isOk());

        // Validate the InventarioActual in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInventarioActualUpdatableFieldsEquals(
            partialUpdatedInventarioActual,
            getPersistedInventarioActual(partialUpdatedInventarioActual)
        );
    }

    @Test
    @Transactional
    void patchNonExistingInventarioActual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventarioActual.setId(longCount.incrementAndGet());

        // Create the InventarioActual
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventarioActualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, inventarioActualDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(inventarioActualDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInventarioActual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventarioActual.setId(longCount.incrementAndGet());

        // Create the InventarioActual
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventarioActualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(inventarioActualDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInventarioActual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventarioActual.setId(longCount.incrementAndGet());

        // Create the InventarioActual
        InventarioActualDTO inventarioActualDTO = inventarioActualMapper.toDto(inventarioActual);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventarioActualMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(inventarioActualDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InventarioActual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInventarioActual() throws Exception {
        // Initialize the database
        insertedInventarioActual = inventarioActualRepository.saveAndFlush(inventarioActual);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the inventarioActual
        restInventarioActualMockMvc
            .perform(delete(ENTITY_API_URL_ID, inventarioActual.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return inventarioActualRepository.count();
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

    protected InventarioActual getPersistedInventarioActual(InventarioActual inventarioActual) {
        return inventarioActualRepository.findById(inventarioActual.getId()).orElseThrow();
    }

    protected void assertPersistedInventarioActualToMatchAllProperties(InventarioActual expectedInventarioActual) {
        assertInventarioActualAllPropertiesEquals(expectedInventarioActual, getPersistedInventarioActual(expectedInventarioActual));
    }

    protected void assertPersistedInventarioActualToMatchUpdatableProperties(InventarioActual expectedInventarioActual) {
        assertInventarioActualAllUpdatablePropertiesEquals(
            expectedInventarioActual,
            getPersistedInventarioActual(expectedInventarioActual)
        );
    }
}
