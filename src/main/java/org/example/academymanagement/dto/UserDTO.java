package org.example.academymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String jobRole;
    private String username;
    private String password;
}
