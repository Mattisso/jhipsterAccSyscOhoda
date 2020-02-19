package com.papi.ohadasys.service;

import com.papi.ohadasys.domain.NstBalanceInput;
import com.papi.ohadasys.repository.NstBalanceInputRepository;
import com.papi.ohadasys.repository.search.NstBalanceInputSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link NstBalanceInput}.
 */
@Service
public class NstBalanceInputService {

    private final Logger log = LoggerFactory.getLogger(NstBalanceInputService.class);

    private final NstBalanceInputRepository nstBalanceInputRepository;

    private final NstBalanceInputSearchRepository nstBalanceInputSearchRepository;

    public NstBalanceInputService(NstBalanceInputRepository nstBalanceInputRepository, NstBalanceInputSearchRepository nstBalanceInputSearchRepository) {
        this.nstBalanceInputRepository = nstBalanceInputRepository;
        this.nstBalanceInputSearchRepository = nstBalanceInputSearchRepository;
    }

    /**
     * Save a nstBalanceInput.
     *
     * @param nstBalanceInput the entity to save.
     * @return the persisted entity.
     */
    public NstBalanceInput save(NstBalanceInput nstBalanceInput) {
        log.debug("Request to save NstBalanceInput : {}", nstBalanceInput);
        NstBalanceInput result = nstBalanceInputRepository.save(nstBalanceInput);
        nstBalanceInputSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the nstBalanceInputs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<NstBalanceInput> findAll(Pageable pageable) {
        log.debug("Request to get all NstBalanceInputs");
        return nstBalanceInputRepository.findAll(pageable);
    }

    /**
     * Get one nstBalanceInput by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<NstBalanceInput> findOne(String id) {
        log.debug("Request to get NstBalanceInput : {}", id);
        return nstBalanceInputRepository.findById(id);
    }

    /**
     * Delete the nstBalanceInput by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete NstBalanceInput : {}", id);
        nstBalanceInputRepository.deleteById(id);
        nstBalanceInputSearchRepository.deleteById(id);
    }

    /**
     * Search for the nstBalanceInput corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<NstBalanceInput> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of NstBalanceInputs for query {}", query);
        return nstBalanceInputSearchRepository.search(queryStringQuery(query), pageable);    }
}
