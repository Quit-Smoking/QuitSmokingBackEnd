package com.example.accessingdatamysql.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public @ResponseBody String addPost(@RequestBody PostRequest request){ return postService.addPost(request); }

    @PostMapping("/update")
    public @ResponseBody String updatePost(@RequestParam String token, @RequestBody UpdatePostRequest request){ return postService.updatePost(token, request); }

    @GetMapping("/findByUser")
    public @ResponseBody Iterable<Post> findByUserId(@RequestParam String token){ return postService.findByUserId(token); }

    @GetMapping("/findByPostId")
    public @ResponseBody Post findById(@RequestParam Integer id){ return postService.findById(id); }

    @GetMapping("/all")
    public @ResponseBody Iterable<Post> findAllPost(){
        return postRepository.findAll();
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deletedById(@RequestParam Integer id){
        return postService.deleteById(id);
    }
}
