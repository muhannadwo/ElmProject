package com.example.Repository;

import com.example.Entity.Events;
import com.example.Entity.Ticket;
import com.example.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {


    List<Ticket> findByCanceledFalse();
    List<Ticket> findAllByAttenderid(Users user);
    //Long countByEventsid(Events events);
    List<Ticket> findByEventsidAndCanceledFalse(Events events);

}
