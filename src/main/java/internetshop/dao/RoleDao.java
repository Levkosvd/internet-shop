package internetshop.dao;

import java.util.Set;
import internetshop.model.Role;

public interface RoleDao {

    Role getRole(Long id);

    Set<Role> getAllRoles();
}
