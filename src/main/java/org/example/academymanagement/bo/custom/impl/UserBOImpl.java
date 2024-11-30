package org.example.academymanagement.bo.custom.impl;

import org.example.academymanagement.bo.custom.UserBO;
import org.example.academymanagement.dao.DAOFactory;
import org.example.academymanagement.dao.custom.UserDAO;
import org.example.academymanagement.dao.custom.impl.UserDAOImpl;
import org.example.academymanagement.dto.UserDTO;
import org.example.academymanagement.entity.User;
import org.example.academymanagement.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;

import static org.example.academymanagement.dao.DAOFactory.DAOTypes.USER;

public class UserBOImpl implements UserBO {
    private UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(USER);

    @Override
    public void registerUser(User user) {

    }

    @Override
    public User authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    @Override
    public boolean addUser(UserDTO userDto) throws Exception {
        User user = new User(userDto.getUserId(), userDto.getFullName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getJobRole(), userDto.getUsername(), userDto.getPassword());
        return userDAO.add(user);
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(new UserDTO(user.getUserId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getJobRole(), user.getUsername(), user.getPassword()));
        }
        return userDtoList;
    }

    @Override
    public boolean updateUser(UserDTO userDto) throws Exception {
        User user = new User(userDto.getUserId(), userDto.getFullName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getJobRole(), userDto.getUsername(), userDto.getPassword());
        return userDAO.update(user); // Delegate to DAO layer
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        userDAO.delete(userId);
    }

    @Override
    public UserDTO isUserExist(UserDTO userDto) {
        try {
            User user = (User) userDAO.exist(userDto.getUserId());
            if (user != null) {
                return new UserDTO(user.getUserId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getJobRole(), user.getUsername(), user.getPassword());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }

    public UserDTO getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            return new UserDTO(user.getUserId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(),
                    user.getAddress(), user.getJobRole(), user.getUsername(), user.getPassword());
        }
        return null;
    }

    public boolean verifyPassword(String enteredPassword, String storedPasswordHash) {
        return PasswordUtil.verifyPassword(enteredPassword, storedPasswordHash);
    }

}
