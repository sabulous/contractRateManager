package com.sabulous.contractRateManager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sabulous.contractRateManager.model.Contract;
import com.sabulous.contractRateManager.model.Role;
import com.sabulous.contractRateManager.model.User;
import com.sabulous.contractRateManager.repositories.ContractRepository;
import com.sabulous.contractRateManager.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;

    private UserRepository userRepository;

    @Autowired
    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Contract> listAll() {
        List<Contract> contracts = new ArrayList<>();
        contractRepository.findAll().forEach(contracts::add);
        return contracts;
    }

    @Override
    public List<Contract> listContractsByUserId(Integer userId) {

        User contractOwner = userRepository.findById(userId).get();

        List<Role> roles = contractOwner.getRoles();
        for(Role role : roles) {
            if(role.getRole().equals("ADMIN")) {
                return listAll();
            }
        }

        List<Contract> contractList = (List<Contract>)contractRepository.findAll();

        return contractList.stream().filter(c -> c.getCreatedBy() == contractOwner.getId()).collect(Collectors.toList());
    }

    @Override
    public Contract getById(Integer id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public Contract saveOrUpdate(Contract contract) {
        // TODO check duplicates
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