package com.example.auth.service;

import com.example.auth.dto.EventDTO;
import com.example.auth.dto.EventPageDTO;
import com.example.auth.dto.TicketTypeDTO;
import com.example.auth.mapper.EventMapper;
import com.example.auth.mapper.TicketTypeMapper;
import com.example.auth.model.Event;
import com.example.auth.model.TicketType;
import com.example.auth.model.enumerador.StatusEvent;
import com.example.auth.repository.EventRepository;
import com.example.auth.security.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @Autowired
    private TicketTypeService ticketTypeService;


    public List<EventDTO> findAll(){
        return eventRepository.findAll().stream().map(EventMapper.INSTANCE::eventToEventDTO).collect(Collectors.toList());
    }

    public EventPageDTO findAllByPage(String search, List<String> status,  Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        List<StatusEvent> statusEvents = Arrays.asList(StatusEvent.values());
        List<StatusEvent> statusFilter = new ArrayList<>();

        status.forEach(statusString -> {
            statusEvents.forEach(statusEvent -> {
                if(statusEvent.name().equals(statusString)){
                    statusFilter.add(statusEvent);
                }
            });
        });

        Page<Event> bookPage = eventRepository.findAllByPage(search.toUpperCase(), !statusFilter.isEmpty() ? statusFilter : statusEvents, pageable);
        return EventPageDTO.builder().content(bookPage.getContent().stream().map(EventMapper.INSTANCE::eventToEventDTO).collect(Collectors.toList()))
                .totalPages(bookPage.getTotalPages()).totalElements(bookPage.getTotalElements())
                .pageNumber(bookPage.getNumber()).pageSize(bookPage.getSize()).build();
    }

    public EventDTO findBy(Long id){
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));
        return EventMapper.INSTANCE.eventToEventDTO(event);
    }

    public EventDTO create(EventDTO eventDTO) throws IllegalAccessException {
        Event event = EventMapper.INSTANCE.eventDTOToEvent(eventDTO);
        event.setUserId(authenticationUtil.getUserLogged());
        event = eventRepository.save(event);
        return EventMapper.INSTANCE.eventToEventDTO(event);
    }

    public EventDTO update(EventDTO eventDTO) throws IllegalAccessException {
        Event oldEvent = eventRepository.findById(eventDTO.getId()).get();
        Event event = new Event();

        if(verifyStatusIsValid(oldEvent.getStatus())){
            event = EventMapper.INSTANCE.eventDTOToEvent(eventDTO);
            event.setUserId(authenticationUtil.getUserLogged());
            if(!verifyStatusIsValid(event.getStatus())){ event.setActive(false);}
        }else{
            throw  new RuntimeException("Status encerrado!");
        }

        List<TicketType> listType =  eventDTO.getTicketType().stream().map(TicketTypeMapper.INSTANCE::ticketTypeDTOToTicketType).toList();
        listType.forEach(ticketType -> {
            ticketType.setEvent(oldEvent);
            if(!ticketTypeService.existsTicketTypeAndEventId(ticketType.getName(), oldEvent.getId())){
               ticketType = ticketTypeService.createTicketType(ticketType);
           }
        });

        event.setTicketType(listType);
        event = eventRepository.save(event);
        return EventMapper.INSTANCE.eventToEventDTO(event);
    }

    public void delete(Long id){
        Event event =  eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));
        eventRepository.delete(event);
    }

    public List<TicketTypeDTO> findAllTicketTypes(Long eventId){
        return ticketTypeService.getAllTicketTypeByEventId(eventId).stream().map(TicketTypeMapper.INSTANCE::ticketTypeToTicketTypeDTO).collect(Collectors.toList());
    }

    public Boolean verifyStatusIsValid(StatusEvent status){
      return status.equals(StatusEvent.IN_PROGRESS);
    }

}
