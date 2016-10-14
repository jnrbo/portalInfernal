package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import model.AttrsValues;
import model.Course;
import model.Teacher;

/**
 * Created by junior and ramon.
 */

public class CourseRepository extends AbstractExtendedRepository<Course> {

    @Override
    public void remove(Course course) {
        delete("Course", new AttrsValues.AttrVal("id", course.getId()));
    }

    @Override
    public void update(Course course) {
        AttrsValues data = new AttrsValues();
        data.add("name", course.getName());
        data.add("teacher_id", course.getTeacher().getId());
        update("Course", data, "id = " + course.getId());
    }

    @Override
    public void save(Course course) {
        if (course.hasId()) {
            update(course);
        } else {
            AttrsValues data = new AttrsValues();
            data.add("name", course.getName());
            data.add("teacher_id", course.getTeacher().getId());
            create("Course", data);
        }
    }

    @Override
    public Course find(int id) {
        ResultSet result = select("Course", "id = ?", id);
        try {
            if (!result.next()) {
                return null;
            }
            Teacher teacher = new Teacher(userRepository.find(result.getInt("teacher_id")));
            return new Course(id(result), name(result), teacher, createdOn(result), null);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Course> findAllBy(String attributeName, int attributeValue) {
        ArrayList<Course> courses = new ArrayList<>();
        ResultSet result = select("Course", attributeName + " = ?", attributeValue);
        try {
            if (result != null) {
                while (result.next()) {
                    Teacher teacher = new Teacher(userRepository.find(result.getInt("teacher_id")));
                    courses.add(new Course(id(result), name(result), teacher, createdOn(result), null));
                }
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UserRepository userRepository = new UserRepository();
}