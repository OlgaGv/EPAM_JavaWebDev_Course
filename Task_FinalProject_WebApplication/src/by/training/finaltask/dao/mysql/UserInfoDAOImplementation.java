package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.UserInfoDAO;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public final class UserInfoDAOImplementation extends BaseDAO implements UserInfoDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoDAOImplementation.class);

    public UserInfoDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public UserInfo get(Integer userID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getUserInfoDAO"))) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                if (resultset.next()) {
                    return getUserInfo(resultset);
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<UserInfo> getAll(int offset, int rowcount) throws PersistentException {
        List<UserInfo> userInfoList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllUserInfoDAO"))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userInfoList.add(getUserInfo(resultSet));
                }
            }
            return userInfoList;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        } finally {
            LOGGER.debug("getAllUserDAO Query Fulfilled!");
        }
    }

    @Override
    public List<UserInfo> getAllStaff(int offset, int rowcount) throws PersistentException {
        List<UserInfo> userInfoList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllStaffInfoDAO"))) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, rowcount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userInfoList.add(getUserInfo(resultSet));
                }
            }
            return userInfoList;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserInfo> getAllStaffByPhone(long phone, int offset, int rowcount) throws PersistentException {
        List<UserInfo> userInfoList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllStaffInfoByPhoneDAO"))) {
            String phoneStr = "%" + phone + "%";
            preparedStatement.setNString(1, phoneStr);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, rowcount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userInfoList.add(getUserInfo(resultSet));
                }
            }
            return userInfoList;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    /*@Override
    public List<UserInfo> getAllByAdoptionUserID(int userID, int offset, int rowcount) throws PersistentException {
        List<UserInfo> userInfoList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllByAdoptionUserIDDAO"))) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, rowcount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userInfoList.add(getUserInfo(resultSet));
                }
            }
            return userInfoList;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public int getCountByAdoptionUserID(int userID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getCountByAdoptionUserIDDAO"))) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }
*/
    @Override
    public List<UserInfo> getAllStaffByFirstName(String firstname, int offset, int rowcount) throws PersistentException {
        List<UserInfo> userInfoList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllStaffInfoByFirstNameDAO"))) {
            preparedStatement.setNString(1, firstname);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, rowcount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userInfoList.add(getUserInfo(resultSet));
                }
            }
            return userInfoList;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Integer userID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("deleteUserInfoDAO"))) {
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        } finally {
            LOGGER.debug("UserInfo Deleted!");
        }
    }

    @Override
    public int add(UserInfo element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("addUserInfoDAO"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,element.getId());
            preparedStatement.setNString(2,element.getEmail());
            preparedStatement.setNString(3,element.getFirstName());
            preparedStatement.setNString(4,element.getLastName());
            Date sqlDate = new Date(element.getDateOfBirth().getTimeInMillis());
            preparedStatement.setDate(5,sqlDate);
            preparedStatement.setNString(6,element.getAddress());
            preparedStatement.setLong(7,element.getPhone());
            int rows = preparedStatement.executeUpdate();
            return rows;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't add row!\n" + e.getMessage(), e);
        }
    }

    @Override
    public UserInfo get() throws PersistentException {
        return null;
    }

    @Override
    public boolean update(UserInfo element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("updateUserInfoDAO"))) {
            preparedStatement.setInt(1,element.getId());
            preparedStatement.setNString(2,element.getEmail());
            preparedStatement.setNString(3,element.getFirstName());
            preparedStatement.setNString(4,element.getLastName());
            Date sqlDate = new Date(element.getDateOfBirth().getTimeInMillis());
            preparedStatement.setDate(5,sqlDate);
            preparedStatement.setNString(6,element.getAddress());
            preparedStatement.setLong(7,element.getPhone());
            preparedStatement.setInt(8,element.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't update user!\n" +
                    e.getMessage(), e);
        }

    }

    @Override
    public boolean delete(UserInfo element) throws PersistentException {
        return false;
    }

    private UserInfo getUserInfo(ResultSet resultSet) throws SQLException
    {
        GregorianCalendar gregCal = new GregorianCalendar();
        gregCal.setTime(resultSet.getDate("dateofbirth"));
        return new UserInfo(
                resultSet.getInt("user_id"),
                resultSet.getString("email"),
                resultSet.getNString("firstname"),
                resultSet.getNString("lastname"),
                gregCal,
                resultSet.getNString("address"),
                resultSet.getLong("phone")
        );
    }
}
