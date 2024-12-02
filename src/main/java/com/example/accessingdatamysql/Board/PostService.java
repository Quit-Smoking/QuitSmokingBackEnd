package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String deletePost(String token, Integer postId){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        postRepository.deleteByPostId(userId);
        return "Deleted";
    }

    public Iterable<Post> findPostByUser(String token){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return postRepository.findPostByUser(userId);
    }

}
