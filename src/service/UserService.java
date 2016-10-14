package service;

import javax.annotation.Resource;

import model.PermissionException;
import model.User;
import model.enums.UserType;
import repository.UserRepository;

public class UserService {


    public User login(int id, String password) {
        User user = repository.login(id, password);

        return user;

    }


    public void createUser(String name, String password, String type, int loggedUser) {
        User logged = repository.find(loggedUser);
        verifyAdmin(logged);
        User user = new User(name, password, UserType.valueOf(type));
        repository.save(user);
    }

    public void deleteUser(int userId, int loggedUser) {
        User toDelete = repository.find(userId);
        User logged = repository.find(loggedUser);
        verifyAdmin(logged);
        repository.remove(toDelete);
    }

    public void updateUser(int userId, String password, String newName, int loggedUser) {
        User logged = repository.find(loggedUser);
        verifyAdmin(logged);
        User user = repository.find(userId);
        user.setName(newName);
        user.setPassword(password);
        repository.save(user);
    }

    public User findUser(int userId) {
        return repository.find(userId);
    }

    private void verifyAdmin(User user) {
        if (!user.isAdmin()) {
            throw new PermissionException();
        }
    }

    private UserRepository repository = new UserRepository();
}