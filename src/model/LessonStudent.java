package model;

/**
 * Created by junior on 07/10/16.
 */
public class LessonStudent {
    private Lesson lesson;
    private Student student;

    public LessonStudent(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
