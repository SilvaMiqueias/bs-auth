package com.example.auth.repository;

import com.example.auth.model.Event;
import com.example.auth.model.enumerador.StatusEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT event FROM Event event " +
            "WHERE (event.status in :statusList) " +
            "AND UPPER(event.title) LIKE CONCAT('%', UPPER(:search), '%') " )
    Page<Event> findAllByPage(@Param("search") String search, @Param("statusList") List<StatusEvent> statusList, Pageable pageable);
}
