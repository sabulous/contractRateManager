package com.sabulous.contractRateManager.services;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.contractRateManager.model.Contract;
import com.sabulous.contractRateManager.model.Role;
import com.sabulous.contractRateManager.model.User;
import com.sabulous.contractRateManager.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private EncryptionService encryptionService;
    
    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
    
    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveOrUpdate(User user) {
        
        if(user.getPassword() == null) {
            return null;
        }
        
        // OPTIONS FOR PASSWORD ENCRYPTION
        // #1
        user.setEncryptedPassword(encryptionService.encodeString(user.getPassword()));
        // above code can be used with PasswordEncoderFactories.createDelegatingPasswordEncoder() created as @Bean in configuration

        // #2
        // user.setEncryptedPassword("{bcrypt}" + encryptionService.encodeString(user.getPassword()));
        // in order to user the code above, new BCryptPasswordEncoder() must be created as @Bean in configuration

        if(userRepository.findByUsername(user.getUsername()) == null) {
            return userRepository.save(user);
        } else {
            return null;
        }

    }
 
    @Override
    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id).get();
        List<Contract> contracts = user.getContracts();
        List<Role> roles = user.getRoles();

        for(Contract contract : contracts) {
            contract.getUsers().remove(user);
        }
        for(Role role : roles) {
            role.getUsers().remove(user);
        }
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
  
}