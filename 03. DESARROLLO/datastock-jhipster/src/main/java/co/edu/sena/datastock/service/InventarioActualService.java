package co.edu.sena.datastock.service;

import co.edu.sena.datastock.service.dto.InventarioActualDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.datastock.domain.InventarioActual}.
 */
public interface InventarioActualService {
    /**
     * Save a inventarioActual.
     *
     * @param inventarioActualDTO the entity to save.
     * @return the persisted entity.
     */
    InventarioActualDTO save(InventarioActualDTO inventarioActualDTO);

    /**
     * Updates a inventarioActual.
     *
     * @param inventarioActualDTO the entity to update.
     * @return the persisted entity.
     */
    InventarioActualDTO update(InventarioActualDTO inventarioActualDTO);

    /**
     * Partially updates a inventarioActual.
     *
     * @param inventarioActualDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InventarioActualDTO> partialUpdate(InventarioActualDTO inventarioActualDTO);

    /**
     * Get all the inventarioActuals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InventarioActualDTO> findAll(Pageable pageable);

    /**
     * Get all the inventarioActuals with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InventarioActualDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" inventarioActual.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InventarioActualDTO> findOne(Long id);

    /**
     * Delete the "id" inventarioActual.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
