package com.example.irctcBackend.dto;

import com.example.irctcBackend.entity.RoleEntity;
import lombok.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId;
    private String name;
    private String email;
    private String password;

    private List<RoleDTO> roleList = new ArrayList<>();

}
