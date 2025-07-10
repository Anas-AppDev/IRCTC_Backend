package com.example.irctcBackend.services;

import com.example.irctcBackend.dto.BookingDTO;
import com.example.irctcBackend.dto.TrainDTO;
import com.example.irctcBackend.dto.UserDTO;
import com.example.irctcBackend.entity.BookingEntity;
import com.example.irctcBackend.entity.TrainEntity;
import com.example.irctcBackend.entity.UserEntity;
import com.example.irctcBackend.exception.ResourceNotFoundException;
import com.example.irctcBackend.repo.BookingRepo;
import com.example.irctcBackend.repo.TrainRepo;
import com.example.irctcBackend.repo.UserRepo;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrainRepo trainRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public BookingDTO bookSeat(int userId, int trainId){

        TrainEntity trainEntity = trainRepo.findById(trainId).orElseThrow(
                () -> new ResourceNotFoundException("Train not found with id "+trainId)
        );

        UserEntity userEntity = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id "+userId)
        );

        // Checking Seat Availability
        if (trainEntity.getAvailableSeats()<=0){
            throw new ResourceNotFoundException("Seats not available");
        }
        else{
            trainEntity.setAvailableSeats(trainEntity.getAvailableSeats()-1);
            trainRepo.save(trainEntity);
        }

        BookingEntity bookingEntity = BookingEntity.builder()
                .trainEntity(trainEntity)
                .userEntity(userEntity)
                .build();
        BookingEntity savedBookingEntity = bookingRepo.save(bookingEntity);
        BookingDTO bookingDTO =  modelMapper.map(savedBookingEntity, BookingDTO.class);

        bookingDTO.setTrainDTO(modelMapper.map(trainEntity, TrainDTO.class));
        bookingDTO.setUserDTO(modelMapper.map(userEntity, UserDTO.class));
        return bookingDTO;
    }

    public BookingDTO getTicketDetails(int ticketId){
        BookingEntity bookingEntity = bookingRepo.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket not found with id "+ticketId)
        );
        BookingDTO bookingDTO = modelMapper.map(bookingEntity, BookingDTO.class);
        bookingDTO.setUserDTO(modelMapper.map(bookingEntity.getUserEntity(), UserDTO.class));
        bookingDTO.setTrainDTO(modelMapper.map(bookingEntity.getTrainEntity(), TrainDTO.class));

        return bookingDTO;
    }
}
