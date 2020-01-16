package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.exeptions.AuthenticationException;
import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {

    Optional<User> findByLogin(String login, String password) throws AuthenticationException;

    Optional<User> findByToken(String token);

}
