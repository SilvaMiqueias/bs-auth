package com.example.auth.mapper;


import com.example.auth.dto.TicketTypeDTO;
import com.example.auth.model.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketTypeMapper {

    TicketTypeMapper INSTANCE = Mappers.getMapper(TicketTypeMapper.class);

    @Mapping(target = "event", ignore = true)
    TicketTypeDTO ticketTypeToTicketTypeDTO(TicketType ticketType);

    TicketType  ticketTypeDTOToTicketType(TicketTypeDTO ticketTypeDTO);
}
