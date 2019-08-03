package com.sabulous.contractRateManager.services;

import java.util.List;

import com.sabulous.contractRateManager.model.Contract;

public interface ContractService {
    List<Contract> getAllContracts();
    Contract getContractById(int id);
    Contract addOrEditContract(Contract contract);
    void deleteContract(int id);
    void printContracts();
    void printContract(int id);
    int findNextId();
}