package com.example.Servicee.ServiceImplementation;

import com.example.Entity.Feedback;
import com.example.Entity.Ticket;
import com.example.Repository.FeedbackRepository;
import com.example.Repository.TicketRepository;
import com.example.Servicee.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
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




    @Override
    public Iterable<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).get();
    }

    @Override
    public void createfeedback (Feedback fb,Long tid) {
        Ticket ticket = ticketRepository.findById(tid).get();
        fb.setTicketid(ticketRepository.findById(tid).get());


        if (ticket.isAttended()) {
            feedbackRepository.save(fb);
        }
    }

    @Override
    public void updatefeedback (Feedback feedback){
        feedbackRepository.save(feedback);
    }

    @Override
    public void isDeleted(long id) {
        feedbackRepository.findById(id).get().setDeleted(true);
        feedbackRepository.save(findById(id));
    }
}
