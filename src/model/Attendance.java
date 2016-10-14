package model;

import java.util.Date;

/**
 * Created by junior and ramon.
 */
public class Attendance extends Entity {

    private Student student;
    private boolean attended;
    private Course course;

    public Attendance(boolean attended, Student student, Course course) {
        super();
        this.student = student;
        this.attended = attended;
        this.course = course;
    }

    public Attendance(int id, boolean attended, Student student, Course course, Date createdOn) {
        super(id, createdOn);
        this.student = student;
        this.attended = attended;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public User getStudent() {
        return student;
    }

    public boolean isAttended() {
        return attended;
    }
}
