package internetshop.service;

import internetshop.exeptions.AuthenticationException;
import internetshop.exeptions.DataProcessingException;
import internetshop.model.User;

public interface UserService extends GenericService<User, Long> {

    User login(String login, String password)
            throws AuthenticationException, DataProcessingException;

}
