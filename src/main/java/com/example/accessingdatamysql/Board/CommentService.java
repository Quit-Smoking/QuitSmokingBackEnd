package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.User.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public String addComment(CommentRequest request){
        User user = userService.findByToken(request.getToken());

        if(postService.isExistsById(request.getPostId())){
            Post post = postService.findById(request.getPostId());

            Comment comment = new Comment();

            comment.setUser(user);
            comment.setPost(post);
            // parent를 어떻게 할지 생각.
            comment.setParentCommentId(request.getParentCommentId());
            comment.setContent(request.getContent());
            comment.setCreatedAt(LocalDate.now());
            try{
                commentRepository.save(comment);
                // ??? 왜 post를 업데이트 하는지?
                //postRepository.save(post);
                return "Saved";
            }catch (Exception e){
                return "failed to save : " + e.getMessage();
            }
        }else{
            return "The post does not exist";
        }
    }

    public String updateComment(String token, Integer id, String content){
        User user = userService.findByToken(token);

        Comment comment = findById(id);

        if(comment == null){
            return "The comment does not exist";
        }
        // comment의 유저와 일치하는지.
        if(Objects.equals(user, comment.getUser())){

            comment.setContent(content);
            comment.setCreatedAt(LocalDate.now());

            try{
                commentRepository.save(comment);
                return "Updated";
            }catch(Exception e){
                return "Update failed : " + e.getMessage();
            }
        }else {
            return "You can't change someone else's comment";
        }
    }

    @Transactional
    public String deleteById(String token, Integer id){
        User user = userService.findByToken(token);

        Comment comment = findById(id);
        if(Objects.equals(user, comment.getUser())){
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
    public CommentRespond getCommentById(Integer id){
        Optional<Comment> commentOptional = commentRepository.findById(id);
        // comment를 찾는다.
        Comment comment = commentOptional.orElse(null);

        return parseCommentToRespond(comment);
    }

    public Comment findById(Integer id){
        Optional<Comment> commentOptional = commentRepository.findById(id);
        // comment를 찾는다.
        return commentOptional.orElse(null);
    }


    public List<CommentRespond> getCommentRespondByToken(String token){
        User user = userService.findByToken(token);

        List<CommentRespond> responds = new ArrayList<>();
        // 유저가 가진 코멘트들.
        List<Comment> comments = user.getComments();

        for(Comment comment : comments){
            CommentRespond respond = parseCommentToRespond(comment);

            responds.add(respond);
        }

        return responds;
    }

    public List<CommentRespond> getCommentRespondByPostId(Integer postId){
        Post post = postService.findById(postId);

        List<Comment> comments = post.getComments();

        List<CommentRespond> responds = new ArrayList<>();
        for(Comment comment : comments){
            CommentRespond respond = parseCommentToRespond(comment);

            responds.add(respond);
        }

        return responds;
    }


    // comment를 respond로 변경.
    public CommentRespond parseCommentToRespond(Comment comment){
        CommentRespond respond = new CommentRespond();

        // respond 설정.
        respond.setId(comment.getId());
        respond.setContent(comment.getContent());
        respond.setPostId(comment.getPost().getId());
        respond.setParentCommentId(comment.getParentCommentId());
        respond.setCreatedAt(comment.getCreatedAt());

        return respond;
    }




}
