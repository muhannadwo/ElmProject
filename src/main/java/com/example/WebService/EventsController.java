package com.example.WebService;

import com.example.Entity.Events;
import com.example.Servicee.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/api/events")

public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping (value = "/all/events", method = RequestMethod.GET)
    public Iterable<Events> findall(){
        return eventsService.findAll();
    }

    @RequestMapping (value = "/event/{id}", produces = "application/json")
    public ResponseEntity<Events> findById(@PathVariable Long id){
        Optional<Events> event =  eventsService.findById(id);

        if( event.isPresent()){
             return ResponseEntity.ok(event.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping (value = "/create/{id}")
    public void createevent(@Valid  @RequestBody Events event, @PathVariable Long id){
        eventsService.createEvent(event,id);
    }

    @PostMapping (value = "/update/{eid}")
    public void updateevent(@RequestBody Events evnt, @PathVariable long eid){

        eventsService.updateEvent(evnt,eid);
    }

    @RequestMapping (value = "/active/{id}")
    public void isactive (@PathVariable Long id){
        eventsService.isActiveEvent(id);
    }

    @RequestMapping (value = "/deactive/{id}")
    public void isnotactive (@PathVariable Long id){ eventsService.isNotActiveEvent(id);}

    @RequestMapping (value = "/delete/{id}")
    public void isdeleted(@PathVariable Long id){
        eventsService.isDeleted(id);
    }

    @RequestMapping (value = "/all/deleted")
    public List<Events> findalldeleted(){

        return eventsService.findAllDeleted();
    }

    @RequestMapping (value = "/approved")
    public List<Events> findallactiveevents (){
        return eventsService.findAllActiveEvents();
    }

    @RequestMapping (value = "/city/{eventcity}")
    public List<Events> findByEventcity (@PathVariable String eventcity){
        return eventsService.findByEventcity(eventcity);
    }

    @RequestMapping (value = "/date/{date}")
    public List<Events> findByEventdate (@PathVariable String date){
        return eventsService.findByEventdate(LocalDate.parse(date));
    }

    @RequestMapping (value = "/search/{city}/{date}")
    public List<Events> findboth (@PathVariable String city, @PathVariable String date){

        return eventsService.findByEventcityAndEventdateIn(city,LocalDate.parse(date));
    }

    @RequestMapping (value = "/search1/{city}/{date}")
    public List<Events> findboth1 (@PathVariable String city, @PathVariable String date){

        return eventsService.findByEventcityAndEventdateAndDeletedFalse(city,LocalDate.parse(date));
    }

}

