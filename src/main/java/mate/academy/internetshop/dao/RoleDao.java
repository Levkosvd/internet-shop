package mate.academy.internetshop.dao;

import java.util.Set;
import mate.academy.internetshop.model.Role;

public interface RoleDao {

    Role getRole(Long id);

    Set<Role> getAllRoles();


}
