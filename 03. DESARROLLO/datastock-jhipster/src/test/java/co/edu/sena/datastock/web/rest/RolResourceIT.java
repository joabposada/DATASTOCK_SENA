package co.edu.sena.datastock.web.rest;

import static co.edu.sena.datastock.domain.RolAsserts.*;
import static co.edu.sena.datastock.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.datastock.IntegrationTest;
import co.edu.sena.datastock.domain.Rol;
import co.edu.sena.datastock.repository.RolRepository;
import co.edu.sena.datastock.service.dto.RolDTO;
import co.edu.sena.datastock.service.mapper.RolMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RolResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RolResourceIT {

    private static final String DEFAULT_NOMBRE_ROL = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ROL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/rols";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolMapper rolMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRolMockMvc;

    private Rol rol;

    private Rol insertedRol;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rol createEntity() {
        return new Rol().nombreRol(DEFAULT_NOMBRE_ROL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rol createUpdatedEntity() {
        return new Rol().nombreRol(UPDATED_NOMBRE_ROL);
    }

    @BeforeEach
    void initTest() {
        rol = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedRol != null) {
            rolRepository.delete(insertedRol);
            insertedRol = null;
        }
    }

    @Test
    @Transactional
    void createRol() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Rol
        RolDTO rolDTO = rolMapper.toDto(rol);
        var returnedRolDTO = om.readValue(
            restRolMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rolDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RolDTO.class
        );

        // Validate the Rol in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedRol = rolMapper.toEntity(returnedRolDTO);
        assertRolUpdatableFieldsEquals(returnedRol, getPersistedRol(returnedRol));

        insertedRol = returnedRol;
    }

    @Test
    @Transactional
    void createRolWithExistingId() throws Exception {
        // Create the Rol with an existing ID
        rol.setId(1L);
        RolDTO rolDTO = rolMapper.toDto(rol);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRolMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rolDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNombreRolIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        rol.setNombreRol(null);

        // Create the Rol, which fails.
        RolDTO rolDTO = rolMapper.toDto(rol);

        restRolMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rolDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRols() throws Exception {
        // Initialize the database
        insertedRol = rolRepository.saveAndFlush(rol);

        // Get all the rolList
        restRolMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rol.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreRol").value(hasItem(DEFAULT_NOMBRE_ROL)));
    }

    @Test
    @Transactional
    void getRol() throws Exception {
        // Initialize the database
        insertedRol = rolRepository.saveAndFlush(rol);

        // Get the rol
        restRolMockMvc
            .perform(get(ENTITY_API_URL_ID, rol.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rol.getId().intValue()))
            .andExpect(jsonPath("$.nombreRol").value(DEFAULT_NOMBRE_ROL));
    }

    @Test
    @Transactional
    void getNonExistingRol() throws Exception {
        // Get the rol
        restRolMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRol() throws Exception {
        // Initialize the database
        insertedRol = rolRepository.saveAndFlush(rol);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rol
        Rol updatedRol = rolRepository.findById(rol.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRol are not directly saved in db
        em.detach(updatedRol);
        updatedRol.nombreRol(UPDATED_NOMBRE_ROL);
        RolDTO rolDTO = rolMapper.toDto(updatedRol);

        restRolMockMvc
            .perform(put(ENTITY_API_URL_ID, rolDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rolDTO)))
            .andExpect(status().isOk());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRolToMatchAllProperties(updatedRol);
    }

    @Test
    @Transactional
    void putNonExistingRol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rol.setId(longCount.incrementAndGet());

        // Create the Rol
        RolDTO rolDTO = rolMapper.toDto(rol);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRolMockMvc
            .perform(put(ENTITY_API_URL_ID, rolDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rolDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rol.setId(longCount.incrementAndGet());

        // Create the Rol
        RolDTO rolDTO = rolMapper.toDto(rol);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRolMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rolDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rol.setId(longCount.incrementAndGet());

        // Create the Rol
        RolDTO rolDTO = rolMapper.toDto(rol);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRolMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rolDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRolWithPatch() throws Exception {
        // Initialize the database
        insertedRol = rolRepository.saveAndFlush(rol);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rol using partial update
        Rol partialUpdatedRol = new Rol();
        partialUpdatedRol.setId(rol.getId());

        partialUpdatedRol.nombreRol(UPDATED_NOMBRE_ROL);

        restRolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRol.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRol))
            )
            .andExpect(status().isOk());

        // Validate the Rol in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRolUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedRol, rol), getPersistedRol(rol));
    }

    @Test
    @Transactional
    void fullUpdateRolWithPatch() throws Exception {
        // Initialize the database
        insertedRol = rolRepository.saveAndFlush(rol);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rol using partial update
        Rol partialUpdatedRol = new Rol();
        partialUpdatedRol.setId(rol.getId());

        partialUpdatedRol.nombreRol(UPDATED_NOMBRE_ROL);

        restRolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRol.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRol))
            )
            .andExpect(status().isOk());

        // Validate the Rol in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRolUpdatableFieldsEquals(partialUpdatedRol, getPersistedRol(partialUpdatedRol));
    }

    @Test
    @Transactional
    void patchNonExistingRol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rol.setId(longCount.incrementAndGet());

        // Create the Rol
        RolDTO rolDTO = rolMapper.toDto(rol);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rolDTO.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rolDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rol.setId(longCount.incrementAndGet());

        // Create the Rol
        RolDTO rolDTO = rolMapper.toDto(rol);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rolDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rol.setId(longCount.incrementAndGet());

        // Create the Rol
        RolDTO rolDTO = rolMapper.toDto(rol);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRolMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rolDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRol() throws Exception {
        // Initialize the database
        insertedRol = rolRepository.saveAndFlush(rol);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rol
        restRolMockMvc.perform(delete(ENTITY_API_URL_ID, rol.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rolRepository.count();
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

    protected Rol getPersistedRol(Rol rol) {
        return rolRepository.findById(rol.getId()).orElseThrow();
    }

    protected void assertPersistedRolToMatchAllProperties(Rol expectedRol) {
        assertRolAllPropertiesEquals(expectedRol, getPersistedRol(expectedRol));
    }

    protected void assertPersistedRolToMatchUpdatableProperties(Rol expectedRol) {
        assertRolAllUpdatablePropertiesEquals(expectedRol, getPersistedRol(expectedRol));
    }
}
