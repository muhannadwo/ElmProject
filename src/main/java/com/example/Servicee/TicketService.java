package com.example.Servicee;


import com.example.DTOs.TicketDTO;
import com.example.Entity.Ticket;
import com.example.Entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface TicketService {



     List<Ticket> findAll();

     Optional<Ticket> findById(Long id);

     Ticket createTicket (Long uid, Long eid);

     void updateTicket (Ticket tkt);

     Ticket IsCanceled(Long id);

     ResponseEntity IsAttended(Long id);

     List<Ticket> findAllByUser(long uid);

     List<Ticket> findByCanceledFalse();


}
