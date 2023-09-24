package com.example.blogapi.config;

import com.example.blogapi.domain.Post;
import com.example.blogapi.domain.User;
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

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        // Deleta para sempre ser os documentos inseridos por aqui
        userRepository.deleteAll();

        User breno = new User(null, "Breno de Abreu Moreira", "brenomoreira@gmail.com");
        User mariana = new User(null, "Mariana Ribeiro Novaes", "ribeiro_m@gmail.com");
        User hugo = new User(null, "Hugo Campos Pinto", "hugo_cp@gmail.com");

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", mariana);
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", mariana);

        userRepository.saveAll(Arrays.asList(breno, mariana, hugo));

    }
}
