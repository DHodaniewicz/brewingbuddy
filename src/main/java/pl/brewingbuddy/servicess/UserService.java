package pl.brewingbuddy.servicess;

import pl.brewingbuddy.entities.User;

public interface UserService {
    User findByUserName(String name);

    void saveUser(User user);
}
