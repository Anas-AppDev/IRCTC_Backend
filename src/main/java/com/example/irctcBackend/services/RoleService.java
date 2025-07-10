package com.example.irctcBackend.services;

import com.example.irctcBackend.dto.RoleDTO;
import com.example.irctcBackend.entity.RoleEntity;
import com.example.irctcBackend.repo.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {


    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO createRole(RoleDTO roleDTO){
        RoleEntity roleEntity = modelMapper.map(roleDTO, RoleEntity.class);
        RoleEntity savedRoleEntity = roleRepo.save(roleEntity);
        return modelMapper.map(savedRoleEntity, RoleDTO.class);
    }

    public List<RoleDTO> getRoles(){
        List<RoleEntity> roleEntityList = roleRepo.findAll();
        List<RoleDTO> roleDTOS = roleEntityList.stream().map(
                roleEntity -> modelMapper.map(roleEntity, RoleDTO.class)
        ).collect(Collectors.toList());
        return roleDTOS;
    }
}
