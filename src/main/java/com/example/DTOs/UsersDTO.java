package com.example.DTOs;

import com.example.Entity.Roles;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UsersDTO {

    @NotNull
    @Size(min = 2,max = 15,message = "Name Must Be Between 2,15 Characters!")
    private  String firstname;

    @Size(min = 2,max = 20,message = " Last Name Must Be Between 2,20 Characters!")
    private  String lastname;

    @Email @NotNull
    private String useremail;

    @DateTimeFormat
    private Date userdate;

    @NotNull@NotBlank(message = "Password Must Be With Not '-/'")
    private String password;

    @Size(min = 1,max = 20, message = "Must Not Be Larger Than 20 Characters!")
    private String city;

    @Size(min = 1,max = 10)
    private String usergender;

    private Roles rolesid;

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

    public Date getUserdate() {
        return userdate;
    }

    public void setUserdate(Date userdate) {
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

    public Roles getRolesid() {
        return rolesid;
    }

    public void setRolesid(Roles rolesid) {
        this.rolesid = rolesid;
    }
}
