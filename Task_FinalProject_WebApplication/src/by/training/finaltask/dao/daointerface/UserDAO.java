package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface UserDAO extends DAO<User> {

    User get(Integer userID) throws PersistentException;
    User get(String username) throws PersistentException;
    List<User> getAll() throws PersistentException;
    boolean delete(Integer userID) throws PersistentException;
}