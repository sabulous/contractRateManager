package com.sabulous.contractRateManager.repositories;

import com.sabulous.contractRateManager.model.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Role findByRole(String role);
}