package com.example.accessingdatamysql.User;

public class LoginRequest {

    // LoginRequest의 email과 password는 유저가 입력한, 프론트에서 요청한 Raw의 값이다.
    private String email;
    private String password;
    private String nickname;

    public String getEmail() {return email;}

    public String getPassword() {return password;}

    public String getNickname() {return nickname;}

}
