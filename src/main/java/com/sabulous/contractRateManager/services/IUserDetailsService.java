package com.sabulous.contractRateManager.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {
    public Integer getLoggedInUserId();
}