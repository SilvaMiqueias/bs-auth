package com.example.auth.dto;

import com.example.auth.model.enumerador.NameTicketType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeDTO {

    private Long id;
    private NameTicketType name;
    private BigDecimal price;
    private Integer totalQuantity;
    private Integer saleQuantity;
    private Boolean active;
    private EventDTO event;
    private LocalDateTime createdAt;
}
