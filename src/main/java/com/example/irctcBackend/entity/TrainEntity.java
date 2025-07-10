package com.example.irctcBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "train_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainId;

    private String trainName;
    private int availableSeats;
    private int totalSeats;
    private String source;
    private String destination;

    @OneToMany(mappedBy = "trainEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookings = new ArrayList<>();
}
