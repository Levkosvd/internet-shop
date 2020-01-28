package mate.academy.internetshop.service;

import java.util.Optional;
import mate.academy.internetshop.exeptions.AuthenticationException;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {

    User login(String login, String password)
            throws AuthenticationException, DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;
}
