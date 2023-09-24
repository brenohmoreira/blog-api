package com.example.blogapi.config;

import com.example.blogapi.domain.Post;
import com.example.blogapi.domain.User;
import com.example.blogapi.dto.AuthorDTO;
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

        User breno = new User(null, "Breno de Abreu Moreira", "brenomoreira@gmail.com");
        User mariana = new User(null, "Mariana Ribeiro Novaes", "ribeiro_m@gmail.com");
        User hugo = new User(null, "Hugo Campos Pinto", "hugo_cp@gmail.com");

        // Deve ser antes, pois DTO entra com ID e para ter o ID, já deve estar salvo
        userRepository.saveAll(Arrays.asList(breno, mariana, hugo));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(mariana));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(mariana));

        postRepository.saveAll(Arrays.asList(post1, post2));

        mariana.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(mariana);
    }
}
