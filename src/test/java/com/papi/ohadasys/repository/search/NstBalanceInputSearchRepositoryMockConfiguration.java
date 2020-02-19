package com.papi.ohadasys.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link NstBalanceInputSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class NstBalanceInputSearchRepositoryMockConfiguration {

    @MockBean
    private NstBalanceInputSearchRepository mockNstBalanceInputSearchRepository;

}
