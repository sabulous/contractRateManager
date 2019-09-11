package com.sabulous.contractRateManager.services;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.contractRateManager.model.Role;
import com.sabulous.contractRateManager.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }
 
    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).get();
    }
 
    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.save(role);
    }
 
    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

}