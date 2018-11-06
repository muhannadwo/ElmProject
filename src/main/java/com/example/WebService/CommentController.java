package com.example.WebService;


import com.example.Entity.Comment;
import com.example.Servicee.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/all/comments",method = RequestMethod.GET)
    public Iterable<Comment> findAll(){
        return commentService.findAll();
    }

    @GetMapping(value = "/comment/{id}")
    public Comment findById(@PathVariable Long id){
        return commentService.findById(id);
    }

    @PostMapping(value = "/create/{uid}/{eid}")
    public void createcomment(@RequestBody Comment comment, @PathVariable long uid, @PathVariable long eid){
        commentService.createComment(comment,uid,eid);
    }

    @PutMapping(value = "/update",produces = "application/json")
    public void updatecomment(@RequestBody Comment comment){
         commentService.updateComment(comment);
    }

    @GetMapping(value = "/all/event/{eid}")
    public List<Comment> findByEvent(@PathVariable long eid){
        return commentService.finAllByEvent(eid);
    }

    @GetMapping(value = "/all/user/{uid}")
    public List<Comment> findByUser(@PathVariable long uid){
        return commentService.finAllByUser(uid);
    }


}
