package com.example.blogapi.services;

import com.example.blogapi.domain.User;
import com.example.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Para ser injetável
@Service
public class UserService {

    // Injeção de dependência
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
}
