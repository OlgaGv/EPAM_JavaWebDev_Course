package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface UserDAO extends DAO<User> {

    User get(Integer userID) throws PersistentException;
    User get(String username) throws PersistentException;
    User get(String user, String pass) throws PersistentException;
    List<User> getAll(int offset, int rowcount) throws PersistentException;
    boolean delete(Integer userID) throws PersistentException;
    int getAmountOfAllStaff() throws PersistentException;
    int getAmountOfAllStaffByFirstName(String firstname) throws PersistentException;
    int getAmountOfAllStaffByPhone(long phone) throws PersistentException;
    List<User> getAllStaff(int offset, int rowcount) throws PersistentException;
    List<User> getAllStaffByFirstName(String firstname, int offset, int rowcount) throws PersistentException;
    List<User> getAllStaffByPhone(long phone, int offset, int rowcount) throws PersistentException;

}
