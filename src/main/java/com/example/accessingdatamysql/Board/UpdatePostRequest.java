package com.example.accessingdatamysql.Board;

public class UpdatePostRequest {
    private String token;
    private Integer id;
    private String title;
    private String content;

    public String getToken(){ return token; }
    public Integer getId(){ return id; }
    public String getTitle(){ return title; }
    public String getContent(){ return content; }
}
