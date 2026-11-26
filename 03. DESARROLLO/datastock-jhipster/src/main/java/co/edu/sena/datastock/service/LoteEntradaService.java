package co.edu.sena.datastock.service;

import co.edu.sena.datastock.service.dto.LoteEntradaDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.datastock.domain.LoteEntrada}.
 */
public interface LoteEntradaService {
    /**
     * Save a loteEntrada.
     *
     * @param loteEntradaDTO the entity to save.
     * @return the persisted entity.
     */
    LoteEntradaDTO save(LoteEntradaDTO loteEntradaDTO);

    /**
     * Updates a loteEntrada.
     *
     * @param loteEntradaDTO the entity to update.
     * @return the persisted entity.
     */
    LoteEntradaDTO update(LoteEntradaDTO loteEntradaDTO);

    /**
     * Partially updates a loteEntrada.
     *
     * @param loteEntradaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LoteEntradaDTO> partialUpdate(LoteEntradaDTO loteEntradaDTO);

    /**
     * Get all the loteEntradas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LoteEntradaDTO> findAll(Pageable pageable);

    /**
     * Get all the loteEntradas with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LoteEntradaDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" loteEntrada.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoteEntradaDTO> findOne(Long id);

    /**
     * Delete the "id" loteEntrada.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
