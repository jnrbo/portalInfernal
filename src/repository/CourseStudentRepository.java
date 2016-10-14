package repository;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by junior and ramon.
 */

public class CourseStudentRepository extends AbstractRepository<CourseStudent> {

    @Override
    public void save(CourseStudent courseStudent) {
        AttrsValues data = new AttrsValues();
        data.add("course_id", courseStudent.getCourse().getId());
        data.add("student_id", courseStudent.getStudent().getId());
        create("Course_Student", data);
    }

    @Override
    public CourseStudent find(int id) {
        ResultSet result = select("Course_Student", "id = ?", id);
        try {
            if (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                return new CourseStudent(course, student);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CourseStudent> findAllBy(String attributeName, int attributeValue) {
        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        ResultSet result = select("Course_Student", attributeName + " = ?", attributeValue);
        try {
            while (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                courseStudents.add(new CourseStudent(course, student));
            }
            return courseStudents;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private UserRepository userRepository = new UserRepository();
    private CourseRepository courseRepository = new CourseRepository();
}
