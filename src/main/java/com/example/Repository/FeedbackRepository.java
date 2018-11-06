package com.example.Repository;


import com.example.Entity.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    List<Feedback> findAll();
    Optional<Feedback> findById(Long id);

}
