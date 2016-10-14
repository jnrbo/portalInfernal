package model;

import java.util.Date;

import model.enums.UserType;

/**
 * Created by junior and ramon.
 */
public class Student extends User {

    public Student(User user) {
        this(user.getId(), user.getName(), user.getPassword(), user.getCreatedOn());
    }

    public Student(String name, String password) {
        super(name, password, UserType.STUDENT);
    }

    public Student(int id, String name, String password, Date createdOn) {
        super(id, name, password, UserType.STUDENT, createdOn);
    }

    @Override
    public String toString() {
        return "Estudante " + getName();
    }
}
