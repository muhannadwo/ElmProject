package com.example.Repository;

import com.example.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// Here We Declare Our Functions That Were Gonna Use From Sql Table From Users [ Like findById or findByName ]
@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {



}
