package co.edu.sena.datastock.web.rest;

import co.edu.sena.datastock.repository.LoteEntradaRepository;
import co.edu.sena.datastock.service.LoteEntradaService;
import co.edu.sena.datastock.service.dto.LoteEntradaDTO;
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
 * REST controller for managing {@link co.edu.sena.datastock.domain.LoteEntrada}.
 */
@RestController
@RequestMapping("/api/lote-entradas")
public class LoteEntradaResource {

    private static final Logger LOG = LoggerFactory.getLogger(LoteEntradaResource.class);

    private static final String ENTITY_NAME = "loteEntrada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoteEntradaService loteEntradaService;

    private final LoteEntradaRepository loteEntradaRepository;

    public LoteEntradaResource(LoteEntradaService loteEntradaService, LoteEntradaRepository loteEntradaRepository) {
        this.loteEntradaService = loteEntradaService;
        this.loteEntradaRepository = loteEntradaRepository;
    }

    /**
     * {@code POST  /lote-entradas} : Create a new loteEntrada.
     *
     * @param loteEntradaDTO the loteEntradaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loteEntradaDTO, or with status {@code 400 (Bad Request)} if the loteEntrada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LoteEntradaDTO> createLoteEntrada(@Valid @RequestBody LoteEntradaDTO loteEntradaDTO) throws URISyntaxException {
        LOG.debug("REST request to save LoteEntrada : {}", loteEntradaDTO);
        if (loteEntradaDTO.getId() != null) {
            throw new BadRequestAlertException("A new loteEntrada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        loteEntradaDTO = loteEntradaService.save(loteEntradaDTO);
        return ResponseEntity.created(new URI("/api/lote-entradas/" + loteEntradaDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, loteEntradaDTO.getId().toString()))
            .body(loteEntradaDTO);
    }

    /**
     * {@code PUT  /lote-entradas/:id} : Updates an existing loteEntrada.
     *
     * @param id the id of the loteEntradaDTO to save.
     * @param loteEntradaDTO the loteEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loteEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the loteEntradaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loteEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LoteEntradaDTO> updateLoteEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LoteEntradaDTO loteEntradaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update LoteEntrada : {}, {}", id, loteEntradaDTO);
        if (loteEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loteEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loteEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        loteEntradaDTO = loteEntradaService.update(loteEntradaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loteEntradaDTO.getId().toString()))
            .body(loteEntradaDTO);
    }

    /**
     * {@code PATCH  /lote-entradas/:id} : Partial updates given fields of an existing loteEntrada, field will ignore if it is null
     *
     * @param id the id of the loteEntradaDTO to save.
     * @param loteEntradaDTO the loteEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loteEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the loteEntradaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the loteEntradaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the loteEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LoteEntradaDTO> partialUpdateLoteEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LoteEntradaDTO loteEntradaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LoteEntrada partially : {}, {}", id, loteEntradaDTO);
        if (loteEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loteEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loteEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LoteEntradaDTO> result = loteEntradaService.partialUpdate(loteEntradaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loteEntradaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /lote-entradas} : get all the loteEntradas.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loteEntradas in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LoteEntradaDTO>> getAllLoteEntradas(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get a page of LoteEntradas");
        Page<LoteEntradaDTO> page;
        if (eagerload) {
            page = loteEntradaService.findAllWithEagerRelationships(pageable);
        } else {
            page = loteEntradaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lote-entradas/:id} : get the "id" loteEntrada.
     *
     * @param id the id of the loteEntradaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loteEntradaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoteEntradaDTO> getLoteEntrada(@PathVariable("id") Long id) {
        LOG.debug("REST request to get LoteEntrada : {}", id);
        Optional<LoteEntradaDTO> loteEntradaDTO = loteEntradaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loteEntradaDTO);
    }

    /**
     * {@code DELETE  /lote-entradas/:id} : delete the "id" loteEntrada.
     *
     * @param id the id of the loteEntradaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoteEntrada(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete LoteEntrada : {}", id);
        loteEntradaService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
