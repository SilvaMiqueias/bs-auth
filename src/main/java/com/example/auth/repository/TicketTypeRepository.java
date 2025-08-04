package com.example.auth.repository;

import com.example.auth.model.TicketType;
import com.example.auth.model.enumerador.NameTicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    List<TicketType> findAllByEventId(@Param("eventId") Long id);

    Boolean existsByEventIdAndName(Long eventId, NameTicketType name);
}
