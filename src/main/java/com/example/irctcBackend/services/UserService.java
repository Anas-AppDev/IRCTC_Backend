package com.example.irctcBackend.services;

import com.example.irctcBackend.dto.RoleDTO;
import com.example.irctcBackend.dto.UserDTO;
import com.example.irctcBackend.entity.RoleEntity;
import com.example.irctcBackend.entity.UserEntity;
import com.example.irctcBackend.exception.ResourceNotFoundException;
import com.example.irctcBackend.repo.RoleRepo;
import com.example.irctcBackend.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO){

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        RoleEntity roleEntity = roleRepo.findById(1).orElseThrow(
                () -> new ResourceNotFoundException("Role not found with id : "+ 1)
        );
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.getRoleList().add(roleEntity);
        UserEntity savedUserEntity = userRepo.save(userEntity);

        UserDTO userDTO1 = modelMapper.map(savedUserEntity, UserDTO.class);
        userDTO1.setRoleList(
                savedUserEntity.getRoleList().stream().map(
                        roleEntity1 -> modelMapper.map(roleEntity1, RoleDTO.class)
                ).collect(Collectors.toList())
        );

        return userDTO1;
    }

    public UserDTO createAdmin(UserDTO userDTO){

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        RoleEntity roleEntity = roleRepo.findById(2).orElseThrow(
                () -> new ResourceNotFoundException("Role not found with id : "+ 2)
        );
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.getRoleList().add(roleEntity);
        UserEntity savedUserEntity = userRepo.save(userEntity);

        UserDTO userDTO1 = modelMapper.map(savedUserEntity, UserDTO.class);
        userDTO1.setRoleList(
                savedUserEntity.getRoleList().stream().map(
                        roleEntity1 -> modelMapper.map(roleEntity1, RoleDTO.class)
                ).collect(Collectors.toList())
        );

        return userDTO1;
    }

    public UserDTO updateUser(UserDTO userDTO, int userId){

        Optional<UserEntity> optionalUserEntity = userRepo.findById(userId);
        if (optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setName(userDTO.getName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setPassword(userDTO.getPassword());

            UserEntity savedUserEntity = userRepo.save(userEntity);
            return modelMapper.map(savedUserEntity, UserDTO.class);
        }
        else{
            throw new ResourceNotFoundException("User id not found");
        }
    }

    public void deleteUser(int userID){
        UserEntity userEntity = userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User id not found"));
        userRepo.delete(userEntity);
    }

    public UserDTO getUser(int userId){
        UserEntity userEntity = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id not found"));
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        userDTO.setRoleList(userEntity.getRoleList().stream().map(
                roleEntity -> modelMapper.map(roleEntity, RoleDTO.class)
        ).collect(Collectors.toList()));
        return userDTO;
    }

    public List<UserDTO> getAllUser(){
        List<UserEntity> list = userRepo.findAll();
        List<UserDTO> dtoList = list.stream().map(userEntity -> {
            UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
            userDTO.setRoleList(userEntity.getRoleList().stream().map(
                    roleEntity -> modelMapper.map(roleEntity, RoleDTO.class)
            ).collect(Collectors.toList()));
            return userDTO;
        }).collect(Collectors.toList());
        return dtoList;
    }
}
