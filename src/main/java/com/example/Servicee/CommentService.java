package com.example.Servicee;


import com.example.Entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {


    Iterable<Comment> findAll();

    Comment findById(Long id);

    void createComment (Comment comment, Long uid, Long eid);

    void updateComment (Comment comment);

    void IsCanceled(Long id);

    List<Comment> finAllByEvent(long eid);
    List<Comment> finAllByUser(long uid);

}
