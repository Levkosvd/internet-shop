package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exeptions.AuthenticationException;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    @Inject
    private static BucketDao bucketDao;

    @Override
    public void create(User entity)
            throws DataProcessingException {
        userDao.create(entity);
    }

    @Override
    public User get(Long id)
            throws DataProcessingException {
        return userDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find "
                + "user by this User ID!"));
    }

    @Override
    public void update(User entity)
            throws DataProcessingException {
        userDao.update(entity);
    }

    @Override
    public boolean deleteById(Long id)
            throws DataProcessingException {
        return userDao.deleteById(id);
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException, DataProcessingException {
        Optional<User> user = userDao.findByLogin(login);

        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect login or password");
        }
        bucketDao.create(new Bucket(user.get().getId()));
        return user.get();
    }

    @Override
    public Optional<User> findByToken(String token)
            throws DataProcessingException {
        return userDao.findByToken(token);

    }

    @Override
    public List<User> getAll()
            throws DataProcessingException {
        return userDao.getAll();
    }
}
