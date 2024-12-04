package com.example.accessingdatamysql.Board;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @Operation(summary = "댓글달기", description = "댓글을 db에 저장")
    public String addComment(@RequestBody CommentRequest request){
        return commentService.addComment(request);
    }
    @PostMapping("/update")
    @Operation(summary = "댓글 수정하기")
    public String updateComment(@RequestParam String token, @RequestParam Integer id, @RequestParam String content){
        return commentService.updateComment(token, id, content);
    }

    @GetMapping("/findById")
    @Operation(summary = "특정 댓글 수정하기", description = "댓글 id로 db 댓글 찾기")
    public Comment findById(@RequestParam Integer id){
        return commentService.findById(id);
    }

    @GetMapping("/findByUser")
    @Operation(summary = "사용자의 모든 댓글 반환")
    public Iterable<Comment> findByUserId(@RequestParam String token){
        return commentService.findByUserId(token);
    }

    @GetMapping("/findByPostId")
    @Operation(summary = "post의 모든 댓글 불러오기", description = "Parameter로 postId를 받아서 게시물의 해당하는 모든 댓글들을 불러오기")
    public Iterable<Comment> findByPostId(@RequestParam Integer postId){
        return commentService.findCommentByPostId(postId);
    }

    @GetMapping("/all")
    @Operation(summary = "api 확인을 위해서 db에 모든 댓글 불러오기")
    public Iterable<Comment> getAllComment(){
        return commentRepository.findAll();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "댓글 삭제하기", description = "댓글 id로 db에 댓글을 삭제")
    public String deleteComment(@RequestParam String token, @RequestParam Integer id){
        return commentService.deleteById(token, id);
    }
}
