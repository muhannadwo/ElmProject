package com.example.Servicee.ServiceImplementation;


import com.example.Configs.ObjectMapperUtils;
import com.example.DTOs.TicketDTO;
import com.example.Entity.Events;
import com.example.Entity.Ticket;
import com.example.Entity.Users;
import com.example.Repository.EventsRepository;
import com.example.Repository.TicketRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService {


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private EmailSendingService emailSendingService;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
//        List<TicketDTO> ticketDTOS = ObjectMapperUtils.mapAll(tickets, TicketDTO.class);
//        return ticketDTOS;

    }


    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket createTicket ( Long uid, Long eid) {

        Ticket tkt = new Ticket();

        long counter = ticketRepository.countByCanceledTrueOrCanceledFalse();
        Events events = eventsRepository.findById(eid).get();
        Users users = usersRepository.findById(uid).get();

        events.setEventcapacity(events.getEventcapacity());

//        if (events.isPresent() && users.isPresent()
//                && events.get().isActive() && !events.get().isDeleted() && !users.get().isDeleted()){
        //  if( events.isDeleted() == true && !events.isActive() && events.getEventdate() ==  users.isDeleted() == true)
        LocalDate date = LocalDate.now().minusDays(1);
        if(events.isActive() && !events.isDeleted() && events.getEventdate().isAfter(date) &&
                events.getEcount() < events.getEventcapacity()) {
//        List<Ticket> tickets = ticketRepository.findAllByAttenderid(users);
            for (Ticket tekt : ticketRepository.findAllByAttenderid(users))
                if (events.getEventdate().equals(tekt.getEventsid().getEventdate())) {
                    return null;
                }

            tkt.setAttenderid(usersRepository.findById(uid).get());
            tkt.setEventsid(eventsRepository.findById(eid).get());
            tkt.setEventname(eventsRepository.findById(eid).get().getEventname());
            tkt.setEventdate(eventsRepository.findById(eid).get().getEventdate().toString());

            events.setEcount(1+events.getEcount());
            tkt.setCount(1+counter);
//            emailSendingService.sendNotificaitoin(usersRepository.findById(uid).get().getUseremail(), "Thanks For Booking", " Hope You enjoy The Event! "+ tkt.getAttenderid().getFirstname());
            return ticketRepository.save(tkt);


//            tkt.setAttenderid(users);
//            tkt.setEventsid(events);
//
//
//            ticketRepository.save(tkt);

        }
        return null;
    }




    @Override
    public  void updateTicket ( Ticket tikt){

        ticketRepository.save(tikt);

    }

    @Override
    public Ticket IsCanceled(Long id) {
        if (ticketRepository.findById(id).isPresent()){
        ticketRepository.findById(id).get().setCanceled(true);
        Events events=ticketRepository.findById(id).get().getEventsid();
        events.setEcount(events.getEcount() - 1);
//        emailSendingService.sendNotificaitoin(ticketRepository.findById(id).get().getAttenderid().getUseremail(),
//                "Ticket Canceled!", "Hope You Book Tickets Again");
        return ticketRepository.save(findById(id).get());}
        else{
            return null;
        }
    }

    @Override
    public ResponseEntity IsAttended(Long id) {

        if (ticketRepository.findById(id).isPresent()){
        ticketRepository.findById(id).get().setAttended(true);
        return ResponseEntity.ok(ticketRepository.save(findById(id).get()));}
        else{ return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<Ticket> findAllByUser(long uid) {
        return ticketRepository.findAllByAttenderid(usersRepository.findById(uid).get());
    }

    @Override
    public List<Ticket> findByCanceledFalse() {
        return ticketRepository.findByCanceledFalse();
    }
}
