package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AttrsValues;
import model.ClassRoom;
import model.enums.ClassRoomType;

/**
 * Created by junior on 02/10/16.
 */

public class ClassRoomRepository extends AbstractExtendedRepository<ClassRoom> {

    @Override
    public void remove(ClassRoom classRoom) {
        delete("ClassRoom", new AttrsValues.AttrVal("id", classRoom.getId()));
    }

    @Override
    public void update(ClassRoom classRoom) {
        AttrsValues data = new AttrsValues();
        data.add("number", classRoom.getNumber());
        data.add("type", classRoom.getType());
        update("ClassRoom", data, "id = " + classRoom.getId());
    }

    @Override
    public void save(ClassRoom classRoom) {
        if (classRoom.hasId()) {
            update(classRoom);
        } else {
            AttrsValues data = new AttrsValues();
            data.add("number", classRoom.getNumber());
            data.add("type", classRoom.getType());
            create("ClassRoom", data);
        }
    }

    @Override
    public ClassRoom find(int id) {
        ResultSet result = select("ClassRoom", "id = ?", id);
        try {
            if (result.next()) {
                ClassRoomType type = ClassRoomType.valueOf(result.getString("type"));
                return new ClassRoom(id(result), result.getInt("number"), type, createdOn(result));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ClassRoom> findAllBy(String attributeName, int attributeValue) {
        ResultSet result = select("ClassRoom", null, null);
        ArrayList<ClassRoom> classRoomrrayList = new ArrayList<>();
        try {
            while (result.next()) {
                ClassRoomType type = ClassRoomType.valueOf(result.getString("type"));
                classRoomrrayList.add(new ClassRoom(id(result), result.getInt("number"), type, createdOn(result)));
            }
            return classRoomrrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
