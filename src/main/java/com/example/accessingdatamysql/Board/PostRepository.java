package com.example.accessingdatamysql.Board;


import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer id);
    void deleteById(Integer id);
}
