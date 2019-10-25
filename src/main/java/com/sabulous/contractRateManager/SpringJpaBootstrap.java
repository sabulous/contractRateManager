package com.sabulous.contractRateManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sabulous.contractRateManager.model.Contract;
import com.sabulous.contractRateManager.model.Role;
import com.sabulous.contractRateManager.model.User;
import com.sabulous.contractRateManager.repositories.ContractRepository;
import com.sabulous.contractRateManager.services.RoleService;
import com.sabulous.contractRateManager.services.UserService;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ContractRepository contractRepository;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadRoles();
        loadUsers(); 
    }

    // loadContracts can be used to bootstrap initial contract records
    private List<Contract> loadContracts() {
        List<Contract> list = new ArrayList<>();

        Contract contract = new Contract();
        contract.setAgentName("TNT");
        contract.setOrigin("JFK");
        contract.setDestination("DXB");
        contract.setCommodity("DGR");
        contract.setCurrency("USD");
        contract.setStatus("DRAFT");
        contract.setValidFrom(new Date(2022, 5, 4));
        contract.setValidTo(new Date(2024, 2, 6));
        contract.setWeightBreak("Q100");
        contract.setValue("1.4");
        contract.setCreatedBy(1); // TODO db user id management
        contractRepository.save(contract);

        list.add(contract);

        Contract contract2 = new Contract();
        contract2.setAgentName("NAMCO");
        contract2.setOrigin("BRU");
        contract2.setDestination("BEY");
        contract2.setCommodity("GEN");
        contract2.setCurrency("EUR");
        contract2.setStatus("ACTIVE");
        contract2.setValidFrom(new Date(2023, 1, 4));
        contract2.setValidTo(new Date(2025, 2, 7));
        contract2.setWeightBreak("Q1000");
        contract2.setValue("0.9");
        contract2.setCreatedBy(2);
        contractRepository.save(contract2);

        list.add(contract2);
        
        Contract contract3 = new Contract();
        contract3.setAgentName("HELLMANN");
        contract3.setOrigin("VKO");
        contract3.setDestination("LOS");
        contract3.setCommodity("VAL");
        contract3.setCurrency("RUB");
        contract3.setStatus("ACTIVE");
        contract3.setValidFrom(new Date(2022, 11, 7));
        contract3.setValidTo(new Date(2023, 6, 4));
        contract3.setWeightBreak("Q300");
        contract3.setValue("3.25");
        contract3.setCreatedBy(7);
        contractRepository.save(contract3);

        list.add(contract3);

        return list;
    }
    
    private void loadUsers() {        
        User user1 = new User();
        user1.setName("userName");
        user1.setSurname("userSurname");
        user1.setEmail("userEmail");
        user1.setUsername("user");
        user1.setPassword("userPw");

        userService.saveOrUpdate(user1);
        
        User user2 = new User();
        user2.setName("adminName");
        user2.setSurname("adminSurname");
        user2.setEmail("adminEmail");
        user2.setUsername("admin");
        user2.setPassword("adminPw");
        user2.addRole(new Role("ADMIN"));

        userService.saveOrUpdate(user2);
    }
    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
    }
    
}