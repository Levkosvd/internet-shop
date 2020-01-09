package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import java.util.List;

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
        return userDao.get(id).get();
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
    public List<User> getAll() {
        return userDao.getAll();
    }
}
