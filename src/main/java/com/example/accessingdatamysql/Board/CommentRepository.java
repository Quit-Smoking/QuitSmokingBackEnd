package com.example.accessingdatamysql.Board;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findCommentByUserId(Integer userId);
    Iterable<Comment> findCommentByPostId(Integer postId);
    void deleteAllByUserId(Integer userId);
    void deleteAllByPostId(Integer postId);
}