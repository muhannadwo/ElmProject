package com.example.WebService;

import com.example.Entity.Roles;
import com.example.Servicee.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping (value = "/api/roles")

public class RolesController {

    @Autowired
    private RolesService rolesService;

//    @RequestMapping(value = "/all/roles", method = RequestMethod.GET)
//    public Iterable<Roles> findall(){
//
//        return rolesService.findAll();
//
//    }
//
//    @RequestMapping (value = "/roles/{id}")
//    public Roles findbyid(@PathVariable Long id){
//        return rolesService.findById(id);
//    }


}




