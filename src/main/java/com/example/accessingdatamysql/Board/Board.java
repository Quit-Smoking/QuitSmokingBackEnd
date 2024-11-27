package com.example.accessingdatamysql.Board;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int userid;

    private String content;

    private LocalDate date;

    public int getId(){
        return id;
    }

    public int getUserid(){
        return userid;
    }

    public String getContent(){
        return content;
    }

    public LocalDate getDate(){
        return date;
    }

}
