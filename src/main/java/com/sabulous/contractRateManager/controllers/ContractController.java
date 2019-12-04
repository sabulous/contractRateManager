package com.sabulous.contractRateManager.controllers;

import java.util.List;

import com.sabulous.contractRateManager.model.Contract;
import com.sabulous.contractRateManager.model.Role;
import com.sabulous.contractRateManager.services.ContractService;
import com.sabulous.contractRateManager.services.IUserDetailsService;
import com.sabulous.contractRateManager.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private UserService userService;

    @Autowired
    private IUserDetailsService userDetailsService;

    // retrieves contracts that the logged in user created
    @RequestMapping("/contracts")
    public String getContracts(Model model) {
        // returns contracts only if owned by the logged in user
        model.addAttribute("contracts", contractService.listContractsByUserId(userDetailsService.getLoggedInUserId()));
        
        Contract contract = new Contract();
        List<Role> roles = userService.getById(userDetailsService.getLoggedInUserId()).getRoles();
        String primaryRole = "ADMIN";
        for(Role r : roles) {
            primaryRole = r.getRole();
            if(r.getRole().equals("ADMIN")) {
                break;
            }
        }
        contract.setPrimaryRole(primaryRole);

        contract.setCreatedBy(userDetailsService.getLoggedInUserId());

        model.addAttribute("contract", contract);
        
        return "contracts";
    }

    // retrieves contracts whose role is the same with the logged in user's
    @RequestMapping("/contractsByRole")
    public String getOwnedContracts(@RequestParam String role, Model model) {
        model.addAttribute("contracts", contractService.listContractsByRole(role));

        Contract contract = new Contract();

        List<Role> roles = userService.getById(userDetailsService.getLoggedInUserId()).getRoles();
        String primaryRole = "ADMIN";
        for(Role r : roles) {
            primaryRole = r.getRole();
            if(r.getRole().equals("ADMIN")) {
                break;
            }
        }
        contract.setPrimaryRole(primaryRole);

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

    @PostMapping("contracts/edit")
    public String editContract(@ModelAttribute("contract") Contract contract, BindingResult result) {
        contractService.saveOrUpdate(contract);
        contract.print();
        return "redirect:/contracts";
    }

}