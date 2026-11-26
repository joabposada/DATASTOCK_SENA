package co.edu.sena.datastock.service;

import co.edu.sena.datastock.service.dto.HistorialRolUsuarioDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.datastock.domain.HistorialRolUsuario}.
 */
public interface HistorialRolUsuarioService {
    /**
     * Save a historialRolUsuario.
     *
     * @param historialRolUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    HistorialRolUsuarioDTO save(HistorialRolUsuarioDTO historialRolUsuarioDTO);

    /**
     * Updates a historialRolUsuario.
     *
     * @param historialRolUsuarioDTO the entity to update.
     * @return the persisted entity.
     */
    HistorialRolUsuarioDTO update(HistorialRolUsuarioDTO historialRolUsuarioDTO);

    /**
     * Partially updates a historialRolUsuario.
     *
     * @param historialRolUsuarioDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HistorialRolUsuarioDTO> partialUpdate(HistorialRolUsuarioDTO historialRolUsuarioDTO);

    /**
     * Get all the historialRolUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistorialRolUsuarioDTO> findAll(Pageable pageable);

    /**
     * Get all the historialRolUsuarios with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistorialRolUsuarioDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" historialRolUsuario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HistorialRolUsuarioDTO> findOne(Long id);

    /**
     * Delete the "id" historialRolUsuario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
