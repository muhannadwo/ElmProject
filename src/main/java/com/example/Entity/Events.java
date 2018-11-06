package com.example.Entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "Events")
public class Events {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long eventid;


    private  String eventname;
    private LocalDate eventdate;
    private long eventcapacity;
    private boolean active;
    private boolean deleted ;
    private String eventcity;
    private String eventtime;
    private long ecount = 0;

    // User [ Organizer ] Object.

    @ManyToOne //(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    //@JoinColumn (name = "User_Id")//(name = "Organizer_Id", referencedColumnName = "User_Id")
    private Users organizer_id;

    @OneToMany//(mappedBy = "commentid")
    private List<Comment> comments;


    // Getters and Setters for organizer.


    public Events() { }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public LocalDate getEventdate() {
        return eventdate;
    }

    public void setEventdate(LocalDate eventdate) {
        this.eventdate = eventdate;
    }

    public long getEventcapacity() {
        return eventcapacity;
    }

    public void setEventcapacity(long eventcapacity) {
        this.eventcapacity = eventcapacity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getEventcity() {
        return eventcity;
    }

    public void setEventcity(String eventcity) {
        this.eventcity = eventcity;
    }

    public Users getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(Users organizer_id) {
        this.organizer_id = organizer_id;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public long getEcount() {
        return ecount;
    }

    public void setEcount(long ecount) {
        this.ecount = ecount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
