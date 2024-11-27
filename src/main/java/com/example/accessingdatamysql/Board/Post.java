package com.example.accessingdatamysql.Board;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int userid;

    private String title;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setUserid(int userid){
        this.userid = userid;
    }

    public int getUserid(){
        return userid;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
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
