package model;

/**
* Created by junior and ramon.
 */
public class CourseLesson extends Entity {

    private Course course;
    private Lesson lesson;

    public CourseLesson(Course course, Lesson lesson) {
        this.lesson = lesson;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
