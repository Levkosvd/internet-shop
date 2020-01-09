package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.data.Storage;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    private static long idGenerator = 0L;
    @Override
    public void create(User user) {
        user.setId(++idGenerator);
        Storage.users.add(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Can't find order with id - " + id)));
    }

    @Override
    public void update(User user) {
        Optional<User> currentOrder = Optional.ofNullable(Storage.users.stream()
                .filter(s -> s.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user by id - " + user.getId())));

        Storage.users.set(Storage.users.indexOf(currentOrder.get()), user);
    }

    @Override
    public boolean deleteById(Long id) {
        User targetItem = Storage.users.stream().filter((s) -> s.getId().equals(id)).findFirst().get();
        return Storage.users.remove(targetItem);
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }
}
