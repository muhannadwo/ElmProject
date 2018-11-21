package com.example.Entity;


import javax.persistence.*;

@Entity
@Table (name = "Roles")
public class Roles {

    @Id
    private String rolename;

    public Roles() { }


    public String getRolename() { return rolename; }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
