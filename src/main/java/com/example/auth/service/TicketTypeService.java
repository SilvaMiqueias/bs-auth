package com.example.auth.service;

import com.example.auth.dto.TicketTypeDTO;
import com.example.auth.mapper.TicketTypeMapper;
import com.example.auth.model.TicketType;
import com.example.auth.model.enumerador.NameTicketType;
import com.example.auth.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTypeService {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public TicketType createTicketType(TicketType ticketType) {
        if(!ticketTypeRepository.existsByEventIdAndName(ticketType.getEvent().getId(), ticketType.getName())){
           return ticketTypeRepository.save(ticketType);
        }else throw new RuntimeException("Ticket type already exists");
    }

    public void updateTicketType(TicketTypeDTO ticketTypeDTO) {
      TicketType ticketType = ticketTypeRepository.findById(ticketTypeDTO.getId()).orElse(null);
      if(ticketType != null){
          ticketType.setPrice(ticketTypeDTO.getPrice());
          ticketType.setTotalQuantity(ticketTypeDTO.getTotalQuantity());
          ticketType.setActive(ticketTypeDTO.getActive());
          ticketTypeRepository.save(ticketType);
      }else throw new RuntimeException("Tipo não encontrado");
    }

    public List<TicketType> getAllTicketTypeByEventId(Long eventId) {
        return ticketTypeRepository.findAllByEventId(eventId);
    }

    public void inactiveTicketType(Long id) {
        TicketType type = ticketTypeRepository.findById(id).orElse(null);
        if (type != null) {
            type.setActive(false);
            ticketTypeRepository.save(type);
        }else throw new RuntimeException("Ticket type not found");
    }

    public boolean existsTicketTypeAndEventId(NameTicketType name, Long eventId) {
        return ticketTypeRepository.existsByEventIdAndName(eventId, name);
    }
}
