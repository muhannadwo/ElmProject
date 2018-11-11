package com.example.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
@Table (name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long feedbackid;

    @Max(5)
    private int feedbackrate;
    @Size(min = 1,max = 255)
    private String feedbackcomment;
    private boolean deleted ;

    // Object from users class.

    @ManyToOne
    private  Ticket ticketid;

    // setters and getters for user object.


    public Feedback() {
    }

    public long getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(long feedbackid) {
        this.feedbackid = feedbackid;
    }

    public int getFeedbackrate() {
        return feedbackrate;
    }

    public void setFeedbackrate(int feedbackrate) {
        this.feedbackrate = feedbackrate;
    }

    public String getFeedbackcomment() {
        return feedbackcomment;
    }

    public void setFeedbackcomment(String feedbackcomment) {
        this.feedbackcomment = feedbackcomment;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Ticket getTicketid() {
        return ticketid;
    }

    public void setTicketid(Ticket ticketid) {
        this.ticketid = ticketid;
    }
}