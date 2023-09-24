package com.example.blogapi.config;

import com.example.blogapi.domain.Post;
import com.example.blogapi.domain.User;
import com.example.blogapi.dto.AuthorDTO;
import com.example.blogapi.dto.CommentDTO;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        // Deleta para sempre ser os documentos inseridos por aqui
        userRepository.deleteAll();
        postRepository.deleteAll();

        User breno = new User(null, "Breno", "brenomoreira@gmail.com");
        User paulo = new User(null, "Paulo", "paulo@gmail.com");
        User joao = new User(null, "João", "jão@gmail.com");

        // Deve ser antes, pois DTO entra com ID e para ter o ID, já deve estar salvo
        userRepository.saveAll(Arrays.asList(breno, paulo, joao));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(paulo));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(paulo));

        CommentDTO comment1 = new CommentDTO("Boa viagem!", new AuthorDTO(breno), sdf.parse("21/03/2018"));

        post1.getComments().addAll(Arrays.asList(comment1));

        postRepository.saveAll(Arrays.asList(post1, post2));

        paulo.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(paulo);
    }
}
