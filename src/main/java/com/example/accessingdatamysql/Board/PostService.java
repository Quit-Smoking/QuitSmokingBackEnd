package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String addPost(PostRequest request){
        Post n = new Post();

        String email = jwtUtil.extractEmail(request.getToken());
        Integer userId = userRepository.findByEmail(email).getId();

        n.setUserId(userId);
        n.setTitle(request.getTitle());
        n.setContent(request.getContent());
        n.setCreatedAt(request.getCreatedAt());

        postRepository.save(n);

        return "Saved";
    }

    public String updatePost(UpdatePostRequest request){

        Post post = findById(request.getId());

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        try{
            postRepository.save(post);
            return "Updated";
        } catch(Exception e){
            return "Update not successful : " + e.getMessage();
        }
    }

    public String deleteById(Integer id){
        postRepository.deletePostById(id);

        if(postRepository.existsById(id)){
            return "Deleted";
        }else{
            return "Delete failed";
        }
    }

    public Iterable<Post> findByUserId(String token){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return postRepository.findPostByUserId(userId);
    }

    //CrudRepository 인터페이스의 내장 함수 findById를 이용해 Optional<Post>를 리턴하고 만약 객체가 존재하지 않으면 Exception 메시지
    public Post findById(Integer id){
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElse(null);
    }
}
