package com.example.accessingdatamysql.Board;

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

    @PostMapping("/change")
    public @ResponseBody String changeComment(@RequestParam String token, @RequestParam Integer id, @RequestParam String content){
        return commentService.changeComment(token, id, content);
    }


    //@GetMapping("/findByUser")

    //@Getmapping("/findByCommentId")
}
