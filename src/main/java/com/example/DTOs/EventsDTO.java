package com.example.DTOs;

import com.example.Entity.Comment;
import com.example.Entity.Users;

import java.time.LocalDate;
import java.util.List;

public class EventsDTO {

    private  String eventname;
    private LocalDate eventdate;
    private long eventcapacity;
    private String eventcity;
    private String eventtime;
    private long ecount = 0;
    private UsersDTO organizer_id;
    private List<Comment> comments;


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

    public String getEventcity() {
        return eventcity;
    }

    public void setEventcity(String eventcity) {
        this.eventcity = eventcity;
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

    public UsersDTO getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(UsersDTO organizer_id) {
        this.organizer_id = organizer_id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
