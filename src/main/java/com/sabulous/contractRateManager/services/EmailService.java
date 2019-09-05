package com.sabulous.contractRateManager.services;

public interface EmailService {
    public void setupAccount();
    public String sendNotification(String to, String subject, String body);
}