package com.example.DTOs;

import com.example.Entity.Comment;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class EventsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventid;

    private boolean active;

    @Size(min = 2,max = 15)
    private  String eventname;
    @DateTimeFormat
    private LocalDate eventdate;
    @Max(300)
    private long eventcapacity;
    @Size(min = 2,max = 15)
    private String eventcity;
    private String eventtime;
    private long ecount = 0;

    private long count = 0;

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

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
