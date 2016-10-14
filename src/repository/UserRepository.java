package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AttrsValues;
import model.User;
import model.enums.UserType;

/**
 * Created by junior and ramon.
 */

public class UserRepository extends AbstractExtendedRepository<User> {

    public User login(int id, String password) {
        ResultSet result = select("User", "id = ? AND password = ?", id, password);
        return buildUser(result);
    }

    @Override
    public void remove(User user) {
        delete("User", new AttrsValues.AttrVal("id", user.getId()));
    }

    @Override
    public void update(User user) {
        AttrsValues data = new AttrsValues();
        data.add("name", user.getName());
        data.add("password", user.getPassword());
        update("User", data, "id = " + user.getId());
    }

    @Override
    public void save(User user) {
        if (user.hasId()) {
            update(user);
        } else {
            AttrsValues data = new AttrsValues();
            data.add("name", user.getName());
            data.add("password", user.getPassword());
            data.add("type", user.getType());
            create("User", data);
        }
    }

    @Override
    public User find(int id) {
        ResultSet result = select("User", "id = ?", id);
        return buildUser(result);
    }

    private User buildUser(ResultSet result) {
        try {
            if (result.next()) {
                return new User(id(result), name(result), result.getString("password"), UserType.valueOf(result.getString("type")), createdOn(result));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> findAllBy(String attributeName, int attributeValue) {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet result = select("User", null, 0);
            while (result.next()) {
                users.add(new User(id(result), name(result), result.getString("password"), UserType.valueOf(result.getString("type")), createdOn(result)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }
}
