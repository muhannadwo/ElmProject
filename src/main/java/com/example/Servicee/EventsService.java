package com.example.Servicee;

import com.example.Entity.Events;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface EventsService {



    Iterable<Events> findAll();

    Optional<Events> findById(Long id);

    void createEvent(Events evnt, Long id);

    void updateEvent(Events event, long eid);

    void isDeleted(Long id);

    void isActiveEvent(Long id);

    void isNotActiveEvent(Long id);

    List<Events> findAllDeleted ();

    List<Events> findAllActiveEvents();

    List<Events> findByEventcity(String city);

    List<Events> findByEventdate(LocalDate eventdate);

    List<Events> findByEventcityAndEventdateIn(String city, LocalDate date);

    List<Events> findByEventcityAndEventdateAndDeletedFalse(String city, LocalDate date);
}
