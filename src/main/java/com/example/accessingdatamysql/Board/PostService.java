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
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public String addPost(PostRequest request){
        Post n = new Post();

        User user = userService.findByToken(request.getToken());
        n.setUser(user);
        n.setTitle(request.getTitle());
        n.setContent(request.getContent());
        n.setCreatedAt(request.getCreatedAt());

        try{
            postRepository.save(n);
            return "Saved";
        }catch(Exception e){
            return "Failed to save : " + e.getMessage();
        }
    }

    public String updatePost(String token, UpdatePostRequest request){
        User user = userService.findByToken(token);

        // postId로 post를 찾음.
        Post post = findById(request.getId());

        if(post == null){
            return "The post you want to update, does not exist";
        }
        // 실제 유저와 post의 유저가 같은지 검사.
        if(Objects.equals(user, findById(request.getId()).getUser())){

            post.setTitle(request.getTitle());
            post.setContent(request.getContent());
            post.setCreatedAt(LocalDate.now());

            try{
                postRepository.save(post);
                return "Updated";

            }catch(Exception e){
                return "Update failed : " + e.getMessage();
            }
        }else{
            return "You can't change someone else's post";
        }

    }

    public String likePost(Integer id){
        if(isExistsById(id)){
            Post post = findById(id);
            post.setNumberOfLikes(post.getNumberOfLikes()+1);
            postRepository.save(post);
            return "Saved";
        }else{
            return "The post doesn't exist";
        }
    }

    @Transactional
    public String deleteById(String token, Integer id){
        User user = userService.findByToken(token);

        Post post = findById(id);

        if(post == null){
            return "The post you want to delete, does not exist";
        }
        // post의 유저인지 검사.
        if(Objects.equals(user, post.getUser())){
            try{
                postRepository.deleteById(id);
                return "Deleted";
            }catch (Exception e){
                return "Failed to Delete : " + e.getMessage();
            }
        }else{
            return "You cannot delete someone else's post";
        }
    }

    // 수정 필요.
    public List<PostResponse> getPostResponsesByToken(String token){
        User user = userService.findByToken(token);
        List<Post> posts = user.getPosts();

        List<PostResponse> responses = new ArrayList<>();
        for(Post post : posts){
            PostResponse response = parsePostToResponse(post);

            responses.add(response);
        }

        return responses;
    }

    public PostResponse getPostResponseById(Integer id)
    {
        return parsePostToResponse(findById(id));
    }

    public List<PostResponse> getAllPostResponses(){
        List<PostResponse> responses = new ArrayList<>();

        Iterable<Post> posts = postRepository.findAll();

        for(Post post : posts){
            PostResponse response = parsePostToResponse(post);

            responses.add(response);
        }

        return responses;
    }

    //CrudRepository 인터페이스의 내장 함수 findById를 이용해 Optional<Post>를 리턴하고 만약 객체가 존재하지 않으면 Exception 메시지
    public Post findById(Integer id){
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElse(null);
    }

    public boolean isExistsById(Integer id){
        return postRepository.existsById(id);
    }

    public PostResponse parsePostToResponse(Post post)
    {
        PostResponse response = new PostResponse();

        response.setId(post.getId());
        response.setContent(post.getContent());
        response.setTitle(post.getTitle());
        response.setNumberOfLikes(post.getNumberOfLikes());
        response.setCreatedAt(post.getCreatedAt());
        response.setNumberOfComments(post.getNumberOfComments());

        return response;
    }

}
