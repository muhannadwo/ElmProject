package com.example.WebService;

import com.example.Entity.Feedback;
import com.example.Servicee.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping (value = "/api/feedback")

public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping (value = "/all/feedback", method = RequestMethod.GET)
    public Iterable<Feedback> findAll(){

        return feedbackService.findAll();
    }

    @RequestMapping(value = "/feedback/{id}")
    public Feedback findbyid(@PathVariable Long id){

        return feedbackService.findById(id);
    }

    @PostMapping (value = "/create/{tid}" )
    public void createfeedback (@RequestBody Feedback fb,@PathVariable Long tid){
         feedbackService.createfeedback(fb,tid);
    }

    @PostMapping (value = "/update")
    public void updatefeedback (Feedback fedback){
        feedbackService.updatefeedback(fedback);
    }

    @RequestMapping (value = "/delete/{id}")
    public void isdeleted (@PathVariable long id){
        feedbackService.isDeleted(id);
    }
}
