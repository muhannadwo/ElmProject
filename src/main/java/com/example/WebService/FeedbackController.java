package com.example.WebService;

import com.example.DTOs.FeedbackDTO;
import com.example.Entity.Feedback;
import com.example.Repository.FeedbackRepository;
import com.example.Servicee.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping (value = "/api/feedback")

public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @RequestMapping (value = "/all/feedback", method = RequestMethod.GET)
    public Iterable<Feedback> findAll(){

        return feedbackService.findAll();
    }

    @GetMapping(value = "/feedback/{id}")
    public ResponseEntity findbyid(@PathVariable Long id){

        if (feedbackRepository.findById(id).isPresent()){
        return ResponseEntity.ok(feedbackService.findById(id));}
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping (value = "/create/{tid}" )
    @PreAuthorize("(hasRole('USER'))")
    public ResponseEntity createfeedback (@Valid @RequestBody FeedbackDTO feedbackDTO, @PathVariable Long tid, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }else{
            return ResponseEntity.ok(feedbackService.createfeedback(feedbackDTO, tid));
        }
    }

    @PutMapping (value = "/update")
    @PreAuthorize("(hasRole('USER'))")
    public void updatefeedback (@Valid @RequestBody FeedbackDTO feedbackDTO,@PathVariable long id){
        feedbackService.updatefeedback(feedbackDTO,id);
    }

    @GetMapping (value = "/delete/{id}")
    @PreAuthorize("(hasAnyRole('ADMIN','USER'))")
    public ResponseEntity isdeleted (@PathVariable long id){
        if (feedbackRepository.findById(id).isPresent())
        return ResponseEntity.ok(feedbackService.isDeleted(id));
        else
            return ResponseEntity.notFound().build();
    }
}
