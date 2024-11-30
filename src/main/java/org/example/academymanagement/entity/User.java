package org.example.academymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.academymanagement.util.PasswordUtil;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "job_role", nullable = false)
    private String jobRole;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // Use PasswordUtil for hashing the password before saving
    public void setHashedPassword(String plainPassword) {
        this.password = PasswordUtil.hashPassword(plainPassword);
    }

    // Use PasswordUtil for verifying the password during login
    public boolean verifyPassword(String plainPassword) {
        return PasswordUtil.verifyPassword(plainPassword, this.password);
    }
}
