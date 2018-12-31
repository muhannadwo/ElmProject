package com.example.Servicee;


import com.example.DTOs.CommentDTO;
import com.example.Entity.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {


    Iterable<Comment> findAll();

    Comment findById(Long id);

    Comment createComment (Comment comment,Long uid, Long eid);

    ResponseEntity updateComment (CommentDTO commentDTO,long id);

    ResponseEntity IsCanceled(Long id);

    List<Comment> finAllByEvent(long eid);
    List<Comment> finAllByUser(long uid);

}
