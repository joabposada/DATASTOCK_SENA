package co.edu.sena.datastock.service.impl;

import co.edu.sena.datastock.domain.HistorialMovimiento;
import co.edu.sena.datastock.repository.HistorialMovimientoRepository;
import co.edu.sena.datastock.service.HistorialMovimientoService;
import co.edu.sena.datastock.service.dto.HistorialMovimientoDTO;
import co.edu.sena.datastock.service.mapper.HistorialMovimientoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link co.edu.sena.datastock.domain.HistorialMovimiento}.
 */
@Service
@Transactional
public class HistorialMovimientoServiceImpl implements HistorialMovimientoService {

    private static final Logger LOG = LoggerFactory.getLogger(HistorialMovimientoServiceImpl.class);

    private final HistorialMovimientoRepository historialMovimientoRepository;

    private final HistorialMovimientoMapper historialMovimientoMapper;

    public HistorialMovimientoServiceImpl(
        HistorialMovimientoRepository historialMovimientoRepository,
        HistorialMovimientoMapper historialMovimientoMapper
    ) {
        this.historialMovimientoRepository = historialMovimientoRepository;
        this.historialMovimientoMapper = historialMovimientoMapper;
    }

    @Override
    public HistorialMovimientoDTO save(HistorialMovimientoDTO historialMovimientoDTO) {
        LOG.debug("Request to save HistorialMovimiento : {}", historialMovimientoDTO);
        HistorialMovimiento historialMovimiento = historialMovimientoMapper.toEntity(historialMovimientoDTO);
        historialMovimiento = historialMovimientoRepository.save(historialMovimiento);
        return historialMovimientoMapper.toDto(historialMovimiento);
    }

    @Override
    public HistorialMovimientoDTO update(HistorialMovimientoDTO historialMovimientoDTO) {
        LOG.debug("Request to update HistorialMovimiento : {}", historialMovimientoDTO);
        HistorialMovimiento historialMovimiento = historialMovimientoMapper.toEntity(historialMovimientoDTO);
        historialMovimiento = historialMovimientoRepository.save(historialMovimiento);
        return historialMovimientoMapper.toDto(historialMovimiento);
    }

    @Override
    public Optional<HistorialMovimientoDTO> partialUpdate(HistorialMovimientoDTO historialMovimientoDTO) {
        LOG.debug("Request to partially update HistorialMovimiento : {}", historialMovimientoDTO);

        return historialMovimientoRepository
            .findById(historialMovimientoDTO.getId())
            .map(existingHistorialMovimiento -> {
                historialMovimientoMapper.partialUpdate(existingHistorialMovimiento, historialMovimientoDTO);

                return existingHistorialMovimiento;
            })
            .map(historialMovimientoRepository::save)
            .map(historialMovimientoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HistorialMovimientoDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all HistorialMovimientos");
        return historialMovimientoRepository.findAll(pageable).map(historialMovimientoMapper::toDto);
    }

    public Page<HistorialMovimientoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return historialMovimientoRepository.findAllWithEagerRelationships(pageable).map(historialMovimientoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialMovimientoDTO> findOne(Long id) {
        LOG.debug("Request to get HistorialMovimiento : {}", id);
        return historialMovimientoRepository.findOneWithEagerRelationships(id).map(historialMovimientoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete HistorialMovimiento : {}", id);
        historialMovimientoRepository.deleteById(id);
    }
}
