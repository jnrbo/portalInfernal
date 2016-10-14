package model;

import java.util.Date;

/**
 * Created by junior and ramon.
 */
public class Lesson extends Entity {

    private ClassRoom classRoom;
    private Date lessonDate;

    public Lesson(ClassRoom classRoom, Date lessonDate) {
        super();
        this.classRoom = classRoom;
        this.lessonDate = lessonDate;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public Date getLessonDate() {
        return lessonDate;
    }
}