package com.example.irctcBackend.controller;

import com.example.irctcBackend.dto.BookingDTO;
import com.example.irctcBackend.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/booking/")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    ResponseEntity<BookingDTO> bookSeat(@RequestParam("userId") int userId, @RequestParam("trainId") int trainId){
        BookingDTO bookingDTO1 = bookingService.bookSeat(userId, trainId);
        return new ResponseEntity<>(bookingDTO1, HttpStatus.CREATED);
    }

    @GetMapping("{ticketId}")
    ResponseEntity<BookingDTO> getTicketDetails(@PathVariable int ticketId){

        BookingDTO bookingDTO1 = bookingService.getTicketDetails(ticketId);
        return new ResponseEntity<>(bookingDTO1, HttpStatus.OK);
    }
}
