package org.example.academymanagement.dao.custom;

import org.example.academymanagement.dao.CrudDAO;
import org.example.academymanagement.entity.User;

public interface UserDAO extends CrudDAO<User> {

    User authenticate(String username, String plainPassword);

    User getUserByUsername(String username) ;

}
