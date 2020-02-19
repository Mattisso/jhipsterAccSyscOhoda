package com.papi.ohadasys.repository.search;

import com.papi.ohadasys.domain.NstBalanceInput;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link NstBalanceInput} entity.
 */
public interface NstBalanceInputSearchRepository extends ElasticsearchRepository<NstBalanceInput, String> {
}
