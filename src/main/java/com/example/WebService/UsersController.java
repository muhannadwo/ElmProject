package com.example.WebService;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Users;
import com.example.Repository.UsersRepository;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/api/users")
//@CrossOrigin
public class UsersController {


    @Autowired
    private UserService userService;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;



    @GetMapping (value = "/all/users")
    @PreAuthorize("(hasRole('ADMIN'))")
    public Iterable<Users> findall(){
        return  userService.findAll();
    }

    @GetMapping (value = "/user/{id}")
//    @PreAuthorize("(hasAnyRole('ADMIN','USER'))")
    public ResponseEntity findbyid(@PathVariable Long id){
        if(usersRepository.findById(id).isPresent()){
        return ResponseEntity.ok(userService.findById(id));}
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping  (value = "/create/{rid}")
    public ResponseEntity createuser(@Valid @RequestBody UsersDTO usrdto,@Valid @PathVariable Long rid, BindingResult bindingResult)
    {
            if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());}

            else{
                return ResponseEntity.ok(userService.createUser(usrdto,rid));
            }

    }

    @PutMapping (value = "/update/{uid}")
    public ResponseEntity updateUser(@Valid @RequestBody UsersDTO usersDTO,@Valid @PathVariable Long uid, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        else{
            return ResponseEntity.ok(userService.updateUser(usersDTO,uid));
        }
    }

    @GetMapping (value = "/delete/{id}")
//    @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity<Void> isdeleted (@PathVariable Long id){

        if (usersRepository.findById(id).isPresent()){
            userService.IsDeleted(id);
        return ResponseEntity.ok().build();}
        else{return ResponseEntity.notFound().build();
        }


    }

    @GetMapping (value = "/all/deleted")
    @PreAuthorize("(hasRole('ADMIN'))")
    public List<Users> findalldeleted(){

        return userService.findAllIfDeleted();
    }

    @GetMapping (value = "/phone/{number}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Users>> findbyphonenumber(@PathVariable int number){
        if(usersRepository.findByPhonenumber(number).isEmpty()){
        return ResponseEntity.notFound().build();}else{

            return ResponseEntity.ok(usersRepository.findByPhonenumber(number));
        }
    }







}



