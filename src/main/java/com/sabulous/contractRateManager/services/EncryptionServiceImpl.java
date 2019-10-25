package com.sabulous.contractRateManager.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("encryptionService")
public class EncryptionServiceImpl implements EncryptionService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }    

    @Override
    public String encodeString(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public boolean checkPassword(String plainPassword, String password) {
        return passwordEncoder.matches(plainPassword, password);
    }

}