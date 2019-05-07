package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface UserInfoDAO extends DAO<UserInfo> {

    UserInfo get(Integer userID) throws PersistentException;
    List<UserInfo> getAll() throws PersistentException;
    boolean delete(Integer userID) throws PersistentException;

}
