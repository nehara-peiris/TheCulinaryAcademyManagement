package org.example.academymanagement.bo.custom;

import org.example.academymanagement.bo.SuperBO;
import org.example.academymanagement.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    boolean addUser(UserDTO userDto) throws Exception;

    List<UserDTO> getAllUsers() throws Exception;

    boolean updateUser(UserDTO userDto) throws Exception;


    void deleteUser(String userId) throws Exception;

    boolean isUserExist(String userId) throws Exception;
}
