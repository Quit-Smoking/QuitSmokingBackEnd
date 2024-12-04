package com.example.accessingdatamysql.Board;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public @ResponseBody String addComment(@RequestBody CommentRequest request){
        return commentService.addComment(request);
    }
    @PostMapping("/update")
    public @ResponseBody String updateComment(@RequestParam String token, @RequestParam Integer id, @RequestParam String content){
        return commentService.updateComment(token, id, content);
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deleteComment(@RequestParam String token, @RequestParam Integer id){
        return commentService.deleteById(token, id);
    }

    @GetMapping("/findById")
    public @ResponseBody Comment findById(@RequestParam Integer id){
        return commentService.findById(id);
    }

    @GetMapping("/findByUser")
    public @ResponseBody Iterable<Comment> findByUserId(@RequestParam String token){
        return commentService.findByUserId(token);
    }

    @GetMapping("/findByPostId")
    @Operation(summary = "post의 모든 댓글 불러오기", description = "Parameter로 postId를 받아서 게시물의 해당하는 모든 댓글들을 불러오기")
    public @ResponseBody Iterable<Comment> findByPostId(@RequestParam Integer postId){
        return commentService.findCommentByPostId(postId);
    }
}
