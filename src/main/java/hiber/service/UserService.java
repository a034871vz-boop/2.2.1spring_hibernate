package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void clear();
    void removeUserById(long id);
    User getUserByCar(int series);
}
