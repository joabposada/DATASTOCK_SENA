package co.edu.sena.datastock.web.rest;

import co.edu.sena.datastock.repository.HistorialMovimientoRepository;
import co.edu.sena.datastock.service.HistorialMovimientoService;
import co.edu.sena.datastock.service.dto.HistorialMovimientoDTO;
import co.edu.sena.datastock.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link co.edu.sena.datastock.domain.HistorialMovimiento}.
 */
@RestController
@RequestMapping("/api/historial-movimientos")
public class HistorialMovimientoResource {

    private static final Logger LOG = LoggerFactory.getLogger(HistorialMovimientoResource.class);

    private static final String ENTITY_NAME = "historialMovimiento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistorialMovimientoService historialMovimientoService;

    private final HistorialMovimientoRepository historialMovimientoRepository;

    public HistorialMovimientoResource(
        HistorialMovimientoService historialMovimientoService,
        HistorialMovimientoRepository historialMovimientoRepository
    ) {
        this.historialMovimientoService = historialMovimientoService;
        this.historialMovimientoRepository = historialMovimientoRepository;
    }

    /**
     * {@code POST  /historial-movimientos} : Create a new historialMovimiento.
     *
     * @param historialMovimientoDTO the historialMovimientoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historialMovimientoDTO, or with status {@code 400 (Bad Request)} if the historialMovimiento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HistorialMovimientoDTO> createHistorialMovimiento(
        @Valid @RequestBody HistorialMovimientoDTO historialMovimientoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save HistorialMovimiento : {}", historialMovimientoDTO);
        if (historialMovimientoDTO.getId() != null) {
            throw new BadRequestAlertException("A new historialMovimiento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        historialMovimientoDTO = historialMovimientoService.save(historialMovimientoDTO);
        return ResponseEntity.created(new URI("/api/historial-movimientos/" + historialMovimientoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, historialMovimientoDTO.getId().toString()))
            .body(historialMovimientoDTO);
    }

    /**
     * {@code PUT  /historial-movimientos/:id} : Updates an existing historialMovimiento.
     *
     * @param id the id of the historialMovimientoDTO to save.
     * @param historialMovimientoDTO the historialMovimientoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historialMovimientoDTO,
     * or with status {@code 400 (Bad Request)} if the historialMovimientoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historialMovimientoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HistorialMovimientoDTO> updateHistorialMovimiento(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody HistorialMovimientoDTO historialMovimientoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update HistorialMovimiento : {}, {}", id, historialMovimientoDTO);
        if (historialMovimientoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historialMovimientoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historialMovimientoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        historialMovimientoDTO = historialMovimientoService.update(historialMovimientoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historialMovimientoDTO.getId().toString()))
            .body(historialMovimientoDTO);
    }

    /**
     * {@code PATCH  /historial-movimientos/:id} : Partial updates given fields of an existing historialMovimiento, field will ignore if it is null
     *
     * @param id the id of the historialMovimientoDTO to save.
     * @param historialMovimientoDTO the historialMovimientoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historialMovimientoDTO,
     * or with status {@code 400 (Bad Request)} if the historialMovimientoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the historialMovimientoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the historialMovimientoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HistorialMovimientoDTO> partialUpdateHistorialMovimiento(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody HistorialMovimientoDTO historialMovimientoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update HistorialMovimiento partially : {}, {}", id, historialMovimientoDTO);
        if (historialMovimientoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historialMovimientoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historialMovimientoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HistorialMovimientoDTO> result = historialMovimientoService.partialUpdate(historialMovimientoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historialMovimientoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /historial-movimientos} : get all the historialMovimientos.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historialMovimientos in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HistorialMovimientoDTO>> getAllHistorialMovimientos(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get a page of HistorialMovimientos");
        Page<HistorialMovimientoDTO> page;
        if (eagerload) {
            page = historialMovimientoService.findAllWithEagerRelationships(pageable);
        } else {
            page = historialMovimientoService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historial-movimientos/:id} : get the "id" historialMovimiento.
     *
     * @param id the id of the historialMovimientoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historialMovimientoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HistorialMovimientoDTO> getHistorialMovimiento(@PathVariable("id") Long id) {
        LOG.debug("REST request to get HistorialMovimiento : {}", id);
        Optional<HistorialMovimientoDTO> historialMovimientoDTO = historialMovimientoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historialMovimientoDTO);
    }

    /**
     * {@code DELETE  /historial-movimientos/:id} : delete the "id" historialMovimiento.
     *
     * @param id the id of the historialMovimientoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorialMovimiento(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete HistorialMovimiento : {}", id);
        historialMovimientoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
