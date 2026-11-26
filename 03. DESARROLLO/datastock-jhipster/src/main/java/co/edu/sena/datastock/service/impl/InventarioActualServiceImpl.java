package co.edu.sena.datastock.service.impl;

import co.edu.sena.datastock.domain.InventarioActual;
import co.edu.sena.datastock.repository.InventarioActualRepository;
import co.edu.sena.datastock.service.InventarioActualService;
import co.edu.sena.datastock.service.dto.InventarioActualDTO;
import co.edu.sena.datastock.service.mapper.InventarioActualMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link co.edu.sena.datastock.domain.InventarioActual}.
 */
@Service
@Transactional
public class InventarioActualServiceImpl implements InventarioActualService {

    private static final Logger LOG = LoggerFactory.getLogger(InventarioActualServiceImpl.class);

    private final InventarioActualRepository inventarioActualRepository;

    private final InventarioActualMapper inventarioActualMapper;

    public InventarioActualServiceImpl(
        InventarioActualRepository inventarioActualRepository,
        InventarioActualMapper inventarioActualMapper
    ) {
        this.inventarioActualRepository = inventarioActualRepository;
        this.inventarioActualMapper = inventarioActualMapper;
    }

    @Override
    public InventarioActualDTO save(InventarioActualDTO inventarioActualDTO) {
        LOG.debug("Request to save InventarioActual : {}", inventarioActualDTO);
        InventarioActual inventarioActual = inventarioActualMapper.toEntity(inventarioActualDTO);
        inventarioActual = inventarioActualRepository.save(inventarioActual);
        return inventarioActualMapper.toDto(inventarioActual);
    }

    @Override
    public InventarioActualDTO update(InventarioActualDTO inventarioActualDTO) {
        LOG.debug("Request to update InventarioActual : {}", inventarioActualDTO);
        InventarioActual inventarioActual = inventarioActualMapper.toEntity(inventarioActualDTO);
        inventarioActual = inventarioActualRepository.save(inventarioActual);
        return inventarioActualMapper.toDto(inventarioActual);
    }

    @Override
    public Optional<InventarioActualDTO> partialUpdate(InventarioActualDTO inventarioActualDTO) {
        LOG.debug("Request to partially update InventarioActual : {}", inventarioActualDTO);

        return inventarioActualRepository
            .findById(inventarioActualDTO.getId())
            .map(existingInventarioActual -> {
                inventarioActualMapper.partialUpdate(existingInventarioActual, inventarioActualDTO);

                return existingInventarioActual;
            })
            .map(inventarioActualRepository::save)
            .map(inventarioActualMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InventarioActualDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all InventarioActuals");
        return inventarioActualRepository.findAll(pageable).map(inventarioActualMapper::toDto);
    }

    public Page<InventarioActualDTO> findAllWithEagerRelationships(Pageable pageable) {
        return inventarioActualRepository.findAllWithEagerRelationships(pageable).map(inventarioActualMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InventarioActualDTO> findOne(Long id) {
        LOG.debug("Request to get InventarioActual : {}", id);
        return inventarioActualRepository.findOneWithEagerRelationships(id).map(inventarioActualMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete InventarioActual : {}", id);
        inventarioActualRepository.deleteById(id);
    }
}
