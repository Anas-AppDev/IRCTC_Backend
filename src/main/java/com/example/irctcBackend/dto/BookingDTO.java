package com.example.irctcBackend.dto;

import com.example.irctcBackend.entity.TrainEntity;
import com.example.irctcBackend.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private int bookingId;
    private TrainDTO trainDTO;
    private UserDTO userDTO;
}
