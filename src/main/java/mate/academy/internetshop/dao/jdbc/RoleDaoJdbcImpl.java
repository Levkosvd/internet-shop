package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.Role;
@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role getRole(Long id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String getRoleById = "SELECT * FROM roles WHERE role_id = ?;";
        try {
            preparedStatement = connection.prepareStatement(getRoleById);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            Role role = Role.of(resultSet.getString("role_name"));
            role.setId((long) resultSet.getInt("role_id"));
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Role> getAllRolesForUser(Long userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Set<Role> allRoles = new HashSet<>();
        String getAllRoles = "SELECT * FROM role_user "
                + "JOIN roles ON role_user.role_id = roles.role_id WHERE user_id = ? ;";
        try {
            preparedStatement = connection.prepareStatement(getAllRoles);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Role role = Role.of(resultSet.getString("role_name"));
                role.setId(resultSet.getLong("role_id"));
                allRoles.add(role);
            }
            return allRoles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRoles;
    }

    @Override
    public Set<Role> getAllRoles() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Set<Role> allRoles = new HashSet<>();
        String getAllRoles = "SELECT * FROM roles;";
        try {
            preparedStatement = connection.prepareStatement(getAllRoles);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Role role = Role.of(resultSet.getString("role_name"));
                role.setId(resultSet.getLong("role_id"));
                allRoles.add(role);
            }
            return allRoles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRoles;
    }
}
