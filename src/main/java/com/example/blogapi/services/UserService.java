package com.example.blogapi.services;

import com.example.blogapi.domain.User;
import com.example.blogapi.dto.UserDTO;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Para ser injetável
@Service
public class UserService {

    // Injeção de dependência
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        // Retorna null ou o obj no repository.findById(). Se não for null, return obj. Se for, orElseThrow com a exception
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public void delete(String id) {
        // Reaproveitar o tratamento de exceção de findById
        User user = findById(id);
        repository.delete(user);
    }

    public User update(User user) {
        // Pegando o User do banco
        User newUser = repository.findById(user.getId()).get();
        updateData(newUser, user);
        return repository.save(newUser);
    }

    public void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
