package com.example.Servicee.ServiceImplementation;

import com.example.DTOs.FeedbackDTO;
import com.example.Entity.Feedback;
import com.example.Entity.Ticket;
import com.example.Repository.FeedbackRepository;
import com.example.Repository.TicketRepository;
import com.example.Servicee.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class FeedbackServiceImplementation implements FeedbackService {


    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public Iterable<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public ResponseEntity findById(Long id) {
        if (feedbackRepository.findById(id).isPresent()){
        return ResponseEntity.ok(feedbackRepository.findById(id).get());}
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity createfeedback (FeedbackDTO feedbackDTO, Long tid) {

        if (ticketRepository.findById(tid).isPresent()) {

            Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);
            Ticket ticket = ticketRepository.findById(tid).get();
            feedback.setTicketid(ticketRepository.findById(tid).get());

        if (ticket.isAttended()) {
            return ResponseEntity.ok(feedbackRepository.save(feedback));
        }


    }
    return ResponseEntity.badRequest().build();
    }

    @Override
    public void updatefeedback (FeedbackDTO feedbackDTO, long id){
        Feedback feedback2 = feedbackRepository.findById(id).get();

        Feedback feedback1 = modelMapper.map(feedbackDTO, Feedback.class);
        feedback1.setTicketid(feedback2.getTicketid());
        feedback1.setFeedbackid(id);

        feedbackRepository.save(feedback1);
    }

    @Override
    public ResponseEntity isDeleted(long id) {

        if (feedbackRepository.findById(id).isPresent()){
        Feedback feedback = feedbackRepository.findById(id).get();
        feedback.setDeleted(true);
        return ResponseEntity.ok(feedback);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
