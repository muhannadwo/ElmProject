package com.example.WebService;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Users;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/api/users")

public class UsersController {


    @Autowired
    private UserService userService;
    @Autowired
    private EmailSendingService emailSendingService;

    @RequestMapping (value = "/all/users", method = RequestMethod.GET)
    public Iterable<Users> findall(){
        return  userService.findAll();
    }

    @RequestMapping (value = "/user/{id}")
    public Users findbyid(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping  (value = "/create/{rid}")
    public void createuser(@Valid @RequestBody UsersDTO usrdto, @Valid @PathVariable Long rid)
    {

            userService.createUser(usrdto,rid);

    }

    @PutMapping (value = "/update/{uid}")
    public void updateUser(@Valid @RequestBody UsersDTO usersDTO,@Valid @PathVariable Long uid){

        userService.updateUser(usersDTO,uid);
    }

    @RequestMapping (value = "/delete/{id}")
    public void isdeleted (@PathVariable Long id){

        userService.IsDeleted(id);


    }

    @RequestMapping (value = "/all/deleted")
    public List<Users> findalldeleted(){

        return userService.findAllIfDeleted();
    }







}



