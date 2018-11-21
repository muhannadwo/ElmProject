package com.example.WebService;


import com.example.DTOs.TicketDTO;
import com.example.Entity.Ticket;
import com.example.Entity.Users;
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




    @RequestMapping (value = "/all/tickets", method = RequestMethod.GET)
    @PreAuthorize("(hasRole('ADMIN'))")
    public List<TicketDTO> findall(){

        return ticketService.findAll();
    }

    @RequestMapping (value = "/all/user/{uid}", method = RequestMethod.GET)
    @PreAuthorize("(hasAnyRole('ADMIN','USER'))")
    public Iterable<Ticket> findalluser(@PathVariable long uid){

        return ticketService.findAllByUser(uid);
    }

    @RequestMapping (value = "/all/delete", method = RequestMethod.GET)
    @PreAuthorize("(hasRole('ADMIN'))")
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

    @PostMapping (value = "/create/{uid}/{eid}")
    @PreAuthorize("(hasRole('USER'))")
    public ResponseEntity createticket (@RequestBody Ticket tkt, @PathVariable Long uid, @PathVariable Long eid, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }else{
            return ResponseEntity.ok(ticketService.createTicket(tkt,uid,eid));
        }
    }

    @PostMapping (value = "/update")
    public  void updateticket(@RequestBody Ticket tikt){
        ticketService.updateTicket(tikt);
    }

    @RequestMapping (value = "/cancel/{id}")
    @PreAuthorize("(hasAnyRole('ADMIN','USER'))")
    public ResponseEntity isdeleted(@PathVariable Long id){
        if (ticketRepository.findById(id).isPresent()){
        return ResponseEntity.ok(ticketService.IsCanceled(id));}
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping (value = "/attended/{id}")
    @PreAuthorize("(hasRole('ORG'))")
    public ResponseEntity isattended(@PathVariable Long id){
        if (ticketRepository.findById(id).isPresent()){
        return ResponseEntity.ok(ticketService.IsAttended(id));}
        else{
            return ResponseEntity.notFound().build();}
    }
}
