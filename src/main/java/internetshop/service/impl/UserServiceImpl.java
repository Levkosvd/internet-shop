package internetshop.service.impl;

import internetshop.dao.BucketDao;
import internetshop.dao.UserDao;
import internetshop.exeptions.AuthenticationException;
import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Bucket;
import internetshop.model.User;
import internetshop.service.UserService;
import internetshop.util.HashUtil;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    @Inject
    private static BucketDao bucketDao;

    @Override
    public void create(User entity)
            throws DataProcessingException {
        entity.setPassword(HashUtil.hashPassword(entity.getPassword(), entity.getSalt()));
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
        if (user.isEmpty() || !user.get().getPassword()
                .equals(HashUtil.hashPassword(password, user.get().getSalt()))) {
            throw new AuthenticationException("Incorrect login or password");
        }
        bucketDao.create(new Bucket(user.get().getId()));
        return user.get();
    }

    @Override
    public List<User> getAll()
            throws DataProcessingException {
        return userDao.getAll();
    }
}
