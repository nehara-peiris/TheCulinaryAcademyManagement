package org.example.academymanagement.dao.custom.impl;

import org.example.academymanagement.dao.custom.UserDAO;
import org.example.academymanagement.entity.User;


import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User search(User entity) throws Exception {
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        return null;
    }

    @Override
    public User exist(String id) throws Exception {
        return null;
    }
}
