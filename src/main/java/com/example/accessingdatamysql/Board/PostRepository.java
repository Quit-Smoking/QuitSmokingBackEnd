package com.example.accessingdatamysql.Board;


import org.springframework.data.repository.CrudRepository;

//CrudRepository의 내장된 findById()를 사용하면 Post가 아닌 Optional<Post> 타입이 리턴됨으로 서비스에서 따로 Post 타입으로 바꿔줘야하기에 불편하다.
public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findPostByUserId(Integer userId);
    void deleteAllByUserId(Integer userId);
}