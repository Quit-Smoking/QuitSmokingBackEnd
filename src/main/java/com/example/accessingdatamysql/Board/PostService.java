package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        Post post = postRepository.findPostById(request.getId());

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        try{
            postRepository.save(post);
            return "Updated";
        } catch(Exception e){
            return "Update not successful : " + e.getMessage();
        }
    }

    public String deleteByPostId(Integer id){
        postRepository.deleteById(id);

        if(postRepository.existsById(id)){
            return "Deleted";
        }else{
            return "Delete failed";
        }
    }

    public Iterable<Post> findPostByUser(String token){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return postRepository.findPostByUserId(userId);
    }


}
