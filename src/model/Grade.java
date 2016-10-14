package model;

import model.enums.Bimester;

/**
 * Created by junior and ramon.
 */
public class Grade extends Entity {

    private Student student;
    private Course course;
    private Bimester bimester;
    private int score;

    public Grade(int score, Student student, Course course, Bimester bimester) {
        super();
        this.bimester = bimester;
        this.student = student;
        this.course = course;
        this.score = score;
    }

    public Bimester getBimester() {
        return bimester;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public int getScore() {
        return score;
    }

}
