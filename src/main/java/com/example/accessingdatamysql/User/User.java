package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.Board.Comment;
import com.example.accessingdatamysql.Board.Post;
import com.example.accessingdatamysql.UserCessationRecord.UserCessationRecord;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecord;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    private String nickname;

    // userStartRecord와 연결.
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserStartRecord userStartRecord;

    // userCessationRecord와 연결.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCessationRecord> userCessationRecords;

    // post와 연결.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    //comment는 post와만 연결되어 있음.
    // post와 연결.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserStartRecord getUserStartRecord() {
        return userStartRecord;
    }

    public void setUserStartRecord(UserStartRecord userStartRecord) {
        this.userStartRecord = userStartRecord;
    }

    public List<UserCessationRecord> getUserCessationRecords() {
        return userCessationRecords;
    }

    public void setUserCessationRecords(List<UserCessationRecord> userCessationRecords) {
        this.userCessationRecords = userCessationRecords;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}