package com.example.Servicee.ServiceImplementation;


import com.example.DTOs.CommentDTO;
import com.example.Entity.Comment;
import com.example.Entity.Events;
import com.example.Entity.Users;
import com.example.Repository.CommentRepository;
import com.example.Repository.EventsRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private ModelMapper modelMapper;




    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
            return commentRepository.findById(id).get();
    }

    @Override
    public Comment createComment( Comment comment,Long uid, Long eid) {

        long commentcounter = commentRepository.countByCanceledTrueOrCanceledFalse();
        Users users = usersRepository.findById(uid).get();
        Events events = eventsRepository.findById(eid).get();

        LocalDateTime date = LocalDateTime.now().minusMinutes(2);
        long counter = commentRepository.countByUseridAndEventsidAndCdateAfter(users,events,date);
       // if ( comment.getCdate().isAfter(date)) {
        if (counter == 0) {

            comment.setEventname(events.getEventname());
            comment.setUserid(usersRepository.findById(uid).get());
            //here
            comment.setEventsid(eventsRepository.findById(eid).get());
            comment.setCdate(LocalDateTime.now());
            //get comments array list from event class and add the new comment in it.
            events.getComments().add(comment);
            comment.setCount(1+commentcounter);
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public ResponseEntity updateComment(CommentDTO commentDTO, long id) {

        if (commentRepository.findById(id).isPresent()) {
            Comment comment = commentRepository.findById(id).get();
            Comment comment1 = modelMapper.map(commentDTO, Comment.class);
            comment1.setEventsid(comment.getEventsid());
            comment1.setUserid(comment.getUserid());
            comment1.setCommentid(id);
            return ResponseEntity.ok(commentRepository.save(comment1));

        }return ResponseEntity.badRequest().build();

    }

    @Override
    public ResponseEntity IsCanceled(Long id) {

        if (commentRepository.findById(id).isPresent()){
        Comment comment = commentRepository.findById(id).get();
        comment.setCanceled(true);
        return ResponseEntity.ok(comment);}
        else{
            return ResponseEntity.notFound().build();
        }
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
