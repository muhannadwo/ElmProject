package com.example.DTOs;

import com.example.Entity.Events;
import com.example.Entity.Users;

public class TicketDTO {

    private Users attenderid;
    private Events eventsid;

    public Users getAttenderid() {
        return attenderid;
    }

    public void setAttenderid(Users attenderid) {
        this.attenderid = attenderid;
    }

    public Events getEventsid() {
        return eventsid;
    }

    public void setEventsid(Events eventsid) {
        this.eventsid = eventsid;
    }
}
