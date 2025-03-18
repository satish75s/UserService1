package com.user.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(unique = true) 
    private String email;
    @Column(unique = true) 
    private String employeeId;
    @Column(unique = true) 
    private String mobile;
    @Column(unique = true) 
    private String username;

    // Getters and Setters
}
