package com.sabulous.contractRateManager.services;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.contractRateManager.model.Contract;
import com.sabulous.contractRateManager.repositories.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;

    @Autowired
    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> listAll() {
        List<Contract> contracts = new ArrayList<>();
        contractRepository.findAll().forEach(contracts::add);
        return contracts;
    }

    @Override
    public Contract getById(Integer id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public Contract saveOrUpdate(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void delete(Integer id) {
        contractRepository.deleteById(id);
    }

    public void printContracts() {
        List<Contract> contracts = listAll();
        contracts.forEach(Contract::print);
    }

}