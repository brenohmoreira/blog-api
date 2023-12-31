package com.example.blogapi.services;

import com.example.blogapi.domain.Post;
import com.example.blogapi.domain.User;
import com.example.blogapi.dto.UserDTO;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Para ser injetável
@Service
public class PostService {

    // Injeção de dependência
    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        // Retorna null ou o obj no repository.findById(). Se não for null, return obj. Se for, orElseThrow com a exception
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
