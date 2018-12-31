package com.example.Servicee;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Roles;
import com.example.Entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {




    Iterable<Users> findAll();
    UsersDTO findById(Long id);

    Users createUser(UsersDTO usersDTO, Long rid);

    Users updateUser(UsersDTO usersDTO, Long uid);

    void IsDeleted(Long id);

    List<Users> findAllIfDeleted();

    List<Users> findByPhonenumber(int number);

    UsersDTO findByUserName(String a);
   Roles findByUser(String username);




}
