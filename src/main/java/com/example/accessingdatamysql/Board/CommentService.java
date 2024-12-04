package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
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

    public String updateComment(String token, Integer id, String content){
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email);

        Comment comment = findById(id);
        if(comment == null){
            return "The comment does not exist";
        }

        if(Objects.equals(user.getId(), findById(id).getUserId())){

            comment.setContent(content);
            comment.setCreatedAt(LocalDate.now());

            try{
                commentRepository.save(comment);
                return "Saved";
            }catch(Exception e){
                return "Save Failed : " + e.getMessage();
            }
        }else {
            return "You can't change someone else's comment";
        }
    }

    public String deleteById(String token, Integer id){
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email);

        if(Objects.equals(user.getId(), findById(id).getUserId())){
            try{
                commentRepository.deleteById(id);
                return "Deleted";
            }catch (Exception e){
                return "Delete failed : " + e.getMessage();
            }
        }else{
            return "You can't delete someone else's comment";
        }
    }

    //CrudRepository 인터페이스의 내장 함수 findById를 이용해 Optional<Post>를 리턴하고 만약 객체가 존재하지 않으면 Exception 메시지
    public Comment findById(Integer id){
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional.orElse(null);
    }

    public Iterable<Comment> findByUserId(String token){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return commentRepository.findCommentByUserId(userId);
    }
}
