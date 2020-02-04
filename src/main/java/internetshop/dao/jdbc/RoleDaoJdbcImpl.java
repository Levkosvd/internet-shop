package internetshop.dao.jdbc;

import internetshop.dao.RoleDao;
import internetshop.lib.Dao;
import internetshop.model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role getRole(Long id) {
        String getRoleById = "SELECT * FROM roles WHERE role_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getRoleById)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Role role = Role.of(resultSet.getString("role_name"));
            role.setId((long) resultSet.getInt("role_id"));
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Role> getAllRoles() {
        Set<Role> allRoles = new HashSet<>();
        String getAllRoles = "SELECT * FROM roles;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllRoles)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
