package com.example.auth.mapper;


import com.example.auth.dto.EventDTO;
import com.example.auth.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "ticketType", ignore = true)
    EventDTO eventToEventDTO(Event event);

    Event  eventDTOToEvent(EventDTO eventDTO);
}
