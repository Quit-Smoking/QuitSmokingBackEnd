package com.example.accessingdatamysql.UserStartRecord;

import com.example.accessingdatamysql.User.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="UserStartRecord")
public class UserStartRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String motive;

    private String resolution;

    private Integer numbersSmoked;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getNumbersSmoked() {
        return numbersSmoked;
    }

    public void setNumbersSmoked(Integer numbersSmoked) {
        this.numbersSmoked = numbersSmoked;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }
}
