package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.UserStartRecord.UserStartRecord;
import jakarta.persistence.*;

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

}