package com.example.accessingdatamysql.Board;

import com.example.accessingdatamysql.User.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer parentCommentId = 0;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public void setPost(Post post){
        this.post = post;
    }

    public Post getPost(){
        return post;
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

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }
}
