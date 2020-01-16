package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exeptions.AuthenticationException;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public void create(User entity) {
        userDao.create(entity);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find "
                + "user by this User ID!"));
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean delete(User entity) {
        return userDao.delete(entity);
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        Optional<User> user = userDao.findByLogin(login);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect login or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userDao.findByToken(token);

    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
