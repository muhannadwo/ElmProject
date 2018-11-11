package com.example.DTOs;



public class TicketDTO {

    private UsersDTO attenderid;
    private EventsDTO eventsid;

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
}
