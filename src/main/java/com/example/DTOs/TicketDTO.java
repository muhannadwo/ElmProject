package com.example.DTOs;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TicketDTO {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long ticketid;

    private String eventname;
    private String eventdate;


    private UsersDTO attenderid;
    private EventsDTO eventsid;

    private long count = 0;


    public UsersDTO getAttenderid() {
        return attenderid;
    }

    public void setAttenderid(UsersDTO attenderid) {
        this.attenderid = attenderid;
    }

    public EventsDTO getEventsid() {
        return eventsid;
    }

    public void setEventsid(EventsDTO eventsid) {
        this.eventsid = eventsid;
    }

    public long getTicketid() {
        return ticketid;
    }

    public void setTicketid(long ticketid) {
        this.ticketid = ticketid;
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
