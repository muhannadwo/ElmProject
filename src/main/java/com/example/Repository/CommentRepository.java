package com.example.Repository;


import com.example.Entity.Comment;
import com.example.Entity.Events;
import com.example.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByEventsid(Events events);
    List<Comment> findAllByUserid(Users users);
    long countByUseridAndEventsidAndCdateAfter(Users users, Events events, LocalDateTime dateTime);

    long countByCanceledTrueOrCanceledFalse();

}
