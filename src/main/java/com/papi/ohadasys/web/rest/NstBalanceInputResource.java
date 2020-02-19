package com.papi.ohadasys.web.rest;

import com.papi.ohadasys.domain.NstBalanceInput;
import com.papi.ohadasys.service.NstBalanceInputService;
import com.papi.ohadasys.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.papi.ohadasys.domain.NstBalanceInput}.
 */
@RestController
@RequestMapping("/api")
public class NstBalanceInputResource {

    private final Logger log = LoggerFactory.getLogger(NstBalanceInputResource.class);

    private static final String ENTITY_NAME = "nstBalanceInput";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NstBalanceInputService nstBalanceInputService;

    public NstBalanceInputResource(NstBalanceInputService nstBalanceInputService) {
        this.nstBalanceInputService = nstBalanceInputService;
    }

    /**
     * {@code POST  /nst-balance-inputs} : Create a new nstBalanceInput.
     *
     * @param nstBalanceInput the nstBalanceInput to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nstBalanceInput, or with status {@code 400 (Bad Request)} if the nstBalanceInput has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nst-balance-inputs")
    public ResponseEntity<NstBalanceInput> createNstBalanceInput(@Valid @RequestBody NstBalanceInput nstBalanceInput) throws URISyntaxException {
        log.debug("REST request to save NstBalanceInput : {}", nstBalanceInput);
        if (nstBalanceInput.getId() != null) {
            throw new BadRequestAlertException("A new nstBalanceInput cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NstBalanceInput result = nstBalanceInputService.save(nstBalanceInput);
        return ResponseEntity.created(new URI("/api/nst-balance-inputs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nst-balance-inputs} : Updates an existing nstBalanceInput.
     *
     * @param nstBalanceInput the nstBalanceInput to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nstBalanceInput,
     * or with status {@code 400 (Bad Request)} if the nstBalanceInput is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nstBalanceInput couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nst-balance-inputs")
    public ResponseEntity<NstBalanceInput> updateNstBalanceInput(@Valid @RequestBody NstBalanceInput nstBalanceInput) throws URISyntaxException {
        log.debug("REST request to update NstBalanceInput : {}", nstBalanceInput);
        if (nstBalanceInput.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NstBalanceInput result = nstBalanceInputService.save(nstBalanceInput);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nstBalanceInput.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nst-balance-inputs} : get all the nstBalanceInputs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nstBalanceInputs in body.
     */
    @GetMapping("/nst-balance-inputs")
    public ResponseEntity<List<NstBalanceInput>> getAllNstBalanceInputs(Pageable pageable) {
        log.debug("REST request to get a page of NstBalanceInputs");
        Page<NstBalanceInput> page = nstBalanceInputService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nst-balance-inputs/:id} : get the "id" nstBalanceInput.
     *
     * @param id the id of the nstBalanceInput to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nstBalanceInput, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nst-balance-inputs/{id}")
    public ResponseEntity<NstBalanceInput> getNstBalanceInput(@PathVariable String id) {
        log.debug("REST request to get NstBalanceInput : {}", id);
        Optional<NstBalanceInput> nstBalanceInput = nstBalanceInputService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nstBalanceInput);
    }

    /**
     * {@code DELETE  /nst-balance-inputs/:id} : delete the "id" nstBalanceInput.
     *
     * @param id the id of the nstBalanceInput to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nst-balance-inputs/{id}")
    public ResponseEntity<Void> deleteNstBalanceInput(@PathVariable String id) {
        log.debug("REST request to delete NstBalanceInput : {}", id);
        nstBalanceInputService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/nst-balance-inputs?query=:query} : search for the nstBalanceInput corresponding
     * to the query.
     *
     * @param query the query of the nstBalanceInput search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/nst-balance-inputs")
    public ResponseEntity<List<NstBalanceInput>> searchNstBalanceInputs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of NstBalanceInputs for query {}", query);
        Page<NstBalanceInput> page = nstBalanceInputService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
