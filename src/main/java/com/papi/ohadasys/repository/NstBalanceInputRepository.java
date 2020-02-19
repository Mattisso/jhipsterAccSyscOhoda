package com.papi.ohadasys.repository;

import com.papi.ohadasys.domain.NstBalanceInput;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the NstBalanceInput entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NstBalanceInputRepository extends MongoRepository<NstBalanceInput, String> {

}
