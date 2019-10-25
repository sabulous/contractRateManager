package com.sabulous.contractRateManager.controllers;

import com.sabulous.contractRateManager.model.Contract;
import com.sabulous.contractRateManager.services.ContractService;
import com.sabulous.contractRateManager.services.IUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CRUDController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private IUserDetailsService userDetailsService;

    @GetMapping("/contracts")
    public String getContracts(Model model) {
        model.addAttribute("contracts", contractService.listContractsByUserId(userDetailsService.getLoggedInUserId())); // returns contracts only if owned by the logged in user
        
        Contract contract = new Contract();
        contract.setCreatedBy(userDetailsService.getLoggedInUserId());
        model.addAttribute("contract", contract);
        
        return "contracts";
    }
    
    @RequestMapping("/contracts/delete/{contractId}")
    public String deleteContract(@PathVariable int contractId) {
        contractService.delete(contractId);
        return "redirect:/contracts";
    }

    @PostMapping("contracts/add")
    public String addContract(@ModelAttribute("contract") Contract contract, BindingResult result ) {
        contractService.saveOrUpdate(contract);
        contract.print();
        return "redirect:/contracts";
    }

}