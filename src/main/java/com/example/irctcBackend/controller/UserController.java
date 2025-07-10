package com.example.irctcBackend.controller;

import com.example.irctcBackend.dto.RoleDTO;
import com.example.irctcBackend.dto.UserDTO;
import com.example.irctcBackend.entity.RoleEntity;
import com.example.irctcBackend.payloads.ApiResponse;
import com.example.irctcBackend.repo.RoleRepo;
import com.example.irctcBackend.services.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userDTO1 = userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
    }

    @PostMapping("admin")
    ResponseEntity<UserDTO> createAdmin(@RequestBody UserDTO userDTO){
        UserDTO userDTO1 = userService.createAdmin(userDTO);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
    }

    @PutMapping("{userId}")
    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable int userId){
        UserDTO userDTO1 = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    ResponseEntity<ApiResponse> deletUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse(true, "User deleted with id "+userId), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    ResponseEntity<UserDTO> getUser(@PathVariable int userId){
        UserDTO userDTO1 = userService.getUser(userId);
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> userDTO1 = userService.getAllUser();
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }
}
