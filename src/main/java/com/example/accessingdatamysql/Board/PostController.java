package com.example.accessingdatamysql.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;


    @PostMapping(path = "/add")
    public @ResponseBody String addPost(@RequestBody PostRequest request){ return postService.addPost(request); }

    @GetMapping(path="/findPostByUser")
    public @ResponseBody Iterable<Post> findPostByUser(@RequestParam String token){
        return postService.findPostByUser(token);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Post> findAllPost(){
        return postRepository.findAll();
    }

}
