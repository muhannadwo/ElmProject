package com.example.WebService;

import com.example.Configs.ObjectMapperUtils;
import com.example.DTOs.EventsDTO;
import com.example.Entity.Events;
import com.example.Repository.EventsRepository;
import com.example.Servicee.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping (value = "/api/events")

public class EventsController {

    @Autowired
    private EventsService eventsService;


    @GetMapping (value = "/all/events")
    @PreAuthorize("(hasRole('ORG'))")
    public List<Events> findall(){
       return eventsService.findAll();
    }

    @GetMapping (value = "/event/{id}", produces = "application/json")
    public ResponseEntity findById(@PathVariable Long id){
            if(eventsService.findById(id)!=null){
             return ResponseEntity.ok(eventsService.findById(id));
        }else
            {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping (value = "/create/{id}")
    @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity createevent( @RequestBody @Valid EventsDTO eventsDTO, @PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());}
            else {return ResponseEntity.ok(eventsService.createEvent(eventsDTO, id));}
    }

    @PutMapping (value = "/update/{eid}")
    @PreAuthorize("(hasAnyRole('ADMIN','ORG'))")
    public ResponseEntity updateevent(@RequestBody @Valid EventsDTO eventsDTO, @PathVariable long eid, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        else{
            return ResponseEntity.ok(eventsService.updateEvent(eventsDTO,eid));
        }
    }

    @GetMapping (value = "/active/{id}")
//    @PreAuthorize("(hasRole('ORG'))")
    public void isactive (@PathVariable Long id){
        eventsService.isActiveEvent(id);
    }

    @GetMapping (value = "/deactive/{id}")
    @PreAuthorize("(hasRole('ORG'))")
    public void isnotactive (@PathVariable Long id){ eventsService.isNotActiveEvent(id);}

    @GetMapping (value = "/delete/{id}")
//    @PreAuthorize("(hasAnyRole('ADMIN'))")
    public void isdeleted(@PathVariable Long id){
        eventsService.isDeleted(id);
    }

    @GetMapping (value = "/all/deleted")
    @PreAuthorize("(hasRole('ADMIN'))")
    public List<Events> findalldeleted(){

        return eventsService.findAllDeleted();
    }

    @GetMapping (value = "/approved")
    public List<Events> findallactiveevents (){
        return eventsService.findAllActiveEvents();
    }

    @GetMapping (value = "/city/{eventcity}")
    public List<Events> findByEventcity (@PathVariable String eventcity){
        return eventsService.findByEventcity(eventcity);
    }

    @GetMapping (value = "/date/{date}")
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

