package com.example.Entity;

import javax.persistence.*;

@Entity
@Table (name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long feedbackid;

    private int feedbackrate;
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