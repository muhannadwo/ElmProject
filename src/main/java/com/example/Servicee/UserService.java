package com.example.Servicee;


import com.example.Entity.Roles;
import com.example.Entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {




    Iterable<Users> findAll();
    Users findById(Long id);

    void createUser(Users usr, Long rid);

    void updateUser(Users user, long rid);

    void IsDeleted(Long id);

    List<Users> findAllIfDeleted();



}
