package com.example.accessingdatamysql.Board;

import java.time.LocalDate;

public class PostRequest {

    private String token;
    private String title;
    private String content;
    private LocalDate createdAt;

    public String getToken(){
        return token;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

}
