package com.example.irctcBackend.repo;

import com.example.irctcBackend.dto.TrainDTO;
import com.example.irctcBackend.entity.TrainEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepo extends JpaRepository<TrainEntity, Integer> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TrainEntity> findById(int trainId);

    Optional<List<TrainEntity>> findBySourceAndDestination(String source, String destination);

}
