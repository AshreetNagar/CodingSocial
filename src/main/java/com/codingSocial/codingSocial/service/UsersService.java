package com.codingSocial.codingSocial.service;

import org.springframework.stereotype.Service;
import com.codingSocial.codingSocial.model.UsersModel;
import com.codingSocial.codingSocial.repository.UsersRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsersService {

    PasswordEncoder passwordEncoder;
    
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(null, 0);
    }

    public UsersModel registerUser(String login, String password, String email) {
        if(login == null && password == null) {
            return null;
        }
        else {
            if(usersRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    public UsersModel save(UsersModel usersModel) {
        String encodedPassword = this.passwordEncoder.encode(usersModel.getPassword());
        usersModel.setPassword(encodedPassword);
        return this.usersRepository.save(usersModel);
    }
};
