package com.example.WebService;


import com.example.Entity.Ticket;
import com.example.Entity.Users;
import com.example.Repository.UsersRepository;
import com.example.Servicee.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;




    @RequestMapping (value = "/all/tickets", method = RequestMethod.GET)
    public Iterable<Ticket> findall(){

        return ticketService.findAll();
    }

    @RequestMapping (value = "/all/user/{uid}", method = RequestMethod.GET)
    public Iterable<Ticket> findalluser(@PathVariable long uid){

        return ticketService.findAllByUser(uid);
    }

    @RequestMapping (value = "/all/delete", method = RequestMethod.GET)
    public List<Ticket> findallnotcanceled(){

        return ticketService.findByCanceledFalse();
    }

    @GetMapping(value = "/ticket/{id}")
    public Ticket finByid(@PathVariable Long id){
        return ticketService.findById(id);
    }

    @PostMapping (value = "/create/{uid}/{eid}")
    public void createticket (@RequestBody Ticket tkt, @PathVariable Long uid, @PathVariable Long eid){
        ticketService.createTicket(tkt,uid,eid);
    }

    @PostMapping (value = "/update")
    public  void updateticket(@RequestBody Ticket tikt){
        ticketService.updateTicket(tikt);
    }

    @RequestMapping (value = "/cancel/{id}")
    public void isdeleted(@PathVariable Long id){
        ticketService.IsCanceled(id);
    }

    @RequestMapping (value = "/attended/{id}")
    public void isattended(@PathVariable Long id){
        ticketService.IsAttended(id);
    }
}
