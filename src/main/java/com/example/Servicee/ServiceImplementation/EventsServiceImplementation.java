package com.example.Servicee.ServiceImplementation;


import com.example.Configs.ObjectMapperUtils;
import com.example.DTOs.EventsDTO;
import com.example.Entity.Events;
import com.example.Entity.Ticket;
import com.example.Repository.EventsRepository;
import com.example.Repository.TicketRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.EventsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<EventsDTO> findAll(){

    List<Events> events =  eventsRepository.findAll();
    List<EventsDTO> eventsDTOS = ObjectMapperUtils.mapAll(events,EventsDTO.class);
    return eventsDTOS;

    }

    @Override
    public Optional<Events> findById(Long id) {
        return eventsRepository.findById(id);
    }

    @Override
    public ResponseEntity createEvent(EventsDTO eventsDTO, Long id){

        LocalDate date = LocalDate.now().minusDays(1);
        Events events = modelMapper.map(eventsDTO, Events.class);

        if ( date.isBefore(events.getEventdate())) {
            events.setOrganizer_id(usersRepository.findById(id).get());
            return ResponseEntity.ok(eventsRepository.save(events));
        } return ResponseEntity.badRequest().build();
    }

    @Override
    public  ResponseEntity updateEvent(EventsDTO eventsDTO, long eid){

        Events event = eventsRepository.findById(eid).get();

        if ( eventsRepository.findById(eid).isPresent()){

        Events events = modelMapper.map(eventsDTO, Events.class);
        events.setOrganizer_id(event.getOrganizer_id());
        events.setEventid(eid);

//        List<Ticket> ticket = ticketRepository.findByEventsidAndCanceledFalse(event);
//        for (Ticket ticket1 : ticket){
//            emailSendingService.sendNotificaitoin(ticket1.getAttenderid().getUseremail(),"Event Updated! ","The Event  '" + event.getEventname() + "'  Was Updated");
//        }
            return ResponseEntity.ok(eventsRepository.save(events));


        }
            else{
            return ResponseEntity.badRequest().build();
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





