package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();
    void add(User user);
    void delete(int id);
    void edit(User user);
    User getById(int id);

}
