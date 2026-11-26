package co.edu.sena.datastock.service.impl;

import co.edu.sena.datastock.domain.HistorialRolUsuario;
import co.edu.sena.datastock.repository.HistorialRolUsuarioRepository;
import co.edu.sena.datastock.service.HistorialRolUsuarioService;
import co.edu.sena.datastock.service.dto.HistorialRolUsuarioDTO;
import co.edu.sena.datastock.service.mapper.HistorialRolUsuarioMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link co.edu.sena.datastock.domain.HistorialRolUsuario}.
 */
@Service
@Transactional
public class HistorialRolUsuarioServiceImpl implements HistorialRolUsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(HistorialRolUsuarioServiceImpl.class);

    private final HistorialRolUsuarioRepository historialRolUsuarioRepository;

    private final HistorialRolUsuarioMapper historialRolUsuarioMapper;

    public HistorialRolUsuarioServiceImpl(
        HistorialRolUsuarioRepository historialRolUsuarioRepository,
        HistorialRolUsuarioMapper historialRolUsuarioMapper
    ) {
        this.historialRolUsuarioRepository = historialRolUsuarioRepository;
        this.historialRolUsuarioMapper = historialRolUsuarioMapper;
    }

    @Override
    public HistorialRolUsuarioDTO save(HistorialRolUsuarioDTO historialRolUsuarioDTO) {
        LOG.debug("Request to save HistorialRolUsuario : {}", historialRolUsuarioDTO);
        HistorialRolUsuario historialRolUsuario = historialRolUsuarioMapper.toEntity(historialRolUsuarioDTO);
        historialRolUsuario = historialRolUsuarioRepository.save(historialRolUsuario);
        return historialRolUsuarioMapper.toDto(historialRolUsuario);
    }

    @Override
    public HistorialRolUsuarioDTO update(HistorialRolUsuarioDTO historialRolUsuarioDTO) {
        LOG.debug("Request to update HistorialRolUsuario : {}", historialRolUsuarioDTO);
        HistorialRolUsuario historialRolUsuario = historialRolUsuarioMapper.toEntity(historialRolUsuarioDTO);
        historialRolUsuario = historialRolUsuarioRepository.save(historialRolUsuario);
        return historialRolUsuarioMapper.toDto(historialRolUsuario);
    }

    @Override
    public Optional<HistorialRolUsuarioDTO> partialUpdate(HistorialRolUsuarioDTO historialRolUsuarioDTO) {
        LOG.debug("Request to partially update HistorialRolUsuario : {}", historialRolUsuarioDTO);

        return historialRolUsuarioRepository
            .findById(historialRolUsuarioDTO.getId())
            .map(existingHistorialRolUsuario -> {
                historialRolUsuarioMapper.partialUpdate(existingHistorialRolUsuario, historialRolUsuarioDTO);

                return existingHistorialRolUsuario;
            })
            .map(historialRolUsuarioRepository::save)
            .map(historialRolUsuarioMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HistorialRolUsuarioDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all HistorialRolUsuarios");
        return historialRolUsuarioRepository.findAll(pageable).map(historialRolUsuarioMapper::toDto);
    }

    public Page<HistorialRolUsuarioDTO> findAllWithEagerRelationships(Pageable pageable) {
        return historialRolUsuarioRepository.findAllWithEagerRelationships(pageable).map(historialRolUsuarioMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialRolUsuarioDTO> findOne(Long id) {
        LOG.debug("Request to get HistorialRolUsuario : {}", id);
        return historialRolUsuarioRepository.findOneWithEagerRelationships(id).map(historialRolUsuarioMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete HistorialRolUsuario : {}", id);
        historialRolUsuarioRepository.deleteById(id);
    }
}
