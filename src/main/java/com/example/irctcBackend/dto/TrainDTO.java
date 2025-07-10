package com.example.irctcBackend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {

    private int trainId;
    private String trainName;
    private int availableSeats;
    private int totalSeats;
    private String source;
    private String destination;
}
