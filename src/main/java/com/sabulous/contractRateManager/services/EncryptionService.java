package com.sabulous.contractRateManager.services;

public interface EncryptionService {
    String encodeString(String plainPassword);
    boolean checkPassword(String plainPassword, String password);
}