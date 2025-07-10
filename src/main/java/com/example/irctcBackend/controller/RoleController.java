package com.example.irctcBackend.controller;

import com.example.irctcBackend.dto.RoleDTO;
import com.example.irctcBackend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping()
    ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO){
        RoleDTO roleDTO1 = roleService.createRole(roleDTO);
        return new ResponseEntity<>(roleDTO1, HttpStatus.CREATED);
    }

    @GetMapping()
    ResponseEntity<List<RoleDTO>> getRoles(){
        List<RoleDTO> roleDTOS = roleService.getRoles();
        return new ResponseEntity<>(roleDTOS, HttpStatus.OK);
    }
}
