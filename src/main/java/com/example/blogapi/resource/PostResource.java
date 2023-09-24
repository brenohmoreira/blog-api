package com.example.blogapi.resource;

import com.example.blogapi.domain.Post;
import com.example.blogapi.domain.User;
import com.example.blogapi.dto.UserDTO;
import com.example.blogapi.services.PostService;
import com.example.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);

        return ResponseEntity.ok().body(post);
    }
}
