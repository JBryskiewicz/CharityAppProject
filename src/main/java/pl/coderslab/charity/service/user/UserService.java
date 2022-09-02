package pl.coderslab.charity.service.user;

import pl.coderslab.charity.domain.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}
