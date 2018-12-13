package com.example.WebService;

import com.example.Configs.MyUserDetails;
import com.example.Servicee.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class userLoginController {
//    @Autowired
//    private MyUserDetails myUserDetails;
    @Autowired
    private UserService userService;


    @GetMapping ("/userLogin")
    public ResponseEntity login(Principal principal){
//        UserDetails userDetails = myUserDetails.loadUserByUsername(principal.getName());

        Map<String,Object> map = new HashMap<>();
        map.put("Role", userService.findByUser(principal.getName()).getRolename());
        map.put("userId",userService.findByUserName(principal.getName()).getUserid());
//        return ResponseEntity.ok(userService.findByUser(principal.getName()));
        return ResponseEntity.ok(map);
    }

}
