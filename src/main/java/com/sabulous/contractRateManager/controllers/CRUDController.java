package com.sabulous.contractRateManager.controllers;

import com.sabulous.contractRateManager.model.Contract;
import com.sabulous.contractRateManager.services.ContractService;

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

    @GetMapping("/contracts")
    public String getContracts(Model model) {
        model.addAttribute("contracts", contractService.listAll());
        model.addAttribute("contract", new Contract());
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
        return "redirect:/contracts"; // TODO show details of new contract for possible editing
    }

    @RequestMapping("contracts/addMultiple")
    public String addContractMultiple() {
        // TODO add service invocation
        return "redirect:/contracts";
    }
}