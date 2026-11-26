package co.edu.sena.datastock.web.rest;

import co.edu.sena.datastock.repository.InventarioActualRepository;
import co.edu.sena.datastock.service.InventarioActualService;
import co.edu.sena.datastock.service.dto.InventarioActualDTO;
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
 * REST controller for managing {@link co.edu.sena.datastock.domain.InventarioActual}.
 */
@RestController
@RequestMapping("/api/inventario-actuals")
public class InventarioActualResource {

    private static final Logger LOG = LoggerFactory.getLogger(InventarioActualResource.class);

    private static final String ENTITY_NAME = "inventarioActual";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InventarioActualService inventarioActualService;

    private final InventarioActualRepository inventarioActualRepository;

    public InventarioActualResource(
        InventarioActualService inventarioActualService,
        InventarioActualRepository inventarioActualRepository
    ) {
        this.inventarioActualService = inventarioActualService;
        this.inventarioActualRepository = inventarioActualRepository;
    }

    /**
     * {@code POST  /inventario-actuals} : Create a new inventarioActual.
     *
     * @param inventarioActualDTO the inventarioActualDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventarioActualDTO, or with status {@code 400 (Bad Request)} if the inventarioActual has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<InventarioActualDTO> createInventarioActual(@Valid @RequestBody InventarioActualDTO inventarioActualDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save InventarioActual : {}", inventarioActualDTO);
        if (inventarioActualDTO.getId() != null) {
            throw new BadRequestAlertException("A new inventarioActual cannot already have an ID", ENTITY_NAME, "idexists");
        }
        inventarioActualDTO = inventarioActualService.save(inventarioActualDTO);
        return ResponseEntity.created(new URI("/api/inventario-actuals/" + inventarioActualDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, inventarioActualDTO.getId().toString()))
            .body(inventarioActualDTO);
    }

    /**
     * {@code PUT  /inventario-actuals/:id} : Updates an existing inventarioActual.
     *
     * @param id the id of the inventarioActualDTO to save.
     * @param inventarioActualDTO the inventarioActualDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventarioActualDTO,
     * or with status {@code 400 (Bad Request)} if the inventarioActualDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inventarioActualDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<InventarioActualDTO> updateInventarioActual(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InventarioActualDTO inventarioActualDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update InventarioActual : {}, {}", id, inventarioActualDTO);
        if (inventarioActualDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventarioActualDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventarioActualRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        inventarioActualDTO = inventarioActualService.update(inventarioActualDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inventarioActualDTO.getId().toString()))
            .body(inventarioActualDTO);
    }

    /**
     * {@code PATCH  /inventario-actuals/:id} : Partial updates given fields of an existing inventarioActual, field will ignore if it is null
     *
     * @param id the id of the inventarioActualDTO to save.
     * @param inventarioActualDTO the inventarioActualDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventarioActualDTO,
     * or with status {@code 400 (Bad Request)} if the inventarioActualDTO is not valid,
     * or with status {@code 404 (Not Found)} if the inventarioActualDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the inventarioActualDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InventarioActualDTO> partialUpdateInventarioActual(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InventarioActualDTO inventarioActualDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update InventarioActual partially : {}, {}", id, inventarioActualDTO);
        if (inventarioActualDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventarioActualDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventarioActualRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InventarioActualDTO> result = inventarioActualService.partialUpdate(inventarioActualDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inventarioActualDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /inventario-actuals} : get all the inventarioActuals.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventarioActuals in body.
     */
    @GetMapping("")
    public ResponseEntity<List<InventarioActualDTO>> getAllInventarioActuals(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get a page of InventarioActuals");
        Page<InventarioActualDTO> page;
        if (eagerload) {
            page = inventarioActualService.findAllWithEagerRelationships(pageable);
        } else {
            page = inventarioActualService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inventario-actuals/:id} : get the "id" inventarioActual.
     *
     * @param id the id of the inventarioActualDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventarioActualDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InventarioActualDTO> getInventarioActual(@PathVariable("id") Long id) {
        LOG.debug("REST request to get InventarioActual : {}", id);
        Optional<InventarioActualDTO> inventarioActualDTO = inventarioActualService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inventarioActualDTO);
    }

    /**
     * {@code DELETE  /inventario-actuals/:id} : delete the "id" inventarioActual.
     *
     * @param id the id of the inventarioActualDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventarioActual(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete InventarioActual : {}", id);
        inventarioActualService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
