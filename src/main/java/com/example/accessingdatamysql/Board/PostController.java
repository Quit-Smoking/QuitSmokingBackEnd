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

    @PostMapping("/update")
    public @ResponseBody String updatePost(@RequestBody UpdatePostRequest request){ return postService.updatePost(request); }

    @GetMapping("/findPostByUser")
    public @ResponseBody Iterable<Post> findPostByUser(@RequestParam String token){ return postService.findPostByUser(token); }

    @GetMapping("/all")
    public @ResponseBody Iterable<Post> findAllPost(){
        return postRepository.findAll();
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deletedByPostId(@RequestParam Integer id){
        return postService.deleteByPostId(id);
    }
}
