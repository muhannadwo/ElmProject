package com.example.WebService;


import com.example.Entity.Users;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void createuser(@Valid @RequestBody Users usr, @PathVariable Long rid)
    {
            userService.createUser(usr,rid);

    }

    @PutMapping (value = "/update/{rid}")
    public void updateUser(@Valid @RequestBody Users user,@PathVariable long rid){

        userService.updateUser(user,rid);
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



