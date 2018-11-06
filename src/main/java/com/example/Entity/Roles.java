package com.example.Entity;


import javax.persistence.*;

@Entity
@Table (name = "Roles")
public class Roles {

    @Id
    private  long roleid;
    private String rolename;

    public Roles() { }

    public long getRoleid() { return roleid; }

    public void setRoleid(long roleid) { this.roleid = roleid; }

    public String getRolename() { return rolename; }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
