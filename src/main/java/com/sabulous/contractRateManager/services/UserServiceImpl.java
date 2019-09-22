package com.sabulous.contractRateManager.services;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.contractRateManager.model.Role;
import com.sabulous.contractRateManager.model.User;
import com.sabulous.contractRateManager.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveOrUpdate(User user) {
        
        if(user.getEncryptedPassword() == null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setEncryptedPassword(encoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }
 
    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}