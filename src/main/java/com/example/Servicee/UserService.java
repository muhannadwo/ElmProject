package com.example.Servicee;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {




    Iterable<Users> findAll();
    Users findById(Long id);

    void createUser(UsersDTO usersDTO, Long rid);

    void updateUser(UsersDTO usersDTO, Long uid);

    void IsDeleted(Long id);

    List<Users> findAllIfDeleted();



}
