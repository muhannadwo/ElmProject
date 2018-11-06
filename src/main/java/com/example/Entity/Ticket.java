package com.example.Entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private long ticketid;

    @ColumnDefault("0")
    private boolean attended;
    private boolean canceled;

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
}