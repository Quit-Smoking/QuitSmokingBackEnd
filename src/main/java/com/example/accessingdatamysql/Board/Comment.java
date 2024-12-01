package com.example.accessingdatamysql.Board;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer postId;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setPostId(Integer postId){
        this.postId = postId;
    }

    public Integer getPostId(){
        return postId;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setCreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt){
        this.updatedAt = updatedAt;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }
}
