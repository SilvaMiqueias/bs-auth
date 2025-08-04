package com.example.auth.model;

import com.example.auth.model.enumerador.NameTicketType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ticket_type")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NameTicketType name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "sale_quantity")
    private Integer saleQuantity;

    @Column
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        active = true;
        saleQuantity = 0;
    }
}
