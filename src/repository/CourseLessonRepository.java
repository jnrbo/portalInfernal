package repository;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by junior and ramon.
 */

public class CourseLessonRepository extends AbstractRepository<CourseLesson> {

    @Override
    public void save(CourseLesson courseLesson) {
        AttrsValues data = new AttrsValues();
        data.add("course_id", courseLesson.getCourse().getId());
        data.add("lesson_id", courseLesson.getLesson().getId());
        create("Course_Lesson", data);
    }

    @Override
    public CourseLesson find(int id) {
        ResultSet result = select("Course_Lesson", "id = ?", id);
        try {
            if (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Lesson lesson = lessonRepository.find(result.getInt("lesson_id"));
                return new CourseLesson(course, lesson);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CourseLesson> findAllBy(String attributeName, int attributeValue) {
        ArrayList<CourseLesson> courseLessons = new ArrayList<>();
        ResultSet result = select("Course_Lesson", attributeName + " = ?", attributeValue);
        try {
            while (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Lesson lesson = lessonRepository.find(result.getInt("lesson_id"));
                courseLessons.add(new CourseLesson(course, lesson));
            }
            return courseLessons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private CourseRepository courseRepository = new CourseRepository();
    private LessonRepository lessonRepository = new LessonRepository();
}
