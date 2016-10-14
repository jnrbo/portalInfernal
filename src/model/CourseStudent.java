package model;

/**
 * Created by junior and ramon.
 */
public class CourseStudent {

    Course course;
    Student student;

    public CourseStudent(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }
}
