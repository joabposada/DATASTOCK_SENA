package co.edu.sena.datastock.web.rest;

import co.edu.sena.datastock.repository.HistorialRolUsuarioRepository;
import co.edu.sena.datastock.service.HistorialRolUsuarioService;
import co.edu.sena.datastock.service.dto.HistorialRolUsuarioDTO;
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
 * REST controller for managing {@link co.edu.sena.datastock.domain.HistorialRolUsuario}.
 */
@RestController
@RequestMapping("/api/historial-rol-usuarios")
public class HistorialRolUsuarioResource {

    private static final Logger LOG = LoggerFactory.getLogger(HistorialRolUsuarioResource.class);

    private static final String ENTITY_NAME = "historialRolUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistorialRolUsuarioService historialRolUsuarioService;

    private final HistorialRolUsuarioRepository historialRolUsuarioRepository;

    public HistorialRolUsuarioResource(
        HistorialRolUsuarioService historialRolUsuarioService,
        HistorialRolUsuarioRepository historialRolUsuarioRepository
    ) {
        this.historialRolUsuarioService = historialRolUsuarioService;
        this.historialRolUsuarioRepository = historialRolUsuarioRepository;
    }

    /**
     * {@code POST  /historial-rol-usuarios} : Create a new historialRolUsuario.
     *
     * @param historialRolUsuarioDTO the historialRolUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historialRolUsuarioDTO, or with status {@code 400 (Bad Request)} if the historialRolUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HistorialRolUsuarioDTO> createHistorialRolUsuario(
        @Valid @RequestBody HistorialRolUsuarioDTO historialRolUsuarioDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save HistorialRolUsuario : {}", historialRolUsuarioDTO);
        if (historialRolUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new historialRolUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        historialRolUsuarioDTO = historialRolUsuarioService.save(historialRolUsuarioDTO);
        return ResponseEntity.created(new URI("/api/historial-rol-usuarios/" + historialRolUsuarioDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, historialRolUsuarioDTO.getId().toString()))
            .body(historialRolUsuarioDTO);
    }

    /**
     * {@code PUT  /historial-rol-usuarios/:id} : Updates an existing historialRolUsuario.
     *
     * @param id the id of the historialRolUsuarioDTO to save.
     * @param historialRolUsuarioDTO the historialRolUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historialRolUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the historialRolUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historialRolUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HistorialRolUsuarioDTO> updateHistorialRolUsuario(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody HistorialRolUsuarioDTO historialRolUsuarioDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update HistorialRolUsuario : {}, {}", id, historialRolUsuarioDTO);
        if (historialRolUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historialRolUsuarioDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historialRolUsuarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        historialRolUsuarioDTO = historialRolUsuarioService.update(historialRolUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historialRolUsuarioDTO.getId().toString()))
            .body(historialRolUsuarioDTO);
    }

    /**
     * {@code PATCH  /historial-rol-usuarios/:id} : Partial updates given fields of an existing historialRolUsuario, field will ignore if it is null
     *
     * @param id the id of the historialRolUsuarioDTO to save.
     * @param historialRolUsuarioDTO the historialRolUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historialRolUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the historialRolUsuarioDTO is not valid,
     * or with status {@code 404 (Not Found)} if the historialRolUsuarioDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the historialRolUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HistorialRolUsuarioDTO> partialUpdateHistorialRolUsuario(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody HistorialRolUsuarioDTO historialRolUsuarioDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update HistorialRolUsuario partially : {}, {}", id, historialRolUsuarioDTO);
        if (historialRolUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historialRolUsuarioDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historialRolUsuarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HistorialRolUsuarioDTO> result = historialRolUsuarioService.partialUpdate(historialRolUsuarioDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historialRolUsuarioDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /historial-rol-usuarios} : get all the historialRolUsuarios.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historialRolUsuarios in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HistorialRolUsuarioDTO>> getAllHistorialRolUsuarios(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get a page of HistorialRolUsuarios");
        Page<HistorialRolUsuarioDTO> page;
        if (eagerload) {
            page = historialRolUsuarioService.findAllWithEagerRelationships(pageable);
        } else {
            page = historialRolUsuarioService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historial-rol-usuarios/:id} : get the "id" historialRolUsuario.
     *
     * @param id the id of the historialRolUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historialRolUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HistorialRolUsuarioDTO> getHistorialRolUsuario(@PathVariable("id") Long id) {
        LOG.debug("REST request to get HistorialRolUsuario : {}", id);
        Optional<HistorialRolUsuarioDTO> historialRolUsuarioDTO = historialRolUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historialRolUsuarioDTO);
    }

    /**
     * {@code DELETE  /historial-rol-usuarios/:id} : delete the "id" historialRolUsuario.
     *
     * @param id the id of the historialRolUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorialRolUsuario(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete HistorialRolUsuario : {}", id);
        historialRolUsuarioService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
