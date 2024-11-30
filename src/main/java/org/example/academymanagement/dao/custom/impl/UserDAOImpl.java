package org.example.academymanagement.dao.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.academymanagement.dao.custom.UserDAO;
import org.example.academymanagement.entity.User;
import org.example.academymanagement.util.PasswordUtil;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;


    @Override
    public User authenticate(String username, String password) {
        try {
            // Retrieve the user by username
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            List<User> result = query.getResultList();

            if (result.isEmpty()) {
                return null;
            }

            User user = result.get(0);

            // Verify password using PasswordUtil
            if (PasswordUtil.verifyPassword(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            // Log the exception (optional) and return null
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean add(User entity) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(User entity) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(String id) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User search(User entity) throws Exception {
        // You can implement a search by specific criteria (e.g., by userId or username)
        return entityManager.find(User.class, entity.getUserId());
    }

    @Override
    public List<User> getAll() throws Exception {
        try {
            TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User exist(String id) throws Exception {
        return entityManager.find(User.class, id);
    }

    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getResultStream().findFirst().orElse(null);
    }
}
