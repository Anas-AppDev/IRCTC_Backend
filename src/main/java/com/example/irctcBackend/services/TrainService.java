package com.example.irctcBackend.services;

import com.example.irctcBackend.dto.TrainDTO;
import com.example.irctcBackend.entity.TrainEntity;
import com.example.irctcBackend.exception.ResourceNotFoundException;
import com.example.irctcBackend.repo.TrainRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;

    @Autowired
    private ModelMapper modelMapper;

    public TrainDTO createTrain(TrainDTO trainDTO){
        TrainEntity trainEntity = modelMapper.map(trainDTO, TrainEntity.class);
        TrainEntity savedTrainEntity = trainRepo.save(trainEntity);
        return modelMapper.map(savedTrainEntity, TrainDTO.class);
    }

    @Transactional
    public void deleteTrain(int trainId){
        TrainEntity trainEntity = trainRepo.findById(trainId).orElseThrow(
                () -> new ResourceNotFoundException("Train not found with id "+trainId)
        );
        trainRepo.delete(trainEntity);
    }

    public List<TrainDTO> getAllTrains(){
        List<TrainEntity> trainEntities = trainRepo.findAll();
        List<TrainDTO> trainDTOS = trainEntities.stream().map(
                trainEntity -> modelMapper.map(trainEntity, TrainDTO.class)
        ).collect(Collectors.toList());
        return trainDTOS;
    }

    public List<TrainDTO> getSeatAvailability(String source, String destination){

        List<TrainEntity> trainEntities = trainRepo.findBySourceAndDestination(source, destination).orElseThrow(
                () -> new ResourceNotFoundException("Trains not found with source : "+source + " and destination : "+destination)
        );
        List<TrainDTO> trainDTOS = trainEntities.stream().map(
                trainEntity -> modelMapper.map(trainEntity, TrainDTO.class)
        ).collect(Collectors.toList());
        return trainDTOS;
    }
}
