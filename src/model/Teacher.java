package model;

import java.util.Date;

import model.enums.UserType;

/**
 * Created by junior and ramon.
 */
public class Teacher extends User {

    public Teacher(User user) {
        this(user.getId(), user.getName(), user.getPassword(), user.getCreatedOn());
    }

    public Teacher(String name, String password) {
        super(name, password, UserType.TEACHER);
    }

    public Teacher(int id, String name, String password, Date createdOn) {
        super(id, name, password, UserType.TEACHER, createdOn);
    }
}
