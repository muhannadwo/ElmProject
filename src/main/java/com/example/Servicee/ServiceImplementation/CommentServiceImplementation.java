package com.example.Servicee.ServiceImplementation;


import com.example.Entity.Comment;
import com.example.Entity.Events;
import com.example.Entity.Users;
import com.example.Repository.CommentRepository;
import com.example.Repository.EventsRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {



    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EventsRepository eventsRepository;




    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void createComment(Comment comment, Long uid, Long eid) {

        Users users = usersRepository.findById(uid).get();
        Events events = eventsRepository.findById(eid).get();

        LocalDateTime date = LocalDateTime.now().minusMinutes(2);
        long counter = commentRepository.countByUseridAndEventsidAndCdateAfter(users,events,date);
       // if ( comment.getCdate().isAfter(date)) {
        if (counter == 0){


            comment.setUserid(usersRepository.findById(uid).get());
            //here
            comment.setEventsid(eventsRepository.findById(eid).get());
            comment.setCdate(LocalDateTime.now());
            //get comments array list from event class and add the new comment in it.
            events.getComments().add(comment);
            commentRepository.save(comment);
        }
    }

    @Override
    public void updateComment(Comment comment) {

         commentRepository.save(comment);

    }

    @Override
    public void IsCanceled(Long id) {
        commentRepository.findById(id).get().setCanceled(true);
        commentRepository.save(findById(id));
    }

    @Override
    public List<Comment> finAllByEvent(long eid) {
        return commentRepository.findAllByEventsid(eventsRepository.findById(eid).get());
    }

    @Override
    public List<Comment> finAllByUser(long uid) {
        return commentRepository.findAllByUserid(usersRepository.findById(uid).get());
    }

}
