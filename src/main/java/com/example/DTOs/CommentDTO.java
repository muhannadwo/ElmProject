package com.example.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class CommentDTO {

    @Size(min = 1,max = 255)
    private String ccomment;

    private String eventname;

    private UsersDTO userid;
    @JsonIgnore
    private EventsDTO eventsid;

    private long count = 0;


    @Max(5)
    private int eventrate;

    public String getCcomment() {
        return ccomment;
    }

    public void setCcomment(String ccomment) {
        this.ccomment = ccomment;
    }

    public UsersDTO getUserid() {
        return userid;
    }

    public void setUserid(UsersDTO userid) {
        this.userid = userid;
    }

    public EventsDTO getEventsid() {
        return eventsid;
    }

    public void setEventsid(EventsDTO eventsid) {
        this.eventsid = eventsid;
    }

    public int getEventrate() {
        return eventrate;
    }

    public void setEventrate(int eventrate) {
        this.eventrate = eventrate;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
