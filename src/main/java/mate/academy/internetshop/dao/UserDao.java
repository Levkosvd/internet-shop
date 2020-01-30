package mate.academy.internetshop.dao;

import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.exeptions.AuthenticationException;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {

    Optional<User> findByLogin(String login)
            throws AuthenticationException, DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;

    Set<Role> getAllRolesForUser(Long userId) throws DataProcessingException;

}
