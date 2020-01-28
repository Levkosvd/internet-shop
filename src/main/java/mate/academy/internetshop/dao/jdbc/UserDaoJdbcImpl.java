package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    @Inject
    private static RoleDao roleDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(User entity) throws DataProcessingException {
        Long userId = null;
        String insert = "INSERT INTO internet_shop.users(login, password," +
                " account_balance, token, first_name, surname) "
                + "VALUES (?,?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            entity.setToken(UUID.randomUUID().toString());
            setParameters(entity, preparedStatement);
            while (resultSet.next()) {
                userId = resultSet.getLong(1);
            }
            String insertRoles = "INSERT INTO "
                    + "internet_shop.role_user(user_id, role_id) VALUES (?, ?)";
            rolesForeach(insertRoles,entity,userId,connection);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add user", e);
        }
    }

    @Override
    public Optional<User> get(Long id)
            throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.users WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(setUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get  user by id" + id, e);
        }
        return Optional.empty();
    }

    @Override
    public void update(User entity)
            throws DataProcessingException {
        String query = "UPDATE internet_shop.users SET "
                + "login = ?, password = ?, account_balance = ?, "
                + "token = ?, first_name = ?, surname = ? WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(7, entity.getId());
            setParameters(entity, preparedStatement);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update user", e);
        }
    }

    @Override
    public boolean deleteById(Long id)
            throws DataProcessingException {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM internet_shop.users WHERE user_id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 1) {
                logger.info("Success delete");
                return true;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user by id", e);
        }
        return false;
    }

    @Override
    public List<User> getAll()
            throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.users;";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement showPrepStatement = connection.prepareStatement(query);
               ResultSet resultSet = showPrepStatement.executeQuery()) {
            while (resultSet.next()) {
                userList.add(setUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all user", e);
        }
        return userList;
    }

    @Override
    public Optional<User> findByLogin(String login)
            throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.users WHERE login = ?;";
        return setUserWithParameters(query,login);
    }

    @Override
    public Optional<User> findByToken(String token)
            throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.items WHERE token = ?;";
        return setUserWithParameters(query, token);
    }

    private Optional<User> setUserWithParameters(String query, String parameter)
            throws DataProcessingException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(setUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find user by token", e);
        }
        return Optional.empty();
    }

    private static void rolesForeach(String query, User user, Long userId, Connection connection)
            throws DataProcessingException {
        PreparedStatement preparedStatement = null;
        Set<Role> roles = roleDao.getAllRoles();
        for (Role roleOfUser : user.getRoles()) {
            for (Role roleOfDatabase: roles) {
                if (roleOfUser.getRoleName().equals(roleOfDatabase.getRoleName())) {
                    try {
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setLong(1, userId);
                        preparedStatement.setLong(2, roleOfDatabase.getId());
                        preparedStatement.execute();
                    } catch (SQLException e) {
                        throw new DataProcessingException("Error search roles", e);
                    }
                }
            }
        }
    }

    private  User setUser(ResultSet resultSet)
            throws DataProcessingException {
        try {
            User getUser = new User();
            getUser.setLogin(resultSet.getString("login"));
            getUser.setPassword(resultSet.getString("password"));
            getUser.setToken(resultSet.getString("token"));
            getUser.setFirstName(resultSet.getString("first_name"));
            getUser.setSurname(resultSet.getString("surname"));
            getUser.setAccountBalance(resultSet.getDouble("account_balance"));
            getUser.setId(resultSet.getLong("user_id"));
            getUser.setRoles(getAllRolesForUser(getUser.getId()));
            return getUser;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find user", e);
        }

    }

    private static void setParameters(User entity, PreparedStatement preparedStatement)
            throws DataProcessingException {
        try {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setDouble(3, entity.getAccountBalance());
            preparedStatement.setString(4, entity.getToken());
            preparedStatement.setString(5, entity.getFirstName());
            preparedStatement.setString(6, entity.getSurname());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't set parameters", e);
        }
    }

    @Override
    public Set<Role> getAllRolesForUser(Long userId) throws DataProcessingException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Set<Role> allRoles = new HashSet<>();
        String getAllRoles = "SELECT * FROM role_user "
                + "JOIN roles ON role_user.role_id = roles.role_id WHERE user_id = ? ;";
        try {
            preparedStatement = connection.prepareStatement(getAllRoles);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = Role.of(resultSet.getString("role_name"));
                role.setId(resultSet.getLong("role_id"));
                allRoles.add(role);
            }
            return allRoles;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't set parameters", e);
        }
    }
}
