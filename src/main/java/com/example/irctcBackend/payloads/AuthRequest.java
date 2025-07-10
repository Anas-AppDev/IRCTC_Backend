package com.example.irctcBackend.payloads;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    private String email;
    private String password;

}
