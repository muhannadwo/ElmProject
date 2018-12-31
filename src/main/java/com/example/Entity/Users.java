package com.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table (name = "Users")
public class Users {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

   // @Column(name = "First_Name")

    @NotNull
    @Size(min = 2,max = 15)
    private  String username;

    @NotNull
    @Size(min = 2,max = 15)
    private  String firstname;

    //@Column (name = "Last_Name")
    @Size(min = 2,max = 20)
    private  String lastname;

    @Email@NotNull
    //@Column (name = "User_Email")
    private String useremail;

    @JsonIgnore
    //@Column (name = "Is_Deleted")
//    @ColumnDefault(value = "1")1
    private boolean deleted = true;

    @DateTimeFormat
   // @Column (name = "User_Date")
    private LocalDate userdate;

    @NotNull@NotBlank
    private String password;

    @Size(min = 1,max = 20)
    private String city;

    @Size(min = 1,max = 10)
    private String usergender;

    @NumberFormat
    private int phonenumber;

    private long count = 0;

    // Object From Roles.

    //(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    //@JoinColumn(name = "role",referencedColumnName = "Roles_Id")
    @ManyToOne
    @JoinColumn(referencedColumnName = "rolename",name = "role")
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

    public LocalDate getUserdate() { return userdate; }

    public void setUserdate(LocalDate userdate) { this.userdate = userdate; }

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

 public int getPhonenumber() {
  return phonenumber;
 }

 public void setPhonenumber(int phonenumber) {
  this.phonenumber = phonenumber;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
