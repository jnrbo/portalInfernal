package repository;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by junior and ramon.
 */

public class LessonStudentRepository extends AbstractRepository<LessonStudent> {

    @Override
    public void save(LessonStudent lessonStudent) {
        AttrsValues data = new AttrsValues();
        data.add("student_id", lessonStudent.getStudent().getId());
        data.add("lesson_id", lessonStudent.getLesson().getId());
        create("Lesson_Student", data);
    }

    @Override
    public LessonStudent find(int id) {
        ResultSet result = select("Lesson_Student", "id = ?", id);
        try {
            if (result.next()) {
                Lesson lesson = lessonRepository.find(result.getInt("lesson_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                return new LessonStudent(lesson, student);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<LessonStudent> findAllBy(String attributeName, int attributeValue) {
        ArrayList<LessonStudent> lessonStudents = new ArrayList<>();
        ResultSet result = select("Lesson_Student", attributeName + " = ?", attributeValue);
        try {
            while (result.next()) {
                Lesson lesson = lessonRepository.find(result.getInt("lesson_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                lessonStudents.add(new LessonStudent(lesson, student));
            }
            return lessonStudents;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private UserRepository userRepository = new UserRepository();
    private LessonRepository lessonRepository = new LessonRepository();
}
