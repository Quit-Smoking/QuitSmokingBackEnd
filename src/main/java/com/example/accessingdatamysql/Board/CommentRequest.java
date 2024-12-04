package com.example.accessingdatamysql.Board;

public class CommentRequest {

    private String token;
    private Integer postId;
    private Integer parentCommentId;
    private String content;

    public String getToken(){
        return token;
    }

    public Integer getPostId(){ return postId; }

    public Integer getParentCommentId(){ return parentCommentId; }

    public String getContent(){
        return content;
    }
}