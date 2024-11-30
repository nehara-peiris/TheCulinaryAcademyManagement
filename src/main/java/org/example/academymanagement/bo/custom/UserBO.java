package org.example.academymanagement.bo.custom;

import org.example.academymanagement.bo.SuperBO;
import org.example.academymanagement.dto.UserDTO;
import org.example.academymanagement.entity.User;

import java.util.List;

public interface UserBO extends SuperBO {

    void registerUser(User user);

    public User authenticate(String username, String password);

    boolean addUser(UserDTO userDto) throws Exception;

    List<UserDTO> getAllUsers() throws Exception;

    boolean updateUser(UserDTO userDto) throws Exception;


    void deleteUser(String userId) throws Exception;

    UserDTO isUserExist(UserDTO userDto);

    UserDTO getUserByUsername(String username) ;

    boolean verifyPassword(String enteredPassword, String storedPasswordHash);

    }
