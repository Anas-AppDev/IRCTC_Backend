package com.example.irctcBackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "user_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookings = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roleList = new ArrayList<>();








    // UserEntity fetching from Username from database

    // Returns the roles/authorities of the user for authorization (e.g., ROLE_USER, ROLE_ADMIN)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> rolesList = roleList.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName()))
                .collect(Collectors.toList());
        return rolesList;
    }

    // Returns the username (email in this case) used for authentication
    @Override
    public String getUsername() {
        return email;
    }

    // Indicates if the user's account is not expired (always true here)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indicates if the user's account is not locked (always true here)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indicates if the user's credentials (password) are not expired (always true here)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indicates if the user's account is enabled (always true here)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
