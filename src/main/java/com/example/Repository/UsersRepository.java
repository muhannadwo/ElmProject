package com.example.Repository;

import com.example.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// Here We Declare Our Functions That Were Gonna Use From Sql Table From Users [ Like findById or findByName ]
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    List<Users> findByPhonenumber(int number);
    Users findByUsername(String username);
    long countByDeletedTrueOrDeletedFalse();

}
