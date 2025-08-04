package com.example.auth.model;

import com.example.auth.model.enumerador.StatusEvent;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private byte[] image;

    @Column(name = "cep")
    private String cep;

    @Column(name = "road")
    private String road;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "locality")
    private String locality;

    @Column(name = "state")
    private String state;

    @Column(name = "number_locality")
    private String numberLocality;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User userId;

    @Enumerated(EnumType.STRING)
    private StatusEvent status;

    @Column
    private Boolean active;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TicketType> ticketType = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = StatusEvent.IN_PROGRESS;
        active = true;
    }
}
