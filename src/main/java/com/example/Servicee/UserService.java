package com.example.Servicee;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {




    Iterable<Users> findAll();
    ResponseEntity findById(Long id);

    ResponseEntity createUser(UsersDTO usersDTO, Long rid);

    ResponseEntity updateUser(UsersDTO usersDTO, Long uid);

    void IsDeleted(Long id);

    List<Users> findAllIfDeleted();



}
