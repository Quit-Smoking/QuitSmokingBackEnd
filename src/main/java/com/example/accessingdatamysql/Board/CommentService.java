package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String addComment(CommentRequest request){
        String email = jwtUtil.extractEmail(request.getToken());
        Integer userId = userRepository.findByEmail(email).getId();

        Comment comment = new Comment();

        comment.setUserId(userId);
        comment.setPostId(request.getPostId());
        comment.setParentCommentId(request.getParentCommentId());
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDate.now());

        try{
            commentRepository.save(comment);
            return "Saved";
        } catch (Exception e){
            return "Failed to save : " + e.getMessage();
        }

    }

}
