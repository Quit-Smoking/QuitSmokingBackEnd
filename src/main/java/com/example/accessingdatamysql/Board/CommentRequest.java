package com.example.accessingdatamysql.Board;

import java.time.LocalDate;

public class CommentRequest {

    private String token;
    private Post post;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public String getToken(){
        return token;
    }

    public Post getPost(){
        return post;
    }

    public String getContent(){
        return content;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

    //userid,
}
