package com.example.Servicee.ServiceImplementation;


import com.example.Entity.Events;
import com.example.Entity.Ticket;
import com.example.Repository.EventsRepository;
import com.example.Repository.TicketRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventsServiceImplementation implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EmailSendingService emailSendingService;

    @Override
    public Iterable<Events> findAll() {
        return eventsRepository.findAll();
    }

    @Override
    public Optional<Events> findById(Long id) {
        return eventsRepository.findById(id);
    }

    @Override
    public void createEvent(Events evnt, Long id){
        LocalDate date = LocalDate.now().minusDays(1);

        if ( date.isBefore(evnt.getEventdate())) {
            evnt.setOrganizer_id(usersRepository.findById(id).get());
            eventsRepository.save(evnt);
        }
    }

    @Override
    public  void updateEvent(Events event, long eid){

        event.setOrganizer_id(usersRepository.findById(eid).get());
        eventsRepository.save(event);
        List<Ticket> ticket = ticketRepository.findByEventsidAndCanceledFalse(event);
        for (Ticket ticket1 : ticket){
            emailSendingService.sendNotificaitoin(ticket1.getAttenderid().getUseremail(),"Event Updated! ","The Event  '" + event.getEventname() + "'  Was Updated");
        }

    }

    @Override
    public void isDeleted(Long id) {

        Events events = eventsRepository.findById(id).get();
        events.setDeleted(true);
        eventsRepository.save(findById(id).get());
        List<Ticket> tickets = ticketRepository.findByEventsidAndCanceledFalse(events);
        for (Ticket ticket : tickets){
            emailSendingService.sendNotificaitoin(ticket.getAttenderid().getUseremail(),"Event Deleted!", "Event '" + events.getEventname() + "' Was Deleted!");
        }
    }


    @Override
    public void isActiveEvent(Long id){
        eventsRepository.findById(id).get().setActive(true);
        eventsRepository.save(findById(id).get());
    }

    @Override
    public void isNotActiveEvent(Long id) {
        eventsRepository.findById(id).get().setActive(false);
        eventsRepository.save(findById(id).get());
    }

    @Override
    public List<Events> findAllDeleted() {

        Iterable<Events> list = new ArrayList<>();
        list = eventsRepository.findAll();
        Iterable<Events> list1 = new ArrayList<>();
        for ( Events evnt : list){

            if (!evnt.isDeleted()) ((ArrayList<Events>) list1).add(evnt);

        }
        return (List<Events>) list1;
    }

    @Override
    public List<Events> findAllActiveEvents() {

        LocalDate today = LocalDate.now();
        List<Events> list = new ArrayList<>();
        list = (List<Events>) eventsRepository.findAll();

        List<Events> list1 = new ArrayList<>();

        for ( Events events : list) {

            if ( today.minusDays(1).isBefore(events.getEventdate() )  && events.isActive() && !events.isDeleted())
                list1.add(events);
        }
        return list1;
    }

    @Override
    public List<Events> findByEventcity(String city) {


//        List<Events> list = new ArrayList<>();
//        list = (List<Events>) eventsRepository.findAll();
//
//        List<Events> list1 = new ArrayList<>();
//
//        for ( Events evnt : list ){
//
//            if ( evnt.getEventcity() == city)
//                list1.add(evnt);
//        }

        return eventsRepository.findByEventcity(city);

    }

    @Override
    public List<Events> findByEventdate(LocalDate eventdate) {
        return eventsRepository.findByEventdate(eventdate);
    }

    @Override
    public List<Events> findByEventcityAndEventdateIn(String city, LocalDate date) {
        return eventsRepository.findByEventcityAndEventdateIn(city,date);
    }

    @Override
    public List<Events> findByEventcityAndEventdateAndDeletedFalse(String city, LocalDate date) {
        return eventsRepository.findByEventcityAndEventdateAndDeletedFalse(city,date);
    }


}





