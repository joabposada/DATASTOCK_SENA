package co.edu.sena.datastock.service;

import co.edu.sena.datastock.service.dto.HistorialMovimientoDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.datastock.domain.HistorialMovimiento}.
 */
public interface HistorialMovimientoService {
    /**
     * Save a historialMovimiento.
     *
     * @param historialMovimientoDTO the entity to save.
     * @return the persisted entity.
     */
    HistorialMovimientoDTO save(HistorialMovimientoDTO historialMovimientoDTO);

    /**
     * Updates a historialMovimiento.
     *
     * @param historialMovimientoDTO the entity to update.
     * @return the persisted entity.
     */
    HistorialMovimientoDTO update(HistorialMovimientoDTO historialMovimientoDTO);

    /**
     * Partially updates a historialMovimiento.
     *
     * @param historialMovimientoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HistorialMovimientoDTO> partialUpdate(HistorialMovimientoDTO historialMovimientoDTO);

    /**
     * Get all the historialMovimientos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistorialMovimientoDTO> findAll(Pageable pageable);

    /**
     * Get all the historialMovimientos with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistorialMovimientoDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" historialMovimiento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HistorialMovimientoDTO> findOne(Long id);

    /**
     * Delete the "id" historialMovimiento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
