package com.example.Servicee.ServiceImplementation;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Users;
import com.example.Repository.RolesRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImplementation implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private EmailSendingService emailSendingService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<Users> findAll(){

        return usersRepository.findAll();

    }

    @Override
    public Users findById(Long id){
        return usersRepository.findById(id).get();
    }

    @Override
    public void createUser(UsersDTO usrdto, Long rid){


         Users usr = modelMapper.map(usrdto,Users.class);
        usr.setRoleid( rolesRepository.findById(rid).get());
        emailSendingService.sendNotificaitoin(usr.getUseremail(),"Thanks For Registering!","Good To Have You With Us.");

         usersRepository.save(usr);

    }

    @Override
    public void updateUser(UsersDTO usersDTO, Long uid){

         Users user1 = usersRepository.findById(uid).get();

          Users users = modelMapper.map(usersDTO,Users.class);
          users.setRoleid(user1.getRoleid());
//          if (usersRepository.findById(uid).isPresent()){
              users.setUserid(uid);
              usersRepository.save(users);





    }

    @Override
    public void IsDeleted(Long id){

        usersRepository.findById(id).get().setDeleted(true);
        usersRepository.save(findById(id));
    }

    @Override
    public List<Users> findAllIfDeleted() {


        Iterable<Users> list = new ArrayList<>();
        list = usersRepository.findAll();
        Iterable<Users> list1 = new ArrayList<>();
        for ( Users usr : list){

            if (!usr.isDeleted()) ((ArrayList<Users>) list1).add(usr);

        }
        return (List<Users>) list1;
    }
}
