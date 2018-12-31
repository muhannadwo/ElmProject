package com.example.Entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private long ticketid;

    private String eventname;
    private String eventdate;

    @ColumnDefault("0")
    private boolean attended;
    private boolean canceled;

    private long count = 0;

    // Events Object.
    @ManyToOne //(cascade = CascadeType.ALL)
    private Events eventsid;
    // User Object.
    @ManyToOne //(cascade = CascadeType.ALL)
    private Users attenderid;


    public Ticket() {
    }

    public long getTicketid() {
        return ticketid;
    }

    public void setTicketid(long ticketid) {
        this.ticketid = ticketid;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Events getEventsid() {
        return eventsid;
    }

    public void setEventsid(Events eventsid) {
        this.eventsid = eventsid;
    }

    public Users getAttenderid() {
        return attenderid;
    }

    public void setAttenderid(Users attenderid) {
        this.attenderid = attenderid;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}