package com.example.blogapi.config;

import com.example.blogapi.domain.User;
import com.example.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Deleta para sempre ser os documentos inseridos por aqui
        userRepository.deleteAll();

        User breno = new User(null, "Breno de Abreu Moreira", "brenomoreira@gmail.com");
        User mariana = new User(null, "Mariana Ribeiro Novaes", "ribeiro_m@gmail.com");
        User hugo = new User(null, "Hugo Campos Pinto", "hugo_cp@gmail.com");

        userRepository.saveAll(Arrays.asList(breno, mariana, hugo));
        
    }
}
