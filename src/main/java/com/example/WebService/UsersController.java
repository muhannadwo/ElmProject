package com.example.WebService;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Users;
import com.example.Repository.UsersRepository;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/api/users")

public class UsersController {


    @Autowired
    private UserService userService;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping (value = "/all/users", method = RequestMethod.GET)
    public Iterable<Users> findall(){
        return  userService.findAll();
    }

    @RequestMapping (value = "/user/{id}")
    public ResponseEntity findbyid(@PathVariable Long id){
        if(usersRepository.findById(id).isPresent()){
        return ResponseEntity.ok(userService.findById(id));}
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping  (value = "/create/{rid}")
    public ResponseEntity createuser(@Valid @RequestBody UsersDTO usrdto, @Valid @PathVariable Long rid, BindingResult bindingResult)
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

    @RequestMapping (value = "/delete/{id}")
    public ResponseEntity isdeleted (@PathVariable Long id){

        if (usersRepository.findById(id).isPresent()){
        return ResponseEntity.ok(userService.IsDeleted(id));}
        else{return ResponseEntity.notFound().build();
        }


    }

    @RequestMapping (value = "/all/deleted")
    public List<Users> findalldeleted(){

        return userService.findAllIfDeleted();
    }

    @RequestMapping (value = "/phone/{number}")
    public ResponseEntity<List<Users>> findbyphonenumber(@PathVariable int number){
        if(usersRepository.findByPhonenumber(number).isEmpty()){
        return ResponseEntity.notFound().build();}else{

            return ResponseEntity.ok(usersRepository.findByPhonenumber(number));
        }
    }







}



