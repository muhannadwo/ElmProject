package com.example.Repository;


import com.example.Entity.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EventsRepository extends CrudRepository<Events, Long> {

    List<Events> findByEventcity(String eventcity);
    List<Events> findByEventdate(LocalDate eventdate);
    List<Events> findByEventcityAndEventdateIn(String city, LocalDate date);
    List<Events> findByEventcityAndEventdateAndDeletedFalse(String city, LocalDate date);


}
