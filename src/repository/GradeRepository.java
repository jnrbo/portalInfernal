package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import model.AttrsValues;
import model.Course;
import model.Grade;
import model.Student;
import model.enums.Bimester;

/**
 * Created by junior and ramon.
 */

public class GradeRepository extends AbstractRepository<Grade> {

    @Override
    public void save(Grade grade) {
        AttrsValues data = new AttrsValues();
        data.add("score", grade.getScore());
        data.add("bimester", grade.getBimester());
        data.add("course_id", grade.getCourse().getId());
        data.add("student_id", grade.getStudent().getId());
        create("Grade", data);
    }

    @Override
    public Grade find(int id) {
        ResultSet result = select("Grade", "id = ?", id);
        try {
            if (result.next()) {
                Bimester bimester = Bimester.valueOf(result.getString("bimester"));
                Course course = courseRepository.find(result.getInt("course_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                return new Grade(result.getInt("score"), student, course, bimester);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Grade> findAllBy(String attributeName, int attributeValue) {
        // TODO Auto-generated method stub
        return null;
    }


    private CourseRepository courseRepository = new CourseRepository();
    private UserRepository userRepository = new UserRepository();
}
