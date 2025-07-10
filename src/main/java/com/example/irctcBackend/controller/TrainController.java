package com.example.irctcBackend.controller;

import com.example.irctcBackend.dto.TrainDTO;
import com.example.irctcBackend.payloads.ApiResponse;
import com.example.irctcBackend.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/train/")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping()
    ResponseEntity<TrainDTO> createTrain(@RequestBody TrainDTO trainDTO){
        TrainDTO trainDTO1 = trainService.createTrain(trainDTO);
        return new ResponseEntity<>(trainDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("{trainId}")
    ResponseEntity<ApiResponse> deleteTrain(@PathVariable int trainId){
        trainService.deleteTrain(trainId);
        return new ResponseEntity<>(new ApiResponse(true, "Train deleted with id "+trainId), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<TrainDTO>> getAllTrains(){
        List<TrainDTO> trainDTOS = trainService.getAllTrains();
        return new ResponseEntity<>(trainDTOS, HttpStatus.OK);
    }

    @GetMapping("seats")
    ResponseEntity<List<TrainDTO>> getSeatAvailability(@RequestParam("source") String source, @RequestParam("destination") String destination){
        List<TrainDTO> trainDTOS = trainService.getSeatAvailability(source, destination);
        return new ResponseEntity<>(trainDTOS, HttpStatus.OK);
    }
}
