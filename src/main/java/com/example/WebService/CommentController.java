package com.example.WebService;


import com.example.DTOs.CommentDTO;
import com.example.Entity.Comment;
import com.example.Repository.CommentRepository;
import com.example.Servicee.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/all/comments")
    public Iterable<Comment> findAll(){
        return commentService.findAll();
    }

    @GetMapping(value = "/comment/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        if (commentRepository.findById(id).isPresent()){
        return ResponseEntity.ok(commentService.findById(id));}
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/create/{uid}/{eid}")
    public ResponseEntity createcomment(@Valid @RequestBody Comment comment, @PathVariable long uid, @PathVariable long eid, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        else{
        return ResponseEntity.ok(commentService.createComment(comment,uid,eid));}
    }

    @PutMapping(value = "/update/{id}",produces = "application/json")
    public ResponseEntity updatecomment(@Valid@RequestBody CommentDTO commentDTO,@PathVariable long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }else {
         return ResponseEntity.ok(commentService.updateComment(commentDTO,id));}
    }

    @GetMapping(value = "/all/event/{eid}")
    public List<Comment> findByEvent(@PathVariable long eid){
        return commentService.finAllByEvent(eid);
    }

    @GetMapping(value = "/all/user/{uid}")
    @PreAuthorize("(hasAnyRole('ADMIN','USER'))")
    public List<Comment> findByUser(@PathVariable long uid){
        return commentService.finAllByUser(uid);
    }

    @GetMapping(value = "/cancel/{id}")
    public ResponseEntity cancelcomment(@PathVariable long id){
        if (commentRepository.findById(id).isPresent()){
            return ResponseEntity.ok(commentService.IsCanceled(id));
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
