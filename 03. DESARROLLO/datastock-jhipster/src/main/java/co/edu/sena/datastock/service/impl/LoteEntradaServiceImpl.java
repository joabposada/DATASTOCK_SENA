package co.edu.sena.datastock.service.impl;

import co.edu.sena.datastock.domain.LoteEntrada;
import co.edu.sena.datastock.repository.LoteEntradaRepository;
import co.edu.sena.datastock.service.LoteEntradaService;
import co.edu.sena.datastock.service.dto.LoteEntradaDTO;
import co.edu.sena.datastock.service.mapper.LoteEntradaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link co.edu.sena.datastock.domain.LoteEntrada}.
 */
@Service
@Transactional
public class LoteEntradaServiceImpl implements LoteEntradaService {

    private static final Logger LOG = LoggerFactory.getLogger(LoteEntradaServiceImpl.class);

    private final LoteEntradaRepository loteEntradaRepository;

    private final LoteEntradaMapper loteEntradaMapper;

    public LoteEntradaServiceImpl(LoteEntradaRepository loteEntradaRepository, LoteEntradaMapper loteEntradaMapper) {
        this.loteEntradaRepository = loteEntradaRepository;
        this.loteEntradaMapper = loteEntradaMapper;
    }

    @Override
    public LoteEntradaDTO save(LoteEntradaDTO loteEntradaDTO) {
        LOG.debug("Request to save LoteEntrada : {}", loteEntradaDTO);
        LoteEntrada loteEntrada = loteEntradaMapper.toEntity(loteEntradaDTO);
        loteEntrada = loteEntradaRepository.save(loteEntrada);
        return loteEntradaMapper.toDto(loteEntrada);
    }

    @Override
    public LoteEntradaDTO update(LoteEntradaDTO loteEntradaDTO) {
        LOG.debug("Request to update LoteEntrada : {}", loteEntradaDTO);
        LoteEntrada loteEntrada = loteEntradaMapper.toEntity(loteEntradaDTO);
        loteEntrada = loteEntradaRepository.save(loteEntrada);
        return loteEntradaMapper.toDto(loteEntrada);
    }

    @Override
    public Optional<LoteEntradaDTO> partialUpdate(LoteEntradaDTO loteEntradaDTO) {
        LOG.debug("Request to partially update LoteEntrada : {}", loteEntradaDTO);

        return loteEntradaRepository
            .findById(loteEntradaDTO.getId())
            .map(existingLoteEntrada -> {
                loteEntradaMapper.partialUpdate(existingLoteEntrada, loteEntradaDTO);

                return existingLoteEntrada;
            })
            .map(loteEntradaRepository::save)
            .map(loteEntradaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LoteEntradaDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all LoteEntradas");
        return loteEntradaRepository.findAll(pageable).map(loteEntradaMapper::toDto);
    }

    public Page<LoteEntradaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return loteEntradaRepository.findAllWithEagerRelationships(pageable).map(loteEntradaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LoteEntradaDTO> findOne(Long id) {
        LOG.debug("Request to get LoteEntrada : {}", id);
        return loteEntradaRepository.findOneWithEagerRelationships(id).map(loteEntradaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete LoteEntrada : {}", id);
        loteEntradaRepository.deleteById(id);
    }
}
