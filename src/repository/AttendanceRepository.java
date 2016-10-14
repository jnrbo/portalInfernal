package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import model.Attendance;
import model.AttrsValues;
import model.Course;
import model.Student;

/**
 * Created by junior and ramon.
 */

public class AttendanceRepository extends AbstractRepository<Attendance> {

    @Override
    public void save(Attendance attendance) {
        AttrsValues data = new AttrsValues();
        data.add("attended", attendance.isAttended());
        data.add("course_id", attendance.getCourse().getId());
        data.add("student_id", attendance.getStudent().getId());
        create("Attendance", data);
    }

    @Override
    public Attendance find(int id) {
        ResultSet result = select("Attendance", "id = ?", id);
        try {
            if (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                return new Attendance(id(result), result.getBoolean("attended"), student, course, createdOn(result));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Attendance> findAllBy(String attributeName, int attributeValue) {
        try {
            ArrayList<Attendance> attendances = new ArrayList<>();
            ResultSet result = select("Attendance", attributeName + " = ?", attributeValue);
            while (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                attendances.add(new Attendance(id(result), result.getBoolean("attended"), student, course, createdOn(result)));
            }
            return attendances;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private CourseRepository courseRepository = new CourseRepository();
    private UserRepository userRepository = new UserRepository();

}
