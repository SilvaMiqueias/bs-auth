package com.example.auth.dto;

import com.example.auth.model.enumerador.StatusEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private Long id;
    private String title;
    private String description;
    private String cep;
    private String road;
    private String complement;
    private String neighborhood;
    private String locality;
    private String state;
    private String numberLocality;
    private byte[] image;
    private UserDTO user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private StatusEvent status;
    private Boolean active;
    private List<TicketTypeDTO> ticketType;
}
