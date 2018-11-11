package com.example.Servicee;

import com.example.DTOs.FeedbackDTO;
import com.example.Entity.Feedback;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FeedbackService {



     Iterable<Feedback> findAll();
     ResponseEntity findById(Long id);

     ResponseEntity createfeedback (FeedbackDTO feedbackDTO, Long id);
     void updatefeedback (FeedbackDTO feedbackDTO, long id);

     ResponseEntity isDeleted(long id);

}
