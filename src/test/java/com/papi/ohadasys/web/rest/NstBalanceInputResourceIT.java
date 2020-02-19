package com.papi.ohadasys.web.rest;

import com.papi.ohadasys.JhipsterAccSyscOhodaApp;
import com.papi.ohadasys.domain.NstBalanceInput;
import com.papi.ohadasys.repository.NstBalanceInputRepository;
import com.papi.ohadasys.repository.search.NstBalanceInputSearchRepository;
import com.papi.ohadasys.service.NstBalanceInputService;
import com.papi.ohadasys.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.util.Collections;
import java.util.List;

import static com.papi.ohadasys.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NstBalanceInputResource} REST controller.
 */
@SpringBootTest(classes = JhipsterAccSyscOhodaApp.class)
public class NstBalanceInputResourceIT {

    private static final String DEFAULT_NUM_COMPTE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_COMPTE = "BBBBBBBBBB";

    private static final String DEFAULT_INTITUL_COMPTE = "AAAAAAAAAA";
    private static final String UPDATED_INTITUL_COMPTE = "BBBBBBBBBB";

    private static final Long DEFAULT_SOLDE_DEBIT = 1L;
    private static final Long UPDATED_SOLDE_DEBIT = 2L;

    private static final Long DEFAULT_SOLDE_CREDIT = 1L;
    private static final Long UPDATED_SOLDE_CREDIT = 2L;

    private static final String DEFAULT_COMPTE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COMPTE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private NstBalanceInputRepository nstBalanceInputRepository;

    @Autowired
    private NstBalanceInputService nstBalanceInputService;

    /**
     * This repository is mocked in the com.papi.ohadasys.repository.search test package.
     *
     * @see com.papi.ohadasys.repository.search.NstBalanceInputSearchRepositoryMockConfiguration
     */
    @Autowired
    private NstBalanceInputSearchRepository mockNstBalanceInputSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNstBalanceInputMockMvc;

