package model;

import java.util.Date;

import model.enums.UserType;

/**
 * Created by junior and ramon.
 */
public class User extends Entity {

    private String name;
    private String password;
    private UserType type;

    public User(String name, String password, UserType type) {
        super();
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public User(int id, String name, String password,  UserType type, Date createdOn) {
        super(id, createdOn);
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public String getTypeLabel() {
        return getType().getLabel();
    }

    public boolean isAdmin() {
        return UserType.ADMIN.equals(this.getType());
    }

    public boolean isTeacher() {
        return UserType.TEACHER.equals(this.getType());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}