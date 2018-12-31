package com.example.WebService;


import com.example.DTOs.TicketDTO;
import com.example.Entity.Ticket;
import com.example.Entity.Users;
import com.example.Repository.EventsRepository;
import com.example.Repository.TicketRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.RegionSpanIterator;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventsRepository eventsRepository;




    @GetMapping (value = "/all/tickets")
    @PreAuthorize("(hasRole('ADMIN'))")
    public List<Ticket> findall(){

        return ticketService.findAll();
    }

    @GetMapping (value = "/all/user/{uid}")
    public Iterable<Ticket> findalluser(@PathVariable long uid){

        return ticketService.findAllByUser(uid);
    }

    @GetMapping (value = "/all/delete")
//    @PreAuthorize("(hasRole('ADMIN'))")
    public List<Ticket> findallnotcanceled(){

        return ticketService.findByCanceledFalse();
    }

    @GetMapping(value = "/ticket/{id}")
    public ResponseEntity<Ticket> finByid(@PathVariable Long id){
        Optional<Ticket> ticket =  ticketService.findById(id);

        if( ticket.isPresent()){
            return ResponseEntity.ok(ticket.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping (value = "/create/{uid}/{eid}")
    @PreAuthorize("(hasRole('USER'))")
    public ResponseEntity createticket ( @PathVariable Long uid, @PathVariable Long eid){
        if (eventsRepository.findById(eid) == null ) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(ticketService.createTicket(uid,eid));
        }
    }

    @PutMapping (value = "/update")
    public  void updateticket(@RequestBody Ticket tikt){
        ticketService.updateTicket(tikt);
    }

    @GetMapping (value = "/cancel/{id}")
    @PreAuthorize("(hasAnyRole('ORG','USER'))")
    public ResponseEntity isdeleted(@PathVariable Long id){
        if (ticketRepository.findById(id).isPresent()){
        return ResponseEntity.ok(ticketService.IsCanceled(id));}
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping (value = "/attended/{id}")
    @PreAuthorize("(hasRole('ORG'))")
    public ResponseEntity isattended(@PathVariable Long id){
        if (ticketRepository.findById(id).isPresent()){
        return ResponseEntity.ok(ticketService.IsAttended(id));}
        else{
            return ResponseEntity.notFound().build();}
    }





}
