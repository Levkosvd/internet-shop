package internetshop.dao;

import java.util.Optional;
import java.util.Set;
import internetshop.exeptions.AuthenticationException;
import internetshop.exeptions.DataProcessingException;
import internetshop.model.Role;
import internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {

    Optional<User> findByLogin(String login)
            throws AuthenticationException, DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;

    Set<Role> getAllRolesForUser(Long userId) throws DataProcessingException;

}
