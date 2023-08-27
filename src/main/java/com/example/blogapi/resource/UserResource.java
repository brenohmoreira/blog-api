package com.example.blogapi.resource;

import com.example.blogapi.domain.User;
import com.example.blogapi.dto.UserDTO;
import com.example.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    // Endpoint /users (requisição GET)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();

        List<UserDTO> listDTO = list.stream().map(item -> new UserDTO(item)).toList();

        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findAll(@PathVariable String id) {
        UserDTO userDTO = new UserDTO(userService.findById(id));

        return ResponseEntity.ok().body(userDTO);
    }

}
