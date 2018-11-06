package com.example.Servicee;

import com.example.Entity.Feedback;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FeedbackService {



     Iterable<Feedback> findAll();
     Feedback findById(Long id);

     void createfeedback (Feedback fb, Long id);
     void updatefeedback (Feedback feedback);

     void isDeleted(long id);

}
