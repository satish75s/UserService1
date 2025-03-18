package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String username;
    private String email;
    private String employeeId;
    private String mobile;
   

    // Getters, setters, constructors
}
