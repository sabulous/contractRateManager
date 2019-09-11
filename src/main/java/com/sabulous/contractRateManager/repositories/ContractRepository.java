package com.sabulous.contractRateManager.repositories;

import com.sabulous.contractRateManager.model.Contract;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Integer> {
    
}