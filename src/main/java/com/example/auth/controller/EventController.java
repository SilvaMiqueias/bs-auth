package com.example.auth.controller;

import com.example.auth.dto.EventDTO;
import com.example.auth.dto.EventPageDTO;
import com.example.auth.dto.TicketTypeDTO;
import com.example.auth.model.enumerador.StatusEvent;
import com.example.auth.service.EventService;
import com.example.auth.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketTypeService ticketTypeService;

    @GetMapping("/find-all")
    private ResponseEntity<List<EventDTO>> findAll(){
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/find-all-by-page")
    private ResponseEntity<EventPageDTO> findAllByPage(@RequestParam(value = "search", defaultValue = "") String search,
                                                       @RequestParam(value = "status", defaultValue = "") List<String> status,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(eventService.findAllByPage(search, status, page, size));
    }

    @GetMapping("/find-by/{id}")
    private ResponseEntity<EventDTO> findBy(@PathVariable Long id){
        return ResponseEntity.ok(eventService.findBy(id));
    }

    @PostMapping("/create")
    private ResponseEntity<?> create(@RequestBody EventDTO eventDTO) throws IllegalAccessException {
        return ResponseEntity.ok( eventService.create(eventDTO));
    }

    @PutMapping("/update")
        private ResponseEntity<EventDTO> update(@RequestBody EventDTO eventDTO) throws IllegalAccessException {
        return ResponseEntity.ok(eventService.update(eventDTO));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-all-ticket-type-by-event/{id}")
    private ResponseEntity<List<TicketTypeDTO>> findAllTicketTypeByEventId(@PathVariable Long id){
        return ResponseEntity.ok(eventService.findAllTicketTypes(id));
    }

    @PutMapping("/ticket-type/update")
    private ResponseEntity<EventDTO> updateTicketType(@RequestBody TicketTypeDTO ticketTypeDTO) throws IllegalAccessException {
        ticketTypeService.updateTicketType(ticketTypeDTO);
        return ResponseEntity.ok().build();
    }
}