    private NstBalanceInput nstBalanceInput;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NstBalanceInputResource nstBalanceInputResource = new NstBalanceInputResource(nstBalanceInputService);
        this.restNstBalanceInputMockMvc = MockMvcBuilders.standaloneSetup(nstBalanceInputResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NstBalanceInput createEntity() {
        NstBalanceInput nstBalanceInput = new NstBalanceInput()
            .numCompte(DEFAULT_NUM_COMPTE)
            .intitulCompte(DEFAULT_INTITUL_COMPTE)
            .soldeDebit(DEFAULT_SOLDE_DEBIT)
            .soldeCredit(DEFAULT_SOLDE_CREDIT)
            .compteNumber(DEFAULT_COMPTE_NUMBER);
        return nstBalanceInput;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NstBalanceInput createUpdatedEntity() {
        NstBalanceInput nstBalanceInput = new NstBalanceInput()
            .numCompte(UPDATED_NUM_COMPTE)
            .intitulCompte(UPDATED_INTITUL_COMPTE)
            .soldeDebit(UPDATED_SOLDE_DEBIT)
            .soldeCredit(UPDATED_SOLDE_CREDIT)
            .compteNumber(UPDATED_COMPTE_NUMBER);
        return nstBalanceInput;
    }

    @BeforeEach
    public void initTest() {
        nstBalanceInputRepository.deleteAll();
        nstBalanceInput = createEntity();
    }

    @Test
    public void createNstBalanceInput() throws Exception {
        int databaseSizeBeforeCreate = nstBalanceInputRepository.findAll().size();

        // Create the NstBalanceInput
        restNstBalanceInputMockMvc.perform(post("/api/nst-balance-inputs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nstBalanceInput)))
            .andExpect(status().isCreated());

        // Validate the NstBalanceInput in the database
        List<NstBalanceInput> nstBalanceInputList = nstBalanceInputRepository.findAll();
        assertThat(nstBalanceInputList).hasSize(databaseSizeBeforeCreate + 1);
        NstBalanceInput testNstBalanceInput = nstBalanceInputList.get(nstBalanceInputList.size() - 1);
        assertThat(testNstBalanceInput.getNumCompte()).isEqualTo(DEFAULT_NUM_COMPTE);
        assertThat(testNstBalanceInput.getIntitulCompte()).isEqualTo(DEFAULT_INTITUL_COMPTE);
        assertThat(testNstBalanceInput.getSoldeDebit()).isEqualTo(DEFAULT_SOLDE_DEBIT);
        assertThat(testNstBalanceInput.getSoldeCredit()).isEqualTo(DEFAULT_SOLDE_CREDIT);
        assertThat(testNstBalanceInput.getCompteNumber()).isEqualTo(DEFAULT_COMPTE_NUMBER);

        // Validate the NstBalanceInput in Elasticsearch
        verify(mockNstBalanceInputSearchRepository, times(1)).save(testNstBalanceInput);
    }

    @Test
    public void createNstBalanceInputWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nstBalanceInputRepository.findAll().size();

        // Create the NstBalanceInput with an existing ID
        nstBalanceInput.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restNstBalanceInputMockMvc.perform(post("/api/nst-balance-inputs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nstBalanceInput)))
            .andExpect(status().isBadRequest());

        // Validate the NstBalanceInput in the database
        List<NstBalanceInput> nstBalanceInputList = nstBalanceInputRepository.findAll();
        assertThat(nstBalanceInputList).hasSize(databaseSizeBeforeCreate);

        // Validate the NstBalanceInput in Elasticsearch
        verify(mockNstBalanceInputSearchRepository, times(0)).save(nstBalanceInput);
    }


    @Test
    public void checkNumCompteIsRequired() throws Exception {
        int databaseSizeBeforeTest = nstBalanceInputRepository.findAll().size();
        // set the field null
        nstBalanceInput.setNumCompte(null);

        // Create the NstBalanceInput, which fails.

        restNstBalanceInputMockMvc.perform(post("/api/nst-balance-inputs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nstBalanceInput)))
            .andExpect(status().isBadRequest());

        List<NstBalanceInput> nstBalanceInputList = nstBalanceInputRepository.findAll();
        assertThat(nstBalanceInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIntitulCompteIsRequired() throws Exception {
        int databaseSizeBeforeTest = nstBalanceInputRepository.findAll().size();
        // set the field null
        nstBalanceInput.setIntitulCompte(null);

        // Create the NstBalanceInput, which fails.

        restNstBalanceInputMockMvc.perform(post("/api/nst-balance-inputs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nstBalanceInput)))
            .andExpect(status().isBadRequest());

        List<NstBalanceInput> nstBalanceInputList = nstBalanceInputRepository.findAll();
        assertThat(nstBalanceInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNstBalanceInputs() throws Exception {
        // Initialize the database
        nstBalanceInputRepository.save(nstBalanceInput);

        // Get all the nstBalanceInputList
        restNstBalanceInputMockMvc.perform(get("/api/nst-balance-inputs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nstBalanceInput.getId())))
            .andExpect(jsonPath("$.[*].numCompte").value(hasItem(DEFAULT_NUM_COMPTE)))
            .andExpect(jsonPath("$.[*].intitulCompte").value(hasItem(DEFAULT_INTITUL_COMPTE)))
            .andExpect(jsonPath("$.[*].soldeDebit").value(hasItem(DEFAULT_SOLDE_DEBIT.intValue())))
            .andExpect(jsonPath("$.[*].soldeCredit").value(hasItem(DEFAULT_SOLDE_CREDIT.intValue())))
            .andExpect(jsonPath("$.[*].compteNumber").value(hasItem(DEFAULT_COMPTE_NUMBER)));
    }
    
    @Test
    public void getNstBalanceInput() throws Exception {
        // Initialize the database
        nstBalanceInputRepository.save(nstBalanceInput);

        // Get the nstBalanceInput
        restNstBalanceInputMockMvc.perform(get("/api/nst-balance-inputs/{id}", nstBalanceInput.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nstBalanceInput.getId()))
            .andExpect(jsonPath("$.numCompte").value(DEFAULT_NUM_COMPTE))
            .andExpect(jsonPath("$.intitulCompte").value(DEFAULT_INTITUL_COMPTE))
            .andExpect(jsonPath("$.soldeDebit").value(DEFAULT_SOLDE_DEBIT.intValue()))
            .andExpect(jsonPath("$.soldeCredit").value(DEFAULT_SOLDE_CREDIT.intValue()))
            .andExpect(jsonPath("$.compteNumber").value(DEFAULT_COMPTE_NUMBER));
    }

    @Test
    public void getNonExistingNstBalanceInput() throws Exception {
        // Get the nstBalanceInput
        restNstBalanceInputMockMvc.perform(get("/api/nst-balance-inputs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNstBalanceInput() throws Exception {
        // Initialize the database
        nstBalanceInputService.save(nstBalanceInput);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockNstBalanceInputSearchRepository);

        int databaseSizeBeforeUpdate = nstBalanceInputRepository.findAll().size();

        // Update the nstBalanceInput
        NstBalanceInput updatedNstBalanceInput = nstBalanceInputRepository.findById(nstBalanceInput.getId()).get();
        updatedNstBalanceInput
            .numCompte(UPDATED_NUM_COMPTE)
            .intitulCompte(UPDATED_INTITUL_COMPTE)
            .soldeDebit(UPDATED_SOLDE_DEBIT)
            .soldeCredit(UPDATED_SOLDE_CREDIT)
            .compteNumber(UPDATED_COMPTE_NUMBER);

        restNstBalanceInputMockMvc.perform(put("/api/nst-balance-inputs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNstBalanceInput)))
            .andExpect(status().isOk());

        // Validate the NstBalanceInput in the database
        List<NstBalanceInput> nstBalanceInputList = nstBalanceInputRepository.findAll();
        assertThat(nstBalanceInputList).hasSize(databaseSizeBeforeUpdate);
        NstBalanceInput testNstBalanceInput = nstBalanceInputList.get(nstBalanceInputList.size() - 1);
        assertThat(testNstBalanceInput.getNumCompte()).isEqualTo(UPDATED_NUM_COMPTE);
        assertThat(testNstBalanceInput.getIntitulCompte()).isEqualTo(UPDATED_INTITUL_COMPTE);
        assertThat(testNstBalanceInput.getSoldeDebit()).isEqualTo(UPDATED_SOLDE_DEBIT);
        assertThat(testNstBalanceInput.getSoldeCredit()).isEqualTo(UPDATED_SOLDE_CREDIT);
        assertThat(testNstBalanceInput.getCompteNumber()).isEqualTo(UPDATED_COMPTE_NUMBER);

        // Validate the NstBalanceInput in Elasticsearch
        verify(mockNstBalanceInputSearchRepository, times(1)).save(testNstBalanceInput);
    }

    @Test
    public void updateNonExistingNstBalanceInput() throws Exception {
        int databaseSizeBeforeUpdate = nstBalanceInputRepository.findAll().size();

        // Create the NstBalanceInput

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNstBalanceInputMockMvc.perform(put("/api/nst-balance-inputs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nstBalanceInput)))
            .andExpect(status().isBadRequest());

        // Validate the NstBalanceInput in the database
        List<NstBalanceInput> nstBalanceInputList = nstBalanceInputRepository.findAll();
        assertThat(nstBalanceInputList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NstBalanceInput in Elasticsearch
        verify(mockNstBalanceInputSearchRepository, times(0)).save(nstBalanceInput);
    }

    @Test
    public void deleteNstBalanceInput() throws Exception {
        // Initialize the database
        nstBalanceInputService.save(nstBalanceInput);

        int databaseSizeBeforeDelete = nstBalanceInputRepository.findAll().size();

        // Delete the nstBalanceInput
        restNstBalanceInputMockMvc.perform(delete("/api/nst-balance-inputs/{id}", nstBalanceInput.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NstBalanceInput> nstBalanceInputList = nstBalanceInputRepository.findAll();
        assertThat(nstBalanceInputList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NstBalanceInput in Elasticsearch
        verify(mockNstBalanceInputSearchRepository, times(1)).deleteById(nstBalanceInput.getId());
    }

    @Test
    public void searchNstBalanceInput() throws Exception {
        // Initialize the database
        nstBalanceInputService.save(nstBalanceInput);
        when(mockNstBalanceInputSearchRepository.search(queryStringQuery("id:" + nstBalanceInput.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(nstBalanceInput), PageRequest.of(0, 1), 1));
        // Search the nstBalanceInput
        restNstBalanceInputMockMvc.perform(get("/api/_search/nst-balance-inputs?query=id:" + nstBalanceInput.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nstBalanceInput.getId())))
            .andExpect(jsonPath("$.[*].numCompte").value(hasItem(DEFAULT_NUM_COMPTE)))
            .andExpect(jsonPath("$.[*].intitulCompte").value(hasItem(DEFAULT_INTITUL_COMPTE)))
            .andExpect(jsonPath("$.[*].soldeDebit").value(hasItem(DEFAULT_SOLDE_DEBIT.intValue())))
            .andExpect(jsonPath("$.[*].soldeCredit").value(hasItem(DEFAULT_SOLDE_CREDIT.intValue())))
            .andExpect(jsonPath("$.[*].compteNumber").value(hasItem(DEFAULT_COMPTE_NUMBER)));
    }
}
