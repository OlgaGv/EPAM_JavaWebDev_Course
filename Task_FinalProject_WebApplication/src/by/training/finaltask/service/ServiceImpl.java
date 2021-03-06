package by.training.finaltask.service;

import by.training.finaltask.dao.mysql.*;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


abstract public class ServiceImpl implements Service {

    protected Connection connection;
    private Logger logger = LogManager.getLogger(ServiceImpl.class);

    ServiceImpl(Connection aliveConnection)
    {
        this.connection = aliveConnection;
    }

    protected void commit() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("It is impossible to commit transaction", e);
            throw new PersistentException(e.getMessage(),e);
        }
    }

    protected void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("It is impossible to rollback transaction", e);
            throw new PersistentException(e.getMessage(),e);
        }
    }

    protected BaseDAO createDao(DAOEnum daoEnum) {
        switch (daoEnum) {
            case SHELTER:
                return new ShelterDAOImplementation(connection);
            case ADOPTION:
                return new AdoptionDAOImplementation(connection);
            case PET:
                return new PetDAOImplementation(connection);
            case USER:
                return new UserDAOImplementation(connection);
            case USERINFO:
                return new UserInfoDAOImplementation(connection);
            case BREED:
                return new BreedDAOImplementation(connection);
            default:
                return null;
        }
    }
}
