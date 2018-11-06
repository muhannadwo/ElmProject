package com.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table (name = "Users")
public class Users {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

   // @Column(name = "First_Name")
    @NotNull
    @Size(min = 2,max = 15,message = "Name Must Be Between 2,15 Characters!")
    private  String firstname;

    //@Column (name = "Last_Name")
    @Size(min = 2,max = 20,message = " Last Name Must Be Between 2,20 Characters!")
    private  String lastname;

    @Email@NotNull
    //@Column (name = "User_Email")
    private String useremail;

    @JsonIgnore
    //@Column (name = "Is_Deleted")
    private boolean deleted;

    @DateTimeFormat
   // @Column (name = "User_Date")
    private Date userdate;

    @NotNull@NotBlank(message = "Password Must Be With Not '-/'")
    private String password;

    @Size(min = 1,max = 20, message = "Must Not Be Larger Than 20 Characters!")
    private String city;

    @Size(min = 1,max = 10)
    private String usergender;

    // Object From Roles.

    //(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    //@JoinColumn(name = "role",referencedColumnName = "Roles_Id")
    @ManyToOne
    private Roles roleid;

    // Getters and Setters for Roles.


    public Users() { }

    public long getUserid() { return userid; }

    public void setUserid(long userid) { this.userid = userid; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getUseremail() { return useremail; }

    public void setUseremail(String useremail) { this.useremail = useremail; }

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public Date getUserdate() { return userdate; }

    public void setUserdate(Date userdate) { this.userdate = userdate; }

 public Roles getRoleid() {
  return roleid;
 }

 public void setRoleid(Roles roleid) {
  this.roleid = roleid;
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
}