package com.sabulous.contractRateManager.services;

import com.sabulous.contractRateManager.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {
    public Integer getLoggedInUserId();
    public User getLoggedInUser();
    public String getPrimaryRoleOfLoggedInUser();
}