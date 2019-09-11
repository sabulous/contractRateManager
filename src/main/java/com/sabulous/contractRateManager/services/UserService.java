package com.sabulous.contractRateManager.services;

import com.sabulous.contractRateManager.model.User;

public interface UserService extends CRUDService<User>{
    User getUserByEmail(String email);
}
