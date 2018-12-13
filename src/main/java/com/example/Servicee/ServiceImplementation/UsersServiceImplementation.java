package com.example.Servicee.ServiceImplementation;


import com.example.DTOs.UsersDTO;
import com.example.Entity.Roles;
import com.example.Entity.Users;
import com.example.Repository.RolesRepository;
import com.example.Repository.UsersRepository;
import com.example.Servicee.EmailSendingService;
import com.example.Servicee.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public UsersDTO findById(Long id){


            Users users = usersRepository.findById(id).get();
            UsersDTO usersDTO = modelMapper.map(users, UsersDTO.class);
            return usersDTO;
    }

    @Override
    public Users createUser(UsersDTO usrdto, Long rid){

        String a = "";
        if (rid == 1){
            a = "ROLE_ADMIN";
        }else if(rid == 2){
            a = "ROLE_ORG";
        }else if (rid == 3){
            a= "ROLE_USER";
        }


        Users usr = modelMapper.map(usrdto,Users.class);
        usr.setRoleid( rolesRepository.findById(a).get());
        String encoded=new BCryptPasswordEncoder().encode(usr.getPassword());
        usr.setPassword(encoded);
//        usr.setRoleid(rolesRepository.findById(usrdto.getRole()).get());
        //emailSendingService.sendNotificaitoin(usr.getUseremail(),"Thanks For Registering!","Good To Have You With Us.");

        return usersRepository.save(usr);

    }

    @Override
    public Users updateUser(UsersDTO usersDTO, Long uid){

         Users user1 = usersRepository.findById(uid).get();

          Users users = modelMapper.map(usersDTO,Users.class);

          users.setRoleid(user1.getRoleid());
//          if (usersRepository.findById(uid).isPresent()){
              users.setUserid(uid);

        String encoded=new BCryptPasswordEncoder().encode(users.getPassword());
        users.setPassword(encoded);

              return usersRepository.save(users);
    }

    @Override
    public void IsDeleted(Long id){

        Users users = usersRepository.findById(id).get();
        users.setDeleted(false);
         usersRepository.save(users);

    }

    @Override
    public List<Users> findAllIfDeleted() {


        Iterable<Users> list = new ArrayList<>();
        list = usersRepository.findAll();
        Iterable<Users> list1 = new ArrayList<>();
        for ( Users usr : list){

            if (usr.isDeleted()) ((ArrayList<Users>) list1).add(usr);

        }
        return (List<Users>) list1;
    }

    @Override
    public List<Users> findByPhonenumber(int number) {
        return usersRepository.findByPhonenumber(number);
    }

    @Override
    public UsersDTO findByUserName(String a) {

        Users users = usersRepository.findByUsername(a);
        UsersDTO usersDTO = modelMapper.map(users, UsersDTO.class);
        return usersDTO;
    }
    public Roles findByUser(String username){
        return rolesRepository.findById(usersRepository.findByUsername(username).getRoleid().getRolename()).get();
    }
}
