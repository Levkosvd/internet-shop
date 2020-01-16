package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.data.Storage;
import mate.academy.internetshop.exeptions.AuthenticationException;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    private static long idGenerator = 0L;

    @Override
    public void create(User user) {
        user.setId(++idGenerator);
        user.setToken(UUID.randomUUID().toString());
        Storage.users.add(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id - "
                        + id)));
    }

    @Override
    public void update(User user) {
        Optional<User> currentOrder = Optional.ofNullable(Storage.users.stream()
                .filter(s -> s.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user by id - "
                        + user.getId())));

        Storage.users.set(Storage.users.indexOf(currentOrder.get()), user);
    }

    @Override
    public boolean deleteById(Long id) {
        User targetItem = Storage.users.stream().filter((s) -> s.getId().equals(id))
                .findFirst().get();
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

    @Override
    public Optional<User> findByLogin(String login)
            throws AuthenticationException {
        return Storage.users.stream()
                .filter(l -> l.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public Optional<User> findByToken(String token) {
        return  Storage.users.stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }
}
