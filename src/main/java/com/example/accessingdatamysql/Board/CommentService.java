package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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

    public String changeComment(String token, Integer id, String content){

        Comment comment = findById(id);

        if(comment != null){
            try{
                comment.setContent(content);
                commentRepository.save(comment);
                return "Updated";
            } catch (Exception e){
                return "Failed to Update : " + e.getMessage();
            }
        }else{
            return "No comment found to update";
        }
    }

    //CrudRepository 인터페이스의 내장 함수 findById를 이용해 Optional<Comment>를 리턴하고 만약 객체가 존재하지 않으면 null을 리턴
    public Comment findById(Integer id){
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional.orElse(null);
    }
}
