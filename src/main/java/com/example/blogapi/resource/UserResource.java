package com.example.blogapi.resource;

import com.example.blogapi.domain.Post;
import com.example.blogapi.domain.User;
import com.example.blogapi.dto.UserDTO;
import com.example.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO userDTO = new UserDTO(userService.findById(id));

        return ResponseEntity.ok().body(userDTO);
    }

    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);

        return ResponseEntity.ok().body(user.getPosts());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        // Retorna 204
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }

}
