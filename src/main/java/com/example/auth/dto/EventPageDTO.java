package com.example.auth.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventPageDTO {
    private List<EventDTO> content;
    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
}
