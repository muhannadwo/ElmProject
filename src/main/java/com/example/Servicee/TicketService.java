package com.example.Servicee;


import com.example.Entity.Ticket;
import com.example.Entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TicketService {



     Iterable<Ticket> findAll();

     Ticket findById(Long id);

     void createTicket (Ticket tkt, Long uid, Long eid);

     void updateTicket (Ticket tkt);

     void IsCanceled(Long id);

     void IsAttended(Long id);

     List<Ticket> findAllByUser(long uid);

     List<Ticket> findByCanceledFalse();


}
