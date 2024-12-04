package com.example.accessingdatamysql.Board;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    @Operation(summary = "게시글 작성하기", description = "게시글을 db에 저장하기")
    public String addPost(@RequestBody PostRequest request){ return postService.addPost(request); }

    @PostMapping("/update")
    @Operation(summary = "게시글 수정하기")
    public String updatePost(@RequestParam String token, @RequestBody UpdatePostRequest request){ return postService.updatePost(token, request); }

    @GetMapping("/findByPostId")
    @Operation(summary = "게시글 찾기", description="게시글 id로 게시글 불러오기")
    public Post findById(@RequestParam Integer id){ return postService.findById(id); }

    @GetMapping("/findByUser")
    @Operation(summary = "사용자의 모든 게시글 불러오기")
    public Iterable<Post> findByUserId(@RequestParam String token){ return postService.findByUserId(token); }

    @GetMapping("/all")
    public Iterable<Post> getAllPost(){
        return postRepository.findAll();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "게시글 삭제하기", description="게시글 id로 게시글 삭제하기")
    public String deletedById(@RequestParam String token, @RequestParam Integer id){
        return postService.deleteById(token, id);
    }
}
