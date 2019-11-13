package com.sabulous.contractRateManager.services;

import java.util.List;

import com.sabulous.contractRateManager.model.Contract;

public interface ContractService extends CRUDService<Contract> {
    public List<Contract> listContractsByRole(String role);
    public List<Contract> listContractsByUserId(Integer userId);
}