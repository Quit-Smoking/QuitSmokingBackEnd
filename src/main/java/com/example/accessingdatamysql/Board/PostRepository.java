package com.example.accessingdatamysql.Board;


import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findPostByUser(Integer userId);
    void deleteByPostId(Integer userId);
}
