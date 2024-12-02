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

    @PostMapping("/add")
    public @ResponseBody String addPost(@RequestBody PostRequest request){ return postService.addPost(request); }

    @GetMapping("/findPostByUser")
    public @ResponseBody Iterable<Post> findPostByUser(@RequestParam String token){ return postService.findPostByUser(token); }

    @GetMapping("/all")
    public @ResponseBody Iterable<Post> findAllPost(){
        return postRepository.findAll();
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deletedByPostId(@RequestParam String token, @RequestParam Integer postId){
        return postService.deletePost(token, postId);
    }

}
