package internetshop.dao;

import internetshop.model.Role;
import java.util.Set;

public interface RoleDao {

    Role getRole(Long id);

    Set<Role> getAllRoles();
}
