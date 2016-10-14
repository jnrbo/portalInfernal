package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import model.AttrsValues;
import model.ClassRoom;
import model.Lesson;

/**
 * Created by junior and ramon.
 */

public class LessonRepository extends AbstractRepository<Lesson> {

    @Override
    public void save(Lesson lesson) {
        AttrsValues data = new AttrsValues();
        data.add("classroom_id", lesson.getClassRoom().getId());
        data.add("lesson_date", lesson.getLessonDate());
        create("Lesson", data);
    }

    @Override
    public Lesson find(int id) {
        ResultSet result = select("Lesson", "id = ?", id);
        try {
            if (result.next()) {
                ClassRoom classRoom = classRoomRepository.find(result.getInt("classroom_id"));
                return new Lesson(classRoom, result.getDate("lesson_date"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Lesson> findAllBy(String attributeName, int attributeValue) {
        // TODO Auto-generated method stub
        return null;
    }


    private ClassRoomRepository classRoomRepository = new ClassRoomRepository();
}
