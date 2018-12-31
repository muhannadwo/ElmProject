package com.example.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table (name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;

    @Size(min = 1,max = 255)
    private String ccomment;
    private LocalDateTime cdate;
    @ColumnDefault("0")
    private boolean canceled;
    @Max(5)
    private int eventrate;
    private String eventname;
    private long count = 0;

    @ManyToOne
    private Users userid;

    @ManyToOne
    @JsonIgnore
    private Events eventsid;


    public Comment() {
    }


    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public String getCcomment() {
        return ccomment;
    }

    public void setCcomment(String ccomment) {
        this.ccomment = ccomment;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Events getEventsid() {
        return eventsid;
    }

    public void setEventsid(Events eventsid) {
        this.eventsid = eventsid;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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
