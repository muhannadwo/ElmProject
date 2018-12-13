package com.example.Servicee;

import com.example.DTOs.EventsDTO;
import com.example.Entity.Events;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EventsService {



    List<Events> findAll();

    EventsDTO findById(Long id);

    Events createEvent(EventsDTO eventsDTO, Long id);

    ResponseEntity updateEvent(EventsDTO eventsDTO, long eid);

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
