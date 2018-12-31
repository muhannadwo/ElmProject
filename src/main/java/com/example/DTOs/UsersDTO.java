package com.example.DTOs;

import com.example.Entity.Roles;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class UsersDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;


    @NotNull
    @Size(min = 2,max = 15)
    private  String username;

    @NotNull
    @Size(min = 2,max = 15,message = "Name Must Be Between 2,15 Characters!")
    private  String firstname;

    @Size(min = 2,max = 20,message = " Last Name Must Be Between 2,20 Characters!")
    private  String lastname;

    @Email @NotNull
    private String useremail;

    @DateTimeFormat
    private LocalDate userdate;

    @NotNull@NotBlank(message = "Password Must Be With Not '-/'")
    private String password;

    @Size(min = 1,max = 20, message = "Must Not Be Larger Than 20 Characters!")
    private String city;

    @Size(min = 1,max = 10)
    private String usergender;

    @NumberFormat
    private int phonenumber;

    private long count = 0;


    private Roles role;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public LocalDate getUserdate() {
        return userdate;
    }

    public void setUserdate(LocalDate userdate) {
        this.userdate = userdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsergender() {
        return usergender;
    }

    public void setUsergender(String usergender) {
        this.usergender = usergender;
    }


    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
