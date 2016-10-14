package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by junior Courseon 04/10/16.
 */
public class Course extends Entity {

    private String name;
    private Teacher teacher;
    private ArrayList<Lesson> lessons;

    public Course(String name, Teacher teacher) {
        super();
        this.name = name;
        this.teacher = teacher;
        lessons = new ArrayList<>();
    }

    public Course(int id, String name, Teacher teacher, Date createdOn, ArrayList<Lesson> lessons) {
        super(id, createdOn);
        this.name = name;
        this.teacher = teacher;
        this.lessons = lessons;
    }


    public String getName() {
        return name;
    }

    public boolean addLesson(Lesson lesson) {
        return lessons.add(lesson);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }


    @Override
    public String toString() {
        return name + " Prof: " + getTeacher().getName();
    }
}
